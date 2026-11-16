import java.util.ArrayList;

/**
 * Cherche les palets dans un scan : un palet apparait comme une zone
 * brusquement plus proche que ce qui l'entoure (saut vers le bas,
 * puis saut vers le haut).
 */
public class DetectPalets {

    private static final float SEUIL_SAUT = 10f;   // cm
    private static final float DIST_MAX = 150f;    // cm
    private static final int LARGEUR_MIN = 2;      // nb de mesures
    private static final int LARGEUR_MAX = 60;

    // renvoie des Mesure(angle du centre du palet, distance minimale)
    public static ArrayList<Mesure> detecter(ArrayList<Mesure> m) {
        ArrayList<Mesure> palets = new ArrayList<Mesure>();
        int n = m.size();
        int i = 1;

        while (i < n) {
            float saut = m.get(i - 1).getDistance() - m.get(i).getDistance();

            if (saut > SEUIL_SAUT) {
                int debut = i;
                int j = i + 1;
                while (j < n && m.get(j).getDistance() - m.get(j - 1).getDistance() <= SEUIL_SAUT) {
                    j++;
                }
                int largeur = j - debut;

                if (largeur >= LARGEUR_MIN && largeur <= LARGEUR_MAX) {
                    float min = Float.MAX_VALUE;
                    for (int k = debut; k < j; k++) {
                        min = Math.min(min, m.get(k).getDistance());
                    }
                    float angle = (m.get(debut).getAngle() + m.get(j - 1).getAngle()) / 2f;
                    if (min < DIST_MAX) {
                        palets.add(new Mesure(angle, min));
                    }
                }
                i = j + 1;
            } else {
                i++;
            }
        }
        return palets;
    }

    // palet le plus proche (null si liste vide)
    public static Mesure plusProche(ArrayList<Mesure> palets) {
        Mesure best = null;
        for (Mesure p : palets) {
            if (best == null || p.getDistance() < best.getDistance()) {
                best = p;
            }
        }
        return best;
    }
}
