package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.request.BillProductRequest;
import com.cihanpacal.dininghall.model.request.BillProductUpdateRequest;
import com.cihanpacal.dininghall.model.request.FoodProductRequest;
import com.cihanpacal.dininghall.model.request.FoodProductUpdateRequest;
import com.cihanpacal.dininghall.model.response.BillProductResponse;
import com.cihanpacal.dininghall.model.response.FoodProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BillProductService {
    Page<BillProductResponse> getBillProductsByBillId(Optional<String> filter, Long billId, Pageable pageable);
    Long createBillProduct(BillProductRequest billProductRequest);
    void deleteBillProduct(Long id);
    void updateBillProduct(BillProductUpdateRequest billProductUpdateRequest, Long id);
    void deleteByBillId(Long billId);
}
