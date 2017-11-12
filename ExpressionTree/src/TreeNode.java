
public class TreeNode
{
   private TreeNode left;
   private TreeNode right;
   private String data;
   
   public TreeNode(TreeNode left,TreeNode right,String data)
   {
	   this.left = left;
	   this.right = right;
	   this.data = data;
   }
   
   public TreeNode returnLeft() { 
	   return left;   
	   }
   public TreeNode returnRight() {  
	   return right;  
	   }
   public String returnData() {   
	   return data;   
	   }
   
   public void setLeft(TreeNode left) {  
	   this.left = left;   
	   }
   public void setRight(TreeNode right) {  
	   this.right = right;   
	   }
   public void setData(String data) {  
	   this.data = data;   
	   }
   
   
   public void inOrderTraversal() 
   {    
	   System.out.println("In Order Traversal: ");
	   inOrderTraversal(this);
   }
   
   
   private void inOrderTraversal(TreeNode node)
   {
	   if(node != null)
	   {
		   inOrderTraversal(node.returnLeft());
		   System.out.println(node.returnData());
		   inOrderTraversal(node.returnRight());
	   }
   }
}
