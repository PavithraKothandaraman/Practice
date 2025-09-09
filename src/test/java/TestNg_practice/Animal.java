package TestNg_practice;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Animal {
	@Test
	void colour() {
		System.out.println("choosing");
		}
	@BeforeTest
	void eating() {
		System.out.println("BEforeTEst");
	}

	
	
	
}
