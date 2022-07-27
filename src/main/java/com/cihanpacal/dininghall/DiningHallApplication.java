package com.cihanpacal.dininghall;

import com.cihanpacal.dininghall.config.DiningHallProperties;
import com.cihanpacal.dininghall.model.entity.*;
import com.cihanpacal.dininghall.model.response.StockResponse;
import com.cihanpacal.dininghall.model.response.StockTransactionResponse;
import com.cihanpacal.dininghall.model.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DiningHallApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiningHallApplication.class, args);
    }


    @Bean
    public ModelMapper createModelMapper(){
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);


        modelMapper.typeMap(User.class, UserResponse.class)
                        .addMappings(mapper->{
                            mapper.map(user -> user.getRole(),UserResponse::setRole);
                        });

        modelMapper.typeMap(Stock.class, StockResponse.class)
                .addMappings(mapper->{
                    mapper.map(stock->stock.getProduct().getMeasurementUnit().getShortName(),StockResponse::setMeasurementUnitShortName);
                    mapper.map(stock->stock.getProduct().getMeasurementUnit().getId(),StockResponse::setMeasurementUnitId);
                });

        modelMapper.typeMap(StockOutTransaction.class, StockTransactionResponse.class)
                .addMappings(mapper->{
                    mapper.map(stockOutTransaction->stockOutTransaction.getStock().getProduct().getId(),
                            StockTransactionResponse::setProductId
                    );
                    mapper.map(stockOutTransaction->stockOutTransaction.getStock().getProduct().getName(),
                            StockTransactionResponse::setProductName
                    );
                    mapper.map(stockOutTransaction->stockOutTransaction.getStock().getWarehouse().getId(),
                            StockTransactionResponse::setWarehouseId
                            );
                    mapper.map(stockOutTransaction->stockOutTransaction.getStock().getWarehouse().getName(),
                            StockTransactionResponse::setWarehouseName
                    );
                    mapper.map(stockOutTransaction -> stockOutTransaction.getStock().getProduct().getMeasurementUnit().getShortName(),
                            StockTransactionResponse::setMeasurementUnitShortName
                            );
                });

        modelMapper.typeMap(StockInTransaction.class, StockTransactionResponse.class)
                .addMappings(mapper->{
                    mapper.map(stockInTransaction->stockInTransaction.getStock().getProduct().getId(),
                            StockTransactionResponse::setProductId
                    );
                    mapper.map(stockInTransaction->stockInTransaction.getStock().getProduct().getName(),
                            StockTransactionResponse::setProductName
                    );
                    mapper.map(stockInTransaction->stockInTransaction.getStock().getWarehouse().getId(),
                            StockTransactionResponse::setWarehouseId
                    );
                    mapper.map(stockInTransaction->stockInTransaction.getStock().getWarehouse().getName(),
                            StockTransactionResponse::setWarehouseName
                    );
                    mapper.map(stockInTransaction -> stockInTransaction.getStock().getProduct().getMeasurementUnit().getShortName(),
                            StockTransactionResponse::setMeasurementUnitShortName);
                });



        return modelMapper;
    }


}
