//Michael Granovsky Lab 5
import java.util.*;
import java.io.*;
public class Calculator
{
	public static void main(String[] args) throws IOException
	{
		Scanner kb = new Scanner(new File("in.dat"));
		Scanner inText = new Scanner(System.in);
		ArrayList<String> inputStringLines = new ArrayList<String>();
        String[] textLine;
        TreeNode root;
        
		while(kb.hasNextLine())
		{
			try
			{
				inputStringLines.add(kb.nextLine());
			}
	        catch(NoSuchElementException E)
	        {
	        	System.out.println("NO INPUT LINES FOUND!");
	        	System.exit(0);
	        }	
		}
		
		// process input lines one working one at a time
		for(int index = 0; (index <= inputStringLines.size() - 1); index++)
		{
			// create array of type string from each element within <String>ArrayList. 
		    textLine = inputStringLines.get(index).split(" ");  // convert line of text to array of string.
		    
		    System.out.println("LINE '" + index + "' INPUT TEXT:");
		    for(String data: textLine)                          // (1) print expression before manipulated 
		    {
		    	System.out.print(data + " "); 
		    }
		    root = treeBuilder(textLine);                       // build expression tree for each line, and return the root
		    System.out.println("\n" + "\n" + "EXPRESSION TREE PRINTED: ");
		    printTree(root,0);  // print tree
		    System.out.println("\n" + "FULLY PARENTHESISED EXPRESSION: ");
		    System.out.println(fullyParenExpression(root));     // fully parenthesised expression
		    System.out.println("\n" + "OUTPUT VALUE: " + outputValue(root)); // calculate result
		                                                        
		    if((index < inputStringLines.size() - 1)) 
		    { 
		    	System.out.println("\n" + "Type <Enter> to continue ");
		    
		        if(!inText.next().equals("Enter")) {break;}
		    }
		}
  
		kb.close();
		inText.close();
	}
	
	public static String fullyParenExpression(TreeNode root)
	{
        if(checkIfOperator(root.returnData()))
		{
			return "(" + fullyParenExpression(root.returnLeft()) + " " + root.returnData() + " " + fullyParenExpression(root.returnRight()) + ")";
		}
		else
		{
			return root.returnData();
		}
		
	}
	
	public static double outputValue(TreeNode root)
	{
		String expression;
		if(root == null)
		{
			return 0;
		}
		else if(checkIfOperator(root.returnData()))
		{	
			expression = outputValue(root.returnLeft()) + " " + root.returnData() + " " + outputValue(root.returnRight()); 
		    String[] expressionArray = expression.split(" ");
			return calcValue(expressionArray);
		}
		else
		{
			if(root.returnData().compareTo(" ") == 0)
			{
				return 0;
			}
			else
			{
			   expression = root.returnData();
			   String[] expressionArray = expression.split(" ");
			   return calcValue(expressionArray);
			}
		}
		
	}
	
