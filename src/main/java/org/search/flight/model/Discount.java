package org.search.flight.model;

public enum Discount {

	DISCOUNT_PLUS_33_PERCENT(true), // 33% discount of the price calculated
	INFANT_PRICE(false), // only pay the infant price, set by airline
	DISABILITY_33(true),
	LARGE_FAMILY(true);
	
	private boolean isCombinable;
	
	private Discount(boolean isCombinable) {
		this.setCombinable(isCombinable);
	}

	public boolean isCombinable() {
		return isCombinable;
	}

	public void setCombinable(boolean isCombinable) {
		this.isCombinable = isCombinable;
	}
	
	
}
