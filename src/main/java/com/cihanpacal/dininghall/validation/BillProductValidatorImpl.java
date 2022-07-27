package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.ResourceAlreadyExistException;
import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.BillProduct;
import com.cihanpacal.dininghall.repository.BillProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BillProductValidatorImpl implements BillProductValidator {

    private final BillProductRepository billProductRepository;

    @Override
    public BillProduct checkBillProductExist(Long id) {
        Optional<BillProduct> optionalBillProduct=billProductRepository.findById(id);
        BillProduct billProduct=optionalBillProduct.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Fatura Ürünü"});
        });
        return billProduct;
    }

    @Override
    public void checkBillProductIsAlreadyExistByBillIdAndProductId(Long billId, Long productId) {
        Optional<BillProduct> optionalBillProduct=billProductRepository
                .findByBill_IdAndProduct_Id(billId,productId);
        optionalBillProduct.ifPresent((billProduct)->{
            throw new ResourceAlreadyExistException(
                    "exception.ResourceAlreadyExistException",
                    new Object[]{"Fatura Ürünü"}
            );
        });
    }
}
