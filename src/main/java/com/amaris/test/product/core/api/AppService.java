/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.core.api;

import com.amaris.test.product.core.api.events.QueryEvent;
import com.amaris.test.product.core.api.events.ResponseEvent;

/**
 * Handler for Application services
 *
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
public interface AppService {

  ResponseEvent<String> ping(
      QueryEvent requestEvent);
}
