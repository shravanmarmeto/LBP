package genericUtility;

import java.util.Random;

public class javaUtility {
	public int toGetRandomNumber() {
		Random r = new Random();
		int random = r.nextInt(100000);
		return random;
	}
}
