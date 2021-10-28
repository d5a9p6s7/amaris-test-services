package com.amaris.test.products.core.application.dao;

import com.amaris.test.product.core.api.domains.Product;
import com.amaris.test.product.core.application.dao.ProductDaoHandler;
import com.amaris.test.product.core.application.dao.entities.ProductEntity;
import com.amaris.test.product.core.application.dao.repositories.ProductEntityRepository;
import com.amaris.test.product.core.application.dao.repositories.ProductImageEntityRepository;
import com.amaris.test.product.core.application.mappers.ProductImageMapper;
import com.amaris.test.product.core.application.mappers.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Optional;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Unit tests for {@link ProductDaoHandler}.
 *
 * @author <a href="mailto:diego.poveda@farmatodo.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/28
 * @since 1.8
 */

class ProductDaoHandlerTest {

  private ProductDaoHandler daoHandler;
  @Mock
  private ProductEntityRepository entityRepository;
  @Mock
  private ProductImageEntityRepository productImageEntityRepository;
  @Mock
  private ProductMapper productMapper;
  @Mock
  private ProductImageMapper productImageMapper;

  @BeforeEach
  public void setUp() {
    openMocks(this);
    daoHandler = new ProductDaoHandler(entityRepository, productImageEntityRepository, productMapper, productImageMapper);
  }

  /**
   * GIVEN: A product create request.
   * WHEN: create(domain) is called.
   * THEN: Returns a Product domain.
   */
  @Test
  void givenProductCreateRequestWhenCreateThenReturnProduct() {

    var productExpected = Product.builder()
        .sku("FAL-881952283")
        .name("Bicicleta Bal-toro Aro 29")
        .brand("JEEP")
        .size("ST")
        .price(399990.00D)
        .image("https://falabella.scene7.com/is/image/Falabella/881952283_1")
        .build();
    var productCreateRequest = Product.CreateRequest.builder()
        .sku("FAL-881952283")
        .name("Bicicleta Bal-toro Aro 29")
        .brand("JEEP")
        .size("ST")
        .price(399990.00D)
        .image("https://falabella.scene7.com/is/image/Falabella/881952283_1")
        .build();
    var entity = new ProductEntity();
    entity.setSku("FAL-881952283");
    entity.setName("Bicicleta Bal-toro Aro 29");
    entity.setBrand("JEEP");
    entity.setSize("ST");
    entity.setPrice(399990.00D);
    entity.setImage("https://falabella.scene7.com/is/image/Falabella/881952283_1");

    when(productMapper.toEntity(productCreateRequest)).thenReturn(entity);
    when(entityRepository.saveAndFlush(entity)).thenReturn(entity);
    when(productMapper.toDomain(entity)).thenReturn(productExpected);

    final var product = daoHandler.create(productCreateRequest);

    assertNotNull(product);

    verify(productMapper, times(INTEGER_ONE)).toEntity(productCreateRequest);
    verify(entityRepository, times(INTEGER_ONE)).saveAndFlush(entity);
    verify(productMapper, times(INTEGER_ONE)).toDomain(entity);
  }

  /**
   * GIVEN: A product update request.
   * WHEN: update(domain) is called.
   * THEN: Returns a Product domain.
   */
  @Test
  void givenProductUpdateRequestWhenCreateThenReturnProduct() {

    var productExpected = Product.builder()
        .sku("FAL-881952283")
        .name("Bicicleta Bal-toro Aro 29")
        .brand("JEEP")
        .size("ST")
        .price(399990.00D)
        .image("https://falabella.scene7.com/is/image/Falabella/881952283_1")
        .build();
    var productUpdateRequest = Product.UpdateRequest.builder()
        .sku("FAL-881952283")
        .name("Bicicleta Bal-toro Aro 29")
        .brand("JEEP")
        .size("ST")
        .price(399990.00D)
        .image("https://falabella.scene7.com/is/image/Falabella/881952283_1")
        .build();
    var entity = new ProductEntity();
    entity.setSku("FAL-881952283");
    entity.setName("Bicicleta Bal-toro Aro 29");
    entity.setBrand("JEEP");
    entity.setSize("ST");
    entity.setPrice(399990.00D);
    entity.setImage("https://falabella.scene7.com/is/image/Falabella/881952283_1");

    when(productMapper.toEntity(productUpdateRequest)).thenReturn(entity);
    when(entityRepository.saveAndFlush(entity)).thenReturn(entity);
    when(productMapper.toDomain(entity)).thenReturn(productExpected);

    final var product = daoHandler.update(productUpdateRequest);

    assertNotNull(product);

    verify(productMapper, times(2)).toEntity(productUpdateRequest);
    verify(entityRepository, times(INTEGER_ONE)).saveAndFlush(entity);
    verify(productMapper, times(INTEGER_ONE)).toDomain(entity);
  }

