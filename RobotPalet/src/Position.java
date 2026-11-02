

/**
 * Représente l'état de la position et de l'orientation du robot EV3 sur le terrain.
 * 
 * Cette classe suit l'orientation de l'angle du robot et indique si sa position sur le terrain
 * est connue ou perdue. L'angle est mesuré par rapport au système de coordonnées du terrain où
 * 180 degrés représente le camp adverse et 0 degré représente la zone de l'équipe.
 * 
 * On suppose que le robot commence en face du camp adverse avec une position inconnue.
 */

public class Position {

	    /**
	     * L'orientation de l'angle du robot en degrés.
	     * 180 degrés = direction du camp adverse
	     * 0 degré = direction de la zone de l'équipe
	     */
	    public float angle;

	    /**
	     * Indicateur précisant si la position du robot sur le terrain est perdue (vrai) ou connue (faux).
	     */
	    public boolean perdu;

	    /**
	     * Construit une nouvelle instance de Position avec les valeurs par défaut.
	     * 
	     * Initialise l'angle du robot à 180 degrés (face au camp adverse)
	     * et marque la position comme perdue (perdu = true), car l'emplacement exact
	     * sur le terrain est initialement inconnu.
	     */
	    public Position() {
	        angle = 180;
	        perdu = true;
	    }

	    /**
	     * Récupère l'état actuel de perte de position du robot.
	     * 
	     * @return true si la position du robot est perdue ou inconnue, false si la position est connue
	     */
	    public boolean getPerdu() {
	        return perdu;
	    }

	    /**
	     * Définit si la position du robot sur le terrain est connue ou perdue.
	     * 
	     * @param b true pour marquer la position comme perdue, false pour la marquer comme connue
	     */
	    public void setPerdu(boolean b) {
	        perdu = b;
	    }

	    /**
	     * Récupère l'orientation actuelle de l'angle du robot.
	     * 
	     * @return l'angle en degrés (180 = camp adverse, 0 = zone de l'équipe)
	     */
	    public float getPosition() {
	        return angle;
	    }

	    /**
	     * Définit l'orientation de l'angle du robot.
	     * 
	     * @param i l'angle en degrés à définir (180 = camp adverse, 0 = zone de l'équipe)
	     */
	    public void setAngle(float i) {
	        this.angle = i;
	    }
	}
