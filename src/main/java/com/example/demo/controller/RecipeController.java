package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.demo.entity.Recipe;
import com.example.demo.form.RecipeForm;
import com.example.demo.service.RecipeService;

@Controller
@RequestMapping("/")
public class RecipeController {

	private final RecipeService recipeService;
	
	@Autowired
	public RecipeController(RecipeService recipeService) {
	  this.recipeService = recipeService;
	}
	
	@GetMapping("/recipe")
	public String list(Model model) {
		
		List<Recipe> recipes = recipeService.findAll();
		model.addAttribute("recipes", recipes);
		
		return "recipe/recipe.html";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("recipeForm", new RecipeForm());
		return "recipe/create.html";
	}
	
	@PostMapping("/create")
	public String create(@Validated RecipeForm recipeForm, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return"recipe/recipe.html";
		}
//		Formの中身をEntityに詰め直す
		Recipe recipe = new Recipe();	
		recipe.setName(recipeForm.getName());
		recipe.setCalorie(recipeForm.getCalorie());
		Recipe createRecipe = recipeService.create(recipe);
		
		return"redirect:/update" + createRecipe.getId();	
	}
	
	
}
