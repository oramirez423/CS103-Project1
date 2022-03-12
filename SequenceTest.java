/* Omar Ramirez
   CSC 103 Project 1 
   This program SequenceTest will test the program DoubleArraySeq 
   in making sure all methods run correctly.
*/

import java.util.*;

class SequenceTest{
   public static void main (String[] args){
      boolean options = true;
      DoubleArraySeq data = new DoubleArraySeq();
      System.out.println("Welcome to my testing program for DoubleArraySeq.");
     
      while (options == true){                  //main menu will keep running 
         int menuOption = getMenuOption();      //and calling getMenuOption to get an 
         if(menuOption == 12) {                 //option until user enters 12
            options = false;                    //then program will end
         }
         //switch to run each option in the menu   
         switch(menuOption) { 
         
            case 1:  printingSequence(data);
                     break;
                                                      
            case 2:  reportingCapacity(data);
                     break;
            
            case 3:  settingCurrentElement(data); 
                     break;
                    
            case 4:  addingNumToFront(data); 
                     break;
            
            case 5:  addingNumToEnd(data);
                     break;
                    
            case 6:  addingBefore(data); 
                     break; 
                       
            case 7:  addingAfter(data);
                     break;
            
            case 8:  removingFirstNum(data); 
                     break;
            
            case 9:  deletingNumAtLocation(data); 
                     break;
                    
            case 10: displayingValueAtLocation(data);
                     break;
                     
            case 11: displayingLastElement(data);
                     break;
                                 
         } 
      }
      
      System.out.println("Program has ended. Thank you for testing it."); 
   }
   
   public static int getMenuOption(){
      Scanner menuInput = new Scanner(System.in);        //getMenuOption ensures user will never be able to crash program 
      boolean flag = true;                               //by only accepting integer numbers in the correct menu range. 
      int number = -1;
      while(flag==true){                                 //otherwise loop will continue prompting user to enter input 
         printMenu();          
         try{                         
            number = menuInput.nextInt();
            if (number < 1 || number > 12) {
               throw new Exception("Not an option. Please enter a menu option.");
            }
            flag = false;   
         } catch (InputMismatchException e) { 
            System.out.println("Invalid input. Please enter a menu option.");
            menuInput.nextLine();
         } catch (Exception e) {
            System.out.println(e.getMessage());
         }
      }
      menuInput.close();
      return number; //returns the menuOption
   }     
   
   public static int getLocation(){
      Scanner locationInput = new Scanner(System.in);          //getLocation makes sure user enters an n value 
      boolean flag2 = true;                                    //that is in the right range and is an integer
      int integer = -1;                                        //because getLocation will be the index of the data 
      while(flag2 == true){                                    //that user wants to access
         try{
            integer = locationInput.nextInt();
               if(integer < 1){
                  throw new Exception("Value outside of range. Try again.");
               }
            flag2 = false;
         } catch (InputMismatchException e) {
            System.out.println("Please enter an integer.");
            locationInput.nextLine();
         } catch (Exception e) {
            System.out.println(e.getMessage());
         }
      }
      locationInput.close();
      return integer;
   }
   
   public static double getDouble(){
      Scanner doubleInput = new Scanner(System.in);            //getDouble is for the user to enter any double and insert 
      boolean flag3 = true;                                    //it as an element in the array
      double element = 0;
      while(flag3 == true){                                    //will not accept strings or chars as input
         try{
            element = doubleInput.nextDouble();
            flag3 = false;
         } catch (InputMismatchException e) {
            System.out.println("Please enter an integer.");
            doubleInput.nextLine();
         }
      }
      doubleInput.close();
      return element;
   }
   
