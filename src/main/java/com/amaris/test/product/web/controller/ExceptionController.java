/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.web.controller;

import com.amaris.test.product.core.api.events.ResponseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Slf4j
@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

  // 405
  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      final HttpRequestMethodNotSupportedException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    logger.info("method: handleHttpRequestMethodNotSupported()");
    final StringBuilder builder = new StringBuilder();
    builder.append(ex.getMethod());
    builder.append(" method is not supported for this request. Supported methods are ");
    ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(builder.toString()), headers, HttpStatus.METHOD_NOT_ALLOWED, request);
  }

  // 415
  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
      final HttpMediaTypeNotSupportedException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    logger.info("method: handleHttpMediaTypeNotSupported()");
    final StringBuilder builder = new StringBuilder();
    builder.append(ex.getContentType());
    builder.append(" media type is not supported. Supported media types are ");
    ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(builder.toString()), headers, HttpStatus.UNSUPPORTED_MEDIA_TYPE, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
      HttpMediaTypeNotAcceptableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    logger.info("method: handleHttpMediaTypeNotAcceptable()");
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(ex.getMessage()), headers, HttpStatus.NOT_ACCEPTABLE, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(
      MissingPathVariableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    logger.info("method: handleMissingPathVariable()");
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(ex.getMessage()), headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  // 400
  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      final MissingServletRequestParameterException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    logger.info("method: handleMissingServletRequestParameter()");
    final String error = ex.getParameterName() + " parameter is missing";
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(error), headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleServletRequestBindingException(
      ServletRequestBindingException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    logger.info("method: handleServletRequestBindingException()");
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(ex.getMessage()), headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleConversionNotSupported(
      ConversionNotSupportedException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    logger.info("method: handleConversionNotSupported()");
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(ex.getMessage()), headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(
      final TypeMismatchException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    logger.info("method: handleTypeMismatch()");
    final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(error), headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    logger.info("method: handleHttpMessageNotReadable()");
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(ex.getMessage()), headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotWritable(
      HttpMessageNotWritableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    logger.info("method: handleHttpMessageNotWritable()");
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(ex.getMessage()), headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    logger.info("method: handleMethodArgumentNotValid()");
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(ex.getMessage()), headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(
      final MissingServletRequestPartException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    logger.info("method: handleMissingServletRequestPart()");
    final String error = ex.getRequestPartName() + " part is missing";
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(error), headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleBindException(
      final BindException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    logger.info("method: handleBindException()");
    final var errors = new ArrayList<String>();
    for (final FieldError error : ex.getBindingResult().getFieldErrors())
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    for (final ObjectError error : ex.getBindingResult().getGlobalErrors())
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(ex.getMessage()), headers, HttpStatus.BAD_REQUEST, request);
  }

  // 404
  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(
      final NoHandlerFoundException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    logger.info("method: handleNoHandlerFoundException()");
    final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(ex.getMessage()), headers, HttpStatus.NOT_FOUND, request);
  }

  @Override
  protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
      AsyncRequestTimeoutException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    logger.info("method: handleNoHandlerFoundException()");
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(ex.getMessage()), headers, HttpStatus.SERVICE_UNAVAILABLE, request);
  }

  // 400
  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
      final MethodArgumentTypeMismatchException ex,
      final WebRequest request) {
    logger.info("method: handleMethodArgumentTypeMismatch()");
    final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(error), null, HttpStatus.BAD_REQUEST, request);
  }

  // 404
  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolation(
      final ConstraintViolationException ex,
      final WebRequest request) {
    logger.info("method: handleConstraintViolation()");
    final List<String> errors = new ArrayList<String>();
    for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
    }
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(ex.getMessage()), null, HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(
      Exception ex,
      WebRequest request) {
    logger.info("method: handleAllExceptions()");
    logger.error("error", ex);
    return this.handleExceptionInternal(ex, new ResponseEvent<String>().error(ex.getMessage()), null, HttpStatus.INTERNAL_SERVER_ERROR, request);
  }
}
