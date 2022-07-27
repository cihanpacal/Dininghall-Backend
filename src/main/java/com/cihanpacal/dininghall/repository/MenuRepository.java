package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.Food;
import com.cihanpacal.dininghall.model.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends JpaRepository<Menu,Long> {

    @Query("SELECT m FROM Menu m WHERE m.name LIKE %:filter% ")
    Page<Menu> findAll(
            @Param("filter") String filter,
            Pageable pageable
    );

    @Query("SELECT count(mf) FROM MenuFood mf WHERE mf.menu.id= :menuId")
    Long getCountOfMenuFoodsByMenu(@Param("menuId") Long menuId);

}
