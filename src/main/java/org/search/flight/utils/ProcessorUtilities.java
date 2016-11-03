package org.search.flight.utils;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.search.flight.Application;
import org.search.flight.error.DateConversionException;

public class ProcessorUtilities {

	static final Logger LOG = Logger.getLogger(ProcessorUtilities.class);

	private static ProcessorUtilities instance;

	public static ProcessorUtilities getProcessorUtilities() {
		if (instance == null) {
			instance = new ProcessorUtilities();
		}

		return instance;
	}

	public int stringParseToInt(String strNumber) {

		int toReturn = 0;

		try {
			toReturn = Integer.parseInt(strNumber);
		} catch (Exception e) {
			LOG.warn("Integer was expected. Get: " + strNumber+"Returned 0 by Default");
		}

		return toReturn;
	}

	public LocalDate stringToLocalDate(String when) {

		LocalDate localDateToReturn = null;

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			localDateToReturn = LocalDate.parse(when, formatter);
		} catch (Exception e) {
			LOG.error("Error while trying to convert String to LocalDate. Expected format dd-mm-yyyy and get " + when,
					e);
			throw new DateConversionException();
		}

		return localDateToReturn;
	}
	
	public void importSampleData() {

		try {
			CSVReaderAirport cSVReaderAirport = new CSVReaderAirport(
					Application.class.getClassLoader().getResource("Airports.csv").toURI().getPath());
			cSVReaderAirport.importCSV();

			CSVReaderAirline cSVReaderAirline = new CSVReaderAirline(
					Application.class.getClassLoader().getResource("Airlines.csv").toURI().getPath());
			cSVReaderAirline.importCSV();

			CSVReaderFlight cSVReaderFlight = new CSVReaderFlight(
					Application.class.getClassLoader().getResource("Flights.csv").toURI().getPath());
			cSVReaderFlight.importCSV();

		} catch (URISyntaxException e) {
			LOG.error("ERROR while recovering .csv data", e);
		}
	}
}
