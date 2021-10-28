/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.core.api.dao;

import com.amaris.test.product.core.api.domains.Product;

import java.util.List;
import java.util.Optional;

/**
 * Repository for {@link Product}.
 *
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
public interface ProductDao {

  Product create(
      Product.CreateRequest domain);

  Product update(
      Product.UpdateRequest domain);

  Optional<Product> findBySku(
      String sku);

  List<Product> findAll();

  void deleteById(
      String id);
}
