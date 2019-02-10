/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab1.longestsequence;

import se.kth.popup.Kattio;

public class LISReader 
{
	private static int[] getSequence(Kattio kattio)
	{
		int size = kattio.getInt();
    	int[] sequence = new int[size];
    	for (int i = 0; i < size; i++)
    	{
    		sequence[i] = kattio.getInt();
    	}
    	return sequence;
	}
	
	private static void printSolution(int[] lis)
	{
    	int lis_size = lis.length;
    	System.out.println(lis_size);
    	for (int i = lis_size - 1; i >= 0; i--)
    	{
        	System.out.print(lis[i] + " ");
    	}
    	System.out.println("");
	}
	
    public static void run(Kattio kattio) 
    {
        while (kattio.hasMoreTokens())
        {
        	LIS solver = new LIS(getSequence(kattio));
        	int[] lis = solver.getLongestIncreasingSubsequence();
        	printSolution(lis);
        }
        kattio.close();
    }

    public static void main(String[] args) 
    {
        run(new Kattio());
    }
}