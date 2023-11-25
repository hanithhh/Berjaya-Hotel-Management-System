# Berjaya-Hotel-Management-System

  public class LinkedList
{
   Node first;
   Node current;
   Node last;
   
   //default constructor
   public LinkedList()
   {
      first=current=last = null;
   }
   
  // check whether list is empty
   public boolean isEmpty()
   {
      return (first == null);
   }
   
 // insert at the front of list
   public void insertAtFront(Object insertItem)
   {
      Node newNode = new Node(insertItem); 
     //create new node with value received
      
      if (isEmpty())
      {
         first = newNode;
         last = newNode;
      }
      else
      {
         newNode.next = first;
         first = newNode;
      }
   }
   
   
  // insert at the end of list
   public void insertAtBack(Object insertItem)
   {
      Node newNode = new Node(insertItem);
      
      if(isEmpty())
      {
         first = newNode;
         last = newNode;
      }
      else
      {
         last.next = newNode;
         last = newNode;
      }
   }
   
   
 // delete element from front
   public Object removeFromFront()
   {
      Object removeItem = null;
      
      if(isEmpty())
      {
         return removeItem;
      }
      
      removeItem = first.data;//point to first.data
      
      if(first == last)
      {
         first = null;
         last = null;
      }
      else
         first = first.next;
     return removeItem;
   }
   
   // delete element from second
   public Object removeFromSecond1()
   {
      Object removeItem = null;
      
      if(isEmpty())
      {
         return removeItem;
      }
      
      //point to second node data
      removeItem = first.next.data; 
           
      if(first == last)
      {
         first = null;
         last = null;
      }
      
      else
         first = first.next.next;
     return removeItem;
   }

   // delete element from second
   public Object removeFromSecond()
   {
      Object removeItem = null;
      
      if(isEmpty())
      {
         return removeItem;
      }
                 
      if(first.next==null)
      {
         return null;
         
      }
      
      else{
         //point to second node data
         current = first.next;
         first.next = current.next;
         removeItem = current.data; 
     }
     return removeItem;
     
   }
   
 // delete element from back
   public Object removeFromBack()
   {
      Object removeItem = null;
      
      if(isEmpty())
      {
         return removeItem;
      }
      
      removeItem = last.data;//store data should be remove
      
      if (first == last)
      {
         first = null;
         last = null;
      }
      else
      {
         current = first;
         while(current.next != last)
            current = current.next;
         last = current;
         last.next = null;
      }
      return removeItem;
   }
 
 // get the first node
   public Object getFirst()
   {
      if(isEmpty())
         return null;
      else
      {
         current = first;
         return current.data;
      }
   }
 
 //Return element of the next node pointed by current node
   public Object getNext()
   {
      if(current == last)
         return null;
      else
      {
         current = current.next;
         return current.data;
      }
   }

}//end class LinkedList user define
