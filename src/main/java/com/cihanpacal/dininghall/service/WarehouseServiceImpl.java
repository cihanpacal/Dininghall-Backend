package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.Warehouse;
import com.cihanpacal.dininghall.model.request.WarehouseRequest;
import com.cihanpacal.dininghall.model.response.WarehouseResponse;
import com.cihanpacal.dininghall.repository.WarehouseRepository;
import com.cihanpacal.dininghall.util.OperationMessage;
import com.cihanpacal.dininghall.validation.WarehouseValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class WarehouseServiceImpl implements WarehouseService{

    private final WarehouseRepository warehouseRepository;
    private final WarehouseValidator warehouseValidator;
    private final ModelMapper modelMapper;

    @Override
    public Page<WarehouseResponse> getWarehouses(Optional<String> filter, Pageable pageable) {
        return warehouseRepository.findAll(filter.orElse(""),pageable).map((warehouse)->{
            return modelMapper.map(warehouse, WarehouseResponse.class);
        });
    }

    @Override
    public Long createWarehouse(WarehouseRequest warehouseRequest) {
        Warehouse warehouse=modelMapper.map(warehouseRequest,Warehouse.class);
        warehouseRepository.save(warehouse);

        return warehouse.getId();
    }

    @Override
    public WarehouseResponse getWarehouse(Long id) {
        Warehouse warehouse= warehouseValidator.checkWarehouseExist(id);
        WarehouseResponse warehouseResponse=modelMapper.map(warehouse,WarehouseResponse.class);

        return warehouseResponse;
    }

    @Override
    public void updateWarehouse(WarehouseRequest warehouseRequest, Long id) {
        Warehouse warehouse= warehouseValidator.checkWarehouseExist(id);
        warehouse.setName(warehouseRequest.getName());
        warehouse.setDescription(warehouseRequest.getDescription());

        warehouseRepository.save(warehouse);
    }

    @Override
    public void deleteWarehouse(Long id) {
        Warehouse warehouse= warehouseValidator.checkWarehouseExist(id);
        warehouseValidator.checkAtLeastOneDiningHallExists(warehouse.getId(), OperationMessage.DELETE);
        warehouseValidator.checkAtLeastOneStockExists(warehouse.getId(), OperationMessage.DELETE);
        warehouseValidator.checkAtLeastOneBillExists(warehouse.getId(),OperationMessage.DELETE);
        warehouseRepository.deleteById(warehouse.getId());
    }
}
