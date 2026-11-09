import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import lejos.hardware.Button;

public class Main {

    public static void main(String[] args) {

        Robot r = new Robot();

        try {
            System.out.println("Debut du scan");

     
            ArrayList<Mesure> mesures = r.scanner();

            System.out.println("Scan termine");
            System.out.println("Nombre de mesures : " + mesures.size());

    
            BufferedWriter fichier = new BufferedWriter(
                new FileWriter("./mesures.csv")
            );

            fichier.write("Angle;Distance");
            fichier.newLine();

            
            for (Mesure m : mesures) {

                fichier.write(
                    m.getAngle() + ";" + m.getDistance()
                );

                fichier.newLine();
            }

          
            fichier.close();

            System.out.println("Fichier enregistre");
            System.out.println("Nom : mesures.csv");

            while (!Button.ESCAPE.isDown()) {
            }

        } catch (IOException e) {

            System.out.println("Erreur fichier : " + e.getMessage());

        } finally {

            r.stop();
            r.fermerCapteur();
        }//hh
    }
}