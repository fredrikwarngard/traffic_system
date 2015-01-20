import org.junit.rules.ExpectedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

public class TestLane {
    
    @Test
    public void testStep() {    // check that the cars won't move if the have cars ahead!
	Lane laneExample = new Lane(3);
	Car newCar1 = new Car(7,2);
	laneExample.putLast(newCar1);
	laneExample.step();
	Car newCar2 = new Car(1,2);
	laneExample.putLast(newCar2);
	laneExample.step();
	Car snapShotCar1 = laneExample.getAnyCar(0);
	Car snapShotCar2 = laneExample.getAnyCar(1);
	laneExample.step();
	assertEquals("The first car in lane should be the same as before step", laneExample.getAnyCar(0), snapShotCar1);
	assertEquals("The second car in lane should be the same as before step", laneExample.getAnyCar(1), snapShotCar2);
	//}
    }
    
    //pre do
    
    @Test
    public void testStep2() {  // check that the cars will move if the are no cars ahead
	Lane laneExample2 = new Lane(3);
	Car newCarOne = new Car(1,2);
	laneExample2.putLast(newCarOne);
	laneExample2.step();
	Car newCarTwo = new Car(2,1);
	laneExample2.putLast(newCarTwo);
	laneExample2.step();
	
	assertEquals("We expect the Car inputted in the lane two steps ago is now first", laneExample2.getAnyCar(0), newCarOne);
	assertEquals("We expect the Car inputted in the lane one step ago is now second", laneExample2.getAnyCar(1), newCarTwo);
	assertEquals("We expect the third spot in the lane now to be empty", laneExample2.getAnyCar(2), null);
    }
    

    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Test
    public void testPutLast() { //putLast when and when not there is already a car in the last spot
	Lane laneExample3 = new Lane(3);
	Car newCarAlpha = new Car(1, 2);
	laneExample3.putLast(newCarAlpha);
	Lane laneExample4 = new Lane(2);
	Car newCarGamma = new Car(3, 1);
	laneExample4.putLast(newCarGamma);
	//laneExample3.step();   // KAN KÖRAS OM MAN VILL ATT DET SKA GÅ SÖNDER
	assertEquals("The new car newCarGamma is inserted in the lanes' last spot", laneExample4.getAnyCar(1), newCarGamma);
	Car newCarBeta = new Car(6,2);
	exception.expect(Lane.OverflowException.class);
	laneExample3.putLast(newCarBeta);
    }
}

