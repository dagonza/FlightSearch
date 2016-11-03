package org.search.flight.service;

import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

public class CalculateRatesTest {

	private CalculateRates victim = new CalculateRates();
	private LocalDate plannedDate;
	private BigDecimal rateByDate;
	private long daysBeforeDeparture;
	private BigDecimal price;
	private BigDecimal finalPrice;

	@Test
	public final void givenToday_whenCalculateRateDate_thenGetRateBefore2Days() {

		givenDay(0);
		whenCalculateRateByDate();
		thenGetRateIsEquals("1.50");
	}

	@Test
	public final void givenFourDaysTomorrow_whenCalculateRateDate_thenGetRateBefore15Days() {

		givenDay(4);
		whenCalculateRateByDate();
		thenGetRateIsEquals("1.20");
	}

	@Test
	public final void givenTwentyDaysTomorrow_whenCalculateRateDate_thenGetRateBefore30Days() {

		givenDay(20);
		whenCalculateRateByDate();
		thenGetRateIsEquals("1.00");
	}

	@Test
	public final void given40DaysTomorrow_whenCalculateRateDate_thenGetRateMoreThanAMonth() {

		givenDay(40);
		whenCalculateRateByDate();
		thenGetRateIsEquals("0.80");
	}

	@Test
	public final void givenTwoDaysTomorrow_whenCalculateDaysBeforeDeparture_thenGetDays() {

		givenDay(2);
		whenCalculateDaysBeforeDeparture();
		thenGetDaysBeforeDeparture(2);
	}

	@Test
	public final void givenSeventyDaysTomorrow_whenCalculateDaysBeforeDeparture_thenGetDays() {

		givenDay(70);
		whenCalculateDaysBeforeDeparture();
		thenGetDaysBeforeDeparture(70);
	}

	@Test
	public final void givenPrice100_whenCalculateFinalPriceInPercentage1_50_thenGetPrice() {

		givenPrice("100");
		whenCalculateFinalPriceInPercentage("1.5");
		thenGetPrice("150.00");
	}

	@Test
	public final void givenPrice100_whenCalculateFinalPriceInPercentage1_20_thenGetPrice() {

		givenPrice("100");
		whenCalculateFinalPriceInPercentage("1.2");
		thenGetPrice("120.00");
	}

	private void givenPrice(String price) {
		this.price = new BigDecimal(price);

	}

	private void whenCalculateFinalPriceInPercentage(String percentage) {
		finalPrice = victim.calculateFinalPriceInPercentage(price, new BigDecimal(percentage));
	}

	private void thenGetPrice(String price){
		assertThat(finalPrice, closeTo(new BigDecimal(price), new BigDecimal("0.01")));
	}
	
	private void whenCalculateDaysBeforeDeparture() {
		daysBeforeDeparture = victim.calculateDaysBeforeDeparture(plannedDate);

	}

	private void thenGetDaysBeforeDeparture(long days) {
		assertEquals(daysBeforeDeparture, days);

	}

	private void givenDay(long days) {
		plannedDate = LocalDate.now().plusDays(days);

	}

	private void whenCalculateRateByDate() {
		rateByDate = victim.calculateRateByDate(plannedDate);
	}

	private void thenGetRateIsEquals(String price) {
		assertThat(rateByDate, closeTo(new BigDecimal(price), new BigDecimal("0.1")));
	}

}
