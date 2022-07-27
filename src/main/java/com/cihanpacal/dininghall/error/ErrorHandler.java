package com.cihanpacal.dininghall.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RestControllerAdvice
public class ErrorHandler implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @Autowired
    private MessageSource messageSource;

    Logger logger= LoggerFactory.getLogger(ErrorHandler.class);

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorResponse handleError(WebRequest webRequest) {

        Map<String, Object> attributes = this.errorAttributes
                .getErrorAttributes(webRequest,false);


        //String message = (String) attributes.get("message");
        Locale trlocale= Locale.forLanguageTag("tr_TR");
        String message=messageSource.getMessage("exception.InternalServerError",null,trlocale);
        String path = (String) attributes.get("path");
        int status = (Integer) attributes.get("status");

        Map<String, String> errors=new HashMap<>();
        errors.put("message",message);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(message).path(path).status(status)
                .errors(errors).build();
        return errorResponse;
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request, Locale locale) {

        String path=request.getRequestURI();
        Map<String, String> errors=new HashMap<String, String>();
        String message=messageSource.getMessage(ex.getMessage(),ex.getArgs() ,locale);
        errors.put("message",message);

        logger.error(ex.getMessage());

        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.NOT_FOUND.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({EntityHasRelationshipException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse entityHasRelationshipException(EntityHasRelationshipException ex,HttpServletRequest request,Locale locale){

        String path=request.getRequestURI();
        Map<String,String> errors=new HashMap<>();
        String message=messageSource.getMessage(ex.getMessage(),ex.getArgs(),locale);
        errors.put("message",message);

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({EntityMustHasRelationshipException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse entityMustHasRelationshipException(EntityMustHasRelationshipException ex,HttpServletRequest request,Locale locale){

        String path=request.getRequestURI();
        Map<String,String> errors=new HashMap<>();
        String message=messageSource.getMessage(ex.getMessage(),ex.getArgs(),locale);
        errors.put("message",message);

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({ResourceAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse resourceAlreadyExistException(ResourceAlreadyExistException ex,HttpServletRequest request,Locale locale){

        String path=request.getRequestURI();
        Map<String,String> errors=new HashMap<>();
        String message=messageSource.getMessage(ex.getMessage(),ex.getArgs(),locale);
        errors.put("message",message);

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({InsufficientStockException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse insufficientStockException(InsufficientStockException ex,HttpServletRequest request,Locale locale){

        String path=request.getRequestURI();
        Map<String,String> errors=new HashMap<>();
        String message=messageSource.getMessage(ex.getMessage(),null,locale);
        errors.put("message",message);

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({UserAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse userAlreadyExistException(UserAlreadyExistException ex,HttpServletRequest request,Locale locale){

        String path=request.getRequestURI();
        Map<String,String> errors=new HashMap<>();
        String message=messageSource.getMessage(ex.getMessage(),null,locale);
        errors.put("message",message);

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({EmailException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse emailException(EmailException ex,HttpServletRequest request,Locale locale){

        String path=request.getRequestURI();
        Map<String,String> errors=new HashMap<>();
        String message=messageSource.getMessage(ex.getMessage(),null,locale);
        errors.put("message",message);

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({UsedVerificationTokenException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse usedVerificationTokenException(UsedVerificationTokenException ex,HttpServletRequest request,Locale locale){

        String path=request.getRequestURI();
        Map<String,String> errors=new HashMap<>();
        String message=messageSource.getMessage(ex.getMessage(),null,locale);
        errors.put("message",message);

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({ExpiryTokenException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse expiryTokenException(ExpiryTokenException ex,HttpServletRequest request,Locale locale){

        String path=request.getRequestURI();
        Map<String,String> errors=new HashMap<>();
        String message=messageSource.getMessage(ex.getMessage(),null,locale);
        errors.put("message",message);

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({UserAlreadyConfirmedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse userAlreadyConfirmedException(UserAlreadyConfirmedException ex,HttpServletRequest request,Locale locale){

        String path=request.getRequestURI();
        Map<String,String> errors=new HashMap<>();
        String message=messageSource.getMessage(ex.getMessage(),null,locale);
        errors.put("message",message);

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({UserNotConfirmedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse userNotConfirmedException(UserNotConfirmedException ex,HttpServletRequest request,Locale locale){

        String path=request.getRequestURI();
        Map<String,String> errors=new HashMap<>();
        String message=messageSource.getMessage(ex.getMessage(),null,locale);
        errors.put("message",message);

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse accessDeniedException(AccessDeniedException ex,HttpServletRequest request,Locale locale){
        String path=request.getRequestURI();
        Map<String,String> errors=new HashMap<>();
        String message=messageSource.getMessage(ex.getMessage(),null,locale);
        errors.put("message",message);

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.FORBIDDEN.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse badCredentialsException(BadCredentialsException ex,HttpServletRequest request,Locale locale){
        String path=request.getRequestURI();
        Map<String,String> errors=new HashMap<>();
        String message=messageSource.getMessage("exception.BadCredentialsException",null,locale);
        errors.put("message",message);

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.UNAUTHORIZED.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({InvalidOldPasswordException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse invalidOldPasswordException(InvalidOldPasswordException ex,HttpServletRequest request,Locale locale){
        String path=request.getRequestURI();
        Map<String,String> errors=new HashMap<>();
        String message=messageSource.getMessage(ex.getMessage(),null,locale);
        errors.put("message",message);

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({CannotChangeEnabledUserEmailException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse cannotChangeEnabledUserEmailException(CannotChangeEnabledUserEmailException ex, HttpServletRequest request, Locale locale){
        String path=request.getRequestURI();
        Map<String,String> errors=new HashMap<>();
        String message=messageSource.getMessage(ex.getMessage(),null,locale);
        errors.put("message",message);

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.FORBIDDEN.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({DisabledException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse disabledException(DisabledException ex,HttpServletRequest request,Locale locale){
        String path=request.getRequestURI();
        Map<String,String> errors=new HashMap<>();
        String message=messageSource.getMessage("exception.DisabledException",null,locale);
        errors.put("message",message);

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.UNAUTHORIZED.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({LockedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse lockedException(LockedException ex,HttpServletRequest request,Locale locale){
        String path=request.getRequestURI();
        Map<String,String> errors=new HashMap<>();
        String message=messageSource.getMessage("exception.LockedException",null,locale);

        errors.put("message",message);

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.UNAUTHORIZED.value())
                .errors(errors)
                .build();
        return errorResponse;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex,HttpServletRequest request) {

        String path=request.getRequestURI();
        BindingResult bindingResult=ex.getBindingResult();
        List<FieldError> fieldErrors=bindingResult.getFieldErrors();
        Locale trlocale= Locale.forLanguageTag("tr_TR");
        String message=messageSource
                .getMessage("exception.ValidationError",null,trlocale);

        //List<ObjectError> objectErrors=bindingResult.getGlobalErrors();

        Map<String,String> validationErrors=new HashMap<String, String>();
        for(FieldError fieldError:fieldErrors) {
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
//        for(ObjectError objectError:objectErrors) {
//            validationErrors.put(objectError.getObjectName(),objectError.getDefaultMessage());
//        }

        logger.error(ex.getMessage());
        ErrorResponse errorResponse=ErrorResponse.builder()
                .path(path)
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(validationErrors)
                .build();
        return errorResponse;
    }


}