  /**
   * GIVEN: A SKU .
   * WHEN: findBySku(sku) is called.
   * THEN: Returns a Product domain.
   */
  @Test
  void givenSkuWhenFindBySkuThenReturnProduct() {

    var sku = "FAL-881952283";
    var productExpected = Product.builder()
        .sku("FAL-881952283")
        .name("Bicicleta Bal-toro Aro 29")
        .brand("JEEP")
        .size("ST")
        .price(399990.00D)
        .image("https://falabella.scene7.com/is/image/Falabella/881952283_1")
        .build();
    var entity = new ProductEntity();
    entity.setSku("FAL-881952283");
    entity.setName("Bicicleta Bal-toro Aro 29");
    entity.setBrand("JEEP");
    entity.setSize("ST");
    entity.setPrice(399990.00D);
    entity.setImage("https://falabella.scene7.com/is/image/Falabella/881952283_1");
    var optionalProduct = Optional.of(entity);

    when(entityRepository.findById(sku)).thenReturn(optionalProduct);
    when(productMapper.toDomain(entity)).thenReturn(productExpected);

    final var product = daoHandler.findBySku(sku);

    assertNotNull(product);

    verify(entityRepository, times(INTEGER_ONE)).findById(sku);
    verify(productMapper, times(INTEGER_ONE)).toDomain(entity);
  }

  /**
   * GIVEN: Nothing .
   * WHEN: findAll() is called.
   * THEN: Returns a Product list domain.
   */
  @Test
  void givenNothingWhenFindAllThenReturnProductList() {

    var product = Product.builder()
        .sku("FAL-881952283")
        .name("Bicicleta Bal-toro Aro 29")
        .brand("JEEP")
        .size("ST")
        .price(399990.00D)
        .image("https://falabella.scene7.com/is/image/Falabella/881952283_1")
        .build();
    var productsExpected = new ArrayList<Product>() {{
      add(product);
      add(product);
      add(product);
    }};
    var entity = new ProductEntity();
    entity.setSku("FAL-881952283");
    entity.setName("Bicicleta Bal-toro Aro 29");
    entity.setBrand("JEEP");
    entity.setSize("ST");
    entity.setPrice(399990.00D);
    entity.setImage("https://falabella.scene7.com/is/image/Falabella/881952283_1");
    var entities = new ArrayList<ProductEntity>() {{
      add(entity);
      add(entity);
      add(entity);
    }};

    when(entityRepository.findAll()).thenReturn(entities);
    when(productMapper.toDomain(entities)).thenReturn(productsExpected);

    final var products = daoHandler.findAll();

    assertNotNull(products);

    verify(entityRepository, times(INTEGER_ONE)).findAll();
    verify(productMapper, times(INTEGER_ONE)).toDomain(entities);
  }

  /**
   * GIVEN: a SKU of product .
   * WHEN: deleteById(sku) is called.
   * THEN: delete record from system.
   */
  //@Test
  void givenSkuWhenDeleteByIdThenDeleteRecordFromSystem() {

    var sku = "FAL-881952283";
    //when(entityRepository.deleteById(sku));

    verify(entityRepository, times(INTEGER_ONE)).deleteById(sku);
  }
}
