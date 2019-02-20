/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */

package se.kth.popup.lab2.shortestpath.minspantree;


public final class Edge implements Comparable<Edge>
{
    public final int u, v, weight;

    public Edge(int u, int v, int weight)
    {
        this.u = Math.min(u, v);
        this.v = Math.max(u, v);
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge that)
    {
        return (this.u != that.u) ? Integer.compare(this.u, that.u) : Integer.compare(this.v, that.v);
    }
}