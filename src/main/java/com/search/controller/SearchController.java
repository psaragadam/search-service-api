package com.search.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.search.domain.Model;
import com.search.service.SearchService;

@RestController
public class SearchController {

	private final SearchService service;

	@Autowired
	public SearchController(SearchService service) {
		this.service = service;
	}
	
	@GetMapping("/load")
	public void loadData() throws IOException {
		service.loadTestData();
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> getModelBySearchText(@RequestParam(required = false) String searchText){
		try {
			return new ResponseEntity<>(service.getModelsBySearchText(searchText),HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> getAllModel(@RequestBody Model model){
		try {
			return new ResponseEntity<>(service.createModel(model),HttpStatus.ACCEPTED);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
