//Michael Granovsky
import java.util.LinkedList;
import java.util.Queue;
import java.text.DecimalFormat;
import java.util.Scanner; 

public class Airport
{
   
   public static void main(String[ ] args)
   { 
      Scanner kb = new Scanner(System.in);
      System.out.print("Amount of minutes required to land: ");
      final int LANDTIME = kb.nextInt();
      System.out.print("\nAmount of minutes required to take off: ");
      final int TAKEOFFTIME = kb.nextInt();
      System.out.print("\nProbability of arrival during one minute: ");
      final double ARRIVALPROB = kb.nextDouble();
      System.out.print("\nAverage amount of time between plane landings: ");
      final double AVGLANDING = kb.nextDouble();
      System.out.print("\nProbability of departure during one minute: ");
      final double DEPARTUREPROB = kb.nextDouble();
      System.out.print("\nAverage amount of time between plane deparatures: ");
      final double AVGDEPARTURE = kb.nextDouble();
      System.out.print("\nMaximum time in air before crashing: ");
      final int MAXAIRTIME = kb.nextInt();
      System.out.print("\nTotal Simulation Time In Minutes: ");
      final int TOTALSIMTIME = kb.nextInt();
      kb.close();


      
      airportSimulate(TAKEOFFTIME,LANDTIME,MAXAIRTIME,ARRIVALPROB,DEPARTUREPROB,AVGDEPARTURE,AVGLANDING,TOTALSIMTIME);
   }
    
   public static void airportSimulate(int takeOffTime, int landTime, int maxTimeInAir,  double departProb, double landProb, double aveBetTakeOFF,double aveBetLand,double totalTime)
   {
      int totalDepartures = 0; 
      int totalLandings = 0;
      int totalCrashes = 0; 
      int currentMinute = 0; 
      int next;
      char whatType;
      Queue<Integer> landings = new LinkedList<Integer>();
      Queue<Integer> departures = new LinkedList<Integer>();
      BooleanSource arrivalProb = new BooleanSource(landProb);
      BooleanSource departureProb = new BooleanSource(departProb);
      Runway singleRunway = new Runway(landTime, takeOffTime);
      Averager landWaitTimes = new Averager();
      Averager departWaitTimes = new Averager();
      System.out.println("Input: " + "\n");
      System.out.println("Minutes needed for one plane to depart: " + takeOffTime);
      System.out.println("Minutes needed for one plane to land: " + landTime);
      System.out.println("Average time between takeoffs: " + aveBetTakeOFF);
      System.out.println("Average time between Landings: " + aveBetLand);
      System.out.print("Probability of plane arriving to the take off queue for each minute: ");
      System.out.println(departProb);
      System.out.print("Probability of plane arriving to the landing queue for each minute: ");
      System.out.println(landProb);
      System.out.println("Max time in the air before crashing: " + maxTimeInAir);
      System.out.println("Total simulation minutes: " + totalTime + "\n"); 
      if (takeOffTime <= 0 || departProb < 0 || departProb > 1 || totalTime < 0)
         throw new IllegalArgumentException("Values out of range"); 

      for(currentMinute = 0; currentMinute <= totalTime; currentMinute++)
        {
            if(departureProb.query() == true)
            {
                totalDepartures++;
                departures.add(currentMinute);
            }
            
            if(arrivalProb.query() == true)
            {
                totalLandings++;
                landings.add(currentMinute);
            }

            if(singleRunway.runWayIsFull() == false && (departures.isEmpty() == false || landings.isEmpty() == false))
            {
                if(landings.isEmpty())
                {
                    next = departures.remove();
                    whatType = 'D';
                    singleRunway.useRunway(whatType);
                    departWaitTimes.addNumber(currentMinute - next);
                }
                else
                {
                    next = landings.remove();
                    whatType = 'L';
                    if(currentMinute - next <= maxTimeInAir)
                    {
                        singleRunway.useRunway(whatType);
                        landWaitTimes.addNumber(currentMinute - next);
                    }
                    else
                    {
                        totalCrashes++;
                        if(landings.isEmpty() == false)
                        {
                            next = landings.remove();
                            whatType = 'L';
                            singleRunway.useRunway(whatType);
                            landWaitTimes.addNumber(currentMinute - next);
                        }
                    }
                }
            }
            singleRunway.reduceTime();
      }
      DecimalFormat df = new DecimalFormat();
      df.setMaximumFractionDigits(2);
      System.out.println("Output:" + "\n");
      System.out.println("Planes departed: " + departWaitTimes.howManyNumbers( )); 
      if (departWaitTimes.howManyNumbers( ) > 0)
         System.out.println("Average wait for departure: " + df.format(departWaitTimes.average( )) + " min");
      System.out.println("Planes arrived: " + landWaitTimes.howManyNumbers( )); 
      if (landWaitTimes.howManyNumbers( ) > 0)
         System.out.println("Average wait for landing: " + df.format(landWaitTimes.average( ) )+ " min");
      System.out.println("total planes crashed: " + totalCrashes);
   }
    
}