	private static double calcValue(String[] input)
	{
		double doubleOne;
		double doubleTwo;
		
		if(input.length == 1)
    	    return Double.parseDouble(input[0]);		
		
		switch(input[1])
		{
	        case "!":
	        if(Double.parseDouble(input[2]) == 0)
	        	 return 1;
	        
	        else
	        	return 0;
	        
		    
            case "^":
            doubleOne = Double.parseDouble(input[0]);
            doubleTwo = Double.parseDouble(input[2]);
            return Math.pow(doubleOne,doubleTwo);	
            	
            case "%":
            doubleOne = Double.parseDouble(input[0]);
            doubleTwo = Double.parseDouble(input[2]);
    		
            return doubleOne % doubleTwo;	
            case "+":
            doubleOne = Double.parseDouble(input[0]);
            doubleTwo = Double.parseDouble(input[2]);
            return doubleOne + doubleTwo;	
        	
            case "-":
            doubleOne = Double.parseDouble(input[0]);
            doubleTwo = Double.parseDouble(input[2]);
            return doubleOne - doubleTwo;	
            
            case "*":
            doubleOne = Double.parseDouble(input[0]);
            doubleTwo = Double.parseDouble(input[2]);
            return doubleOne * doubleTwo;
            
            case "/":
            doubleOne = Double.parseDouble(input[0]);
            doubleTwo = Double.parseDouble(input[2]);        		
            return doubleOne / doubleTwo;
            
            case "<":
            doubleOne = Double.parseDouble(input[0]);
            doubleTwo = Double.parseDouble(input[2]);
                        		
            if(doubleOne < doubleTwo)
            {
            	return 1;
            }
            else
            {
            	return 0;
            }
            case ">":
            doubleOne = Double.parseDouble(input[0]);
            doubleTwo = Double.parseDouble(input[2]);
                            		
            if(doubleOne > doubleTwo)
            {
                return 1;
            }
            else
            {
                return 0;
            }
            case ">=":
            doubleOne = Double.parseDouble((input[0]));
            doubleTwo = Double.parseDouble(input[2]);
                                		
            if(doubleOne >= doubleTwo)
            {
                return 1;
            }
            else
            {
                return 0;
            }
            case "<=":
            doubleOne = Double.parseDouble(input[0]);
            doubleTwo = Double.parseDouble(input[2]);
                                    		
            if(doubleOne <= doubleTwo)
            {
                return 1;
            }
            else
            {
                return 0;
            }	
            case "==":
            doubleOne = Double.parseDouble(input[0]);
            doubleTwo = Double.parseDouble(input[2]);
                                        		
            if(doubleOne == doubleTwo)
            {
                return 1;
            }
            else
            {
                return 0;
            }	
            case "!=":
            doubleOne = Double.parseDouble(input[0]);
            doubleTwo = Double.parseDouble(input[2]);
                                             		
            if(doubleOne != doubleTwo)
            {
                return 0;
            }
            else
            {
                return 1;
            }	
            case "&&":
            doubleOne = Double.parseDouble(input[0]);
            doubleTwo = Double.parseDouble(input[2]);
                                                 		
            if((doubleOne != 0) && (doubleTwo != 0))
            {
                    return 1;
            }
            else
            {
                    return 0;
            }	    
            case "||":
            doubleOne = Double.parseDouble(input[0]);
            doubleTwo = Double.parseDouble(input[2]);
                                                     		
            if((doubleOne != 0) || (doubleTwo != 0))
            {
                 return 1;
            }
            else
            {
                 return 0;
            }	
		}
		return 0;   
	}	
	private static boolean checkIfOperator(String data)
	{
		switch(data)
		{
		case "!":
	    return true;		
	    case "^":
	    return true;     
	    case "%":
	    return true;      
	    case "+":
	    return true;    
	    case "-":
	    return true;    
	    case "*":
	    return true;    
	    case "/":
	    return true;    
	    case "<":
	    return true;   
	    case ">":
	    return true;    
	    case ">=":
	    return true;    
	    case "<=":
	    return true;    
	    case "==":
	    return true;    
	    case "!=":
	    return true;    
	    case "&&":
	    return true;   
	    case "||":
	    return true; 
	    default:
	    return false;
		}
	}
	
