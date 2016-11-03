package org.search.flight.service;

import java.math.BigDecimal;
import org.apache.log4j.Logger;
import org.search.flight.model.Discount;
import org.search.flight.model.Flight;
import org.search.flight.model.Trip;
import org.search.flight.model.passengers.Passenger;
import org.search.flight.utils.Constants;

public class PriceCalculatorService {

	static final Logger LOG = Logger.getLogger(PriceCalculatorService.class);
	private CalculateRates calculateRates;

	public PriceCalculatorService() {
		calculateRates = new CalculateRates();
	}

	private void applyRate(Passenger passenger, BigDecimal rateByDate) {
		BigDecimal newPrice = calculateRates.calculateFinalPriceInPercentage(passenger.getPrice(), rateByDate);
		passenger.setPrice(newPrice);
	}

	/**
	 * Main function for calculate Trip price
	 * 
	 * @param trip
	 */
	public Trip calculatePrice(Trip trip) {

		BigDecimal rate = calculateRates.calculateRateByDate(trip.getPlannedDate());
		trip.getPassengersList().forEach(passenger -> applyPriceRates(passenger, trip.getFlight(), rate));
		trip.calculateTripTotalPrice();

		return trip;
	}

	private void applyPriceRates(Passenger passenger, Flight flight, BigDecimal rateByDay) {

		passenger.setPrice(flight.getBasePrice());
		applyRate(passenger, rateByDay);
		passenger = applyDiscountPassenger(passenger, flight);
	}

	private Passenger applyDiscountPassenger(Passenger passenger, Flight flight) {

		for (Discount discount : passenger.getDiscountList()) {

			switch (discount) {

			case DISCOUNT_PLUS_33_PERCENT:
				applyRate(passenger, Constants.RATE_DISCOUNT_33_PERCENT);
				break;
			case INFANT_PRICE:
				passenger.setPrice(flight.getAirline().getInfantPrice());
				break;
			case DISABILITY_33:
				break;
			case LARGE_FAMILY:
				break;
			default:
				break;
			}

			if (!discount.isCombinable()) {
				return passenger;
			}
		}

		return passenger;
	}
}
