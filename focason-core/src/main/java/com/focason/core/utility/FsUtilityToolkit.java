// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.utility;



import com.focason.core.annotation.TargetElementType;
import com.focason.core.domain.TokenType;
import com.focason.core.exception.FsIllegalAccessTokenException;
import com.focason.core.resource.UserResource;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Utility class for object conversion, password encryption, and JWT processing.
 * <p>
 * This class provides methods to:
 * - Convert Java objects and their properties, including handling lists.
 * - Encrypt and verify passwords using BCrypt.
 * - Extract payload and email information from JWT tokens.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@NoArgsConstructor
public class FsUtilityToolkit
{
    static final Logger logger = LoggerFactory.getLogger(FsUtilityToolkit.class);

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final String KEY = "QqyEMwgY0WLdEeEf"; // 16 byte
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = 60 * 60 * 24; // 24 Hours
    public static final long ACCESS_TOKEN_EXPIRATION_TIME = 60 * 60; // 60 Minutes
    public static final long VERIFICATION_TOKEN_EXPIRATION_TIME = 60 * 30; // 30 Minutes
    public static final String CLAIMS_SUB = "sub";
    public static final String CLAIMS_EXP = "exp";
    public static final String CLAIMS_EMAIL = "email";
    public static final String CLAIMS_ROLE = "role";

    /**
     * BCrypt password encoder instance.
     * <p>
     * This instance is thread-safe and ensures consistent password encryption and verification.
     * </p>
     */
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // ==================================================
    // ============ Bean Conversion Methods =============
    // ==================================================
    /**
     * Converts the given source object into a new instance of the target type.
     * <p>
     * This method first performs a shallow copy using Spring's {@link BeanUtils#copyProperties(Object, Object)}.
     * Crucially, it then iterates over all List fields in the source object and recursively
     * converts the list elements, enabling deep conversion for nested DTOs.
     * </p>
     * <p>
     * **Note:** Nested List item type is determined by the {@link TargetElementType} annotation
     * on the corresponding field in the target type.
     * </p>
     *
     * @param source The source object to be converted.
     * @param targetType The class type of the target object.
     * @return A new instance of the target type with copied and converted properties, or {@code null} if source is
     *         null.
     * @param <S> Source object type.
     * @param <T> Target object type.
     */
    public static <S, T> T convert(S source, Class<T> targetType) {
        if (source == null) {
            return null;
        }

        try {
            // Check if target type is a Record
            if (targetType.isRecord()) {
                return convertToRecord(source, targetType);
            }

            // 1. Instantiate the target object using default constructor
            T target = targetType.getDeclaredConstructor().newInstance();

            // 2. Perform a shallow copy of properties (using Spring BeanUtils)
            BeanUtils.copyProperties(source, target);

            // 3. Process list fields for deep conversion
            for (Field sourceField : source.getClass().getDeclaredFields()) {
                sourceField.setAccessible(true);
                Object fieldValue = sourceField.get(source);

                if (fieldValue instanceof List<?> sourceList) {
                    if (!sourceList.isEmpty()) {
                        // Determine the target list item type via custom annotation
                        Class<?> targetListItemType = determineTargetListItemType(targetType, sourceField.getName());

                        // Create a new list for the converted items.
                        List<Object> targetList = new ArrayList<>();
                        for (Object item : sourceList) {
                            // Recursively convert list items.
                            Object targetItem = convert(item, targetListItemType);
                            targetList.add(targetItem);
                        }

                        // Reflectively update the target field.
                        Field targetField = findField(target.getClass(), sourceField.getName());
                        if (targetField != null) {
                            targetField.setAccessible(true);
                            targetField.set(target, targetList);
                        }
                    }
                }
            }
            return target;
        } catch (Exception e) {
            // Wrap checked exceptions in a runtime exception for consistent handling
            throw new RuntimeException(
                "Failed to convert objects due to reflection or instantiation error: " + e.getMessage(), e);
        }
    }

