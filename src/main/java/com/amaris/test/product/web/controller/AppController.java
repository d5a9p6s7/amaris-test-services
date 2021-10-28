/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.web.controller;

import com.amaris.test.product.core.api.AppService;
import com.amaris.test.product.core.api.events.QueryEvent;
import com.amaris.test.product.core.api.events.ResponseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Slf4j
@RestController
public class AppController extends APIController {

  private final AppService service;
  private final MessageSource messageSource;

  public AppController(
      AppService service,
      MessageSource messageSource) {
    this.service = service;
    this.messageSource = messageSource;
  }

  @GetMapping("/")
  public String root() {
    //var msg = messageSource.getMessage("", null, LocaleContextHolder.getLocale());
    var msg = getMessage("root()");
    log.info(msg);
    return msg;
  }

  @GetMapping("/ping")
  public ResponseEntity<ResponseEvent<String>> ping() {
    final ResponseEvent<String> responseEvent = service.ping(new QueryEvent());
    log.info("method: ping() -> Response: {}", responseEvent);
    return buildHttpResponse(responseEvent);
  }
}
