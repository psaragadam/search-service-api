package com.search.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.search.domain.Model;
import com.search.repo.ModelRepository;
import com.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{

	private final ModelRepository repo;

	public SearchServiceImpl(ModelRepository repo) {
		this.repo = repo;
	}

	@Override
	public List<Model> getModelsBySearchText(String searchText) {
		if(StringUtils.isEmpty(searchText)) {
			return repo.findAll();
		}
		 List<Model> response= repo.findByNameStartsWith(searchText);
		 System.out.println("****************"+response.size());
		 return response;
	}

	@Override
	public String createModel(Model model) {
		repo.save(model);
		return "Model data inserted into DB successfully!";
	}

	@Override
	public void loadTestData() throws IOException {
		List<Model> models=new ArrayList<Model>();
		String file ="src/main/resources/testData.txt";
		Scanner scanner = new Scanner(new File(file));
		while(scanner.hasNext()) {
			models.add(new Model(scanner.next()));
		}
		System.out.println("#############"+ models.size());
	    scanner.close();
	    if(!models.isEmpty()) {
	    	repo.saveAll(models);
	    }		
	}
	
	
}
