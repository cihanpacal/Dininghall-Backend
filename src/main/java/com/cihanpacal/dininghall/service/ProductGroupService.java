package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.request.ProductGroupRequest;
import com.cihanpacal.dininghall.model.response.ProductGroupResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ProductGroupService {

    Page<ProductGroupResponse> getProductGroups(Optional<String> searchingField, Pageable pageable);


    Long createProductGroup(ProductGroupRequest request);

    ProductGroupResponse getProductGroup(Long id);

    void updateProductGroup(ProductGroupRequest productGroupRequest, Long id);

    void deleteProductGroup(Long id);
}
