/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.core.application.dao.entities;

import com.google.gson.Gson;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity for object Category
 *
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Data
@Entity
@Table(name = "product_image", schema = "public")
public class ProductImageEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private ProductImagePK pk;

  @JoinColumn(name = "sku", referencedColumnName = "sku", insertable = false, updatable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private ProductEntity product;

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
