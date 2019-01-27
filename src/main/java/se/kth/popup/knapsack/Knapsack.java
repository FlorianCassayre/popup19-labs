/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.knapsack;

import java.util.List;
import java.util.ArrayList;


public final class Knapsack
{
	int capacity;
	int[] values;
	int[] weights;
	int n_items;
	int[][] value_matrix;
	
	/**
     * Creates a knapsack problem with certain parameters. We will 
     * use a value matrix where the rows are the items in the problem
     * and the columns are different weights from 1 to the knapsack 
     * capacity. We will solve individual knapsack problems for those 
     * weights and an increasing set of items, and apply dynamic 
     * programming to get the optimal items for the full problem. 
     * @param capacity the capacity in terms of weight
     * @param values the values of the items
     * @param weights the weights of the items
     */
	public Knapsack(int capacity, int[] values, int[] weights)
	{
		this.capacity = capacity;
		this.values = values;
		this.weights = weights;
		this.n_items = values.length;
		this.value_matrix = new int[n_items + 1][capacity + 1];
	}
	
	/**
     * This method generates an empty value matrix and initializes the 
     * first row and column to 0, since the value of a knapsack problem 
     * with no items or no capacity is 0.
     */
	private void setNullValuesInMatrix()
	{
    	for (int i = 0; i < capacity; i++) 
    	{ 
    		value_matrix[0][i] = 0; 
    	}
    	for (int i = 0; i < n_items; i++) 
    	{ 
    		value_matrix[i][0] = 0; 
    	}
	}
	
   /**
	* Gets the value of a cell using the following algorithm:
	* If the item weight is lower than the subproblem capacity, we 
	* determine whether it is worth it to take it or not. If we take 
	* it, we add its value to the value of the optimal set of items
	* for the remaining capacity (which will be already computed). If
	* we don't take it or the item weight is higher than the subproblem
	* capacity, the value for this capacity will not change and we will 
	* keep the previous optimal set of items for it.
    */
	private void computeCell(int item, int weight)
	{
		int value_without_item = value_matrix[item-1][weight];
		if (weights[item-1] <= weight)
		{
			int item_value = values[item-1];
			int remaining_weight_optimal_value = value_matrix[item-1][weight-weights[item-1]]; 	
			int value_with_item = item_value + remaining_weight_optimal_value;
			value_matrix[item][weight] = Math.max(value_with_item, value_without_item);
		}
		else
		{
			value_matrix[item][weight] = value_without_item;
		}
	}
	
	/**
	 * Fills an initialized value matrix with the optimal value for 
	 * every knapsack subproblem.
	 */
	private void fillMatrix()
	{
		setNullValuesInMatrix();
    	for (int item = 1; item < n_items + 1; item++)
    	{
			for (int weight = 1; weight < capacity + 1; weight++)
			{
				computeCell(item, weight);
			}
    	}
	}
	
	/**
	 * Gets the optimal set of items to choose in a knapsack problem
	 * after computing its value matrix. To do so, we look for the last 
	 * item taken (the one which got the optimal knapsack value for
	 * the first time), then substract its weight to the knapsack capacity
	 * and individual value to the whole knapsack value. Then we go to 
	 * the remaining capacity column and repeat this process until getting 
	 * all the item indexes.
	 * @return A list with all the optimal item indexes
	 */
	private List<Integer> getOptimalItemIndexes()
	{
    	List<Integer> item_indexes = new ArrayList<Integer>();
    	int remaining_value = value_matrix[n_items][capacity];
    	int remaining_capacity = capacity;
    	while (remaining_value > 0)
    	{
	    	for (int i = 1; i < n_items+1; i++)
	    	{
	    		if (value_matrix[i][remaining_capacity] == remaining_value)
	    		{
	    			item_indexes.add(i);
	    			remaining_value -= values[i-1];
	    			remaining_capacity -= weights[i-1];	
	    			break;
	    		}
	    	}
    	}
    	return item_indexes;
	}
	
	/**
     * Main method. Computes the value matrix for the full problem
     * and gets the optimal set of items to take in it.
     * @return a list of the chosen items for the reader to print
     */
    public List<Integer> solve()
    {
    	fillMatrix();
    	return getOptimalItemIndexes();
    }
}