package org.search.flight.utils;

import java.math.BigDecimal;

public interface Constants {

	public final BigDecimal RATE_DISCOUNT_33_PERCENT = new BigDecimal(0.67);
	
	public final static BigDecimal RATE_TWO_DAYS_BEFORE = new BigDecimal(1.50);
	public final static BigDecimal RATE_FIFTEEN_DAYS_BEFORE = new BigDecimal(1.20);
	public static final BigDecimal RATE_THIRTY_DAYS_BEFORE = new BigDecimal(1);
	public static final BigDecimal RATE_MORE_THAN_THIRTY_DAYS = new BigDecimal(0.80);
}
