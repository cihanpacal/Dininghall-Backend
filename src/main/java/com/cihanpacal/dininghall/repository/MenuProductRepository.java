package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.FoodProduct;
import com.cihanpacal.dininghall.model.entity.MenuProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MenuProductRepository extends JpaRepository<MenuProduct,Long> {

    @Query("SELECT mp FROM MenuProduct mp WHERE mp.product.name LIKE %:filter% and mp.menu.id=:menuId")
    Page<MenuProduct> findByMenuId(
            @Param("filter") String filter,
            @Param("menuId") Long menuId,
            Pageable pageable
    );

    Optional<MenuProduct> findByMenu_IdAndProduct_Id(Long menuId, Long productId);

    void deleteByMenu_Id(Long menuId);
}
