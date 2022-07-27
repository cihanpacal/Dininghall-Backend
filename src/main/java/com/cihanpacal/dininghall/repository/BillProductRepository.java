package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.BillProduct;
import com.cihanpacal.dininghall.model.entity.FoodProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BillProductRepository extends JpaRepository<BillProduct,Long> {

    @Query("SELECT bp FROM BillProduct bp WHERE bp.product.name LIKE %:filter% and bp.bill.id=:billId")
    Page<BillProduct> findByBillId(
            @Param("filter") String filter,
            @Param("billId") Long billId,
            Pageable pageable
    );

    Optional<BillProduct> findByBill_IdAndProduct_Id(Long billId, Long productId);

    void deleteByBill_Id(Long billId);
}
