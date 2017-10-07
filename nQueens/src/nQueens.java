//Michael Granovsky
import java.util.*;
import java.io.*;
public class NQueens {
    static Stack<Integer>stack = new Stack<Integer>();  
    static int n; 
    static int solution; 
    static int column = 0; 
    static int start;
    public static boolean solve(int n) {
        while(column <= n) { 
            boolean queenPlaced = false; 
            for(int row = start +1; row <= n; row++) {
                if(safty(row)) {
                    queenPlaced = true; 
                    stack.push(row);
                    column++;
                    if(column == n) {
                        solution++;
                        System.out.println("This is solution number: " + solution + " ");
                        print(stack); 
                    }
                    break;
                
                }
            }
            
            if(!queenPlaced) {
                if(stack.isEmpty()) 
                    return false; 
                start = (Integer) stack.pop(); 
                column--; 
            }
            else
                start = 0;
        }
        return false; 
    }
   
    public static boolean safty(int c) {
        for (int column = 0; column < stack.size(); column++) {
            if ((Integer) stack.get(column) == c)
                return false;
            if (Math.abs(c - (Integer) stack.get(column)) == stack.size() - column)
                return false;
        }
        return true;
    }
    public static void print(Stack <Integer> stack){
        for(int i = 0; i < stack.size(); i++ ) {
            for(int j = 0; j < stack.size(); j++) {
                if(j+1 == stack.get(i))
                    System.out.print(" Q ");
                else
                    System.out.print(" - ");
            }
            System.out.println();
        }
        
        System.out.println();
    }
    public static void main(String[] args) throws IOException {
        try{
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter a N value for this N problem: ");
        n = s.nextInt();
        solve(n);
        s.close();
        System.out.println("This N queens problem has " + solution +   " solutions.");
        }catch(InputMismatchException n) { 
            System.out.println("You did not enter a number!");
        }
    }

}
