package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.Bill;
import com.cihanpacal.dininghall.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BillValidatorImpl implements BillValidator {

    private final BillRepository billRepository;

    @Override
    public Bill checkBillExist(Long id) {
        Optional<Bill> optionalBill=billRepository.findById(id);
        Bill bill=optionalBill.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Fatura"});
        });
        return bill;
    }


}
