package com.challengebp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challengebp.dto.ReportDTO;
import com.challengebp.services.IMovementService;


/**
 * @author David Mogrovejo
 */
@RestController
@RequestMapping(path = "/api/reportes/")
public class ReportsController {

	@Autowired
	private IMovementService movementService; 

	
	@GetMapping(path = "")	
	public ResponseEntity<List<ReportDTO>> getAll(@RequestParam String fechaDesde, @RequestParam String fechaHasta) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date fromDate;
		try {
			fromDate = format.parse(fechaDesde);
			Date toDate = format.parse(fechaHasta);
			
			
			return new ResponseEntity<>(movementService.getMovements(fromDate, toDate), HttpStatus.OK);
		} catch (ParseException e) {
			throw new Exception("El formato de Fechas es Incorrecto Intente con 'dd-MM-yyyy'");
		}
	}
	
	
}
