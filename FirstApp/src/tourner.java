import lejos.hardware.motor.Motor;

public class tourner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Motor.A.setSpeed(1000);
		Motor.D.setSpeed(1000);
		Motor.A.forward();
		Motor.D.backward();

	}

}
