package org.search.flight.model.passengers;

import org.search.flight.model.Discount;

public class Child extends Passenger {

	public Child(String name, String gender, int age) {
		super(name, gender, age);
//		this.setDISCOUNT_TO_BASE_PRICE(new BigDecimal("0.67"));
		addTarifa(Discount.DISCOUNT_PLUS_33_PERCENT);
	}

}
