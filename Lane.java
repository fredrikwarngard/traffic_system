/**
 *@author Fredrik Wärngård
 *@date 2015-01-14
 *
 *Lane creates a traffic lane of a certain length <strong>length</strong>
 *and has among its methods <strong>step()</strong> to move all cars in the 
 *lane one step forward if the spot ahead is not taken. It also has methods
 *<strong>firstCar()</strong> that returns if the is a car in the lanes'
 *first spot, <strong>getFirst()</strong> that returns that same car, 
 *<strong>lastFree()</strong> that checks the last spot of the lane for the
 *same thing, and <strong>putLast(Car newCar)</strong> that puts a car in the
 *last spot. The class also contains a <strong>toString()</strong>-method.
 */


public class Lane {
    /**
     *A new exception class, representing an overflow occurance of something. And
     *in this class, the overflow of a lane. This overflow exception will be thrown
     *when the lane is if full to the last spot, meaning no new cars can enter the 
     *lane. If there is a new car but nowhere to put it result in an overflow. 
     *@param message The message included will be printed due to the methods of the 
     *superclass.
     */
    public static class OverflowException extends RuntimeException {
	public OverflowException(String message) {
	    super(message);
	}
    }
    
    private Car[] theLane;
    
    /**
     *The lane is constructed with an input number(int), declaring the length of the lane.
     *<strong>theLane</strong> above shows the structure of the lane. The spots in the lane
     *may be inhibited by any Car object.
     *@param length The number which determines the size of lane, as a positive integer.
     *@return theLane The newly constructed Car array of length <strong>length</strong>.
          *@throws IllegalArgumentException In case lane's length is not at least 1.
     */
    public Lane(int length) throws IllegalArgumentException {       // ändrades, försökte kasta fel, och det blev 42 nya fel
	if (0 < length) {
	    theLane = new Car[length];
	} else {
	    throw new IllegalArgumentException("Lane must be of at least length 1.");
	}
    }

    /**
     *The step function starts checking the first element in the lane. If that 
     *element is null, then the second element is moved over from spot 2 to 
     *spot 1, followed by setting spot 2s' element to null. Because of its 
     *iterative loop, this is followed with doing the same operation with spot 2
     *and its next spot, and so on until the lanes' second last element that 
     *will do its copy from the last element.
     */
    public void step() {
	for(int i = 0; i < theLane.length - 1; ++i){
	    if(theLane[i] == null){
		theLane[i] = theLane[i+1];
		theLane[i+1] = null;
	    }
    	}
    }

    /**
     *It will return the car in the first spot of the lane and set the lanes'
     *first spot to null
     *@return Car the first car in the lane
     */
    public Car getFirst() {
    	Car first = theLane[0];
    	theLane[0] = null;
    	return first;
    }

    /**
     *Returning the car in the first spot of the lane without removing it from 
     *the lane
     *@return Car the first car in the lane
     */
    public Car firstCar() {
    	return theLane[0];
    }

    /**
     *Get a car from any position in the lane
     *@param index The position of the car in the lane.
     *@returns the Car from the desired position.
     */
    public Car getAnyCar(int index) {
	return theLane[index];
    }


    /**
     *Returns true if the last spot of the lane is free, Otherwise false, a 
     *case that will only occur if there is a queue on the lane all up there.
     *@return boolean true if last spot is free, otherwise false
     */
    public boolean lastFree() {
    	return theLane[theLane.length - 1] == null;
    }

    /**
     *Will insert a car on the last spot of the lane.
     *@param inputCar
     *@throws OverflowException Overflow in case the lane is full
     */
    public void putLast(Car inputCar) throws OverflowException {
    	if(lastFree()){
	    theLane[theLane.length - 1] = inputCar;
    	} else {
	    throw new OverflowException("The lane is full, stop sending cars!");
    	}
    }

    /**
     *Will print the lane with empty spots represented as "-" and elements as
     *their defined toString()-methods-
     *@return String the representation of the lane
     */    
    public String toString() {
	String printLane = "";
	for (int i = 0; i < theLane.length; i++) {
	    if (theLane[i] != null) {
		printLane += theLane[i].toString();
	    } else {
		printLane += "-";
	    }
	}
	return printLane;
    }
}
