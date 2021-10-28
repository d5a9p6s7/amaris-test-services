/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.core.application.dao.entities;

import com.google.gson.Gson;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Entity for object Category
 *
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Data
@Entity
@Table(name = "product", schema = "public")
public class ProductEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Basic(optional = false)
  @Column(name = "sku")
  private String sku;

  @Basic(optional = false)
  @Column(name = "name")
  private String name;

  @Basic(optional = false)
  @Column(name = "brand")
  private String brand;

  @Column(name = "size")
  private String size;

  @Basic(optional = false)
  @Column(name = "price")
  private Double price;

  @Basic(optional = false)
  @Column(name = "image")
  private String image;

  @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductImageEntity> images = Collections.emptyList();

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
