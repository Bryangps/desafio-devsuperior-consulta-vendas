package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSumaryDTO;
import com.devsuperior.dsmeta.projections.SaleSumaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> getReport(String minDate, String maxDate, String name, Pageable pageable){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate startDate;
		LocalDate endDate;

		if (maxDate.isEmpty()){
			endDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		else {
			endDate = LocalDate.parse(maxDate, formatter);
		}

		if (minDate.isEmpty()){
			startDate = endDate.minusYears(1L);
		}
		else {
			startDate = LocalDate.parse(minDate, formatter);
		}

		Page<SaleReportDTO> result = repository.searchReport(startDate, endDate, name, pageable);

		return result;
	}


	public List<SaleSumaryDTO> getSumary(String minDate, String maxDate, String name){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate startDate;
		LocalDate endDate;

		if (maxDate.isEmpty()){
			endDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		else {
			endDate = LocalDate.parse(maxDate, formatter);
		}

		if (minDate.isEmpty()){
			startDate = endDate.minusYears(1L);
		}
		else {
			startDate = LocalDate.parse(minDate, formatter);
		}

		List<SaleSumaryProjection> listSale = repository.searchSumary(startDate, endDate, name);
		List<SaleSumaryDTO> result = listSale.stream().map(x -> new SaleSumaryDTO(x)).collect(Collectors.toList());

		return result;
	}

}
