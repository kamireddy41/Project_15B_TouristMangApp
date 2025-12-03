package com.telusko.TouristMangApp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.TouristMangApp.exception.TouristNotFoundException;
import com.telusko.TouristMangApp.model.Tourist;
import com.telusko.TouristMangApp.service.ITouristService;

@RestController
@RequestMapping("/api")
public class TouristController 
{
	@Autowired
	private ITouristService service;
	
	@PostMapping("/register")
	public ResponseEntity<String> addTouristInfo(@RequestBody Tourist tourist)
	{
		try
		{
			String status =service.registerTourist(tourist);
			return new ResponseEntity<String>(status, HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>("Some problem in registration", HttpStatus.INTERNAL_SERVER_ERROR);
 
		}
	}
	
	@GetMapping("/getalltourist")
	public ResponseEntity<?> getTouristsInfo()
	{
		try
		{
			List<Tourist> tourists = service.fetchAllTourist();
			return new ResponseEntity<>(tourists, HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>("Some problem in fteching records", HttpStatus.INTERNAL_SERVER_ERROR);
 
		}
	}
	
	@GetMapping("/gettourist/{id}")
	public ResponseEntity<?> getTouristInfo(@PathVariable("id")Integer id)
	{
		try
		{
			Tourist tourist = service.fetchTouristById(id);
			return new ResponseEntity<>(tourist, HttpStatus.OK);
		}
		catch(TouristNotFoundException t)
		{
			return new ResponseEntity<String>(t.getMessage(), HttpStatus.BAD_REQUEST);
 
		}
	}
	@PutMapping("/updatetourist")
	public ResponseEntity<?> updateTouristInfo(@RequestBody Tourist tourist)
	{
		try
		{
			String status=service.updateTouristData(tourist);
			return new ResponseEntity<>(status, HttpStatus.OK);
			
		}
		catch(TouristNotFoundException t)
		{
			return new ResponseEntity<String>(t.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}

}
