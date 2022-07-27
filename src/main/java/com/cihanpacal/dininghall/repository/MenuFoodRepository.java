package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.FoodProduct;
import com.cihanpacal.dininghall.model.entity.MenuFood;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MenuFoodRepository extends JpaRepository<MenuFood,Long> {

    @Query("SELECT mf FROM MenuFood mf WHERE mf.food.name LIKE %:filter% and mf.menu.id=:menuId")
    Page<MenuFood> findByMenuId(
            @Param("filter") String filter,
            @Param("menuId") Long menuId,
            Pageable pageable
    );

    Optional<MenuFood> findByMenu_IdAndFood_Id(Long menuId, Long foodId);

    void deleteByMenu_Id(Long menuId);

    @Query("SELECT count(fp) FROM FoodProduct fp WHERE fp.food.id= :foodId")
    Long getCountOfFoodProductsByFood(@Param("foodId") Long foodId);
}
