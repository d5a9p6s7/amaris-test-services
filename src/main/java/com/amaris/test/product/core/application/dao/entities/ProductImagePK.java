package com.amaris.test.product.core.application.dao.entities;

import com.google.gson.Gson;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ProductImagePK implements Serializable {

  @Column(name = "sku")
  private String sku;

  @Basic(optional = false)
  @Column(name = "image_url")
  private String imageUrl;

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
