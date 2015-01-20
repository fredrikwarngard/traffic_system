import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

public class TestCar {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Test
    public void testGetBornTime() {
	//	exception.expect(IllegalArgumentException.class);
	//	Car badBorn = new Car(-1, 1);
	Car testCar = new Car(32, 1);
	assertTrue(32 == testCar.getBornTime());
    }
    
    @Test
    public void testGetDestination() {
	Car car1 = new Car(3, 1);
	Car car2 = new Car(3, 2);
       	assertTrue(car1.getDestination() == 1); 
	assertFalse(car2.getDestination() == 1);
      	exception.expect(IllegalArgumentException.class);
	Car badDest = new Car(-1, 0); 
	exception.expect(IllegalArgumentException.class);
        badDest = new Car(1, 4); 
    }
}
