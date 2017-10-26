
//Michael Granovsky
import java.util.Scanner;

public class BinarySearchWithRecursive 
{
    static Scanner kb = new Scanner(System.in);
    static String inputForAnswers;
    static int countAttempts = 0;
    public static void main(String[] args) 
    {
        System.out.println("\n" + "Think of an integer between 1 and 1,000,000" + "\n");
        binarySearch(1,1000000);
        
    }
    
    public static void binarySearch(int low, int high)
    {
        if(low == high) 
        { 
            System.out.println("\n" + "Your number is: " + low + "/n"); 
            System.out.println("Sucessfully calculated after '" + countAttempts + "' attempt(s)!"); 
        }
        else  
        {
            int midpoint = (low + high) / 2;
            System.out.println("\n" + "Is your number '" + midpoint + "'?" + "\n");
            System.out.print("if so type 'yes'..any other responce will be interpreted as 'no':");
            inputForAnswers = kb.nextLine();
            countAttempts++;
            if(inputForAnswers.compareTo("yes") == 0) 
            { 
                System.out.println("\n" + "Your number is '" + midpoint + "'" + "\n"); 
                System.out.println("Sucessfully calculated after " + countAttempts + " attempt(s)!"); 
            }
            else
            {
                    System.out.print("\n" + "Is your number greater than or less than '" + midpoint + "'? type in 'greater' or 'less': ");
                    inputForAnswers = kb.nextLine();
                    if(inputForAnswers.compareTo("greater") == 0) 
                    { 
                        binarySearch((midpoint + 1), high);
                    }
                    else if(inputForAnswers.compareTo("less") == 0)
                    {
                        binarySearch(low, (midpoint - 1));

                    }
                    else
                    {
                        System.out.println("Invalid Command!" + "\n");
                    }
                
        }
      }
    }
}
