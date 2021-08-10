package com.train.ticket;


public class Passengers implements Comparable<Passengers>{
	
	private String name;
	private int age;
	private char gender;
	
	public Passengers() {
		super();
	}
	
	public Passengers(String name, int age, char gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}
	
	@Override
	public int compareTo(Passengers p) {
		return this.getAge() - p.getAge();
	}
}

	
	
	
	
	
	
	


