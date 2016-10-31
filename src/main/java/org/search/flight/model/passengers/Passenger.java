package org.search.flight.model.passengers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.search.flight.model.Discount;

public abstract class Passenger {

	
	private List<Discount> tarifas = new ArrayList<Discount>(0);
	
	private String name;
	private String gender;
	private int age;
	private BigDecimal price = new BigDecimal(0);
	
	public Passenger(String name, String gender, int age) {
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	
	public Passenger(){
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

	public List<Discount> getTarifas() {
		return tarifas;
	}

	public void setTarifas(List<Discount> tarifas) {
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
	
	public String getFormatPrice() {
		return price.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString() + "€";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((tarifas == null) ? 0 : tarifas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passenger other = (Passenger) obj;
		if (age != other.age)
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (tarifas == null) {
			if (other.tarifas != null)
				return false;
		} else if (!tarifas.equals(other.tarifas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Passenger [tarifas=" + tarifas + ", name=" + name + ", gender=" + gender + ", age=" + age + ", price="
				+ price + "]";
	}
	
}
