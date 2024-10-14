package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;

public class Main {

	
	public static void main(String[] args) {
			GPSPoint point1 = new GPSPoint(1628784000, 60.39299, 5.32415, 30.0); 
			GPSPoint point2 = new GPSPoint(1628784060, 60.39300, 5.32420, 25.0); 
			GPSData gpsData = new GPSData(2);
			gpsData.insertGPS(point1);
			gpsData.insertGPS(point2);
			gpsData.print();
	}
}
