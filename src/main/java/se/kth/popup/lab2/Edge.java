/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */

package se.kth.popup.lab2;


public class Edge
{
    public final int u, v, t0, p, weight;

    /**
     * Constructor for an timed-constrained, weighted directed edge.
     * For every natural k, the node can be traversed at time <code>t = t0 + p * k</code>,
     * with a time cost d.
     * @param u the source vertex
     * @param v the destination vertex
     * @param t0 the time at which this edge opens
     * @param p the modular window size
     * @param d the time cost to traverse this edge
     */
    public Edge(int u, int v, int t0, int p, int d)
    {
        this.u = u;
        this.v = v;
        this.t0 = t0;
        this.p = Math.abs(p);
        this.weight = d;
    }

    /**
     * Constructor for a regular weighted directed edge.
     * @param u the source vertex
     * @param v the destination vertex
     * @param weight the weight
     */
    public Edge(int u, int v, int weight)
    {
        this(u, v, 0, 1, weight);
    }

    /**
     * Computes the closest available time slot, according to the constraints.
     * @param t the current time
     * @return the closest time if it exists or <code>Integer.MAX_VALUE</code> otherwise
     */
    public int nextAvailable(int t)
    {
        if (p == 0)
        {
            return t <= t0 ? t0 : Integer.MAX_VALUE;
        }
        else
        {
            final int max = Math.max(t0, t);
            return max + (p - ((max - t0) % p)) % p;
        }
    }
}
