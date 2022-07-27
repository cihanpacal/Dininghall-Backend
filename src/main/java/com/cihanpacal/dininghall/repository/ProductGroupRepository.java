package com.cihanpacal.dininghall.repository;


import com.cihanpacal.dininghall.model.entity.ProductGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductGroupRepository extends JpaRepository<ProductGroup,Long> {

    @Query("SELECT pg FROM ProductGroup pg WHERE pg.name LIKE %:filter%")
    Page<ProductGroup> findAll(@Param("filter") String filter, Pageable pageable);

    @Query("SELECT count(p) FROM Product p WHERE p.productGroup.id= :productGroupId")
    Long getCountOfProductsByProductGroup(@Param("productGroupId") Long productGroupId);



}
