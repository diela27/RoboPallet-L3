package pallette;

import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class StockageDistances {
	 public static void main(String[] args) {

		 // 1. Initialisation du robot
	        DifferentialPilot pilot =
	            new DifferentialPilot(5.6, 11.5, Motor.B, Motor.C);

	        // 2. Initialisation du capteur ultrason
	        EV3UltrasonicSensor sonar =
	            new EV3UltrasonicSensor(SensorPort.S1);

	        float[] sample = new float[sonar.sampleSize()];

	        // 3. Stockage de toutes les distances
	        ArrayList<Integer> historiqueDistances = new ArrayList<Integer>();
	            int nombrePalets = 0;
	            int confirmations = 0;

	            pilot.setTravelSpeed(15.0);

	            Motor.A.setSpeed(200);

	            LCD.clear();
	            LCD.drawString("Robot en recherche", 0, 1);

	            // 4. Demarrage du robot
	            pilot.forward();

	            // 5. Boucle principale : recherche permanente
	            while (!Button.ESCAPE.isDown()) {

	                // Lecture du capteur
	                sonar.fetchSample(sample, 0);

	                float distanceCm = sample[0] * 100;

	                // Enregistrement de chaque distance
	                historiqueDistances.add(distanceCm);

	                // Affichage de la distance
	                LCD.clear(3);
	                LCD.drawString(
	                    "Distance: " + (int) distanceCm + " cm",
	                    0, 3
	                );

	                LCD.clear(4);
	                LCD.drawString(
	                    "Mesures: " + historiqueDistances.size(),
	                    0, 4
	                );

	                LCD.clear(5);
	                LCD.drawString(
	                    "Palets: " + nombrePalets,
	                    0, 5
	                );
	                // Verification de la presence d'un palet
	                if (distanceCm > 0 && distanceCm < 30) {
	                    confirmations++;
	                } else {
	                    confirmations = 0;
	                }

	                // Trois mesures proches consecutives
	                if (confirmations >= 3) {

	                    // ARRET DU ROBOT
	                    pilot.stop();

	                    LCD.clear();
	                    LCD.drawString("Palet detecte !", 0, 2);

	                    // Approche du palet
	                    float approche =
	                        Math.max(0, distanceCm - 8);

	                    pilot.travel(approche);

	                    // FERMETURE DE LA PINCE
	                    Motor.A.rotate(90);

	                    Delay.msDelay(500);

	                    LCD.clear();
	                    LCD.drawString("Palet ramasse !", 0, 2);

	                    nombrePalets++;

	                    Delay.msDelay(1000);

	                    // DEPLACEMENT VERS LA ZONE DE DEPOT
	                    pilot.rotate(180);
	                    pilot.travel(50);

	                    // OUVERTURE DE LA PINCE
	                    Motor.A.rotate(-90);

	                    Delay.msDelay(1000);

	                    LCD.clear();
	                    LCD.drawString("Palet depose !", 0, 2);

	                    // RETOUR A LA ZONE DE RECHERCHE
	                    pilot.rotate(180);
	                    pilot.travel(50);

	                    // Reinitialisation de la detection
	                    confirmations = 0;

	                    // REPRISE DE LA RECHERCHE
	                    LCD.clear();
	                    LCD.drawString("Recherche suivante", 0, 2);

	                    pilot.forward();
	                }

	                Delay.msDelay(100);
	            }

	            // 6. Arret manuel du robot
	            pilot.stop();

	            sonar.close();

	            LCD.clear();
	            LCD.drawString("Robot arrete", 0, 1);
	            LCD.drawString(
	                "Total palets: " + nombrePalets,
	                0, 3
	            );
	            LCD.drawString(
	                "Total mesures: " + historiqueDistances.size(),
	                0, 4
	            );
	        }
	    }


