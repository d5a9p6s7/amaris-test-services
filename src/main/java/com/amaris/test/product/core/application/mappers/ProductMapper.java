/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.core.application.mappers;

import com.amaris.test.product.core.api.domains.Product;
import com.amaris.test.product.core.application.dao.entities.ProductEntity;
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
public class ProductMapper {

  private final ProductImageMapper productImageMapper;

  public ProductMapper(
      ProductImageMapper productImageMapper) {

    this.productImageMapper = productImageMapper;
  }

  public Product toDomain(
      ProductEntity entity) {

    if (Objects.isNull(entity))
      return null;
    return Product.builder()
        .sku(entity.getSku())
        .name(entity.getName())
        .brand((entity.getBrand()))
        .size((entity.getSize()))
        .price(entity.getPrice())
        .image(entity.getImage())
        .images(productImageMapper.toDomain(entity.getImages()))
        .build();
  }

  public List<Product> toDomain(
      List<ProductEntity> entities) {

    if (Objects.isNull(entities))
      return Collections.emptyList();
    return entities.stream().map(this::toDomain).collect(Collectors.toList());
  }

  public ProductEntity toEntity(
      final Product.CreateRequest domain) {

    if (Objects.isNull(domain))
      return null;
    var entity = new ProductEntity();
    entity.setSku(domain.getSku());
    entity.setName(domain.getName());
    entity.setBrand(domain.getBrand());
    entity.setSize(domain.getSize());
    entity.setPrice(domain.getPrice());
    entity.setImage(domain.getImage());
    return entity;
  }

  public ProductEntity toEntity(
      final Product.UpdateRequest domain) {

    if (Objects.isNull(domain))
      return null;
    var entity = new ProductEntity();
    entity.setSku(domain.getSku());
    entity.setName(domain.getName());
    entity.setBrand(domain.getBrand());
    entity.setSize(domain.getSize());
    entity.setPrice(domain.getPrice());
    entity.setImage(domain.getImage());
    return entity;
  }
}
