/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.core.api.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Builder
@Data
public class Product implements Serializable {

  private static final long serialVersionUID = 1L;
  private String sku;
  private String name;
  private String brand;
  private String size;
  private Double price;
  private String image;
  private List<String> images;

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }

  @Data
  @Builder
  public static class CreateRequest implements Serializable {

    @NotNull
    private String sku;
    @NotNull
    private String name;
    @NotNull
    private String brand;
    private String size;
    @NotNull
    private Double price;
    @NotNull
    private String image;
    private List<String> images;

    @Override
    public String toString() {
      return new Gson().toJson(this);
    }
  }

  @Data
  @Builder
  public static class UpdateRequest implements Serializable {

    @JsonIgnore
    private String sku;
    @NotNull
    private String name;
    @NotNull
    private String brand;
    private String size;
    @NotNull
    private Double price;
    @NotNull
    private String image;
    private List<String> images;

    @Override
    public String toString() {
      return new Gson().toJson(this);
    }
  }

  @Data
  @Builder
  public static class DeleteRequest implements Serializable {

    private String sku;

    @Override
    public String toString() {
      return new Gson().toJson(this);
    }
  }
}
