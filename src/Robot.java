import java.util.ArrayList;

import lejos.utility.Delay;

public class Robot {
    private Mouvement mv;
    private Capteur capteurs;

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

    public void tourComplet() {
        mv.tourComplet();
    }

    public float getDistance() {
        return capteurs.getDistance();
    }

    public ArrayList<Mesure> scanner() {
        ArrayList<Mesure> mesures = new ArrayList<Mesure>();

        final long dureeScan = 3200;
        final int intervalle = 50;

        long debut = System.currentTimeMillis();

        mv.demarrerRotationScan();

        try {
            while (System.currentTimeMillis() - debut < dureeScan) {

                long tempsEcoule =
                    System.currentTimeMillis() - debut;

                float angle =
                    (tempsEcoule / (float) dureeScan) * 360.0f;

                float distance = capteurs.getDistance();

                mesures.add(new Mesure(angle, distance));

                Delay.msDelay(intervalle);
            }
        }
       
        
        
        finally {
            mv.stoproue();	
        }

        return mesures;
    }

    public void fermerCapteur() {
        capteurs.fermer();
    }
}
