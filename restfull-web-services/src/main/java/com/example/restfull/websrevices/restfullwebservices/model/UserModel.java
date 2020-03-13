package com.example.restfull.websrevices.restfullwebservices.model;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class UserModel {

	private Integer id;
	
	@Size(min = 2, message = "Must have atleast 2 characters.")
	private String name;
	
	@Past(message = "Must be in the past.")
	private Date birthDate;

	public UserModel(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}
