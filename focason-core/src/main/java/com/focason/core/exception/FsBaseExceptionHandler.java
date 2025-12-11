package com.focason.core.exception;



import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Base Exception Handler
 *
 * @since 0.0.1
 * @author yuanmz9432
 */
@RestControllerAdvice
public class FsBaseExceptionHandler
{
    /**
     * FsException Handler
     *
     * @param fsException {@link FsException}
     * @return ResponseEntity<FsErrorResource>
     */
    @ExceptionHandler(FsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<FsErrorResource> fsExceptionHandler(FsException fsException) {
        String code = fsException.getCode().getValue();
        return ResponseEntity
            .status(Integer.parseInt(code.substring(1, 4)))
            .body(FsErrorResource.builder()
                .code(code)
                .message(fsException.getMessage()).build());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<FsErrorResource> defaultExceptionHandler(Exception e) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(FsErrorResource.builder()
                .code(FsErrorCode.INTERNAL_SERVER_ERROR.getValue())
                .message(e.getMessage()).build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
        MethodArgumentNotValidException.class
    })
    public ResponseEntity<FsErrorResource> paramExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult exceptions = e.getBindingResult();
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                FieldError fieldError = (FieldError) errors.get(0);
                String message = fieldError.getDefaultMessage();
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(FsErrorResource.builder()
                        .code(FsErrorCode.VALIDATION_ERROR.getValue())
                        .message(message).build());
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
