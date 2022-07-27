package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.ProductGroup;
import com.cihanpacal.dininghall.model.request.ProductGroupRequest;
import com.cihanpacal.dininghall.model.response.ProductGroupResponse;
import com.cihanpacal.dininghall.repository.ProductGroupRepository;
import com.cihanpacal.dininghall.util.OperationMessage;
import com.cihanpacal.dininghall.validation.ProductGroupValidator;
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
public class ProductGroupServiceImpl implements ProductGroupService{

    private final ProductGroupRepository productGroupRepository;
    private final ProductGroupValidator productGroupValidator;
    private final ModelMapper modelMapper;


    @Override
    public Page<ProductGroupResponse> getProductGroups(Optional<String> filter, Pageable pageable) {

        return productGroupRepository.findAll(filter.orElse(""),pageable).map((productGroup)->{
          return modelMapper.map(productGroup,ProductGroupResponse.class);
        });

    }

    @Override
    public Long createProductGroup(ProductGroupRequest productGroupRequest) {

        ProductGroup productGroup=modelMapper.map(productGroupRequest,ProductGroup.class);
        productGroupRepository.save(productGroup);

        return productGroup.getId();
    }

    @Override
    public ProductGroupResponse getProductGroup(Long id) {

        ProductGroup productGroup= productGroupValidator.checkProductGroupExist(id);
        ProductGroupResponse productGroupResponse=modelMapper.map(productGroup,ProductGroupResponse.class);

        return productGroupResponse;
    }

    @Override
    public void updateProductGroup(ProductGroupRequest productGroupRequest, Long id) {

        ProductGroup productGroup= productGroupValidator.checkProductGroupExist(id);
        productGroup.setName(productGroupRequest.getName());
        productGroup.setDescription(productGroupRequest.getDescription());

        productGroupRepository.save(productGroup);

    }

    @Override
    public void deleteProductGroup(Long id) {

        ProductGroup productGroup= productGroupValidator.checkProductGroupExist(id);
        productGroupValidator.checkAtLeastOneProductExists(productGroup.getId(), OperationMessage.DELETE);
        productGroupRepository.deleteById(productGroup.getId());

    }

}
