import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;
//import static org.junit.Assert.assertTrue;

public class TestLight {

    private Light lightOne = new Light(7, 1);
    private Light lightTwo = new Light(3, 2);
    
    @Test
    public void testLight() {
	assertTrue(lightOne.isGreen() == true); 
	lightOne.step();
	assertTrue(lightOne.isGreen() == false);
	lightTwo.step();
	lightTwo.step();
	assertEquals(lightTwo.getTime(), 2); 
	assertTrue(lightTwo.isGreen() == false); 
	lightTwo.step();
	assertEquals(lightTwo.getTime(), 0); 
	assertTrue(lightTwo.isGreen() == true); 
    }
}
