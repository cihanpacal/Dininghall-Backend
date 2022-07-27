package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.MeasurementUnit;
import com.cihanpacal.dininghall.model.entity.Product;
import com.cihanpacal.dininghall.model.entity.ProductGroup;
import com.cihanpacal.dininghall.model.request.ProductRequest;
import com.cihanpacal.dininghall.model.response.ProductResponse;
import com.cihanpacal.dininghall.repository.ProductRepository;
import com.cihanpacal.dininghall.util.OperationMessage;
import com.cihanpacal.dininghall.validation.MeasurementUnitValidator;
import com.cihanpacal.dininghall.validation.ProductGroupValidator;
import com.cihanpacal.dininghall.validation.ProductValidator;
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
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ProductValidator productValidator;
    private final ProductGroupValidator productGroupValidator;
    private final MeasurementUnitValidator measurementUnitValidator;

    @Override
    public Page<ProductResponse> getProducts(
            Optional<String> filter,
            Optional<Long> optionalProductGroupId,
            Optional<Long > optionalMeasurementUnitId,
            Pageable pageable
    ) {


       Long productGroupId=optionalProductGroupId.orElse(null);
       Long measurementUnitId=optionalMeasurementUnitId.orElse(null);

        return productRepository.findAll(filter.orElse(""),productGroupId,measurementUnitId,pageable).map((product)->{
            return modelMapper.map(product, ProductResponse.class);
        });

    }


    @Override
    public Long createProduct(ProductRequest productRequest) {

       ProductGroup productGroup=productGroupValidator
               .checkProductGroupExist(productRequest.getProductGroupId());
       MeasurementUnit measurementUnit=measurementUnitValidator
                .checkMeasurementUnitExist(productRequest.getMeasurementUnitId());

       Product product=new Product();
       product.setName(productRequest.getName());
       product.setDescription(productRequest.getDescription());
       product.setProductGroup(productGroup);
       product.setMeasurementUnit(measurementUnit);

        productRepository.save(product);

        return product.getId();
    }

    @Override
    public ProductResponse getProduct(Long id) {
        Product product=productValidator.checkProductExist(id);
        ProductResponse productResponse=modelMapper.map(product,ProductResponse.class);
        return productResponse;
    }

    @Override
    public void updateProduct(ProductRequest productRequest, Long id) {
        Product product=productValidator.checkProductExist(id);
        ProductGroup productGroup=productGroupValidator
                .checkProductGroupExist(productRequest.getProductGroupId());
        MeasurementUnit measurementUnit=measurementUnitValidator
                .checkMeasurementUnitExist(productRequest.getMeasurementUnitId());

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setProductGroup(productGroup);
        product.setMeasurementUnit(measurementUnit);

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product=productValidator.checkProductExist(id);
        productValidator.checkAtLeastOneFoodProductExists(id,OperationMessage.DELETE);
        productValidator.checkAtLeastOneStockExists(id, OperationMessage.DELETE);
        productValidator.checkAtLeastOneBillDetailExists(id);
        productValidator.checkAtLeastOneMenuProductExists(id,OperationMessage.DELETE);

        productRepository.deleteById(product.getId());
    }
}
