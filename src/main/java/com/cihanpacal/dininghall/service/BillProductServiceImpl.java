package com.cihanpacal.dininghall.service;


import com.cihanpacal.dininghall.model.entity.*;
import com.cihanpacal.dininghall.model.request.BillProductRequest;
import com.cihanpacal.dininghall.model.request.BillProductUpdateRequest;
import com.cihanpacal.dininghall.model.response.BillProductResponse;
import com.cihanpacal.dininghall.repository.BillProductRepository;
import com.cihanpacal.dininghall.validation.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BillProductServiceImpl implements BillProductService {

    private final BillProductRepository billProductRepository;
    private final ModelMapper modelMapper;
    private final BillProductValidator billProductValidator;
    private final BillValidator billValidator;
    private final ProductValidator productValidator;

    @Override
    public Page<BillProductResponse> getBillProductsByBillId(Optional<String> filter, Long billId, Pageable pageable) {

        billValidator.checkBillExist(billId);

        return billProductRepository.findByBillId(filter.orElse(""),billId,pageable).map((billProduct)->{
            return modelMapper.map(billProduct, BillProductResponse.class);
        });
    }

    @Override
    public Long createBillProduct(BillProductRequest billProductRequest) {
        Bill bill=billValidator
                .checkBillExist(billProductRequest.getBillId());
        Product product=productValidator
                .checkProductExist(billProductRequest.getProductId());
        billProductValidator
                .checkBillProductIsAlreadyExistByBillIdAndProductId(
                        billProductRequest.getBillId(),
                        billProductRequest.getProductId()
                );

        BillProduct billProduct=new BillProduct();
        billProduct.setQuantity(billProductRequest.getQuantity());
        billProduct.setUnitPrice(billProductRequest.getUnitPrice());
        billProduct.setBill(bill);
        billProduct.setProduct(product);
        billProduct.setStatusTime(new Date(0));

        billProductRepository.save(billProduct);

        return  billProduct.getId();
    }

    @Override
    public void deleteBillProduct(Long id) {

        BillProduct billProduct=billProductValidator.checkBillProductExist(id);
        billProductRepository.deleteById(billProduct.getId());

    }

    @Override
    public void updateBillProduct(BillProductUpdateRequest billProductUpdateRequest, Long id) {

        BillProduct billProduct=billProductValidator.checkBillProductExist(id);

        billProduct.setQuantity(billProductUpdateRequest.getQuantity());
        billProduct.setUnitPrice(billProductUpdateRequest.getUnitPrice());

        billProductRepository.save(billProduct);
    }

    @Override
    public void deleteByBillId(Long billId) {
        billProductRepository.deleteByBill_Id(billId);
    }
}
