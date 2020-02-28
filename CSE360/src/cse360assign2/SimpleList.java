/**
 * Benjamin To
 * Class ID: 326
 * Assignment #2
 * The purpose of this class is to create a ten element array that can be
 * manipulated in the following ways: a number can be added to the 
 * beginning (and the list size will increase by 50% if there is not 
 * enough room to add another element), the first occurrence of a number
 * can be removed from the array (and the list size will be decreased by
 * 25% if the list is at least 25% empty after the element is removed),
 * the array can be searched to find the FIRST occurrence of an element, 
 * the array can be printed as a string, and the number of elements in 
 * the array can be seen.
 */

package cse360assign2;

public class SimpleList 
{
	/**
	 * An array named "list" is created
	 * It will be initialized in the constructor below 
	 * An integer called "count" is declared
	 * This will keep track of the number of elements added and removed from
	 * the array.
	 */
	public int[] list;
	public int count;
	
	/**
	 * The instance fields/global variables are initialized.
	 * List is a ten element array that is filled with zeros.
	 * Count keeps track of the number of elements in the array
	 * and is also initialized to zero.
	 */
	public SimpleList()
	{
		list = new int[10];
		count = 0;
	}
	
	/**
	 * Adds the parameter to the beginning of "list"
	 * All elements in the list beforehand are shifted to the right by one. 
	 * Count is incremented by one. 
	 * If there is not enough room to add another element to the array, 
	 * the array's size is increased by 50%.
	 * 
	 * @param 	newElement 	the new integer that will be added to 
	 * 						the front of the array
	 */
	public void add(int newElement)
	{
		//Check to see if the array is full
		if(count == list.length)
		{
			//The array's new size is 50% larger
			int newSize = (int)(1.5 * list.length);
			//Create a new array temp of the same size as list currently
			int[] temp = new int[list.length];
			
			//Copy all elements from the array into temp
			//Since indexing begins at 0, we end at list.length - 1
			for(int index = 0; index < list.length; index++)
			{
				temp[index] = list[index];
			}
			
			list = new int[newSize];
			
			//Copy the elements in temp back to list at one position 
			//further than they were previously to make room for the
			//new element
			for(int index = 0; index < temp.length; index++)
			{
				list[index + 1] = temp[index];
			}
			
			//Add the new element (method parameter)
			list[0] = newElement;
			
		}
		else
		{
			/*
			 * The for loop shifts all elements by setting the one at "index"
			 * to the value of the element before it (at "index - 1"). 
			 * The loop ends at list.length-1 to avoid out-of-bounds exceptions
			 */
			for(int index = list.length - 1; index > 0; index--)
			{
				list[index] = list[index - 1]; 
			} 
			
			//The parameter/new number is set to the beginning of the array
			list[0] = newElement;
			
		}
		
		
		//Increment count
		count++;
		
	}
	
	/**
	 * Removes the first occurrence of the parameter from "list"
	 * Any elements on the right side of the removed element are shifted
	 * to the left. 
	 * Count is decremented by one
	 * If the element is not in the list, none of the above steps occur
	 * If "list" is at least 25% empty after an element has been 
	 * removed, then its size will be reduced by 25%
	 * 
	 * @param	removeThis	the first occurrence of this element in the array
	 * 						will be removed
	 */
	public void remove(int removeThis)
	{
		/*
		 * Track will store the index of the first occurrence
		 * Found will keep track of whether we found the parameter or not
		 */
		boolean found = false;
		int track = 0;
		
		for(int index = 0; index < list.length; index++)
		{
			if(list[index] == removeThis)
			{
				/*
				 * Since we are only removing the first occurrence
				 * of the parameter, this if statement will stop
				 * track from changing to a later index if there are 
				 * multiple occurrences of the same number
				 */
				if(found == false)
				{
					track = index;
					found = true;
				}
			}
		}
		
		/*
		 * The elements are shifted only if we found the parameter
		 * Count is decremented by 1
		 */
		if(found == true)
		{
			while(track < list.length - 1)
			{
				list[track] = list[track + 1];
				track++;
			}
			
			count--;
		}
		
		/*
		 * The values of length and count need to be cast to
		 * doubles in order to calculate and compare the value
		 * to 0.25 (25%). However, the list will decrease in 
		 * size by an integer value.
		 */
		double percentEmpty = ( ((double)(list.length) - (double)(count)
								) / (double)(list.length) );
		
		
		//"list" cannot be reduced to less than one entry
		if(list.length == 1)
		{
			
		}
		
		/*
		 * Check to see if the list is 25% empty by comparing the 
		 * number of empty spaces to list.length (the total number
		 * of spaces). It is calculated above by percentEmpty
		 */
		//else if( ((list.length - count)/list.length) >= 0.25)
		else if(percentEmpty >= 0.25)
		{
			/*
			 * list will be resized as an array that is 25% smaller
			 * Since integer division is used, the ceiling of 75%
			 * of the original array is required to reduce the array
			 * the correct number of elements
			 * i.e. 7/10 --> 7/8
			 */
			int newSize = (int)(Math.ceil(0.75 * list.length));
			
			//temp will save the values inside of list
			int[] temp = new int[list.length];
			
			for(int index = 0; index < list.length; index++)
			{
				temp[index] = list[index];
			}
			
			list = new int[newSize];
			
			//Copy the elements from temp back into list
			for(int index = 0; index < list.length; index++)
			{
				list[index] = temp[index];
			}
			
		}

	}
	
