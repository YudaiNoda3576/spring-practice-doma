package com.example.demo.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class RecipeForm {

	
	private Integer id;
	
	@Size(min=1, max=255)
	@NotEmpty
	private String name;
	
	@PositiveOrZero
	@NotNull
	private Integer calorie;
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getCalorie() {
		return calorie;
	}


	public void setCalorie(Integer calorie) {
		this.calorie = calorie;
	}


}
