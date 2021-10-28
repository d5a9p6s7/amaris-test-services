/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.core.api.events;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Getter
@Setter
@NoArgsConstructor
public class CommandEvent<E extends Serializable> {

  @NotNull
  private E request;
  private Map<String, String> params;

  public String getParam(
      String key) {
    return Objects.nonNull(params) && Objects.nonNull(params.get(key)) ? params.get(key) : null;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
