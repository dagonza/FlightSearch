package org.search.flight;

import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.search.flight.utils.ProcessorUtilities;

public class Application {

	static final Logger LOG = Logger.getLogger(Application.class);

	public static void main(String[] args) {

		if (args.length != 6) {
			LOG.error("Usage requires six parameters");
		}else{

			ProcessorUtilities.getProcessorUtilities().importSampleData();
		
			int numberAdult = ProcessorUtilities.getProcessorUtilities().stringParseToInt(args[0]);
			int numberChild = ProcessorUtilities.getProcessorUtilities().stringParseToInt(args[1]);
			int numberInfant = ProcessorUtilities.getProcessorUtilities().stringParseToInt(args[2]);
			String idOriginAirport = args[3];
			String idDestinationAirport = args[4];
			LocalDate plannedDate = ProcessorUtilities.getProcessorUtilities().stringToLocalDate(args[5]);
		
			FlightSearchApp flightSearchApp = new FlightSearchApp();
			flightSearchApp.calculateTrips(numberAdult, numberChild, numberInfant, idOriginAirport, idDestinationAirport, plannedDate);
		}
	}

}
