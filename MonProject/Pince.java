package MonProject;


import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

public class Pince {
	private boolean ouvert = true;
	public void ouvrir() {
	    Motor.B.forward();
	    Delay.msDelay(500);
	    Motor.B.stop();

	    ouvert = true;
	}
	public void fermer() {
	    Motor.B.backward();
	    Delay.msDelay(500);
	    Motor.B.stop();

	    ouvert = false;
	}
	public boolean estOuvert() {
	    return ouvert;
	}
	// Arrêter le moteur de la pince
	public void arreter() {
		Motor.B.stop();
	}


}
