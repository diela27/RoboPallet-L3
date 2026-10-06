package MonProject;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;

public class Action {

    private NXTRegulatedMotor moteurGauche;
    private NXTRegulatedMotor moteurDroit;
public int distance;
    private int vitesse = 300;


    private EV3ColorSensor capteurCouleur;
    private SampleProvider modeCouleur;
    private float[] echantillonCouleur;

    public Action() {

       
        moteurGauche = Motor.A;
        moteurDroit = Motor.C;

        moteurGauche.setSpeed(vitesse);
        moteurDroit.setSpeed(vitesse);

        
        capteurCouleur = new EV3ColorSensor(SensorPort.S3);

        modeCouleur = capteurCouleur.getColorIDMode();

        echantillonCouleur =
                new float[modeCouleur.sampleSize()];
    }



    public void setVitesse(int vitesse) {

        this.vitesse = vitesse;

        moteurGauche.setSpeed(vitesse);
        moteurDroit.setSpeed(vitesse);
    }


    public void avancer() {

        moteurGauche.forward();
        moteurDroit.forward();
    }


    public void avancer(int distance) {

        int angle = distanceEnDegres(distance);

        moteurGauche.rotate(angle, true);
        moteurDroit.rotate(angle);
    }


    public void avancer(int distance, int vitesse) {

        setVitesse(vitesse);

        int angle = distanceEnDegres(distance);

        moteurGauche.rotate(angle, true);
        moteurDroit.rotate(angle);
    }


    public void avancerAsync(int distance) {

        int angle = distanceEnDegres(Math.abs(distance));

        if (distance >= 0) {

            moteurGauche.rotate(angle, true);
            moteurDroit.rotate(angle, true);

        } else {

            moteurGauche.rotate(-angle, true);
            moteurDroit.rotate(-angle, true);
        }
    }


    public void reculer() {

        moteurGauche.backward();
        moteurDroit.backward();
    }


    public void reculer(int distance) {

        int angle = distanceEnDegres(Math.abs(distance));

        moteurGauche.rotate(-angle, true);
        moteurDroit.rotate(-angle);
    }


    public void reculer(int distance, int vitesse) {

        setVitesse(vitesse);

        int angle = distanceEnDegres(Math.abs(distance));

        moteurGauche.rotate(-angle, true);
        moteurDroit.rotate(-angle);
    }


    public void tournerG(int angle) {

        moteurGauche.rotate(-angle, true);
        moteurDroit.rotate(angle);
    }

    public void tournerD(int angle) {

        moteurGauche.rotate(angle, true);
        moteurDroit.rotate(-angle);
    }

    public void asyncTournerG(int angle) {

        moteurGauche.rotate(-angle, true);
        moteurDroit.rotate(angle, true);
    }


    
    public void asyncTournerD(int angle) {

        moteurGauche.rotate(angle, true);
        moteurDroit.rotate(-angle, true);
    }

    public boolean isMoving() {

        return moteurGauche.isMoving()
                || moteurDroit.isMoving();
    }


    public void stop() {

        moteurGauche.stop(true);
        moteurDroit.stop();
    }


    public void seDecalerD() {

        moteurGauche.rotate(180, true);
        moteurDroit.rotate(-180);
    }


   
    public void seDecalerD(int distance) {

        moteurGauche.rotate(distance, true);
        moteurDroit.rotate(-distance);
    }


    public void seDecalerG() {

        moteurGauche.rotate(-180, true);
        moteurDroit.rotate(180);
    }


    public void seDecalerG(int distance) {

        moteurGauche.rotate(-distance, true);
        moteurDroit.rotate(distance);
    }


    public void avancerJusqueCouleur(String couleurRecherchee) {

        avancer();

        while (true) {

            modeCouleur.fetchSample(echantillonCouleur, 0);

            int couleurDetectee =
                    (int) echantillonCouleur[0];

            if (couleurRecherchee.equalsIgnoreCase("blanc")
                    && couleurDetectee == Color.WHITE) {

                break;
            }

            if (couleurRecherchee.equalsIgnoreCase("noir")
                    && couleurDetectee == Color.BLACK) {

                break;
            }

            if (couleurRecherchee.equalsIgnoreCase("rouge")
                    && couleurDetectee == Color.RED) {

                break;
            }

            if (couleurRecherchee.equalsIgnoreCase("vert")
                    && couleurDetectee == Color.GREEN) {

                break;
            }

            if (couleurRecherchee.equalsIgnoreCase("bleu")
                    && couleurDetectee == Color.BLUE) {

                break;
            }
        }

        stop();
    }


    public void avancerJusqueLigne() {

        avancer();

        while (true) {

            modeCouleur.fetchSample(echantillonCouleur, 0);

            int couleurDetectee =
                    (int) echantillonCouleur[0];

            if (couleurDetectee == Color.BLACK) {
                break;
            }
        }

        stop();
    }
    
    public int distanceEnDegres(int distance) {         

        return distance * 10;
    }

    public void fermer() {

        stop();

        capteurCouleur.close();
    }
    public void test3palets1(boolean droite) {
        if (droite) {
            seDecalerD();
        } else {
            seDecalerG();
        }
    }
    public void recalibrage() {

        stop();

        // Avance doucement pour retrouver la ligne
        avancer(50, 100);

        stop();
    }}

   	