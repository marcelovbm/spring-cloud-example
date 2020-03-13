package com.example.restfull.websrevices.restfullwebservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.restfull.websrevices.restfullwebservices.dao.UserDao;
import com.example.restfull.websrevices.restfullwebservices.exception.UserNotFoundException;
import com.example.restfull.websrevices.restfullwebservices.model.UserModel;

@RestController
@RequestMapping(path = "/users")
public class UsersController {

	@Autowired
	private UserDao userDao = new UserDao();

	/**
	 * This method return to the client all users that are saved in the db.
	 * 
	 * @return List<UserBean>
	 */
	@GetMapping(produces = "application/json")
	public List<UserModel> returnAllUsers() {
		return userDao.findAll();
	}

	/**
	 * This method allow a client to insert a new user in the db.
	 * 
	 * @param user User that is going to be saved in the db.
	 * @return ResponseEntity
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> insertUser(@RequestBody UserModel user) {
		UserModel userSaved = userDao.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userSaved.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
		boolean removed = userDao.deleteUser(id);
		
		if (!removed)
			throw new UserNotFoundException("User not found with id " + id);

		return ResponseEntity.noContent().build();
	}
	
	/**
	 * This method return to the client the User that is represented by the Id send
	 * in the request.
	 * 
	 * @param id Identification of the user.
	 * @return {@link UserBean}
	 */
	@GetMapping(path = "/{id}", produces = "application/json")
	public UserModel returnUser(@PathVariable Integer id) {
		UserModel userBean = userDao.findUser(id);

		if (userBean == null)
			throw new UserNotFoundException("User not found with id " + id);

		return userBean;
	}

}
