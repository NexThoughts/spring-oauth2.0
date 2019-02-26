package com.buzzbuilder.buzzbuilderrest.handler

import com.buzzbuilder.buzzbuilderrest.exception.ArgumentsFailureException
import com.buzzbuilder.buzzbuilderrest.exception.AuthFailureException
import com.buzzbuilder.buzzbuilderrest.exception.NotAuthException
import com.buzzbuilder.buzzbuilderrest.exception.NotAuthorityException
import com.buzzbuilder.buzzbuilderrest.response.BaseResponse
import com.buzzbuilder.buzzbuilderrest.response.SimpleResponse
import org.springframework.beans.ConversionNotSupportedException
import org.springframework.beans.TypeMismatchException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ServerErrorException
import org.springframework.web.servlet.NoHandlerFoundException

import javax.servlet.http.HttpServletResponse

import static com.buzzbuilder.buzzbuilderrest.response.HttpResponse.baseResponse
import static com.buzzbuilder.buzzbuilderrest.response.HttpResponse.simpleResponse

@RestControllerAdvice
public final class ExceptionAdviceHandler {

    final static String SERVER_ERROR_TXT = "Server internal error";
    final static String ARGUMENTS_ERROR_TXT = "Parameter error";
    final static String BAD_REQUEST_TXT = "Bad request";

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse unKnowExceptionHandler() {
        return this.serverErrorHandler();
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse runtimeExceptionHandler(RuntimeException ex) {
        return this.serverErrorHandler();
    }

    /**
     *
     Null pointer exception
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse nullPointerExceptionHandler(Exception e) {

        e.printStackTrace();

        return this.serverErrorHandler();
    }

    /**
     *
     Type conversion exception
     */
    @ExceptionHandler(ClassCastException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse classCastExceptionHandler() {
        return this.serverErrorHandler();
    }

    /**
     * IOException
     */
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse iOExceptionHandler() {
        return this.serverErrorHandler();
    }

    /**
     *  NoSuchMethodException
     */
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse noSuchMethodExceptionHandler() {
        return this.serverErrorHandler();
    }

    /**
     * IndexOutOfBoundsException
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse indexOutOfBoundsExceptionHandler() {
        return this.serverErrorHandler();
    }

    /**
     * 400 Bad Request
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse requestNotReadable() {
        return baseResponse(400, BAD_REQUEST_TXT);
    }

    /**
     * 400错误 类型不匹配
     */
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse requestTypeMismatch() {
        return this.argumentsError();
    }

    /**
     * 400错误 缺少参数
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse requestMissingServletRequest() {
        return this.argumentsError();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse methodArgumentNotValidExceptionHandler() {
        return baseResponse(400, "Parameter error");
    }

    @ExceptionHandler(value = NotAuthorityException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse notAuthority(NotAuthorityException ex) {
        return this.authErrorHandler(2, ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse usernameNotFound(UsernameNotFoundException ex) {
        return baseResponse(400, ex.getMessage());
    }


    @ExceptionHandler(value = NotAuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse notAuth(NotAuthException ex) {
        return this.authErrorHandler(1, ex.getMessage());
    }

    @ExceptionHandler(value = AuthFailureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse authFieald(AuthFailureException ex) {
        return this.authErrorHandler(1, ex.getMessage());
    }

    /**
     * 405错误
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public BaseResponse request405(HttpServletResponse resp) {
        return baseResponse(405, "Request method is incorrect");
    }

    /**
     * 406错误
     */

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public BaseResponse request406(HttpServletResponse resp) {
        return baseResponse(405, "Do not accept the request");

    }

    /**
     * 500错误
     */
    @ExceptionHandler([ConversionNotSupportedException.class, HttpMessageNotWritableException.class] )
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse server500(HttpServletResponse resp, Exception e) {
        return this.serverErrorHandler();
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public BaseResponse httpMediaTypeNotSupportedExceptionHandler(HttpServletResponse resp) {
        return baseResponse(415, "The server was unable to process the media format attached to the request");
    }


    @ExceptionHandler(value = ArgumentsFailureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse argsErrorExceptionHandler(ArgumentsFailureException ex, HttpServletResponse response) {
        return baseResponse(400, ex.getMessage());
    }

    /**
     * 404
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse notFoundException(HttpServletResponse response) {
        return baseResponse(404, "Service not found");
    }

    @ExceptionHandler(value = ServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse serverErrorExceptionHandler(HttpServletResponse response) {
        return this.serverErrorHandler();
    }


    private BaseResponse serverErrorHandler() {
        return baseResponse(500, SERVER_ERROR_TXT);
    }

    private BaseResponse argumentsError() {
        return baseResponse(400, ARGUMENTS_ERROR_TXT);
    }

    /**
     * @param code 1 认证错误（未认证）、2 未授权/没有权限
     * @param msg
     * @return
     */
    private SimpleResponse authErrorHandler(int code, String msg) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("code", code);
        return simpleResponse(401, msg, mp);
    }

}
