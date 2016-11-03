package org.search.flight.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
	
	private final String cvsSplitBy = ",";
	private BufferedReader br = null;
	private String[] splitedLine;
	
    public CSVReader (String csvFile) {

        try {
        	br = new BufferedReader(new FileReader(csvFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

	public boolean extractLine()  {

		try{
			String line;
			while ((line = br.readLine()) != null) {
	
			    // use comma as separator
			    splitedLine = line.split(cvsSplitBy);
			    return true;
			    
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * @return the splitedLine
	 */
	public String[] getSplitedLine() {
		return splitedLine;
	}

}
