import lejos.utility.Delay;

		public class TestBras {

			public static void main(String[] args) {
				// TODO Auto-generated method stub
				Motor.B.setSpeed(200);
				Motor.B.backward();
				// Motor.B.forward();

				Delay.msDelay(4000);

				Motor.B.stop();
			}

		}
	}

}
