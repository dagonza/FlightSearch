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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IATACode == null) ? 0 : IATACode.hashCode());
		result = prime * result + ((infantPrice == null) ? 0 : infantPrice.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Airline other = (Airline) obj;
		if (IATACode == null) {
			if (other.IATACode != null)
				return false;
		} else if (!IATACode.equals(other.IATACode))
			return false;
		if (infantPrice == null) {
			if (other.infantPrice != null)
				return false;
		} else if (!infantPrice.equals(other.infantPrice))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Airline [IATACode=" + IATACode + ", name=" + name + ", infantPrice=" + infantPrice + "]";
	}
	
	
	
	
	

}
