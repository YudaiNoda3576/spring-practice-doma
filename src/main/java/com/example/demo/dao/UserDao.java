package com.example.demo.dao;

import java.util.List;


import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.entity.User;


@Dao
@ConfigAutowireable
public interface UserDao {
	 	@Select
	    User find(String id);

	    @Select
	    List<User> findALl();

	    @Insert
	    int insert(User user);

	    @Update
	    int update(User user);
	
}