    /**
     * Converts a source object to a Record type by matching field names to record components.
     *
     * @param source The source object
     * @param targetType The target Record class
     * @param <S> Source type
     * @param <T> Target Record type
     * @return A new instance of the target Record with mapped values
     * @throws Exception if conversion fails
     */
    private static <S, T> T convertToRecord(S source, Class<T> targetType) throws Exception {
        // Get record components (equivalent to fields in normal classes)
        var recordComponents = targetType.getRecordComponents();
        Object[] args = new Object[recordComponents.length];
        Class<?>[] paramTypes = new Class<?>[recordComponents.length];

        // For each record component, get the corresponding value from source
        for (int i = 0; i < recordComponents.length; i++) {
            var component = recordComponents[i];
            String componentName = component.getName();
            Class<?> componentType = component.getType();
            paramTypes[i] = componentType;

            // Try to get value from source using getter method
            Object value = null;
            try {
                // Try standard getter (e.g., getName)
                String getterName = "get" + Character.toUpperCase(componentName.charAt(0))
                    + componentName.substring(1);
                var getter = source.getClass().getMethod(getterName);
                value = getter.invoke(source);
            } catch (NoSuchMethodException e) {
                // Try boolean getter (e.g., isActive)
                try {
                    String booleanGetterName = "is" + Character.toUpperCase(componentName.charAt(0))
                        + componentName.substring(1);
                    var getter = source.getClass().getMethod(booleanGetterName);
                    value = getter.invoke(source);
                } catch (NoSuchMethodException ex) {
                    // Try direct field access
                    try {
                        var field = findField(source.getClass(), componentName);
                        if (field != null) {
                            field.setAccessible(true);
                            value = field.get(source);
                        }
                    } catch (Exception fieldEx) {
                        logger.warn("Could not get value for component {}: {}", componentName, fieldEx.getMessage());
                    }
                }
            }

            args[i] = value;
        }

        // Find and invoke the canonical constructor
        var constructor = targetType.getDeclaredConstructor(paramTypes);
        return constructor.newInstance(args);
    }

