import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
public class Main {
	public static void main(String[]args) {
		Robot r = new Robot();
		//r.avancer();
		//r.tournerDroite();
		//r.tournerGauche();
		//r.ouvrirBras(4000);
		//r.fermerBras(4000);
		
		//r.tourComplet();
		//r.ouvrirBras(4000);
		while (!Button.ESCAPE.isDown()) {
		    float distance = r.getDistance();

		    LCD.clear();
		    LCD.drawString("Distance :", 0, 0);
		    LCD.drawString(distance + " m", 0, 1);

		    Delay.msDelay(200);
		}
	     
	    }
	}

