/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.core.application;

import com.amaris.test.product.core.api.ProductService;
import com.amaris.test.product.core.api.dao.ProductDao;
import com.amaris.test.product.core.api.domains.Product;
import com.amaris.test.product.core.api.events.CommandEvent;
import com.amaris.test.product.core.api.events.QueryEvent;
import com.amaris.test.product.core.api.events.QueryPKEvent;
import com.amaris.test.product.core.api.events.ResponseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Handler for {@link ProductService}.
 *
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Slf4j
@Service
@Transactional
public class ProductServiceHandler implements ProductService {

  private final ProductDao productDao;

  public ProductServiceHandler(
      ProductDao productDao) {

    this.productDao = productDao;
  }

  @Override
  public ResponseEvent<List<Product>> findAllProducts(
      @Valid @NotNull QueryEvent requestEvent) {

    log.debug("method: findAllProducts({})", requestEvent);
    try {
      return new ResponseEvent<List<Product>>().ok("commons.event.response.success", productDao.findAll());
    } catch (Exception ex) {
      log.error("method: findAllProducts({}) -> Exception: {}", requestEvent, ex.getMessage(), ex);
      return new ResponseEvent<List<Product>>().error(ex.getMessage());
    }
  }

  @Override
  public ResponseEvent<Product> findProductById(
      @Valid @NotNull QueryPKEvent<String> requestEvent) {

    log.debug("method: findProductById({})", requestEvent);
    try {
      var optional = productDao.findBySku(requestEvent.getRequest());
      if (optional.isEmpty())
        return new ResponseEvent<Product>().noContent("commons.event.response.noContent");
      return new ResponseEvent<Product>().ok("commons.event.response.success", optional.get());
    } catch (Exception ex) {
      log.error("method: findProductById({}) -> Exception: {}", requestEvent, ex.getMessage(), ex);
      return new ResponseEvent<Product>().error(ex.getMessage());
    }
  }

  @Override
  public ResponseEvent<Product> createProduct(
      @Valid @NotNull CommandEvent<Product.CreateRequest> requestEvent) {

    log.debug("method: createProduct({})", requestEvent);
    try {
      return new ResponseEvent<Product>().ok("commons.event.response.success", productDao.create(requestEvent.getRequest()));
    } catch (Exception ex) {
      log.error("method: createProduct({}) -> Exception: {}", requestEvent, ex.getMessage(), ex);
      return new ResponseEvent<Product>().error(ex.getMessage());
    }
  }

  @Override
  public ResponseEvent<Product> updateProduct(
      @Valid @NotNull CommandEvent<Product.UpdateRequest> requestEvent) {

    log.debug("method: createProduct({})", requestEvent);
    try {
      var optional = productDao.findBySku(requestEvent.getRequest().getSku());
      if (optional.isEmpty())
        return new ResponseEvent<Product>().noContent("commons.event.response.noContent");
      return new ResponseEvent<Product>().ok("commons.event.response.success", productDao.update(requestEvent.getRequest()));
    } catch (Exception ex) {
      log.error("method: createProduct({}) -> Exception: {}", requestEvent, ex.getMessage(), ex);
      return new ResponseEvent<Product>().error(ex.getMessage());
    }
  }

  @Override
  public ResponseEvent<Product> deleteProduct(
      @Valid @NotNull CommandEvent<Product.DeleteRequest> requestEvent) {

    log.debug("method: deleteProduct({})", requestEvent);
    try {
      productDao.deleteById(requestEvent.getRequest().getSku());
      return new ResponseEvent<Product>().ok("commons.event.response.success");
    } catch (Exception ex) {
      log.error("method: deleteProduct({}) -> Exception: {}", requestEvent, ex.getMessage(), ex);
      return new ResponseEvent<Product>().error(ex.getMessage());
    }
  }
}
