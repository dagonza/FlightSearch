package org.search.flight.model;

public class Airport {

	private String IATACode;
	private String city;
	
	public Airport(String iATACode, String city) {
		super();
		IATACode = iATACode;
		this.city = city;
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
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return IATACode;
	}
	
	
	
}
