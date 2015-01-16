/**
 * The class Car contains is centered around cars, and in its constructor 
 * we may construct a car object, which has the attributes <strong>bornTime
 * </strong>, that tells us when the car was created due to our chosen int-clock,
 * and <strong>dest</strong>, which tells us where the car is going, with a single in.
 *
 * @author Fredrik Wärngård
 * @date 2015-01-14
 */

public class Car {

    private int bornTime;
    private int dest;

    public Car(int bornTime, int dest){
    	this.bornTime	= bornTime;
    	this.dest	= dest;
    }

    /**
     * Returns the time when this.car was created
     * @return The born time for the chosen car. 
     */
    public int getBornTime(){
    	return this.bornTime;
    }

    /**
     * Returns the end destination for the chosen car.
     * @return The end destination for the chosen car. 
     */
    public int getDestination(){
    	return this.dest;
    }

    
    /**
     * Returns the printed representation for the chosen car.
     * @return The printed representation for the chosen car.
     */
    public String toString() {
    	return dest + "";
    }
    
    /**
     * Returns the time that the car has spent on the road (since it was created) so far.
     * @param  time The global time.
     * @return The age of the chosen car, in steps. 
     */
    public float timeInSystem(int time) {
	return (time - this.getBornTime());
    }
    
	
}
