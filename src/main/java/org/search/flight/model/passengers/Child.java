package org.search.flight.model.passengers;

import org.search.flight.model.Discount;

public class Child extends Passenger {

	public Child(String name, String gender, int age) {
		super(name, gender, age);
		addTarifa(Discount.DISCOUNT_PLUS_33_PERCENT);
	}

	public Child() {
		super();
		addTarifa(Discount.DISCOUNT_PLUS_33_PERCENT);
	}

}
