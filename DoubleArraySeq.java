/* Omar Ramirez 
   CSC 103 Project 1 
*/

// File: DoubleArraySeq.java 

// This is an assignment for students to complete after reading Chapter 3 of
// "Data Structures and Other Objects Using Java" by Michael Main.


/******************************************************************************
* This class is a homework assignment;
* A DoubleArraySeq is a collection of double numbers.
* The sequence can have a special "current element," which is specified and 
* accessed through four methods that are not available in the bag class 
* (start, getCurrent, advance and isCurrent).
*
* <dt><b>Note:</b><dd>
*   (1) The capacity of one a sequence can change after it's created, but
*   the maximum capacity is limited by the amount of free memory on the 
*   machine. The constructor, addAfter, 
*   addBefore, clone, 
*   and concatenation will result in an
*   OutOfMemoryError when free memory is exhausted.
*   <p>
*   (2) A sequence's capacity cannot exceed the maximum integer 2,147,483,647
*   (Integer.MAX_VALUE). Any attempt to create a larger capacity
*   results in a failure due to an arithmetic overflow. 
*
* <dt><b>Note:</b><dd>
*   This file contains only blank implementations ("stubs")
*   because this is a Programming Project for my students.
*
* <dt><b>See:</b><dd>see
*   <A HREF="../../../../edu/colorado/collections/DoubleArraySeq.java">
*   Java Source Code for this class
*   (www.cs.colorado.edu/~main/edu/colorado/collections/DoubleArraySeq.java)
*   </A>
*
* @version
*   March 5, 2002
******************************************************************************/
public class DoubleArraySeq implements Cloneable
{
   // Invariant of the DoubleArraySeq class:
   //   1. The number of elements in the sequences is in the instance variable 
   //      manyItems.
   //   2. For an empty sequence (with no elements), we do not care what is 
   //      stored in any of data; for a non-empty sequence, the elements of the
   //      sequence are stored in data[0] through data[manyItems-1], and we
   //      donít care whatís in the rest of data.
   //   3. If there is a current element, then it lies in data[currentIndex];
   //      if there is no current element, then currentIndex equals manyItems. 
   private double[ ] data;
   private int manyItems;
   private int currentIndex; 
   
   /**
   * Initialize an empty sequence with an initial capacity of 10.  Note that
   * the addAfter and addBefore methods work
   * efficiently (without needing more memory) until this capacity is reached.
   * <dt><b>Param-none:</b><dd>
   * <dt><b>Postcondition:</b><dd>
   *   This sequence is empty and has an initial capacity of 10.
   * <dt><b>Exception OutOfMemoryError:</b><dd>
   *   Indicates insufficient memory for: 
   *   new double[10].
   **/   
   public DoubleArraySeq( ) {
      final int INITIAL_CAPACITY = 10; 
      //manyItems and currentIndex both start at 0 
      manyItems = 0;     
      currentIndex = 0;
      data = new double[INITIAL_CAPACITY];  // sets the initial capacity of data[] to 10
   } 
     

   /**
   * Initialize an empty sequence with a specified initial capacity. Note that
   * the addAfter and addBefore methods work
   * efficiently (without needing more memory) until this capacity is reached.
   * <dt><b>Param initialCapacity:</b><dd>
   *   the initial capacity of this sequence
   * <dt><b>Precondition:</b><dd>
   *   initialCapacity is non-negative.
   * <dt><b>Postcondition:</b><dd>
   *   This sequence is empty and has the given initial capacity.
   * <dt><b>Exception IllegalArgumentException:</b><dd>
   *   Indicates that initialCapacity is negative.
   * <dt><b>Exception OutOfMemoryError:</b><dd>
   *   Indicates insufficient memory for: 
   *   new double[initialCapacity].
   **/   
   public DoubleArraySeq(int initialCapacity) {
      //will not allow array to be less than 0
      if(initialCapacity < 0){
         throw new IllegalArgumentException("initialCapacity is negative: " + initialCapacity);
      }
      manyItems = 0;
      currentIndex = 0;
      data = new double[initialCapacity];
   }
        
