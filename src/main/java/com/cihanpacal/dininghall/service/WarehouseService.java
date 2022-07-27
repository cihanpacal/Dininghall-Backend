package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.request.ProductGroupRequest;
import com.cihanpacal.dininghall.model.request.WarehouseRequest;
import com.cihanpacal.dininghall.model.response.ProductGroupResponse;
import com.cihanpacal.dininghall.model.response.WarehouseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface WarehouseService {

    Page<WarehouseResponse> getWarehouses(Optional<String> searchingField, Pageable pageable);


    Long createWarehouse(WarehouseRequest warehouseRequest);

    WarehouseResponse getWarehouse(Long id);

    void updateWarehouse(WarehouseRequest warehouseRequest, Long id);

    void deleteWarehouse(Long id);

}
