package org.search.flight.model.passengers;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.search.flight.model.Discount;

public abstract class Passenger {

	
	private ArrayList<Discount> tarifas;
	
	private String name;
	private String gender;
	private int age;
	
	private BigDecimal price = new BigDecimal(0);
	
	public Passenger(String name, String gender, int age) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		tarifas = new ArrayList<Discount>(0);
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	public ArrayList<Discount> getTarifas() {
		return tarifas;
	}

	public void setTarifas(ArrayList<Discount> tarifas) {
		this.tarifas = tarifas;
	}
	
	public void addTarifa(Discount discount){
		this.tarifas.add(discount);
	}


	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
