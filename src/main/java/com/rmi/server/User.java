package com.rmi.server;

import java.io.Serializable;

public class User implements Serializable {


	private static final long serialVersionUID = 7639910865993266701L;
	private String name;
	private int id;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
