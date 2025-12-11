// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * FsConditionalMappingOnProperty
 * <p>
 * Custom annotation designed to conditionally enable or disable a Spring MVC handler method
 * (e.g., a method annotated with @GetMapping, @PostMapping, etc.) based on the presence
 * and value of a specified Spring environment property.
 *
 * <p>
 * This annotation allows for feature toggling directly at the endpoint level,
 * similar to how {@code @ConditionalOnProperty} works for bean definitions, but applied
 * specifically to request mappings.
 * </p>
 *
 * <p>
 * Targeting only {@link ElementType#METHOD} ensures it is used on controller methods.
 * </p>
 *
 * <p>
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 * </p>
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 */
@Target({
    ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FsConditionalMappingOnProperty
{
    /**
     * The name of the environment property to check.
     * <p>
     * This is a required attribute.
     * </p>
     *
     * @return The property name.
     */
    String name();

    /**
     * The desired value of the property for the condition to match.
     * <p>
     * If the property exists and its value equals this {@code havingValue}, the condition matches.
     * </p>
     * <p>
     * Defaults to an empty string. If the property's actual value is non-empty, the default
     * configuration will require {@code havingValue} to be explicitly set to match.
     * </p>
     *
     * @return The required value of the property.
     */
    String havingValue() default "";

    /**
     * Specifies whether the condition should match if the property is not found in the environment.
     *
     * <ul>
     * <li>If {@code true} and the property is missing, the condition matches (and the mapping is enabled).</li>
     * <li>If {@code false} and the property is missing, the condition does not match (and the mapping is
     * disabled).</li>
     * </ul>
     *
     * @return {@code true} if the condition should match when the property is missing, {@code false} otherwise.
     */
    boolean matchIfMissing() default false;
}
