package org.search.flight.model;

public class Airport {

	private String IATACode;
	private String city;

	public Airport(String iATACode, String city) {
		IATACode = iATACode;
		this.city = city;
	}
	
	public Airport(){
		IATACode="";
		city="";
	}

	/**
	 * @return the iATACode
	 */
	public String getIATACode() {
		return IATACode;
	}

	/**
	 * @param iATACode
	 *            the iATACode to set
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
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return IATACode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IATACode == null) ? 0 : IATACode.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
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
		Airport other = (Airport) obj;
		if (IATACode == null) {
			if (other.IATACode != null)
				return false;
		} else if (!IATACode.equals(other.IATACode))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		return true;
	}

}
