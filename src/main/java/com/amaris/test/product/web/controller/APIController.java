/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.web.controller;

import com.amaris.test.product.core.api.events.ResponseCode;
import com.amaris.test.product.core.api.events.ResponseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Slf4j
public class APIController {

  @Value("${spring.config.activate.on-profile}")
  private String profile;
  @Value("${configuration.properties.time-zone}")
  private String timeZone;
  @Value("${configuration.properties.date-format}")
  private String dateFormat;

  public static <T> ResponseEntity<ResponseEvent<T>> buildHttpResponse(
      ResponseEvent<T> responseEvent) {
    log.debug("method: buildHttpResponse({})", responseEvent);
    final var httpStatus = parseResponseCode(responseEvent.getCode());
    return new ResponseEntity<>(responseEvent, httpStatus);
  }

  public static HttpStatus parseResponseCode(
      final ResponseCode code) {
    log.debug("method: parseResponseCode({})", code);
    switch (code) {
      case OK:
        return HttpStatus.OK;
      case NO_CONTENT:
        return HttpStatus.NO_CONTENT;
      case BAD_REQUEST:
        return HttpStatus.BAD_REQUEST;
      case CONFLICT:
        return HttpStatus.CONFLICT;
      case ERROR:
      default:
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
  }

  protected String getMessage(
      final String methodName) {

    return String.format("method: %s", methodName) +
        String.format(" -> TimeZone: %s,", timeZone) +
        String.format(" -> Date: %s,", new SimpleDateFormat(dateFormat).format(new Date())) +
        String.format(" -> ProfileEntity active: %s", profile);
  }
}
