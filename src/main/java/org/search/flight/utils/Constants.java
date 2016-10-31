package org.search.flight.utils;

import java.math.BigDecimal;

public interface Constants {

	public final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;

	public final BigDecimal RATE_DISCOUNT_33_PERCENT = new BigDecimal(0.67);
	
	public final static BigDecimal RATE_TWO_DAYS_BEFORE = new BigDecimal(1.50);
	public final static BigDecimal RATE_FIFTEEN_DAYS_BEFORE = new BigDecimal(1.20);
	public static final BigDecimal RATE_THIRTY_DAYS_BEFORE = new BigDecimal(1);
	public static final BigDecimal RATE_MORE_THAN_MONTH = new BigDecimal(0.80);
	
	public static final Integer TWO_DAYS = new Integer(2);
	public static final Integer FIFTEEN_DAYS = new Integer(15);
	public static final Integer THIRTY_DAYS = new Integer(30);
}