	public static void printTree(TreeNode node, int level)
	{
		if(node != null)
		{
			printTree(node.returnRight(),(level + 1));
			StringBuilder space = spaceLength(level);
			System.out.println(space + node.returnData());
			printTree(node.returnLeft(),(level + 1));
		}
	}
	
	
	private static StringBuilder spaceLength(int level)
	{
		StringBuilder SB = new StringBuilder();
		String space = "      ";
		
		for(int index = 0; index < level; index++)
		{
			SB.append(space);
		}
		
		return SB;
	}
	public static TreeNode treeBuilder(String[] textLine)
	{
		Stack<TreeNode>nodeHolder = new Stack<TreeNode>();
		String currentString;
		TreeNode leftTreeNode,rightTreeNode,theNewNode;
				
		for(int index = 0; index < textLine.length; index++)
		{
			currentString = textLine[index];
		    switch (currentString)
		    {
		        case "!":
				rightTreeNode = nodeHolder.pop();
				leftTreeNode = new TreeNode(null,null," ");
				nodeHolder.push(new TreeNode(leftTreeNode,rightTreeNode,"!"));
		    	break;
		        case "^":
		        rightTreeNode = nodeHolder.pop();
				leftTreeNode = nodeHolder.pop();
				theNewNode = new TreeNode(leftTreeNode,rightTreeNode,currentString);
				nodeHolder.push(theNewNode); 
			    break;
		        case "%":
		        rightTreeNode = nodeHolder.pop();
			    leftTreeNode = nodeHolder.pop();
			    theNewNode = new TreeNode(leftTreeNode,rightTreeNode,currentString);
			    nodeHolder.push(theNewNode);  
				break;
		        case "+":
		        rightTreeNode = nodeHolder.pop();
		        leftTreeNode = nodeHolder.pop();
		        theNewNode = new TreeNode(leftTreeNode,rightTreeNode,currentString);
		        nodeHolder.push(theNewNode);
		    	break;
		        case "-":
		        rightTreeNode = nodeHolder.pop();
			    leftTreeNode = nodeHolder.pop();
			    theNewNode = new TreeNode(leftTreeNode,rightTreeNode,currentString);
			    nodeHolder.push(theNewNode);
		        break;
		        case "*":
		        rightTreeNode = nodeHolder.pop();
				leftTreeNode = nodeHolder.pop();
				theNewNode = new TreeNode(leftTreeNode,rightTreeNode,currentString);
				nodeHolder.push(theNewNode);
		        break;
		        case "/":
		        rightTreeNode = nodeHolder.pop();
			    leftTreeNode = nodeHolder.pop();
			    theNewNode = new TreeNode(leftTreeNode,rightTreeNode,currentString);
			    nodeHolder.push(theNewNode);
		        break;
		        case "<":
		        rightTreeNode = nodeHolder.pop();
				leftTreeNode = nodeHolder.pop();
				theNewNode = new TreeNode(leftTreeNode,rightTreeNode,currentString);
				nodeHolder.push(theNewNode);
		        break;
		        case ">":
		        rightTreeNode = nodeHolder.pop();
				leftTreeNode = nodeHolder.pop();
				theNewNode = new TreeNode(leftTreeNode,rightTreeNode,currentString);
				nodeHolder.push(theNewNode);
				break;
		        case ">=":
		        rightTreeNode = nodeHolder.pop();
				leftTreeNode = nodeHolder.pop();
				theNewNode = new TreeNode(leftTreeNode,rightTreeNode,currentString);
				nodeHolder.push(theNewNode);
				break;
		        case "<=":
		        rightTreeNode = nodeHolder.pop();
				leftTreeNode = nodeHolder.pop();
				theNewNode = new TreeNode(leftTreeNode,rightTreeNode,currentString);
				nodeHolder.push(theNewNode);
			    break;
		        case "==":
		        rightTreeNode = nodeHolder.pop();
				leftTreeNode = nodeHolder.pop();
				theNewNode = new TreeNode(leftTreeNode,rightTreeNode,currentString);
				nodeHolder.push(theNewNode);
				break;
		        case "!=":
		        rightTreeNode = nodeHolder.pop();
				leftTreeNode = nodeHolder.pop();
				theNewNode = new TreeNode(leftTreeNode,rightTreeNode,currentString);
				nodeHolder.push(theNewNode);
				break;
		        case "&&":
		        rightTreeNode = nodeHolder.pop();
				leftTreeNode = nodeHolder.pop();
				theNewNode = new TreeNode(leftTreeNode,rightTreeNode,currentString);
				nodeHolder.push(theNewNode);
			    break;
		        case "||":
		        rightTreeNode = nodeHolder.pop();
		        
			    leftTreeNode = nodeHolder.pop();
				theNewNode = new TreeNode(leftTreeNode,rightTreeNode,currentString);
				nodeHolder.push(theNewNode);
				break;
		        case "$":
		        index = textLine.length;
		        break;
		        default:
		        nodeHolder.push(new TreeNode(null,null,currentString));
		        break;
		        
		    }
		}
		
		return nodeHolder.pop();
	}

}
