import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

public class TestCar {

    private Car car1 = new Car(7, 1);
    private Car car2 = new Car(3, 3);

    @Test
    public void testCar() {
	assertTrue(car1.getDest() == 1); 
	assertTrue(car2.getDest() == 1);
	assertTrue(Car badDest = new Car(0, 0) = IllegalArgumentException); 
    }
}
