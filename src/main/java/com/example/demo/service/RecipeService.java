package com.example.demo.service;

import java.util.List;


import org.seasar.doma.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.RecipeDao;
import com.example.demo.entity.Recipe;

@Service
public class RecipeService {

	private final RecipeDao dao;
	
	@Autowired
	public RecipeService(RecipeDao dao) {
		this.dao = dao;
	}
	
	public List<Recipe> findAll() {
		return dao.findAll();
	}

	@Transactional
	public Recipe create(Recipe recipe) {
		dao.insert(recipe);
		return recipe;
	}
	
	@Transactional
	public Recipe update(Recipe recipe) {
		dao.update(recipe);
		return recipe;
	}

	public Recipe findOne(int id) {
		return dao.findOne(id);
	}
	

	
	
}
