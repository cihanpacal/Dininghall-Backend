package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.request.DiningHallRequest;
import com.cihanpacal.dininghall.model.request.ProductRequest;
import com.cihanpacal.dininghall.model.response.DiningHallResponse;
import com.cihanpacal.dininghall.model.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DiningHallService {

    Page<DiningHallResponse> getDiningHalls(
            Optional<String> filter,
            Optional<Long> optionalWarehouseId,
            Pageable pageable
    );



    Long createDiningHall(DiningHallRequest diningHallRequest);

    DiningHallResponse getDiningHall(Long id);

    void updateDiningHall(DiningHallRequest diningHallRequest, Long id);

    void deleteDiningHall(Long id);

}
