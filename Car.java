/**
 * The class Car contains is centered around cars, and in its constructor 
 * we may construct a car object, which has the attributes <strong>bornTime
 * </strong>, that tells us when the car was created due to our chosen int-clock,
 * and <strong>dest</strong>, which tells us where the car is going, with a single in.
 *@author Fredrik Wärngård
 *@date 2015-01-14
 *@throws IllegalArgumentException 
 */

/**
 *The constructor for Car is kind of strict. We have exceptions thrown when born time is
 *set to a value less than zero, an exception is thrown. We can not have an upper limit
 *because we do not know the length of the simulation, unless we get it from the Simulation
 *file. The destination is handled weirdly. First we have hard coded that there are two
 *options as destination. This can easily be altered for more destination possibilities.
 *when a non accepted value is inputted as a dest, the car is still created, but as its
 *constructor needs a valid value, the choice has been to input the given value modulo 2
 *plus one. This probably won't please a traveller trying to get to dest 3, but it is a 
 *fair way of handling cases that aren't supposed to be there anyway. Also, of course 
 *exceptions are thrown in these odd cases.
 *@param bornTime An int supposed to represent the cars (global) time of birth.
 *@param dest The destination to which the car is planning to go.
// *@throws e IllegalArgumentException in case any of the two arguments are not appreciated.
 */
public class Car {
    private int bornTime;
    private int dest;
    
    public Car(int bornTime, int dest) throws IllegalArgumentException {
	    if (0 <= bornTime) {
		this.bornTime = bornTime;
	    } else {
		throw new IllegalArgumentException("The borntime for a car must at least 0");
	    }
	    if (0 < dest && dest <= 2) {
		this.dest = dest;
	    } else {
		//		this.dest = ((dest % 2 == 1) ? 1 : 2);
		throw new IllegalArgumentException("Please, choose a valid destination for the car");
	    }
    }

    /**
     * Returns the time when this.car was created
     * @return The born time, an int representing the number of the steps in the simulation, for the chosen car. 
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
     * Returns the printed representation for the chosen car. In this case its destination, from variable <strong>dest</strong>
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
