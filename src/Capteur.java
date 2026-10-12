import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
public class Capteur {
	 private EV3UltrasonicSensor capteur;
	    private SampleProvider distance;
	    private float[] mesure;

	    public Capteur() {
	        capteur = new EV3UltrasonicSensor(SensorPort.S2);

	        distance = capteur.getDistanceMode();

	        mesure = new float[distance.sampleSize()];
	    }//l

	    public float getDistance() {
	        distance.fetchSample(mesure, 0);

	        return mesure[0];
	    }//jj

	    public void fermer() {
	        capteur.close();
	    }
}
