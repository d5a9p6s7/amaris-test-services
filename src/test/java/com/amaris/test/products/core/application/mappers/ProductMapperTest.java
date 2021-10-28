package com.amaris.test.products.core.application.mappers;

import com.amaris.test.product.core.api.domains.Product;
import com.amaris.test.product.core.application.dao.entities.ProductEntity;
import com.amaris.test.product.core.application.mappers.ProductImageMapper;
import com.amaris.test.product.core.application.mappers.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Unit tests for {@link ProductMapper}.
 *
 * @author <a href="mailto:diego.poveda@farmatodo.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
class ProductMapperTest {

  private ProductMapper productMapper;
  @Mock
  private ProductImageMapper productImageMapper;

  @BeforeEach
  public void setUp() {
    openMocks(this);
    productMapper = new ProductMapper(productImageMapper);
  }

  /**
   * GIVEN: Entity null.
   * WHEN: toDomain(entity) is called.
   * THEN: Returns null.
   */
  @Test
  void givenEntityNullWhenToDomainThenReturnNull() {
    ProductEntity entity = null;
    final var domain = productMapper.toDomain(entity);

    assertNull(domain);
  }

  /**
   * GIVEN: Entity list null.
   * WHEN: toDomain(entity) is called.
   * THEN: Returns empty list.
   */
  @Test
  void givenEntityListNullWhenToDomainThenReturnEmptyList() {
    List<ProductEntity> entities = null;
    final var products = productMapper.toDomain(entities);

    assertNotNull(products);
    assertEquals(INTEGER_ZERO, products.size());
  }

  /**
   * GIVEN: ProductEntity entity.
   * WHEN: toDomain(entity) is called.
   * THEN: Returns Product domain.
   */
  @Test
  void givenProductEntityWhenToDomainThenReturnProduct() {
    var entity = new ProductEntity();
    entity.setSku("FAL-881952283");
    entity.setName("Bicicleta Bal-toro Aro 29");
    entity.setBrand("JEEP");
    entity.setSize("ST");
    entity.setPrice(399990.00D);
    entity.setImage("https://falabella.scene7.com/is/image/Falabella/881952283_1");
    entity.setImages(Collections.emptyList());

    when(productImageMapper.toDomain(anyList())).thenReturn(Collections.emptyList());

    final var domain = productMapper.toDomain(entity);

    assertNotNull(domain);

    verify(productImageMapper, times(INTEGER_ONE)).toDomain(anyList());
  }

  /**
   * GIVEN: Entity list not null.
   * WHEN: toDomain(entity) is called.
   * THEN: Returns list of products domains.
   */
  @Test
  void givenEntityListWhenToDomainThenReturnProductList() {
    var entity = new ProductEntity();
    entity.setSku("FAL-881952283");
    entity.setName("Bicicleta Bal-toro Aro 29");
    entity.setBrand("JEEP");
    entity.setSize("ST");
    entity.setPrice(399990.00D);
    entity.setImage("https://falabella.scene7.com/is/image/Falabella/881952283_1");
    entity.setImages(Collections.emptyList());
    List<ProductEntity> entities = new ArrayList<>() {{
      add(entity);
      add(entity);
      add(entity);
    }};
    final var products = productMapper.toDomain(entities);

    assertNotNull(products);
    assertEquals(3, products.size());
  }

  /**
   * GIVEN: create request domain null.
   * WHEN: toEntity(domain) is called.
   * THEN: Returns null.
   */
  @Test
  void givenCreateRequestDomainNullWhenToEntityThenReturnNull() {
    Product.CreateRequest domain = null;
    final var entity = productMapper.toEntity(domain);

    assertNull(entity);
  }

  /**
   * GIVEN: update request domain null.
   * WHEN: toEntity(domain) is called.
   * THEN: Returns null.
   */
  @Test
  void givenUpdateRequestDomainNullWhenToEntityThenReturnNull() {
    Product.UpdateRequest domain = null;
    final var entity = productMapper.toEntity(domain);

    assertNull(entity);
  }

  /**
   * GIVEN: create request domain null.
   * WHEN: toEntity(domain) is called.
   * THEN: Returns null.
   */
  @Test
  void givenCreateRequestDomainWhenToEntityThenReturnProductEntity() {
    final var domain = Product.CreateRequest.builder()
        .sku("FAL-881952283")
        .name("Bicicleta Bal-toro Aro 29")
        .brand("JEEP")
        .size("ST")
        .price(399990.00D)
        .image("https://falabella.scene7.com/is/image/Falabella/881952283_1")
        .build();
    final var entity = productMapper.toEntity(domain);

    assertNotNull(entity);
  }

  /**
   * GIVEN: update request domain.
   * WHEN: toEntity(domain) is called.
   * THEN: Returns a product entity.
   */
  @Test
  void givenUpdateRequestDomainWhenToEntityThenReturnProductEntiry() {
    final var domain = Product.UpdateRequest.builder()
        .sku("FAL-881952283")
        .name("Bicicleta Bal-toro Aro 29")
        .brand("JEEP")
        .size("ST")
        .price(399990.00D)
        .image("https://falabella.scene7.com/is/image/Falabella/881952283_1")
        .build();
    final var entity = productMapper.toEntity(domain);

    assertNotNull(entity);
  }
}