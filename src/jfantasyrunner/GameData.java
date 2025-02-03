package jfantasyrunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class GameData {
	
	
	String pointsRecord="";
	String filepath="src/scrollGame/resources/Data/PointsRecord.txt";
	File dataFile;	
	int pointsRecordInt;

	public GameData() {
		
			try(BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("resources/Data/PointsRecord.txt")))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		     pointsRecord = sb.toString();
		     dataFile = new File(filepath);
		     pointsRecordInt = Integer.valueOf(pointsRecord.trim());
			
			}
		
			catch (IOException x) {
		    System.err.println(x);
		}
	

	} // end constructor

	
	public String getPointsRecord() {
		return pointsRecord;
	}
	
	public int getPointsRecordInt() {
		return pointsRecordInt;
		
	}
	
	public void updatePointsRecord(int record) {
		
		pointsRecordInt=record;
		String recordToString = String.valueOf(record);
		try {
			PrintWriter writer = new PrintWriter(dataFile);
			writer.print(recordToString);
			writer.close();
		} catch (IOException x) {
			System.err.println(x);
		}

	} // end method

} // end class
