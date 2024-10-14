package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {

		this.gpspoints = new GPSPoint[n]; 
		this.antall = 0; 
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;
		
		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			inserted = true;
		}
		return inserted;
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		String[] dateTimeParts = time.split("T");
        String[] timeParts = dateTimeParts[1].split(":");
        int insertTime = Integer.parseInt(timeParts[0]) * 3600 + Integer.parseInt(timeParts[1]) * 60 + Integer.parseInt(timeParts[2].substring(0, 2));
        
        double insertLatitude = Double.parseDouble(latitude); 
        double insertLongitude = Double.parseDouble(longitude); 
        double insertElevation = Double.parseDouble(elevation);
        
        GPSPoint gpspoint = new GPSPoint(insertTime, insertLatitude, insertLongitude, insertElevation);
        return insertGPS(gpspoint);
	}

	public void print() {

        
        for (int i = 0; i < antall; i++) {
            System.out.println(gpspoints[i].toString());
        }
       
    }
	}
