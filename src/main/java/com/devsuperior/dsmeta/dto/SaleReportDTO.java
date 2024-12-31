package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

public class SaleReportDTO extends SaleMinDTO{

    private String sellerName;


    public SaleReportDTO(Long id, LocalDate date, Double amount, String sellerName) {
        super(id,amount, date);
        this.sellerName = sellerName;
    }

    public String getSellerName() {
        return sellerName;
    }
}
