package com.example.demo.dao;

import java.util.List;


import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.entity.Recipe;



@Dao
@ConfigAutowireable
public interface RecipeDao {

	    @Select
	    Recipe findOne(int id);

	    @Select
	    List<Recipe> findAll();

	    @Insert
	    int insert(Recipe recipe);

	    @Update
	    int update(Recipe recipe);

}


