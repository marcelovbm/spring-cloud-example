package com.example.restfull.websrevices.restfullwebservices.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.restfull.websrevices.restfullwebservices.model.UserModel;

@Component
public class UserDao {

	private static List<UserModel> users = new ArrayList<>();
	private int usersCount = 3;
	private static String dateFormat = "dd/mm/yyyy";

	static {
		try {
			users.add(new UserModel(1, "Marcelo Magrinelli", new SimpleDateFormat(dateFormat).parse("06/04/1992")));
			users.add(new UserModel(2, "Renata Rezende", new SimpleDateFormat(dateFormat).parse("18/04/1992")));
			users.add(new UserModel(3, "Neusa Magrinelli", new SimpleDateFormat(dateFormat).parse("08/09/1954")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is responsible to return all users that are saved in the db.
	 * 
	 * @return List<UserBean>
	 */
	public List<UserModel> findAll() {
		return users;
	}

	/**
	 * This method is responsible to return the UserBean that is represented by the
	 * id.
	 * 
	 * @param id Identification of a UserBean.
	 * @return UserBean
	 */
	public UserModel findUser(Integer id) {
		return users.stream().filter(user -> id.equals(user.getId())).findAny().orElse(null);
	}

	/*
	 * This method is responsible to save the UserBean in the db.
	 */
	public UserModel save(UserModel user) {
		if (user.getId() == null)
			user.setId(++usersCount);

		users.add(user);
		return user;
	}

	public boolean deleteUser(Integer id) {

		return users.removeIf(user -> id.equals(user.getId()));
	}
}
