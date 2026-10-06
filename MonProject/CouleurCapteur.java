package MonProject;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class CouleurCapteur {

    private EV3ColorSensor capteur;

    public CouleurCapteur() {
        capteur = new EV3ColorSensor(SensorPort.S3);
    }

    public RGB getCouleur() {

        float[] couleur = new float[3];

        capteur.getRGBMode().fetchSample(couleur, 0);

        int r = (int) (couleur[0] * 255);
        int g = (int) (couleur[1] * 255);
        int b = (int) (couleur[2] * 255);

        return new RGB(r, g, b);
    }

    public String nomCouleur(int r, int g, int b) {

        if (r > 200 && g > 200 && b > 200) {
            return "blanc";
        }

        if (r < 50 && g < 50 && b < 50) {
            return "noir";
        }

        if (r > g * 1.5 && r > b * 1.5) {
            return "rouge";
        }

        if (g > r * 1.3 && g > b * 1.3) {
            return "vert";
        }

        if (b > r * 1.3 && b > g * 1.3) {
            return "bleu";
        }

        return "inconnue";
    }

    public void fermer() {
        capteur.close();
    }
}

