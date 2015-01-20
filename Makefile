#define macros
JUNIT  = junit-4.12.jar:hamcrest-core-1.3.jar

all : 	compileTest

compile	: Car.java Lane.java Light.java TrafficSystem.java Simulation.java
	javac Simulation.java

compileTest : compile TestRunner.java TestCar.java TestLane.java TestLight.java TestTrafficSystem.java	
	javac -cp .:$(JUNIT) TestRunner.java 

test :	compileTest
	java -cp .:$(JUNIT) TestRunner

run : 	compile
	java Simulation 100

javadoc : Car.java Lane.java Light.java TrafficSystem.java Simulation.java
	javadoc -d . Simulation.java
