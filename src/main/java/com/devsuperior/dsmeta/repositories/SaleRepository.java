package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.projections.SaleSumaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleReportDTO(obj.id, obj.date, obj.amount, obj.seller.name) " +
            "From Sale obj " +
            "WHERE obj.date > :startDate and obj.date < :endDate " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) ")
    Page<SaleReportDTO> searchReport(LocalDate startDate, LocalDate endDate, String name, Pageable pageable);


    @Query(nativeQuery = true, value = "SELECT tb_seller.name, sum(tb_sales.amount) as total " +
            "FROM tb_sales " +
            "INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id " +
            "WHERE  tb_sales.date > :startDate and tb_sales.date < :endDate " +
            "and tb_seller.name LIKE UPPER(CONCAT('%', :name, '%')) "+
            "GROUP BY tb_seller.name;")
    List<SaleSumaryProjection> searchSumary(LocalDate startDate, LocalDate endDate, String name);
}
