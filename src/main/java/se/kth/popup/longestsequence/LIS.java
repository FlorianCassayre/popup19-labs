/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.longestsequence;

import java.util.Arrays; 

public final class LIS
{
	int[] sequence;
	int lis_length;
	int[] last_indexes;
	int[] previous_indexes;
	
	/**
	 * Constructor method. Creates an array for storing the endings of the candidate subsequences and
	 * another one for storing the index of the previous number in any stored subsequence (this will 
	 * be necessary for returning the numbers in the LIS). 
	 * @param sequence The sequence to compute.
	 */
	public LIS(int[] sequence)
	{
		this.sequence = sequence;
		this.lis_length = 1;
		this.last_indexes = new int[sequence.length];
		this.previous_indexes = new int[sequence.length];
		Arrays.fill(previous_indexes, -1);
	}
		
	/**
	 * Binary searches among the endings of the stored subsequences for a given key, get the minimum 
	 * of those that is higher than said key and replace that ending value for it.
	 * @param num The number we are looking for between the endings of the stored subsequences.
	 * @return The index in the array of stored LIS endings that is going to be replaced.
	 */
	private int BinarySearch(int num) 
	{ 
		int max_interval = lis_length - 1;
		int min_interval = -1;
		
		while (max_interval - min_interval > 1) 
		{ 
			int median = min_interval + (max_interval - min_interval) / 2; 
			
			if (sequence[last_indexes[median]] >= num) 
				max_interval = median; 
			else
				min_interval = median; 
		} 
		return max_interval; 
	} 
	
	/**
	 * Computes the index in the sequence for the last LIS element and goes 
	 * backwards for the rest of indexes by checking which number comes before it
	 * in the LIS. Repeats this process until computing the whole LIS.
	 * @return the indexes of the sequence that belong to the LIS in reverse order.
	 */
	private int[] outputLIS()
	{
		int[] result = new int[lis_length];
		int counter = 0;
		for (int i = last_indexes[lis_length - 1]; i >= 0; i = previous_indexes[i]) 
		{
			result[counter] = i;
			counter++;
		}		
		return result;
	}
	
	/**
	 * Computes a certain number in the sequence and compares it to the stored
	 * subsequences. 4 cases can occur:
	 * 1) If it is lower than the beginning of the lowest subsequence, create a new 
	 * subsequence starting with this number.
	 * 2) If it is higher than the maximum of the longest stored 
	 * subsequence, extend it with this number.
	 * 3) If it is equal to the beginning of the lowest subsequence or the maximum 
	 * of the longest stored subsequence, do nothing.
	 * 4) If none of the above, binary search the endings of the stored subsequences,
	 * get the minimum of those that is higher than the sequence number and replace that 
	 * ending value for it.
	 * @param i The index of the sequence that is being computed.
	 */
	private void computeSequenceValue(int i)
	{
		if (sequence[i] < sequence[last_indexes[0]]) 
		{
			last_indexes[0] = i; 
		}
		
		else if (sequence[i] > sequence[last_indexes[lis_length - 1]]) 
		{ 
			previous_indexes[i] = last_indexes[lis_length - 1]; 
			last_indexes[lis_length++] = i; 
		} 
		
		else if (sequence[i] > sequence[last_indexes[0]] && sequence[i] < sequence[last_indexes[lis_length - 1]])
		{
			int pos = BinarySearch(sequence[i]); 
			previous_indexes[i] = last_indexes[pos - 1]; 
			last_indexes[pos] = i; 
		} 
	}

	/**
	 * Main method. Computes a sequence and calculates its LIS.
	 * @return the indexes of the sequence that belong to the LIS in reverse order.
	 */
	public int[] getLongestIncreasingSubsequence() 
	{ 				
		for (int i = 1; i < sequence.length; i++) 
		{ 
			computeSequenceValue(i);
		} 
		return outputLIS();
	} 
} 
