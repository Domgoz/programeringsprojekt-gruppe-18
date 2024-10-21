package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import static java.lang.Double.*;
import java.lang.reflect.Method;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 600;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;
	
	private int YBASE;
	
	private int[] PATHC = {0, 200, 0};
	
		public ShowRoute() {
	
			String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
			gpscomputer = new GPSComputer(filename);
	
			gpspoints = gpscomputer.getGPSPoints();
	
		}
	
		public static void main(String[] args) {
			launch(args);
		}
	
		public void run() {
	
			makeWindow("Route", MAPXSIZE + 2*MARGIN, MAPYSIZE + 2*MARGIN);
	
			minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
			minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
	
			maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
			maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
			
			xstep = scale(MAPXSIZE, minlon, maxlon);
			ystep = scale(MAPYSIZE, minlat, maxlat);
			
			YBASE = MARGIN + MAPYSIZE;
			
			showRouteMap();
	
			//replayRoute();
			
			drawErasePath();
			
			showStatistics();
		}
	
		public double scale(int maxsize, double minval, double maxval) {
	
			double step = maxsize / (Math.abs(maxval - minval));
	
			return step;
		}
		
		
		
		
		
		public double getDummy(double dummy) {return dummy;}
		
		public int[] beregnKoordinater(GPSPoint p) {
			int x = MARGIN + (int)((p.getLongitude() - minlon)*xstep + 0.5); 
			int y = YBASE - (int)((p.getLatitude() - minlat)*ystep + 0.5);
			int[] xy = new int[2];
			xy[0] = x; xy[1] = y;
			return xy;
		}
		
		public int dot(GPSPoint P, int s) {
			int[] xy = beregnKoordinater(P);
			int id = fillCircle(xy[0], xy[1], s);
			return id;
		}
		
		public void color(int[] rbg) {
			setColor(rbg[0], rbg[1], rbg[2]);
		}
		
		public void drawErasePath() {
			int[] xy;
			int s = 5;
			int wait = 1;
			int[] rbg = {0, 0, 200};
			
			for (GPSPoint p: gpspoints) {
				color(rbg);
				dot(p, s);
				pause(wait);
				color(PATHC);
				dot(p, s);
				
			}
		}
		
		

		
		
		public void showRouteMap() { 
			
			setColor(0, 200, 0);
		
			for (GPSPoint P: gpspoints) {
				dot(P, 5);	
			}
		}
		
		public void showStatistics() {
			
			int TEXTDISTANCE = 20;
			setColor(0,0,0);
			setFont("Courier",12);
			
			String[] attr = {
			 "Total tid", "Total distanse", "Total stigning",
			 "Maks fart", "Gjennomsnittlig fart", "Forbrenning"};
			
			double[] vals = {
			 getDummy(0), getDummy(0), getDummy(0), 
			 getDummy(0), getDummy(0), getDummy(0)};
			
			for (int i = 0; i < attr.length; i ++) {
				drawString(
					attr[i] + ": " + (vals[i]), 
					MARGIN, 
					TEXTDISTANCE*i);
			}
		}
	
		public void replayRoute() {
			
			setColor(0, 0, 200);
			int[] xy;
			
			GPSPoint p0 = gpspoints[0];
			int id = dot(p0, 5);
			setSpeed(10);

			for (GPSPoint p: gpspoints) {
				xy = beregnKoordinater(p);
				moveCircle(id, xy[0], xy[1]);
			}
	}

}





