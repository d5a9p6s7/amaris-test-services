/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.core.application.dao;

import com.amaris.test.product.core.api.dao.ProductDao;
import com.amaris.test.product.core.api.domains.Product;
import com.amaris.test.product.core.application.dao.entities.ProductEntity;
import com.amaris.test.product.core.application.dao.repositories.ProductEntityRepository;
import com.amaris.test.product.core.application.dao.repositories.ProductImageEntityRepository;
import com.amaris.test.product.core.application.mappers.ProductImageMapper;
import com.amaris.test.product.core.application.mappers.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Handler for {@link ProductDao}.
 *
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Repository
public class ProductDaoHandler implements ProductDao {

  private final ProductEntityRepository entityRepository;
  private final ProductImageEntityRepository productImageEntityRepository;
  private final ProductMapper productMapper;
  private final ProductImageMapper productImageMapper;

  public ProductDaoHandler(
      ProductEntityRepository entityRepository,
      ProductImageEntityRepository productImageEntityRepository,
      ProductMapper productMapper,
      ProductImageMapper productImageMapper) {

    this.entityRepository = entityRepository;
    this.productImageEntityRepository = productImageEntityRepository;
    this.productMapper = productMapper;
    this.productImageMapper = productImageMapper;
  }

  @Override
  public Product create(
      Product.CreateRequest domain) {

    var product = productMapper.toDomain(entityRepository.saveAndFlush(productMapper.toEntity(domain)));
    product.setImages(
        productImageMapper.toDomain(
            productImageEntityRepository.saveAll(
                productImageMapper.toEntity(domain.getSku(), domain.getImages()))));
    return product;
  }

  @Override
  public Product update(
      Product.UpdateRequest domain) {

    var product = productMapper.toDomain(entityRepository.saveAndFlush(productMapper.toEntity(domain)));
    productImageEntityRepository.deleteAll(productImageEntityRepository.findAllByProduct(productMapper.toEntity(domain)));
    product.setImages(
        productImageMapper.toDomain(
            productImageEntityRepository.saveAll(
                productImageMapper.toEntity(domain.getSku(), domain.getImages()))));
    return product;
  }

  @Override
  public Optional<Product> findBySku(
      String sku) {

    return entityRepository.findById(sku).map(productMapper::toDomain);
  }

  @Override
  public List<Product> findAll() {

    return productMapper.toDomain(entityRepository.findAll());
  }

  @Override
  public void deleteById(
      String id) {

    entityRepository.deleteById(id);
  }
}
