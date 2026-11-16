import java.util.ArrayList;
import lejos.utility.Delay;

public class Robot {

    private Mouvement mv;
    private Capteur capteurs;

      private static final float ANGLE_TOUR_COMPLET = 730.0f;

    public Robot() {
        mv = new Mouvement();
        capteurs = new Capteur();
    }

    public void avancer() {
        mv.avancer();
    }

    public void reculer() {
        mv.reculer();
    }

    public void tournerDroite() {
        mv.tournerDroite();
    }

    public void tournerGauche() {
        mv.tournerGauche();
    }

    public void ouvrirBras(int i) {
        mv.ourvirBras(i);
    }

    public void fermerBras(int i) {
        mv.fermerBras(i);
    }

    public void stop() {
        mv.stoproue();
    }

    public float getDistance() {
        return capteurs.getDistance();
    }

   
    public ArrayList<Mesure> scanner() {

        ArrayList<Mesure> mesures = new ArrayList<Mesure>();

        final int intervalle = 6;

        mv.initialiserEncodeurs();
        mv.demarrerRotationScan();

        try {
            while (mv.getRotationScan() < ANGLE_TOUR_COMPLET) {

               
                float rotationMoteurs = mv.getRotationScan();

                
                float angle = (rotationMoteurs / ANGLE_TOUR_COMPLET) * 360.0f;

              
                if (angle > 360.0f) {
                    angle = 360.0f;
                }

              
                float distance = capteurs.getDistance();

                
                mesures.add(new Mesure(angle, distance));

                Delay.msDelay(intervalle);
            }

        } finally {
           
            mv.arreterRotationScan();
        }

        return mesures;
    }

    public void fermerCapteur() {
        capteurs.fermer();
    }
}