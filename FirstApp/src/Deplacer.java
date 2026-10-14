import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

public static void main(String[] args) {
	// TODO Auto-generated method stub

	public class Deplacer {

		public static void main(String[] args) {
			// TODO Auto-generated method stub

			Motor.A.setSpeed(200);
			Motor.D.setSpeed(200);

			Motor.A.forward();
			Motor.D.forward();

			Delay.msDelay(3000);

			Motor.A.stop();
			Motor.D.stop();

		}

	}
}


