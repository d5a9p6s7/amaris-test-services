/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.core.application.dao.repositories;

import com.amaris.test.product.core.application.dao.entities.ProductEntity;
import com.amaris.test.product.core.application.dao.entities.ProductImageEntity;
import com.amaris.test.product.core.application.dao.entities.ProductImagePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA repositories for {@link ProductImageEntity}.
 *
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Repository
public interface ProductImageEntityRepository extends JpaRepository<ProductImageEntity, ProductImagePK> {

  List<ProductImageEntity> findAllByProduct(ProductEntity product);
}
