package com.focason.gateway.handler;



import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * FeignException捕获器
 *
 * @since 0.0.1
 * @author yuanmz9432
 */
@Configuration
public class FeignExceptionHandler implements ErrorDecoder
{
    final Logger logger = LoggerFactory.getLogger(FeignExceptionHandler.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        logger.error("Gateway FeignExceptionHandler is running...");
        // TODO 分析下游服务feign抛出的异常
        return null;
    }
}
