package org.search.flight.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.search.flight.model.Discount;
import org.search.flight.model.Flight;
import org.search.flight.model.Trip;
import org.search.flight.model.passengers.Passenger;
import org.search.flight.utils.Constants;

public class PriceCalculatorService {
	
	static final Logger LOG = Logger.getLogger(PriceCalculatorService.class);
	
	public enum RateByDay {

		RATE_TWO_DAYS_BEFORE(2,Constants.RATE_TWO_DAYS_BEFORE),
		RATE_FIFTEEN_DAYS_BEFORE(15,Constants.RATE_FIFTEEN_DAYS_BEFORE),
		RATE_THIRTY_DAYS_BEFORE(30,Constants.RATE_THIRTY_DAYS_BEFORE),
		RATE_MORE_THAN_THIRTY_DAYS(Long.MAX_VALUE,Constants.RATE_MORE_THAN_THIRTY_DAYS);

		private final long daysBefore;
		private final BigDecimal rateDiscount;
		
		RateByDay(long daysBefore, BigDecimal rateDiscount) {
			this.rateDiscount = rateDiscount;
			this.daysBefore = daysBefore;
		}

		public BigDecimal getRateDiscount(){
			return rateDiscount;
		}

		public static RateByDay getRateByDaysToDeparture(long daysToDeparture){
			return Stream.of(values()).filter(rateByDay -> rateByDay.daysBefore >= daysToDeparture).
					sorted(Comparator.comparing(RateByDay::getDaysBefore)).findFirst().orElse(RATE_MORE_THAN_THIRTY_DAYS);
		}

		public long getDaysBefore() {
			return daysBefore;
		}
	}


	private void calculateFinalPriceInPercentage(Passenger passenger, BigDecimal percentage) {
		
		LOG.info("Apply Rate: " + percentage.setScale(2, BigDecimal.ROUND_HALF_DOWN)+ "%");
		
		BigDecimal finalPrice = passenger.getPrice().multiply(percentage);
		
		passenger.setPrice(finalPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN));
	}

	
	private long calculateDaysBeforeDeparture(LocalDate plannedDate){
		return LocalDate.now().until(plannedDate, ChronoUnit.DAYS);
	}

	
	private void applyDateRate(Passenger passenger, BigDecimal rateByDate){
		
		calculateFinalPriceInPercentage(passenger,rateByDate);
	}
	
	
	/**
	 * 
	 * @param plannedDate planned Date of flight
	 * @return rate of that day
	 */
	private BigDecimal calculateRateByDate(LocalDate plannedDate){
		
		long daysBeforeDeparture = calculateDaysBeforeDeparture(plannedDate);
		LOG.info("Days Before Departure: "+ daysBeforeDeparture);
		
		return RateByDay.getRateByDaysToDeparture(daysBeforeDeparture).getRateDiscount();
	}
	
	/**
	 * Main function for calculate Trip price
	 * @param trip
	 */
	public Trip calculatePrice(Trip trip) {

		BigDecimal rate = calculateRateByDate(trip.getPlannedDate());
		
		LOG.info("Apply Date Rate: " + rate.setScale(2, BigDecimal.ROUND_HALF_DOWN)+ "%");
		
		trip.getPassengersList().forEach(passenger->applyRates(passenger,trip.getFlight(),rate));
		return trip;
	}
	
	private void applyRates(Passenger passenger, Flight flight, BigDecimal rateByDay){
		
		LOG.info("---");
		LOG.info("I'm " +passenger.getClass().getSimpleName());
		
		passenger.setPrice(flight.getBasePrice());
		
		LOG.info("Original Price " + passenger.getFormatPrice());
		
		applyDateRate(passenger,rateByDay);

		for(Discount discount:passenger.getTarifas()){
			
			boolean notCombinable = false;
			
			switch(discount){
			
				case DISCOUNT_PLUS_33_PERCENT:
					calculateFinalPriceInPercentage(passenger, Constants.RATE_DISCOUNT_33_PERCENT);
					break;
				case INFANT_PRICE:
					passenger.setPrice(flight.getAirline().getInfantPrice());
					notCombinable = true;
					break;
				default:
							
			}
			
			if(notCombinable){
				break;
			}
		}
		
		LOG.info("Final Price " + passenger.getFormatPrice());
	}
}
