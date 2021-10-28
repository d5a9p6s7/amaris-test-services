/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.web.controller;

import com.amaris.test.product.core.api.ProductService;
import com.amaris.test.product.core.api.domains.Product;
import com.amaris.test.product.core.api.events.CommandEvent;
import com.amaris.test.product.core.api.events.QueryEvent;
import com.amaris.test.product.core.api.events.QueryPKEvent;
import com.amaris.test.product.core.api.events.ResponseEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.DEFAULT;

/**
 * Controller for {@link Product}.
 *
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Slf4j
@RestController
public class ProductController extends APIController {

  private final ProductService productService;

  public ProductController(
      ProductService productService) {

    this.productService = productService;
  }


  @Operation(
      summary = "Find all Products.",
      description = "Find all Products register in the System."
  )
  @GetMapping("/product")
  public ResponseEntity<ResponseEvent<List<Product>>> findAllProducts() {

    log.info("method: findAllProducts()");
    final var responseEvent = productService.findAllProducts(new QueryEvent());
    log.info("method: findAllProducts() -> {}", responseEvent.getMessage());
    return buildHttpResponse(responseEvent);
  }

  @Operation(
      summary = "Find for a Product by ID.",
      description = "Find for a Product by ID in the System."
  )
  @GetMapping("/product/{sku}")
  public ResponseEntity<ResponseEvent<Product>> findProductById(
      @Parameter(in = DEFAULT, description = "the client id", schema = @Schema())
      @Valid @NotNull @PathVariable("sku") String sku) {

    log.info("method: findProductById({})", sku);
    final var readEvent = new QueryPKEvent<String>();
    readEvent.setRequest(sku);
    final var responseEvent = productService.findProductById(readEvent);
    log.info("method: findProductById({}) -> {}", sku, responseEvent);
    return buildHttpResponse(responseEvent);
  }

  @Operation(
      summary = "Create a Product.",
      description = "Create a Product in the system."
  )
  @PostMapping("/product")
  public ResponseEntity<ResponseEvent<Product>> createProduct(
      @Parameter(in = DEFAULT, description = "create request product", schema = @Schema())
      @Valid @NotNull @RequestBody Product.CreateRequest createClientRequest) {

    log.info("method: createProduct({})", createClientRequest);
    final var requestEvent = new CommandEvent<Product.CreateRequest>();
    requestEvent.setRequest(createClientRequest);
    final var responseEvent = productService.createProduct(requestEvent);
    log.info("method: createProduct({}) -> {}", createClientRequest, responseEvent);
    return buildHttpResponse(responseEvent);
  }

  @Operation(
      summary = "Update Product.",
      description = "update a Product in the system."
  )
  @PutMapping("/product/{sku}")
  public ResponseEntity<ResponseEvent<Product>> updateProduct(
      @Parameter(in = DEFAULT, description = "the product SKU", schema = @Schema())
      @Valid @NotNull @PathVariable("sku") String sku,
      @Parameter(in = DEFAULT, description = "the product update request", schema = @Schema())
      @Valid @NotNull @RequestBody Product.UpdateRequest request) {

    log.info("method: updateProduct({}, {})", sku, request);
    final var requestEvent = new CommandEvent<Product.UpdateRequest>();
    request.setSku(sku);
    requestEvent.setRequest(request);
    final var responseEvent = productService.updateProduct(requestEvent);
    log.info("method: updateProduct({}) -> {}", request, responseEvent);
    return buildHttpResponse(responseEvent);
  }

  @Operation(
      summary = "Delete a Product by SKU.",
      description = "Delete a Product by SKU in the System."
  )
  @DeleteMapping("/product/{sku}")
  public ResponseEntity<ResponseEvent<Product>> deleteProduct(
      @Parameter(in = DEFAULT, description = "the product SKU", schema = @Schema())
      @Valid @NotNull @PathVariable("sku") String sku) {

    log.info("method: deleteProduct({})", sku);
    final var readEvent = new CommandEvent<Product.DeleteRequest>();
    readEvent.setRequest(Product.DeleteRequest.builder().sku(sku).build());
    final var responseEvent = productService.deleteProduct(readEvent);
    log.info("method: deleteProduct({}) -> {}", sku, responseEvent);
    return buildHttpResponse(responseEvent);
  }
}
