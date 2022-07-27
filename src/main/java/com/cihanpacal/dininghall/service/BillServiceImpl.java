package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.Bill;
import com.cihanpacal.dininghall.model.entity.Warehouse;
import com.cihanpacal.dininghall.model.request.BillRequest;
import com.cihanpacal.dininghall.model.response.BillResponse;
import com.cihanpacal.dininghall.repository.BillRepository;
import com.cihanpacal.dininghall.validation.BillValidator;
import com.cihanpacal.dininghall.validation.WarehouseValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final ModelMapper modelMapper;
    private final BillValidator billValidator;
    private final WarehouseValidator warehouseValidator;
    private final BillProductService billProductService;

    @Override
    public Page<BillResponse> getBills(
            Optional<String> optionalStartDateTime,
            Optional<String> optionalEndDateTime,
            Optional<Long> optionalWarehouseId,
            Pageable pageable
    ) {

        Long warehouseId=optionalWarehouseId.orElse(null);
        LocalDateTime startDateTime= LocalDateTime.parse(optionalStartDateTime.orElse("1970-01-01T"+ LocalTime.MIN.toString()));
        LocalDateTime endDateTime=LocalDateTime.parse(optionalEndDateTime.orElse("2100-01-01T"+LocalTime.MAX.toString()));

        return billRepository.findAll(startDateTime,endDateTime,warehouseId,pageable).map((bill)->{
                    return modelMapper.map(bill, BillResponse.class);
        });
    }

    @Override
    public Long createBill(BillRequest billRequest) {
        Warehouse warehouse=warehouseValidator
                .checkWarehouseExist(billRequest.getWarehouseId());

        Bill bill=new Bill();
        bill.setTime(billRequest.getTime());
        bill.setDescription(billRequest.getDescription());
        bill.setWarehouse(warehouse);

        billRepository.save(bill);

        return bill.getId();
    }

    @Override
    public BillResponse getBill(Long id) {
        Bill bill=billValidator.checkBillExist(id);
        BillResponse billResponse=modelMapper.map(bill,BillResponse.class);
        return billResponse;
    }

    @Override
    public void updateBill(BillRequest billRequest, Long id) {
        Bill bill=billValidator.checkBillExist(id);
        Warehouse warehouse=warehouseValidator
                .checkWarehouseExist(billRequest.getWarehouseId());

        bill.setTime(billRequest.getTime());
        bill.setDescription(billRequest.getDescription());
        bill.setWarehouse(warehouse);

        billRepository.save(bill);
    }

    @Override
    public void deleteBill(Long id) {
        Bill bill=billValidator.checkBillExist(id);

        billProductService.deleteByBillId(id);

        billRepository.deleteById(bill.getId());
    }


}
