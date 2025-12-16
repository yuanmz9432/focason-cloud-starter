/*
 * Copyright 2023 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.exception;



import lombok.Builder;

@Builder(toBuilder=true)public record FsErrorResource(String code,String message){}
