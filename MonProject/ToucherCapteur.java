package MonProject;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

public class ToucherCapteur {

	private EV3TouchSensor capteur;

	public ToucherCapteur() {
		capteur = new EV3TouchSensor(SensorPort.S1);
	}

	public boolean estToucher() {
		float[] valeur = new float[1];

		capteur.getTouchMode().fetchSample(valeur, 0);

		return valeur[0] == 1;
	}

	public void fermer() {
		capteur.close();
	}

}

	