   /**
   * Add a new element to this sequence, after the current element. 
   * If the new element would take this sequence beyond its current capacity,
   * then the capacity is increased before adding the new element.
   * <dt><b>Param element:</b><dd>
   *   the new element that is being added
   * <dt><b>Postcondition:</b><dd>
   *   A new copy of the element has been added to this sequence. If there was
   *   a current element, then the new element is placed after the current
   *   element. If there was no current element, then the new element is placed
   *   at the end of the sequence. In all cases, the new element becomes the
   *   new current element of this sequence. 
   * <dt><b>Exception OutOfMemoryError:</b><dd>
   *   Indicates insufficient memory for increasing the sequence's capacity.
   * <dt><b>Note:</b><dd>
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause the sequence to fail with an
   *   arithmetic overflow.
   **/
   public void addAfter(double element) {
      if(manyItems == data.length){       //ensuring there is space for new values inputted into array
         ensureCapacity(manyItems*2 + 1);    
      }   
      if(!isCurrent()){                     
         currentIndex = 0;                            //same as addBefore but not including the current element in the shift up
      } else {                                        //by increasing the currentIndex
         currentIndex++;    
      }                  
      for(int i = manyItems; i > currentIndex; i--){  
         data[i] = data[i-1];
      } 
      manyItems++;                     //increasing the number of doubles in array
      data[currentIndex] = element;    //setting new element to be current element 
   }
   
   /**
   * Add a new element to this sequence, before the current element. 
   * If the new element would take this sequence beyond its current capacity,
   * then the capacity is increased before adding the new element.
   * <dt><b>Param element:</b><dd>
   *   the new element that is being added
   * <dt><b>Postcondition:</b><dd>
   *   A new copy of the element has been added to this sequence. If there was
   *   a current element, then the new element is placed before the current
   *   element. If there was no current element, then the new element is placed
   *   at the start of the sequence. In all cases, the new element becomes the
   *   new current element of this sequence. 
   * <dt><b>Exception OutOfMemoryError:</b><dd>
   *   Indicates insufficient memory for increasing the sequence's capacity.
   * <dt><b>Note:</b><dd>
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause the sequence to fail with an
   *   arithmetic overflow.
   **/
   public void addBefore(double element) { 
      if(manyItems == data.length){
         ensureCapacity(manyItems*2 + 1);
      }
      if(!isCurrent()){    //to set current at beginning 
         currentIndex = 0;
      } 
      for(int i = manyItems; i > currentIndex; i--){     //for loop to shift the values including the current element 
         data[i] = data[i-1];                            //to the right and making space for the new element
      }
      data[currentIndex] = element;
      manyItems++;
   }
   
   
   /**
   * Place the contents of another sequence at the end of this sequence.
   * <dt><b>Param addend:</b><dd>
   *   a sequence whose contents will be placed at the end of this sequence
   * <dt><b>Precondition:</b><dd>
   *   The parameter, addend, is not null. 
   * <dt><b>Postcondition:</b><dd>
   *   The elements from addend have been placed at the end of 
   *   this sequence. The current element of this sequence remains where it 
   *   was, and the addend is also unchanged.
   * <dt><b>Exception NullPointerException:</b><dd>
   *   Indicates that addend is null. 
   * <dt><b>Exception OutOfMemoryError:</b><dd>
   *   Indicates insufficient memory to increase the size of this sequence.
   * <dt><b>Note:</b><dd>
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause an arithmetic overflow
   *   that will cause the sequence to fail.
   **/
   public void addAll(DoubleArraySeq addend) {
      //throwing exception if addend is null
      if(addend == null){     
         throw new NullPointerException("addend is null, so addAll may not be called.");
      }
      ensureCapacity(manyItems + addend.manyItems); //increasing the capacity of data[] by adding number of values in the adddend 
      
      System.arraycopy(addend.data, 0, data, manyItems, addend.manyItems); //copying the array to the end of data[]
      manyItems += addend.manyItems;
   }   
   
   /**
   * Add a new element at the front of the sequence.
   * <dt><b>Param element:</b><dd> 
   *   The new element to be added 
   * <dt><b>Postcondition:</b><dd>
   *   A new copy of the element has been added to the front of this sequence.
   *   The new element becomes the new current element of this sequence. 
   **/

