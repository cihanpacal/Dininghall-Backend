package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.request.BillRequest;
import com.cihanpacal.dininghall.model.request.FoodRequest;
import com.cihanpacal.dininghall.model.response.BillResponse;
import com.cihanpacal.dininghall.model.response.FoodResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BillService {

    Page<BillResponse> getBills(
            Optional<String> optionalStartDateTime,
            Optional<String> optionalEndDateTime,
            Optional<Long> optionalWarehouseId,
            Pageable pageable
    );



    Long createBill(BillRequest billRequest);

    BillResponse getBill(Long id);

    void updateBill(BillRequest billRequest, Long id);

    void deleteBill(Long id);
}
