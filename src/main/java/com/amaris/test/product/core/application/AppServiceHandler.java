/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.core.application;

import com.amaris.test.product.core.api.AppService;
import com.amaris.test.product.core.api.events.QueryEvent;
import com.amaris.test.product.core.api.events.ResponseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Handler for {@link AppService}.
 *
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Slf4j
@Service
public class AppServiceHandler implements AppService {

  @Override
  public ResponseEvent<String> ping(
      QueryEvent requestEvent) {
    try {
      log.debug("method: ping({})", requestEvent);
      return new ResponseEvent<String>().ok("SELECT CURRENT_DATE;");
    } catch (Exception ex) {
      log.error("method: ping({}) -> Exception: {}", requestEvent, ex.getMessage(), ex);
      return new ResponseEvent<String>().error(ex.getMessage());
    }
  }
}
