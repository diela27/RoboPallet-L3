import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class testcapteur {

    public static void main(String[] args) {

        EV3UltrasonicSensor capteur =
                new EV3UltrasonicSensor(SensorPort.S2);

        SampleProvider distance = capteur.getDistanceMode();

        float[] mesure = new float[distance.sampleSize()];

        while (!Button.ESCAPE.isDown()) {

            distance.fetchSample(mesure, 0);

            LCD.clear();
            LCD.drawString("Distance :", 0, 0);
            LCD.drawString(mesure[0] + " m", 0, 1);
        }

        capteur.close();//uhu
    }
}