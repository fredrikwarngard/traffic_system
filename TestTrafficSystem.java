import org.junit.rules.ExpectedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

public class TestTrafficSystem {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    /*    @Test
    public void testExtractCarData() {
	TrafficSystem ts = new TrafficSystem();
	Car testCar = new Car(2, 2);
	ts.putLast(testCar);
	int carDataInitial = ts.extractCarData(testCar);
	assertTrue(carDataInitial == 2);
	ts.step();
	ts.step();
	int carDataAfterStep = ts.extractCarData(testCar);
	assertFalse (carDataInitial == carDataAfterStep);
    }*/
    
    @Test
    public void testStep() {
	TrafficSystem ts = new TrafficSystem();
	Light redLight = new Light(1, 0);
	Lane stepLane = new Lane(3);
	Car CarNumberOne = new Car(0, 2);
	Car CarNumberTwo = new Car(2, 2);
	stepLane.putLast(CarNumberOne);;
	stepLane.step();
	stepLane.step();
	stepLane.putLast(CarNumberTwo);
	assertTrue(stepLane.getAnyCar(0) == CarNumberOne);
	assertTrue(stepLane.getAnyCar(2) == CarNumberTwo);
	
    }

}
