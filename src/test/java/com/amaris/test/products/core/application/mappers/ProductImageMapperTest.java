package com.amaris.test.products.core.application.mappers;

import com.amaris.test.product.core.application.dao.entities.ProductImageEntity;
import com.amaris.test.product.core.application.dao.entities.ProductImagePK;
import com.amaris.test.product.core.application.mappers.ProductImageMapper;
import com.amaris.test.product.core.application.mappers.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link ProductMapper}.
 *
 * @author <a href="mailto:diego.poveda@farmatodo.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
class ProductImageMapperTest {

  private ProductImageMapper productImageMapper;

  @BeforeEach
  public void setUp() {
    productImageMapper = new ProductImageMapper();
  }

  /**
   * GIVEN: Entity null.
   * WHEN: toDomain(entity) is called.
   * THEN: Returns null.
   */
  @Test
  void givenEntityNullWhenToDomainThenReturnNull() {
    ProductImageEntity entity = null;
    final var domain = productImageMapper.toDomain(entity);

    assertNull(domain);
  }

  /**
   * GIVEN: Entity list null.
   * WHEN: toDomain(entity) is called.
   * THEN: Returns empty list.
   */
  @Test
  void givenEntityListNullWhenToDomainThenReturnEmptyList() {
    List<ProductImageEntity> entities = null;
    final var products = productImageMapper.toDomain(entities);

    assertNotNull(products);
    assertEquals(INTEGER_ZERO, products.size());
  }

  /**
   * GIVEN: ProductImageEntity entity.
   * WHEN: toDomain(entity) is called.
   * THEN: Returns URL.
   */
  @Test
  void givenProductEntityWhenToDomainThenReturnProduct() {
    var entity = new ProductImageEntity();
    var pk = new ProductImagePK();
    pk.setSku("FAL-881952283");
    pk.setImageUrl("https://falabella.scene7.com/is/image/Falabella/881952283_1");
    entity.setPk(pk);

    final var url = productImageMapper.toDomain(entity);

    assertNotNull(url);
  }

  /**
   * GIVEN: Entity list not null.
   * WHEN: toDomain(entity) is called.
   * THEN: Returns list of URL's.
   */
  @Test
  void givenEntityListWhenToDomainThenReturnProductList() {
    var entity = new ProductImageEntity();
    var pk = new ProductImagePK();
    pk.setSku("FAL-881952283");
    pk.setImageUrl("https://falabella.scene7.com/is/image/Falabella/881952283_1");
    entity.setPk(pk);
    List<ProductImageEntity> entities = new ArrayList<>() {{
      add(entity);
      add(entity);
      add(entity);
    }};
    final var products = productImageMapper.toDomain(entities);

    assertNotNull(products);
    assertEquals(3, products.size());
  }

  /**
   * GIVEN: SKU and URL
   * WHEN: toEntity(sku, url) is called.
   * THEN: Returns ProductImageEntity.
   */
  @Test
  void givenSkuAndUrlWhenToEntityThenReturnEntity() {
    final var entity = productImageMapper.toEntity("FAL-881952283", "https://falabella.scene7.com/is/image/Falabella/881952283_1");

    assertNotNull(entity);
  }

  /**
   * GIVEN: SKU and URL list.
   * WHEN: toEntity(sku, urls) is called.
   * THEN: Returns ProductImageEntity list.
   */
  @Test
  void givenUrlListNullWhenToEntityThenReturnNull() {
    List<String> domains = null;
    final var entity = productImageMapper.toEntity("FAL-881952283", domains);

    assertNotNull(entity);
  }

  /**
   * GIVEN: SKU and URL list.
   * WHEN: toEntity(sku, urls) is called.
   * THEN: Returns ProductImageEntity list.
   */
  @Test
  void givenSkuAndUrlListWhenToEntityThenReturnProductImageEntityList() {
    List<String> domains = new ArrayList<>() {{
      add("https://falabella.scene7.com/is/image/Falabella/881952283_1");
      add("https://falabella.scene7.com/is/image/Falabella/881952283_1");
      add("https://falabella.scene7.com/is/image/Falabella/881952283_1");
    }};
    final var entity = productImageMapper.toEntity("FAL-881952283", domains);

    assertNotNull(entity);
  }
}