   //calls on the toString to print data's sequence             
   public static void printingSequence(DoubleArraySeq data){                
      try { 
         System.out.println("The Sequence:\n" + data.toString());
      //if sequence is empty, it will catch the exception
      } catch (IllegalStateException e) {                                 
         System.out.println("Sequence is currently empty.");   
      }
   }
   //reports the current capacity of the array
   public static void reportingCapacity(DoubleArraySeq data){                
      System.out.println("The current capacity is: " + data.getCapacity());
   }
   //allows user to set the current element in the array
   public static void settingCurrentElement(DoubleArraySeq data){
      System.out.println("Enter the nth element that you want to become the current element.");
      try{
         int value = getLocation();                                     
         data.setCurrent(value);                                        //calls on setCurrent() method with the integer value being passed in 
         System.out.println("You've changed the current element.");
  
      } catch (IllegalStateException e){                             //catches exception is sequence is empty
         System.out.println("The sequence is currently empty.");     //or if the number they enter is larger than the amount
                                                                     //of elements in the array
      } catch (IllegalArgumentException e){
         System.out.println("The number you entered does not represent a valid location in the array.");
      }
   }
   //adds a number to the front by calling the addFront() method 
   public static void addingNumToFront(DoubleArraySeq data){
      System.out.println("Enter the double you would like to add at the front of this sequence.");
      double value = getDouble();
      data.addFront(value);
      System.out.println(value + " has been added to the front of this sequence.");
   }
   //adds a number to the end of sequence by calling setCurrent() first
   //to get the size of the array and addAfter() to add the value at the end
   public static void addingNumToEnd(DoubleArraySeq data){
      System.out.println("Enter the double you would like to add at the end of this sequence.");
      double value = getDouble();
      try { 
         data.setCurrent(data.size());
         data.addAfter(value);
         System.out.println(value + " has been added to the end of this sequence.");
      } catch (IllegalStateException e){                                                  //if there is no current element because the sequence
         data.addAfter(value);                                                            //is empty, it will add a value anyways
         System.out.println(value + " has been added to the end of this sequence.");
      }
   }
   //adds a value before the current element by calling on addBefore() method
   public static void addingBefore(DoubleArraySeq data){
      System.out.println("Enter the double you would like to add before the current element.");
      double value = getDouble();
      data.addBefore(value);
      System.out.println(value + " has been added before the current element.");
   }
   //adds a value after the current element by calling on addAfter() method
   public static void addingAfter(DoubleArraySeq data){
      System.out.println("Enter the double you would like to add after the current element.");
      double value = getDouble();
      data.addAfter(value);
      System.out.println(value + " has been added after the current element.");
   }
   //removes the first number by calling removeFront() method
   public static void removingFirstNum(DoubleArraySeq data){
      try { 
         data.removeFront();
         System.out.println("First double from the sequence has been deleted.");
      } catch (IllegalStateException e){                                               //catches exception if sequence is empty because then 
         System.out.println("There is no current element that can be removed.");       //there won't be anything to remove
      }
   }
   //deletes a number at the user specified location by calling setCurrent() and removeCurrent() methods 
   public static void deletingNumAtLocation(DoubleArraySeq data){
      System.out.println("Enter the location of the double you want to delete.");
      try { 
         int value = getLocation();
         data.setCurrent(value);
         data.removeCurrent();
         System.out.println("The double has been removed.");
      } catch (IllegalStateException e) {                                       //catches the exception if location entered 
          System.out.println("There is no current element.");                   //is larger than the number of elements in array
      } catch (IllegalArgumentException e) {                                    //or there is no current element 
         System.out.println("n does not represent a valid location in the array.");
      }
   }
   //prints the value at a user specified location by calling getElement()
   public static void displayingValueAtLocation(DoubleArraySeq data){
      System.out.println("Enter the location of the double you want to display.");
      try { 
         int value = getLocation();
         System.out.println(data.getElement(value));
      } catch (IllegalStateException e) {
         System.out.println("There is no element at specified location.");  //catches exceptions if location inputted is invalid
      } catch (IllegalArgumentException e) { 
         System.out.println("The location is either greater than the sequence size, or is zero/negative.");
      }
   }
   //prints the last element in the sequence by calling setCurrent(), size(), and getCurrent()
   public static void displayingLastElement(DoubleArraySeq data){
      try { 
         data.setCurrent(data.size());
         System.out.println("The last element in the sequence is: " + data.getCurrent());          
      } catch (IllegalStateException e) {                                                    //catches exception if the sequence is empty
         System.out.println("The sequence is empty.");
  //    } catch (IllegalArgumentException e) { 
  //       System.out.println("The location is either greater than the sequence size, or is zero/negative.");
      }
   }
   
   public static void printMenu(){
      System.out.println();
      System.out.println("Choose an option:");
      System.out.println("1. Print out to screen the sequence.");    //uses toString()
      System.out.println("2. Report the capacity of sequence.");     //uses getCapacity()
      System.out.println("3. Set the current element location.");    //setCurrent(int)
      System.out.println("4. Add a number to the front of the sequence.");  //uses addFront(double)
      System.out.println("5. Add a number to the end of the sequence.");    //uses size(), setCurrent(int), and addAfter(double)
      System.out.println("6. Add a number before the current element.");    //uses addBefore(double)
      System.out.println("7. Add a number after the current element.");      //uses addAfter(double)
      System.out.println("8. Delete the first number from the sequence.");  //uses removeFront()
      System.out.println("9. Delete a number at a location.");              //uses setCurrent(int) and removeCurrent()
      System.out.println("10. Display the value at a certain location.");   //uses getElement(int)
      System.out.println("11. Display the last element in the sequence.");  //uses size(), setCurrent(int) and getCurrent()
      System.out.println("12. Quit");
   }      

}
