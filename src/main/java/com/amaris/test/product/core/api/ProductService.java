/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.core.api;


import com.amaris.test.product.core.api.domains.Product;
import com.amaris.test.product.core.api.events.CommandEvent;
import com.amaris.test.product.core.api.events.QueryEvent;
import com.amaris.test.product.core.api.events.QueryPKEvent;
import com.amaris.test.product.core.api.events.ResponseEvent;

import java.util.List;

/**
 * Service for {@link Product}.
 *
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
public interface ProductService {

  ResponseEvent<List<Product>> findAllProducts(
      QueryEvent requestEvent);

  ResponseEvent<Product> findProductById(
      QueryPKEvent<String> requestEvent);

  ResponseEvent<Product> createProduct(
      CommandEvent<Product.CreateRequest> requestEvent);

  ResponseEvent<Product> updateProduct(
      CommandEvent<Product.UpdateRequest> requestEvent);

  ResponseEvent<Product> deleteProduct(
      CommandEvent<Product.DeleteRequest> requestEvent);
}
