package org.search.flight.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.stream.Stream;

import org.search.flight.utils.Constants;

public class CalculateRates {

	public enum RateByDay {

		RATE_TWO_DAYS_BEFORE(2, Constants.RATE_TWO_DAYS_BEFORE), 
		RATE_FIFTEEN_DAYS_BEFORE(15, Constants.RATE_FIFTEEN_DAYS_BEFORE),
		RATE_THIRTY_DAYS_BEFORE(30, Constants.RATE_THIRTY_DAYS_BEFORE),
		RATE_MORE_THAN_THIRTY_DAYS(Long.MAX_VALUE, Constants.RATE_MORE_THAN_THIRTY_DAYS);

		private final long daysBefore;
		private final BigDecimal rateDiscount;

		RateByDay(long daysBefore, BigDecimal rateDiscount) {
			this.rateDiscount = rateDiscount;
			this.daysBefore = daysBefore;
		}

		public BigDecimal getRateDiscount() {
			return rateDiscount;
		}

		public static RateByDay getRateByDaysToDeparture(long daysToDeparture) {
			return Stream.of(values()).filter(rateByDay -> rateByDay.daysBefore >= daysToDeparture)
					.sorted(Comparator.comparing(RateByDay::getDaysBefore)).findFirst()
					.orElse(RATE_MORE_THAN_THIRTY_DAYS);
		}

		public long getDaysBefore() {
			return daysBefore;
		}
	}
	
	public BigDecimal calculateRateByDate(LocalDate plannedDate) {

		long daysBeforeDeparture = calculateDaysBeforeDeparture(plannedDate);
		return RateByDay.getRateByDaysToDeparture(daysBeforeDeparture).getRateDiscount();
	}
	
	public long calculateDaysBeforeDeparture(LocalDate plannedDate) {
		return LocalDate.now().until(plannedDate, ChronoUnit.DAYS);
	}
	
	public BigDecimal calculateFinalPriceInPercentage(BigDecimal price, BigDecimal percentage) {
		BigDecimal finalPrice = price.multiply(percentage);
		return finalPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
}
