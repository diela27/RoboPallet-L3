package MonProject;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
public class DistanceCapteur {


	private EV3IRSensor capteur;

	public DistanceCapteur() {
		capteur = new EV3IRSensor(SensorPort.S4);
	}


	public float getDistance() {
		float[] distance = new float[1];

		capteur.getDistanceMode().fetchSample(distance, 0);

		return distance[0];
	}


	public float[] recherche(float[] tab) {


		float nouvelleDistance = getDistance();

		float[] nouveauTab = new float[tab.length + 1];

		for (int i = 0; i < tab.length; i++) {
			nouveauTab[i] = tab[i];
		}


		nouveauTab[tab.length] = nouvelleDistance;

		return nouveauTab;
	}

	public boolean detecterObjet(float distanceMax) {
		return getDistance() < distanceMax;
	}

	public void fermer() {
		capteur.close();
	}
}



