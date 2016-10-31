package org.search.flight.model;

import java.math.BigDecimal;

public class Airline {
	
	private String IATACode;
	private String name;
	private BigDecimal  infantPrice;
	
	public Airline(String iATACode, String name, BigDecimal infantPrice) {
		super();
		IATACode = iATACode;
		this.name = name;
		this.infantPrice = infantPrice;
	}
	/**
	 * @return the iATACode
	 */
	public String getIATACode() {
		return IATACode;
	}
	/**
	 * @param iATACode the iATACode to set
	 */
	public void setIATACode(String iATACode) {
		IATACode = iATACode;
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
	 * @return the infantPrice
	 */
	public BigDecimal getInfantPrice() {
		return infantPrice;
	}
	/**
	 * @param infantPrice the infantPrice to set
	 */
	public void setInfantPrice(BigDecimal infantPrice) {
		this.infantPrice = infantPrice;
	}
	
	

}
