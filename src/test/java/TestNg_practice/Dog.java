package TestNg_practice;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Dog {
	@Test
	void barking() {
		System.out.println("Barking");
		}
	@AfterTest
	void breed() {
		System.out.println("AFterTEst");
	}

}




