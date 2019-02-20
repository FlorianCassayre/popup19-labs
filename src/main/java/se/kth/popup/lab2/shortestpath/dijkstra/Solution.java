/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */

package se.kth.popup.lab2.shortestpath.dijkstra;


public final class Solution
{
    public final int n;
    public final int[] distances, parents;

    public Solution(int n, int[] distances, int[] parents)
    {
        this.n = n;
        this.distances = distances;
        this.parents = parents;
    }
}