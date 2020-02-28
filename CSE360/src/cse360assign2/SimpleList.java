/**
 * Benjamin To
 * Class ID: 326
 * Assignment #2
 * The purpose of this class is to create a ten element array that can be
 * manipulated in the following ways: a number can be added to the 
 * beginning, all occurrences of a number can be removed from the array, 
 * the array can be searched to find the FIRST occurrence of an element,
 * the array can be printed as a string, and the number of elements in the
 * array can be seen.
 */

package cse360assign2;

public class SimpleList 
{
	/**
	 * An array named "list" is created
	 * It will be initialized in the constructor below 
	 * An integer called "count" is declared
	 * This will keep track of the number of elements added and removed from
	 * the array, but it can never exceed 10 
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
	 * All elements in the list beforehand are shifted to the right by one 
	 * Count is incremented by one unless the condition below is met:
	 * If there are already ten elements in the array, the last one is
	 * "deleted" and count remains at 10.
	 * 
	 * @param 	newElement 	the new integer that will be added to 
	 * 						the front of the array
	 */
	public void add(int newElement)
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
		
		//Checking to see if there are already 10 elements in "list"
		if(count == 10)
			count = 10;
		else
			count++;
	}
	
	/**
	 * Removes the first occurrence of the parameter from "list"
	 * Any elements on the right side of the removed element are shifted
	 * to the left. 
	 * Count is decremented by one
	 * If the element is not in the list, none of the above steps occur
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
	 * in the 10 element array (list). 
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

}
