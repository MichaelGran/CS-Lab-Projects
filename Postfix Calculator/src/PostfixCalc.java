// Michael Granovsky 
import java.io.*;
import java.util.*;
public class PostfixCalc {
  public static final String ADD = "+"; 
  public static final String MUL = "*";
  public static final String DIV = "/";
  public static final String SUB = "-";
  public static final String EXP = "^"; 
  public static final String NEG = "_";  //unary negation
  public static final String SQRT = "#"; // square root

	
  public static void main(String[] args) throws IOException {
	  System.out.println("Hello this is a postfix calculator");
	  calculateFile("in.dat");
	}
		
  public static void calculateFile(String fileName) throws IOException {
	  FileInputStream fis = new FileInputStream(fileName);
	  BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	   try {
		String line = null;
		while ((line = br.readLine()) != null) {
			//System.out.println(line); //used this to check if program was reading 
			System.out.println(line + " = " + calculate(line));
	   } 
       } catch (Exception e) { System.out.println("Error!");}

	 
		br.close();
		System.out.println("GoodBye!");
	}
    public static double calculate(String input) {
    	Stack<Double> stack = new Stack<Double>();
        String[] inputs = input.split(" ");

        return handleCalculation(stack, inputs);  
        
    }

    public static double handleCalculation(Stack<Double> stack, String[] el)  {
        double operand2, operand1;
        for(int i = 0; i < el.length; i++) {
            if( el[i].equals(ADD)||el[i].equals(SUB)||el[i].equals(MUL)||el[i].equals(DIV)||el[i].equals(EXP)||el[i].equals(SQRT)||el[i].equals(NEG)) {
                switch(el[i]) { 
                    case ADD: {
                    	operand2 = stack.pop();
                        operand1 = stack.pop();
                        stack.push(operand1 + operand2);
                        break;
                    }

                    case SUB: {
                    	operand2 = stack.pop();
                        operand1 = stack.pop();
                        stack.push(operand1 - operand2);
                        break;
                    }

                    case MUL: {
                    	operand2 = stack.pop();
                        operand1 = stack.pop();
                        stack.push(operand1 * operand2);
                        break;
                    }

                    case DIV: {
                    	operand2 = stack.pop();
                        operand1 = stack.pop();
                        stack.push(operand1 / operand2 );
                        break;
                    }
                    case EXP: {
                    	operand2 = stack.pop();
                        operand1 = stack.pop();
                        stack.push(Math.pow(operand1, operand2));
                        break; 
                        
                    }
                    case NEG: { 
                    	stack.push(-stack.pop());
                    	break;
                    }
                    case SQRT: { 
                        stack.push(Math.sqrt(stack.pop()));
                        break;
                    }

                    
            }
          }  
           
      
          else {
        		stack.push(Double.parseDouble(el[i]));

            }

        }
 

       return (double)stack.peek();
    }
    
 }
