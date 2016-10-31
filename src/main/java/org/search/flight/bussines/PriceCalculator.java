package org.search.flight.bussines;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.search.flight.model.Discount;
import org.search.flight.model.Flight;
import org.search.flight.model.Trip;
import org.search.flight.model.passengers.Passenger;
import org.search.flight.utils.Constants;

public class PriceCalculator {
	
	private Integer daysBeforeDeparture;
	private BigDecimal rateByDate;

	
	private void calculateFinalPriceInPercentage(Passenger passenger, BigDecimal percentage) {
		
		BigDecimal finalPrice = passenger.getPrice().multiply(percentage);
		
		passenger.setPrice(finalPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN));
		
	}

	private void calculateDaysBeforeDeparture(Date plannedDate){
		
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0); 
		
		try{
			daysBeforeDeparture = (int) ((long) (plannedDate.getTime() - today.getTime().getTime())/Constants.MILLSECS_PER_DAY);
		}catch(Exception e){
			daysBeforeDeparture = Integer.MAX_VALUE;
		}
	}

	
//	public BigDecimal getDiscountByDate(Date plannedDate){
//		
//		Calendar threeDaysBefore = Calendar.getInstance();
//		threeDaysBefore.add(Calendar.DAY_OF_MONTH, 3);
//		
//		Calendar sixteenDaysBefore = Calendar.getInstance();
//		sixteenDaysBefore.add(Calendar.DAY_OF_MONTH, 16);
//		
//		Calendar thirtyOneDaysBefore = Calendar.getInstance();
//		thirtyOneDaysBefore.add(Calendar.DAY_OF_MONTH, 31);
//		
//		if(plannedDate.before(threeDaysBefore.getTime())){
//			return new BigDecimal(150);
//		}else if(plannedDate.before(sixteenDaysBefore.getTime())){
//			return new BigDecimal(120);
//		}else if(plannedDate.before(thirtyOneDaysBefore.getTime())){
//			return new BigDecimal(100);
//		}else{
//			return new BigDecimal(80);
//		}
//		
//	}
	
	private void applyDateRate(Passenger passenger){
		
		calculateFinalPriceInPercentage(passenger,rateByDate);
	}
	
	/**
	 * 
	 * @param plannedDate planned Date of flight
	 * @return rate of that day
	 */
	private void calculateRateByDate(Date plannedDate){
		
		calculateDaysBeforeDeparture(plannedDate);
		
		if(daysBeforeDeparture <= Constants.TWO_DAYS){
			rateByDate = Constants.RATE_TWO_DAYS_BEFORE;
			
		}else if(daysBeforeDeparture <= Constants.FIFTEEN_DAYS){
			rateByDate =  Constants.RATE_FIFTEEN_DAYS_BEFORE;
			
		}else if(daysBeforeDeparture <= Constants.THIRTY_DAYS){
			rateByDate = Constants.RATE_THIRTY_DAYS_BEFORE;
			
		}else{
			rateByDate = Constants.RATE_MORE_THAN_MONTH;
		}
	}
	
	/**
	 * Main function for calculate Trip price
	 * @param trip
	 */
	public void caculatePrice(Trip trip) {
		
		calculateRateByDate(trip.getPlannedDate());
		
		for(Passenger passenger : trip.getPassengersList()){
			
			applyRates(passenger, trip.getFlight());
		}
	}
	
	private void applyRates(Passenger passenger, Flight flight){
		
		passenger.setPrice(flight.getBasePrice());
		
		System.out.println("Original Price " + passenger.getPrice());
		
		applyDateRate(passenger);
		
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
		
		System.out.println("Final Price " + passenger.getPrice());
	}
}