   public void addFront(double element) {
      start();                //setting the current index to 0 
      addBefore(element);     //to add a new element before all the other elements.
   }
      
           
   /**
   * Move forward, so that the current element is now the next element in
   * this sequence.
   * <dt><b>Param - none</b><dd>
   * <dt><b>Precondition:</b><dd>
   *   isCurrent() returns true. 
   * <dt><b>Postcondition:</b><dd>
   *   If the current element was already the end element of this sequence 
   *   (with nothing after it), then there is no longer any current element. 
   *   Otherwise, the new element is the element immediately after the 
   *   original current element.
   * <dt><b>Exception IllegalStateException:</b><dd>
   *   Indicates that there is no current element, so 
   *   advance may not be called.
   **/
   public void advance( ) {
      if(!isCurrent()){
         throw new IllegalStateException("There is no current element, so advance may not be called.");
      }
      currentIndex++; //advancing in the sequence by increasing the currentIndex
   }
   
   
   /**
   * Generate a copy of this sequence.
   * <dt><b>Param - none</b><dd>
   * <dt><b>Return:</b><dd>
   *   The return value is a copy of this sequence. Subsequent changes to the
   *   copy will not affect the original, nor vice versa.
   * <dt><b>Exception OutOfMemoryError:</b><dd>
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public DoubleArraySeq clone( )
   {  // Clone a DoubleArraySeq object.
      DoubleArraySeq answer;
      
      try
      {
         answer = (DoubleArraySeq) super.clone( );
      }
      catch (CloneNotSupportedException e)
      {  // This exception should not occur. But if it does, it would probably
         // indicate a programming error that made super.clone unavailable.
         // The most common error would be forgetting the "Implements Cloneable"
         // clause at the start of this class.
         throw new RuntimeException
         ("This class does not implement Cloneable");
      }
      
      answer.data = data.clone( );
      
      return answer;
   }
   
   /**
   *  Method that returns true if sequence is the same length, order and data. 
   * <dt><b>Param - Object obj:</b><dd>
   *   A sequence to compare to our data[] sequence
   * <dt><b>Return:</b><dd> 
   *   Boolean expression if sequence is the same length, order, and data.
   **/
   public boolean equals(Object obj){                          
      boolean result = false;
      if (obj instanceof DoubleArraySeq){
         DoubleArraySeq candidate = (DoubleArraySeq) obj;         //overriding method 
         if (candidate.size() == size()){
            for(int i = 0; i < manyItems; i++){                   //boolean will only return true if the size of the arrays are the same
               if(candidate.getCurrent() == getCurrent()){        //and if each of the elements in the sequence are all equal to each other 
                  result = true;                                  //in the same location
               }
            }
         }
      }
      return result;
   }
   /**
   * Create a new sequence that contains all the elements from one sequence
   * followed by another.
   * <dt><b>Param s1:</b><dd>
   *   the first of two sequences
   * <dt><b>Param s2:</b><dd>
   *   the second of two sequences
   * <dt><b>Precondition</b><dd>
   *   Neither s1 nor s2 is null.
   * <dt><b>Return:</b><dd>
   *   a new sequence that has the elements of s1 followed by the
   *   elements of s2 (with no current element)
   * <dt><b>Exception NullPointerException:</b><dd>
   *   Indicates that one of the arguments is null.
   * <dt><b>Exception OutOfMemoryError:</b><dd>
   *   Indicates insufficient memory for the new sequence.
   * <dt><b>Note</b><dd>
   *   An attempt to create a sequence with a capacity beyond
   *   Integer.MAX_VALUE will cause an arithmetic overflow
   *   that will cause the sequence to fail.
   **/   
   public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2) {
      //throwing exception if either array that is being added is null
      if(s1 == null || s2 == null){
         throw new NullPointerException("At least one of the arguments is null.");
      }
      DoubleArraySeq s3 = new DoubleArraySeq(s1.getCapacity() + s2.getCapacity());     //creating a new array with the capacity of the other arrays combines
      System.arraycopy(s1.data, 0, s3.data, 0, s1.manyItems);                          //copying the data from both arrays, one after the other
      System.arraycopy(s2.data, 0, s3.data, s1.manyItems, s2.manyItems);               //onto the new array
      
      s3.manyItems = s1.manyItems + s2.manyItems;                                      //increasing the stated number of elements in s3 
      s3.currentIndex = s3.manyItems;                                                  //and setting the currentIndex of s3 to manyItems
      return s3; 
   }

   /**
   * Change the current capacity of this sequence.
   * <dt><b>Param minimumCapacity:</b><dd>
   *   the new capacity for this sequence
   * <dt><b>Postcondition:</b><dd>
   *   This sequence's capacity has been changed to at least minimumCapacity.
   *   If the capacity was already at or greater than minimumCapacity,
   *   then the capacity is left unchanged.
   * <dt><b>Exception OutOfMemoryError:</b><dd>
   *   Indicates insufficient memory for: new int[minimumCapacity].
   **/
   public void ensureCapacity(int minimumCapacity) {
      if(data.length < minimumCapacity){                             //creating a new array with bigger capacity
         double[] biggerArray = new double[minimumCapacity];         //if data[] is not large enough.
         System.arraycopy(data, 0, biggerArray, 0, manyItems);       //copying contents of data[] to biggerArray[].
         data = biggerArray;                                         //now both arrays point to the same address
      } 
   }

   
   /**
   * Accessor method to get the current capacity of this sequence. 
   * The add method works efficiently (without needing
   * more memory) until this capacity is reached.
   * <dt><b>Param - none</b><dd>
   * <dt><b>Return:</b><dd>
   *   the current capacity of this sequence
   **/
   public int getCapacity( ) {
      return data.length; //to return the current capacity
   }

   /**
   * Accessor method to get the current element of this sequence. 
   * <dt><b>Param - none</b><dd>
   * <dt><b>Precondition:</b><dd>
   *   isCurrent() returns true.
   * <dt><b>Return:</b><dd>
   *   the current element of this sequence
   * <dt><b>Exception IllegalStateException:</b><dd>
   *   Indicates that there is no current element, so 
   *   getCurrent may not be called.
   **/
   public double getCurrent( ) {
      if (!isCurrent()){
         throw new IllegalStateException("There is no current element, so getCurrent may not be called.");
      }
      return data[currentIndex]; //will return the current element 
   }
   
   /** 
   * Setter method to set the current element of this sequence.
   * <dt><b>Param - n:</b><dd>
   *   The 'nth' element to become the current element.
   * <dt><b>Precondition:</b><dd>
   *   isCurrent() returns true.
   * <dt><b>Exception IllegalStateException:</b><dd>
   *   Indicates the sequence is empty.
   * <dt><b>Exception IllegalArgumentException:</b><dd>
   *   Indicates n does not represent a valid location in the array.
   **/
   public void setCurrent(int n) { 
      if(manyItems == 0){                                                  //throws exceptions if the sequence is empty(manyItems == 0)
         throw new IllegalStateException("The sequence is empty.");        //and if n > manyItems 
      } else if (n > manyItems){
         throw new IllegalArgumentException("n does not represent a valid location in the array.");
      } else {
         currentIndex = (n-1);                                             //otherwise, returns the location by decrementing it and setting it equals
      }                                                                    //to the current index
   }
   /**
   * Accessor method to determine whether this sequence has a specified 
   * current element that can be retrieved with the 
   * getCurrent method. 
   * <dt><b>Param - none</b><dd>
   * <dt><b>Return:</b><dd>
   *   true (there is a current element) or false (there is no current element at the moment)
   **/
   public boolean isCurrent( ) {
      if (currentIndex == manyItems){  //invariant states there is no current element when 
         return false;                 //currentIndex is equal to manyItems
      } else {
         return true;
      }
   }
   
   /**
   * Accessor method that returns the nth element of the sequence.
   * <dt><b>Param - n</b><dd> 
   *   The nth element of the sequence.
   * <dt><b>Precondition:</b><dd> 
   *   isCurrent() returns true.
   * <dt><b>Postcondition:</b><dd>
   *   Returns the nth element of the sequence, and makes 
   *   the current element this nth element.
   * <dt><b>Exception IllegalStateException:</b><dd>
   *   Indicates the sequence is empty.
   * <dt><b>Exception IllegalArgumentException:</b><dd>
   *   Indicates n is greater than the sequence size, 
   *   or n is zero of negative.
   **/
   
   public double getElement(int n) { 
      setCurrent(n);
      return getCurrent();
   }  
              
   /**
   * Remove the current element from this sequence.
   * <dt><b>Param - none</b><dd>
   * <dt><b>Precondition:</b><dd>
   *   isCurrent() returns true.
   * <dt><b>Postcondition:</b><dd>
   *   The current element has been removed from this sequence, and the 
   *   following element (if there is one) is now the new current element. 
   *   If there was no following element, then there is now no current 
   *   element.
   * <dt><b>Exception IllegalStateException:</b><dd>
   *   Indicates that there is no current element.
   **/
   public void removeCurrent( ) {
      if(isCurrent()){
         for(int i = currentIndex + 1; i < manyItems; i++){ //removing a value by shifting all elements 
            data[i-1] = data[i];                            //to the left 
         }                                                  //and the current element is the index at which 
         manyItems--;                                       //the value was removed which the next element 
      } else {                                              //is now on
         throw new IllegalStateException("There is no current element to be removed.");
      }
   }
   /**
   * Remove the front element from this sequence.
   * <dt><b>Param - none</b><dd>
   * <dt><b>Precondition:</b><dd>
   *   isCurrent() returns true.
   * <dt><b>Postcondition:</b><dd>
   *   The element in the front of this sequence has been removed. If there
   *   was only one element, it got removed and made null, otherwise, the
   *   current element is the new front element.
   * <dt><b>Exceptions IllegalStateException:</b><dd>
   *   Indicates the sequence is empty.
   **/   
   public void removeFront( ) {
      start();             
      removeCurrent();
   }
                 
   /**
   * Determine the number of elements in this sequence.
   * <dt><b>Param - none</b><dd>
   * <dt><b>Return:</b><dd>
   *   the number of elements in this sequence
   **/ 
   public int size( ) {
      return manyItems;
   }
   
   /**
   * Set the current element at the front of this sequence.
   * <dt><b>Param - none</b><dd>
   * <dt><b>Postcondition:</b><dd>
   *   The front element of this sequence is now the current element (but 
   *   if this sequence has no elements at all, then there is no current 
   *   element).
   **/ 
   public void start( ) {
      currentIndex = 0;  //the first value in sequence is now current element 
   } 
   
   
   /**
   * Reduce the current capacity of this sequence to its actual size (i.e., the
   * number of elements it contains).
   * <dt><b>Param - none</b><dd>
   * <dt><b>Postcondition:</b><dd>
   *   This sequence's capacity has been changed to its current size.
   * <dt><b>Exception OutOfMemoryError:</b><dd>
   *   Indicates insufficient memory for altering the capacity. 
   **/
   public void trimToSize( ) {
      double[ ] trimmedArray;
      
      if (data.length != manyItems)
      {
         trimmedArray = new double[manyItems];                    //copying new smaller array with the manyItems of 
         System.arraycopy(data, 0, trimmedArray, 0, manyItems);   //data[] and setting it equal to data[]
         data = trimmedArray;
      }
   }
   
   /**
   * Creates a string of all elements in order separated by a space
   * <dt><b>Param - none</b><dd>
   * <dt><b>Postcondition:</b><dd>
   *    All the elements in this sequence will be printed with a space in between.
   * <dt><b>Exception IllegalStateException:</b><dd>
   *    Indicates sequence is empty.
   **/ 
   public String toString() {
     if(!isCurrent() && manyItems == 0){
         throw new IllegalStateException("Sequence is empty, so toString may not be called.");
     }
     String print = "";                      //since these methods will change what the current element is,
     int current = currentIndex;             //the variable current will make sure the index is changed back to original value
     for(start(); isCurrent(); advance()){   
         print += data[currentIndex] + " ";  //for loop to get the values of all elements 
     }                                       //and add them to the variable print
     currentIndex = current;
     return print;   
   }

}//end of class DoubleArraySeq
           
