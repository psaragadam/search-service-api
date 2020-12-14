package com.search.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.search.domain.Model;

public interface SearchService {

	List<Model> getModelsBySearchText(String searchText);

	String createModel(Model model);

	void loadTestData() throws IOException;

}
