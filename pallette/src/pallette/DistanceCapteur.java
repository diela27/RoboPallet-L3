package pallette;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;
public class DistanceCapteur {
	

	    private EV3IRSensor capteur;

	    private static final float DISTANCE_MAX = 0.50f; 

	    private static final float DIAMETRE_ROUE = 5.6f; 
	    private static final float ECART_ROUES = 12.0f;  

	    public DistanceCapteur() {
	        capteur = new EV3IRSensor(SensorPort.S4);
	    }

	    public float getDistance() {

	        float[] distance = new float[1];

	        capteur.getDistanceMode().fetchSample(distance, 0);

	        return distance[0];
	    }

	    private void tourner(float angleRobot) {

	        int angleMoteur =
	                Math.round(angleRobot * ECART_ROUES / DIAMETRE_ROUE);

	        Motor.A.rotate(angleMoteur, true);
	        Motor.D.rotate(-angleMoteur);
	    }

	    public float[][] scanner360() {

	        float[][] objets = new float[2][36];


	        for (int i = 0; i < 36; i++) {

	            float angle = i * 10;
	            float distance = getDistance();

	            System.out.println(
	                    "Angle : " + angle +
	                    " deg | Distance : " + distance + " m"
	            );

	            // Si objet détecté
	            if (distance > 0 && distance <= DISTANCE_MAX) {

	                objets[0][i] = angle;
	                objets[1][i] = distance;

	                System.out.println(
	                        ">>> OBJET DETECTE : " +
	                        angle + " deg ; " +
	                        distance + " m"
	                );
	            }

	            if (i < 35) {
	                tourner(10);
	                Delay.msDelay(100);
	            }
	        }


	        return objets;
	    }

	    public void fermer() {
	        capteur.close();
	    }



	    public static void main(String[] args) {

	        DistanceCapteur capteur = new DistanceCapteur();

	        Delay.msDelay(2000);

	        float[][] objets = capteur.scanner360();
	        for (int i = 0; i < 36; i++) {

	            if (objets[1][i] > 0) {

	                System.out.println(
	                        "Objet -> Angle : "
	                        + objets[0][i]
	                        + " deg ; Distance : "
	                        + objets[1][i]
	                        + " m"
	                );
	            }
	        }

	        capteur.fermer();

	        System.out.println("Scan termine.");
	    }
	

	
}
