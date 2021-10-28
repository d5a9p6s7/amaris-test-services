/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.core.application.mappers;

import com.amaris.test.product.core.api.domains.Product;
import com.amaris.test.product.core.application.dao.entities.ProductImageEntity;
import com.amaris.test.product.core.application.dao.entities.ProductImagePK;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Mapper for {@link Product}.
 *
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Component
public class ProductImageMapper {

  public String toDomain(
      ProductImageEntity entity) {

    if (Objects.isNull(entity))
      return null;
    return entity.getPk().getImageUrl();
  }

  public List<String> toDomain(
      List<ProductImageEntity> entities) {

    if (Objects.isNull(entities))
      return Collections.emptyList();
    return entities.stream().map(this::toDomain).collect(Collectors.toList());
  }

  public ProductImageEntity toEntity(
      final String sku,
      final String url) {

    var entity = new ProductImageEntity();
    var pk = new ProductImagePK();
    pk.setSku(sku);
    pk.setImageUrl(url);
    entity.setPk(pk);

    return entity;
  }

  public List<ProductImageEntity> toEntity(
      final String sku,
      final List<String> urls) {

    if (Objects.isNull(urls))
      return Collections.emptyList();
    return urls.stream().map(url -> toEntity(sku, url)).collect(Collectors.toList());
  }
}
