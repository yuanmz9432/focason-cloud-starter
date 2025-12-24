package com.focason.core.request;

/**
 * TokenValidationRequest
 * <p>
 * Request DTO for validating access token existence in the database.
 * </p>
 *
 * @param accessToken The access token to validate
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record TokenValidationRequest(String accessToken){}
