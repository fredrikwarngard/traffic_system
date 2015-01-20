import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class TestLight {

    @Test
    public void testIsGreen() {
	Light lightOne = new Light(7, 1);
	Light lightTwo = new Light(3, 2);
    	assertTrue(lightOne.isGreen() == true); 
	lightOne.step();
	assertTrue(lightOne.isGreen() == false);
	lightTwo.step();
	lightTwo.step();
    }
    
    @Test
    public void testStep() {
	Light lightOne = new Light(7, 1);
	Light lightTwo = new Light(3, 2);
	lightOne.step();
	lightTwo.step();
	lightTwo.step();
	assertTrue(lightOne.getTime() == 1);
	assertTrue(lightTwo.getTime() == 2);
    }
}