    /**
     * Recursively finds a field with the given name in the class hierarchy (including superclasses).
     *
     * @param clazz The class to start the search from.
     * @param fieldName The name of the field to find.
     * @return The found {@link Field} object, or {@code null} if not found.
     */
    private static Field findField(Class<?> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null && superClass != Object.class) {
                // Recursively search in the superclass
                return findField(superClass, fieldName);
            }
            return null;
        }
    }

    /**
     * Determines the generic type of the list elements in the target type's field.
     * <p>
     * It primarily relies on the custom {@link TargetElementType} annotation for type information.
     * </p>
     *
     * @param targetType The class of the target object.
     * @param fieldName The name of the list field.
     * @return The class type of the list elements, or {@code Object.class} if the annotation is missing.
     */
    private static Class<?> determineTargetListItemType(Class<?> targetType, String fieldName) {
        try {
            Field targetField = findField(targetType, fieldName); // Use findField to check superclasses
            if (targetField != null) {
                TargetElementType annotation = targetField.getAnnotation(TargetElementType.class);
                if (annotation != null) {
                    return annotation.value(); // Return the type specified in the annotation
                }
            }
        } catch (Exception e) {
            // Silently fail or log a warning if field reflection fails, as we fall back to Object.class anyway.
            logger.warn("Could not determine target list item type for field {}: {}", fieldName, e.getMessage());
        }
        return Object.class; // Default to Object.class if type information cannot be reliably determined.
    }

    /**
     * Converts a list of source beans into a list of target beans using the
     * {@link #convert(Object, Class)} method for each element.
     *
     * @param sources The list of source beans.
     * @param type The class type of the target object (list element).
     * @return A new list of the target type {@code T}. Returns an empty list if sources is null or empty.
     * @param <S> The source type (list element type).
     * @param <T> The target type (list element type).
     */
    public static <S, T> List<T> convert(List<S> sources, Class<T> type) {
        List<T> out = new ArrayList<>();
        if (sources == null || sources.isEmpty())
            return out;

        try {
            for (S source : sources) {
                out.add(convert(source, type));
            }
        } catch (Exception e) {
            // Ensure the exception is wrapped appropriately
            throw new RuntimeException("Failed to convert list element: " + e.getMessage(), e);
        }
        return out;
    }

    /**
     * Copies the property values of the given source bean into the target bean.
     * <p>
     * This method only copies properties where the source value is **not null**.
     * It effectively ignores null properties in the source, allowing for partial updates.
     * </p>
     *
     * @param source The source bean whose properties are to be copied.
     * @param target The target bean to copy properties into.
     * @param <S> The source object type.
     * @param <T> The target object type.
     */
    public static <S, T> void convert(S source, T target) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] propertyDescriptors = src.getPropertyDescriptors();
        Set<String> nullPropertyNames = new HashSet<>();

        // Identify properties in the source object that are null
        for (PropertyDescriptor pd : propertyDescriptors) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                nullPropertyNames.add(pd.getName());
        }

        // Copy properties, ignoring those identified as null in the source
        // The last argument is an array of properties to IGNORE during the copy operation.
        BeanUtils.copyProperties(source, target, nullPropertyNames.toArray(new String[0]));
    }

    // ==================================================
    // ======== ID and Code Generation Utilities ========
    // ==================================================
    /**
     * Generates a short, 10-character identifier derived from a UUID.
     * <p>
     * Note: This identifier is **not guaranteed** to be globally unique due to truncation
     * and should be used only where collisions are acceptable or mitigated externally (e.g., verification codes).
     * </p>
     *
     * @return A 10-character identifier in base-36.
     */
    public static String generateShortUUID() {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();

        // Convert the UUID's most significant bits to a long value
        long longValue = uuid.getMostSignificantBits();

        // Convert the long value to a base-36 string and ensure it is positive
        String longAsString = Long.toString(Math.abs(longValue), 36);

        // Return the first 10 characters of the string
        return longAsString.substring(0, Math.min(10, longAsString.length()));
    }

    /**
     * Generates a normal, 36-character unique identifier.
     *
     * @return A unique 36-character identifier (UUID format).
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generates a 6-digit numeric verification code.
     *
     * @return A 6-character numeric string code (e.g., "012345").
     */
    public static String generate6DigitVerificationCode() {
        // Generate a random integer between 100000 (inclusive) and 1000000 (exclusive)
        return String.format("%06d", ThreadLocalRandom.current().nextInt(100000, 1000000));
    }

    // ==================================================
    // =========== Token Generation Utilities ===========
    // ==================================================
    /**
     * Generates a JSON Web Token (JWT) for the given user.
     * <p>
     * This method creates a JWT containing user-specific claims such as UUID, email,
     * role, and token expiration details. The token is signed using a predefined secret key.
     *
     * @param user The {@link UserResource} object representing the authenticated user.
     * @param expiresAt The {@link LocalDateTime} object representing the token's expiration time.
     * @return A signed JWT token as a {@link String}.
     */
    public static String generateAccessToken(UserResource user, LocalDateTime expiresAt) {
        Map<String, Object> payload = new HashMap<>();
        payload.put(CLAIMS_SUB, user.getUid());
        payload.put(Claims.EXPIRATION, Timestamp.valueOf(expiresAt));
        payload.put(Claims.ISSUED_AT, new Date(System.currentTimeMillis()));
        payload.put(CLAIMS_EMAIL, user.getEmail());

        return Jwts.builder()
            .setClaims(payload)
            .signWith(SECRET_KEY)
            .compact();
    }

    /**
     * Generates a Refresh Token for the given user.
     * <p>
     * This method creates a JWT containing user-specific claims such as UUID, email,
     * role, and token expiration details. The token is signed using a predefined secret key.
     *
     * @param user The {@link UserResource} object representing the authenticated user.
     * @param expiryDate The {@link Date} object representing the token's expiration time.
     * @return A signed JWT token as a {@link String}.
     */
    public static String generateRefreshToken(UserResource user, LocalDateTime expiryDate) {
        Map<String, Object> payload = new HashMap<>();
        payload.put(CLAIMS_SUB, user.getUid());
        payload.put(Claims.EXPIRATION, Timestamp.valueOf(expiryDate));
        payload.put(Claims.ISSUED_AT, new Date(System.currentTimeMillis()));

        return Jwts.builder()
            .setClaims(payload)
            .signWith(SECRET_KEY)
            .compact();
    }

    /**
     * Generates an expiration timestamp for an access token.
     * <p>
     * This method creates a new {@link Date} object representing the expiration time of the access token.
     * The expiration time is calculated by adding the predefined expiration duration
     * (ACCESS_TOKEN_EXPIRATION_TIME) to the current system time.
     *
     * @return A {@link Date} object representing the token's expiration time.
     */
    public static LocalDateTime generateExpirationTime(TokenType tokenType) {
        return switch (tokenType) {
            case ACCESS_TOKEN -> LocalDateTime.now().plusSeconds(ACCESS_TOKEN_EXPIRATION_TIME);
            case VERIFICATION_TOKEN -> LocalDateTime.now().plusSeconds(VERIFICATION_TOKEN_EXPIRATION_TIME);
            case REFRESH_TOKEN -> LocalDateTime.now().plusSeconds(REFRESH_TOKEN_EXPIRATION_TIME);
        };
    }


    /**
     * Extracts and decodes the payload (middle portion) of a JWT token.
     * <p>
     * This method removes the optional "Bearer " prefix, splits the token into three parts
     * (Header, Payload, Signature), and Base64-URL-decodes the Payload section.
     * The decoded content is then parsed into a JSON object structure.
     * </p>
     *
     * @param token A valid JSON Web Token string, optionally prefixed with "Bearer ".
     * @return The decoded payload as a JsonObject.
     * @throws RuntimeException If the token format is invalid (e.g., cannot be split or decoded),
     *         or if the JSON parsing fails.
     */
    public static JsonObject decodeJwtPayload(String token) {
        // 1. Clean up token (remove "Bearer " prefix)
        final String cleanToken = token.replace("Bearer ", "");

        // 2. Split and get payload (middle part)
        final String[] parts = cleanToken.split("\\.");
        if (parts.length != 3) {
            throw new RuntimeException("Invalid JWT format. Token must have three parts separated by dots.");
        }
        final String encodedPayload = parts[1];

        // 3. Base64-URL decode the payload
        final byte[] payloadBytes = Base64.getUrlDecoder().decode(encodedPayload);
        final String payloadString = new String(payloadBytes, StandardCharsets.UTF_8);

        // 4. Parse JSON (Note: Assuming JsonParser/JsonObject from Gson/similar lib)
        return (JsonObject) JsonParser.parseString(payloadString);
    }

    /**
     * Extracts the User ID (UID) from a valid JWT token's payload.
     * <p>
     * This method first decodes the token payload using {@link #decodeJwtPayload(String)},
     * and then retrieves the value associated with the standard JWT claim "sub" (Subject).
     * </p>
     *
     * @param token A valid JSON Web Token string.
     * @return The User ID (UID) as a String.
     * @throws RuntimeException (e.g., FsIllegalAccessTokenException) If the token is invalid or the 'sub' claim is
     *         missing.
     */
    public static String extractUserIdFromToken(String token) {
        // NOTE: The following logic depends on the successful return of a JSON Object from getDecodedPayload.
        // The return type has been generalized to Object here to prevent compilation issues with missing JsonObject.
        final JsonObject payload = FsUtilityToolkit.decodeJwtPayload(token);
        return Optional.ofNullable(payload.get(FsUtilityToolkit.CLAIMS_SUB))
            .map(JsonElement::getAsString)
            .orElseThrow(() -> new FsIllegalAccessTokenException("UID is missing in the payload."));
    }

    // ==================================================
    // =============== Password Utilities ===============
    // ==================================================
    /**
     * Hashes a raw password using BCrypt.
     * <p>
     * Each call to this method produces a different hash due to the automatic inclusion of a random salt.
     * The resulting hash is securely stored in a database.
     * </p>
     *
     * @param rawPassword The plain text password to be hashed.
     * @return A securely hashed password string, or {@code null} if the raw password is null.
     */
    public static String encryptPassword(String rawPassword) {
        if (rawPassword == null)
            return null;
        return encoder.encode(rawPassword);
    }

    /**
     * Verifies if a raw password matches a previously hashed password.
     * <p>
     * This method checks whether the provided raw password, when hashed, matches the stored hash.
     * Even though the stored hash changes due to salting, BCrypt ensures the match remains valid.
     * </p>
     * <p>
     * **Note:** This method returns {@code true} if the passwords DO NOT match (mismatch is found).
     * If {@code encodedPassword} is null or empty, it always returns {@code true} (mismatch).
     * </p>
     *
     * @param rawPassword The plain text password entered by the user.
     * @param encodedPassword The hashed password retrieved from the database.
     * @return {@code true} if the raw password does **not** match the stored hash; {@code false} otherwise (they
     *         match).
     */
    public static boolean isPasswordMismatched(String rawPassword, String encodedPassword) {
        // If the stored hash is null or empty, treat it as a mismatch for security.
        if (encodedPassword == null || encodedPassword.isEmpty()) {
            return true;
        }

        // encoder.matches() returns TRUE if they match. We negate it to check for mismatch.
        return !encoder.matches(rawPassword, encodedPassword);
    }

    // ==================================================
    // ======= AES Encryption/Decryption Utilities ======
    // ==================================================
    /**
     * Encrypts a plain text string using AES-128/CBC/PKCS5Padding.
     * <p>
     * A random Initialization Vector (IV) is generated automatically and securely prefixed
     * to the ciphertext before the entire result is Base64-URL encoded for transmission or storage.
     * </p>
     *
     * @param plainText The data to be encrypted.
     * @return The combined IV and ciphertext, Base64-URL encoded, or {@code null} if encryption fails.
     */
    public static String encrypt(String plainText) {
        if (plainText == null)
            return null;

        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // 1. Initialize the cipher, letting Java generate a random IV
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES"));

            // 2. Crucial step: Retrieve the IV automatically generated by Java
            byte[] iv = cipher.getIV();

            // 3. Execute encryption
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            // 4. Concatenate IV and ciphertext, then Base64 encode
            // Recommendation: Concatenate IV (prefix) and ciphertext (suffix)
            byte[] combined = new byte[iv.length + encryptedBytes.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encryptedBytes, 0, combined, iv.length, encryptedBytes.length);

            // Return the Base64 string containing both IV and ciphertext
            return Base64.getUrlEncoder().withoutPadding().encodeToString(combined);
        } catch (Exception e) {
            // Log the failure to encrypt data, including the exception stack trace.
            logger.error("Failed to encrypt data: {}", e.getMessage(), e);
            // In a production environment, it's recommended to throw a custom RuntimeException instead of returning
            // null.
            return null;
        }
    }

    /**
     * Decrypts a Base64-URL encoded string using AES-128/CBC/PKCS5Padding.
     * <p>
     * The method first decodes the string, separates the prefixed IV from the ciphertext,
     * and uses both the IV and the secret key to perform the decryption.
     * </p>
     *
     * @param encrypted The Base64-URL encoded string containing the IV and ciphertext.
     * @return The original plain text string, or {@code null} if decryption fails.
     */
    public static String decrypt(String encrypted) {
        if (encrypted == null)
            return null;

        Cipher cipher;
        try {
            // 1. Decode the entire string
            byte[] combined = Base64.getUrlDecoder().decode(encrypted);

            // 2. Define IV length (AES IV for CBC is always 16 bytes)
            int ivLength = 16;
            if (combined.length < ivLength) {
                // Not enough bytes for IV and ciphertext combined
                throw new IllegalArgumentException("Encrypted string is too short to contain IV.");
            }

            // 3. Separate IV
            byte[] iv = new byte[ivLength];
            System.arraycopy(combined, 0, iv, 0, ivLength);

            // 4. Separate ciphertext
            byte[] cipherText = new byte[combined.length - ivLength];
            System.arraycopy(combined, ivLength, cipherText, 0, cipherText.length);

            // 5. Create IvParameterSpec using the separated IV
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // 6. Initialize the cipher for decryption: Key and IV must be provided
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES"), ivSpec);

            // 7. Execute decryption
            return new String(cipher.doFinal(cipherText), StandardCharsets.UTF_8);
        } catch (Exception e) {
            // Log the failure to decrypt data, including the exception stack trace.
            logger.error("Failed to decrypt data: {}", e.getMessage(), e);
            // In a production environment, it's recommended to throw a custom RuntimeException instead of returning
            // null.
            return null;
        }
    }

    /**
     * Utility method to convert an Instant to a Unix timestamp in seconds
     * for the time zone.
     * <p>
     * This method is specifically designed for token expiration timestamps
     * in applications targeting the Japanese market or requiring JST time zone.
     *
     * @param expiresAt the instant representing the expiration time, cannot be null
     * @return Unix timestamp in seconds representing the expiration time in Asia/Tokyo time zone
     * @throws IllegalArgumentException if expiresAt is null
     *
     * @example
     *          Instant expiration = Instant.now().plusSeconds(3600);
     *          long expiryTimestamp = toTokyoEpochSeconds(expiration);
     *          // Returns: 1731593125 (Unix timestamp in seconds)
     */
    public static long toEpochSeconds(LocalDateTime expiresAt, String zoneId) {
        if (expiresAt == null) {
            throw new IllegalArgumentException("ExpiresAt Instant cannot be null");
        }
        return expiresAt.atZone(ZoneId.of(zoneId)).toEpochSecond();
    }

    /**
     * Utility method to convert an Instant to a Unix timestamp in seconds
     * for the Asia/Tokyo time zone.
     * <p>
     * This method is specifically designed for token expiration timestamps
     * in applications targeting the Japanese market or requiring JST time zone.
     *
     * @param expiresAt the instant representing the expiration time, cannot be null
     * @return Unix timestamp in seconds representing the expiration time in Asia/Tokyo time zone
     * @throws IllegalArgumentException if expiresAt is null
     *
     * @example
     *          Instant expiration = Instant.now().plusSeconds(3600);
     *          long expiryTimestamp = toTokyoEpochSeconds(expiration);
     *          // Returns: 1731593125 (Unix timestamp in seconds)
     */
    public static long toTokyoEpochSeconds(LocalDateTime expiresAt) {
        return toEpochSeconds(expiresAt, "Asia/Tokyo");
    }

    /**
     * Utility method to convert an Instant to a Unix timestamp in seconds
     * for the Asia/Tokyo time zone.
     * <p>
     * This method is specifically designed for token expiration timestamps
     * in applications targeting the Japanese market or requiring JST time zone.
     *
     * @param epochSeconds the instant representing the expiration time, cannot be null
     * @return Unix timestamp in seconds representing the expiration time in Asia/Tokyo time zone
     * @throws IllegalArgumentException if expiresAt is null
     *
     * @example
     *          Instant expiration = Instant.now().plusSeconds(3600);
     *          long expiryTimestamp = toTokyoEpochSeconds(expiration);
     *          // Returns: 1731593125 (Unix timestamp in seconds)
     */
    public static LocalDateTime toLocalDateTime(long epochSeconds) {
        return LocalDateTime.ofEpochSecond(epochSeconds, 0, ZoneOffset.of("Asia/Tokyo"));
    }

    @Deprecated
    public static String convertObjectToJson(Object target) {
        Gson gson = new Gson();
        return gson.toJson(target);
    }
}
