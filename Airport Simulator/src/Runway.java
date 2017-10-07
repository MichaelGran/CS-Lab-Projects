//Michael Granovsky
public class Runway 
{
	private int minutesForLanding;
	private int minutesForTakeOff;
	private int timeRemaining;
	
	public Runway(int landing, int takeOff)
	{
		minutesForLanding= landing;
		minutesForTakeOff = takeOff;
		timeRemaining = 0;
	}
	
	public boolean runWayIsFull()
	{
		return (timeRemaining > 0);
	}
	
	public void reduceTime()
	{
		if(timeRemaining > 0)
			timeRemaining--;
	}
	
	public void useRunway(char whatKind) 
	{
		if(timeRemaining > 0)
			throw new IllegalStateException("The runway is currently occupied!");
		if(whatKind == 'L')
			timeRemaining = minutesForLanding;
		else
		if(whatKind == 'D')
			timeRemaining = minutesForTakeOff;
	}
}