	/**
	 * Returns the number of elements in the array "list"
	 */
	public int count()
	{
		return count;
	}
	
	/**
	 * @return a String of the numbers in list with a space between each 
	 */
	public String toString()
	{
		String listAsString = "";
		
		for(int index = 0; index < count; index++)
		{
			listAsString += list[index] + " ";
		}
		
		/*
		 * Deletes the space after the last element
		 * If there are no elements in "list", then calling substring will
		 * cause an out of bounds error
		 */
		if(count != 0)
		{
			listAsString = listAsString.substring(0, listAsString.length() - 1);
		}
		
		return listAsString;
	}
	
	/**
	 * The parameter searchFor is an integer that this method will look for
	 * in the array (list). 
	 * If the element being looked for is found, its index will be returned.
	 * Otherwise, -1 will be returned. 
	 * In the event that there are multiple occurrences of the same number,
	 * the first one will be returned.
	 */
	public int search(int searchFor)
	{
		/*
		 * If the element is not in the array, -1 will be returned
		 * By having the loop go backwards, index will be changed to the
		 * first occurrence of the parameter if multiple cases exist
		 * Indices of an array are from 0 to one less than its length
		 */
		int location = -1;
		
		for(int index = list.length - 1; index > -1; index--)
		{
			if(list[index] == searchFor)
			{
				location = index;
			}
		}
		
		return location;
	}
	
	/**
	 * The parameter is a new integer that will be added to the end
	 * of "list". If "list" is not large enough to hold the elements
	 * (i.e. count+1 > list.length), then "list" is increased by 50%
	 * 
	 * @param element
	 */
	public void append(int element)
	{
		//Check to see if the array is full
		if(count == list.length)
		{
			//The array's new size is 50% larger
			int newSize = (int)(1.5 * list.length);
			//Create a new array temp of the same size as list currently
			int[] temp = new int[list.length];
			
			//Copy all elements from the array into temp
			//Since indexing begins at 0, we end at list.length - 1
			for(int index = 0; index < list.length; index++)
			{
				temp[index] = list[index];
			}
			
			list = new int[newSize];
			
			//Copy the elements in temp back to list at one position 
			//further than they were previously to make room for the
			//new element
			for(int index = 0; index < temp.length; index++)
			{
				list[index] = temp[index];
			}
			
		}
		
		//Add the new element to the end of list regardless of whether
		//its size was increased or not; increment count
		list[count] = element;
		count++;
		
	}
	
	/**
	 * If there are no elements in "list", negative one is returned
	 * Otherwise, the first element is returned
	 * @return the first element in "list"
	 */
	
	public int first()
	{
		if(count == 0)
			return -1;
		else 
			return list[0];
	}

	/**
	 * If there are no elements in "list", negative one is returned
	 * Otherwise, the last element is returned
	 * @return the last element in "list"
	 */
	public int last()
	{
		if(count == 0)
			return -1;
		else
			/*
			 * Indexing of arrays in Java begins at 0
			 * Therefore, the index of the last element in the array
			 * is located at count minus 1 rather than count itself
			 */
			return list[count - 1];
	}

	/**
	 * The current number of possible locations in list is
	 * equivalent to the length of "list" rather than the value
	 * of count. 
	 * @return the length of "list"
	 */
	public int size()
	{
		return list.length;
	}
	
}
