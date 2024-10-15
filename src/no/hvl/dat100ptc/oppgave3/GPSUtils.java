package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;
		
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;
	}
		

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] lat = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length; i++) {
            lat[i] = gpspoints[i].getLatitude();  
        }

		return lat;
		
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		

		double[] lon = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length; i++) {
            lon[i] = gpspoints[i].getLongitude();  
        }

		return lon;

	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		
		double latitude1 = Math.toRadians(gpspoint1.getLatitude());
        double longitude1 = Math.toRadians(gpspoint1.getLongitude());
        double latitude2 = Math.toRadians(gpspoint2.getLatitude());
        double longitude2 = Math.toRadians(gpspoint2.getLongitude());
		double deltaphi = latitude2 - latitude1;
        double deltadelta = longitude2 - longitude1;
		double a = compute_a(latitude1, latitude2, deltaphi, deltadelta);
		double c = compute_c(a);
		double d = R * c;
		return d;

	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
	
		double a = Math.sin(deltaphi / 2) * Math.sin(deltaphi / 2)
		+ Math.cos(phi1) * Math.cos(phi2) 
		* Math.sin(deltadelta / 2) * Math.sin(deltadelta / 2);
		return a;

	}

	private static double compute_c(double a) {

		return 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs1 = gpspoint1.getTime();
		int secs2 = gpspoint2.getTime();
		int timedif = secs2-secs1;
		double speed = round(distance(gpspoint1, gpspoint2)/timedif);
		
		return speed;

	}

	public static String formatTime(int secs) {
		int hours = secs / 3600;
		int minutes = (secs % 3600) / 60;
		int seconds = secs % 60;
		String TIMESEP = ":";
		String timestr = String.format("%02d%s%02d%s%02d", hours, TIMESEP, minutes, TIMESEP, seconds);
		
	  return String.format("%10s", timestr); 
		
	}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		double roundedValue = Math.round(d * 100.0) / 100.0;

    String formattedString = String.format("%.2f", roundedValue);

    return String.format("%10s", formattedString);
		
	}
}
