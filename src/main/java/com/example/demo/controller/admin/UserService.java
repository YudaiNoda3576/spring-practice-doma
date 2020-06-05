package com.example.demo.controller.admin;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 public com.example.demo.entity.User findBy(String id) {
	        return userDao.find(id);
	    }

	    public List<com.example.demo.entity.User> findAll() {
	        return this.userDao.findALl();
	    }

//	    生の状態のパスワードをここで暗号化している
	    @Transactional
	    public User create(User user) {
	        User newUser = new User();
	        newUser.setLoginId(user.getLoginId());
	        newUser.setName(user.getName());
	        newUser.setEmail(user.getEmail());
	        newUser.setRole(user.getRole());
//	        ここに注目
	        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
	        userDao.insert(newUser);
	        return user;
	    }

	    @Transactional
	    public User update(User user) {
	        userDao.update(user);
	        return user;
	    }

}
