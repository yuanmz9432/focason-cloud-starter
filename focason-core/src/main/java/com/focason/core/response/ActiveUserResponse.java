package com.focason.core.response;

import java.util.List;

/**
 * ActiveUserResponse
 *
 * @param activeUids 有効UID配列
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public record ActiveUserResponse(List<String>activeUids){}
