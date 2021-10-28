/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.core.api.events;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Getter
@Setter
@NoArgsConstructor
public class ResponseEvent<T> {

  private ResponseCode code;
  private String message;
  private T data;

  public ResponseEvent<T> ok(
      String message) {
    return ok(message, null);
  }

  public ResponseEvent<T> ok(
      String message, T data) {
    setCode(ResponseCode.OK);
    setMessage(message);
    setData(data);
    return this;
  }

  public ResponseEvent<T> error(
      String message) {
    setCode(ResponseCode.ERROR);
    setMessage(message);
    return this;
  }

  public ResponseEvent<T> noContent(
      String message) {
    setCode(ResponseCode.NO_CONTENT);
    setMessage(message);
    return this;
  }

  public ResponseEvent<T> badRequest(
      String message) {
    setCode(ResponseCode.BAD_REQUEST);
    setMessage(message);
    return this;
  }

  public ResponseEvent<T> conflict(
      String message) {
    setCode(ResponseCode.CONFLICT);
    setMessage(message);
    return this;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
