package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.*;
import com.cihanpacal.dininghall.model.request.DiningHallRequest;
import com.cihanpacal.dininghall.model.response.DiningHallResponse;
import com.cihanpacal.dininghall.repository.DiningHallRepository;
import com.cihanpacal.dininghall.util.OperationMessage;
import com.cihanpacal.dininghall.validation.DiningHallValidator;
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
public class DiningHallServiceImpl implements DiningHallService{

    private final DiningHallRepository diningHallRepository;
    private final ModelMapper modelMapper;
    private final DiningHallValidator diningHallValidator;
    private final WarehouseValidator warehouseValidator;


    @Override
    public Page<DiningHallResponse> getDiningHalls(Optional<String> filter,Optional<Long> optionalWarehouseId, Pageable pageable) {

        Long warehouseId=optionalWarehouseId.orElse(null);

        return diningHallRepository.findAll(filter.orElse(""),warehouseId,pageable).map((diningHall)->{
            return modelMapper.map(diningHall, DiningHallResponse.class);
        });
    }


    @Override
    public Long createDiningHall(DiningHallRequest diningHallRequest) {

        DiningHall diningHall=new DiningHall();
        diningHall.setName(diningHallRequest.getName());
        diningHall.setDescription(diningHallRequest.getDescription());
        Warehouse warehouse=warehouseValidator
                .checkWarehouseExist(diningHallRequest.getWarehouseId());
        diningHall.setWarehouse(warehouse);


        diningHallRepository.save(diningHall);

        return diningHall.getId();
    }

    @Override
    public DiningHallResponse getDiningHall(Long id) {
        DiningHall diningHall=diningHallValidator.checkDiningHallExist(id);
        DiningHallResponse diningHallResponse=modelMapper.map(diningHall,DiningHallResponse.class);
        return diningHallResponse;
    }

    @Override
    public void updateDiningHall(DiningHallRequest diningHallRequest, Long id) {
        DiningHall diningHall=diningHallValidator.checkDiningHallExist(id);

        Warehouse warehouse=warehouseValidator
                .checkWarehouseExist(diningHallRequest.getWarehouseId());

        diningHall.setName(diningHallRequest.getName());
        diningHall.setDescription(diningHallRequest.getDescription());
        diningHall.setWarehouse(warehouse);



        diningHallRepository.save(diningHall);
    }

    @Override
    public void deleteDiningHall(Long id) {
        DiningHall diningHall=diningHallValidator.checkDiningHallExist(id);
        diningHallValidator.checkAtLeastOneMealExists(id, OperationMessage.DELETE);

        diningHallRepository.deleteById(diningHall.getId());
    }
}
