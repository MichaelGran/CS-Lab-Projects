//Michael Granovsky
public class BooleanSource 
{
	private double probability;
	
	public BooleanSource(double userProb)
	{
		if((userProb < 0) || (1 < userProb))
			throw new IllegalArgumentException("Illegal Probability: " + userProb);
		probability = userProb;
	}
	
	public boolean query()
	{
		return (Math.random() < probability);
	}
}
