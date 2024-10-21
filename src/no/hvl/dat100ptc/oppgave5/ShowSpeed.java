package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 100; 

	private GPSComputer gpscomputer;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	

	public void run() {

		makeWindow("Speed profile", 
				2 * MARGIN + 2 * dummyGetSpeeds(200).length, 
				2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT);
	}
	
	
	public double[] dummyGetSpeeds(int L) {
		double[] farts = new double[L];
		for (int i=0; i < L; i ++) {
			if (i - 2*(i / 2) == 0) {farts[i] = 70.0;}
			else {farts[i] = 20.0;}
		}
		return farts;
	}
	public double dummyGetAverageSpeed() {
		return 45;
	}
	
	
	public void tegnSnitt(double snitt, int ybase) {
		setColor(0, 200, 0);
		drawLine(
				MARGIN, 
				ybase - (int)(snitt + 0.5), 
				MARGIN + 2 * dummyGetSpeeds(200).length, 
				ybase - (int)(snitt + 0.5));
	}
	
	
	public void tegnSøyler(double[] speeds, int ybase) {
		setColor(0, 0, 200);
		int x = MARGIN;
		for (double speed: speeds) {
			x += 2;
			drawLine(x, ybase, x, ybase - (int)(speed + 0.5));
		}
	}
	
	
	public void showSpeedProfile(int ybase) {
		double snitt = dummyGetAverageSpeed();
		double[] speeds = dummyGetSpeeds(200);
		tegnSnitt(snitt, ybase);
		tegnSøyler(speeds, ybase);


	
		
		
	}
}















