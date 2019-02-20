/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */

package se.kth.popup.lab2.shortestpath.dijkstra;


public final class Vertex implements Comparable<Vertex>
{
    public final int v, parent;
    public final int distance;

    public Vertex(int v, int parent, int distance)
    {
        this.v = v;
        this.parent = parent;
        this.distance = distance;
    }

    @Override
    public int compareTo(Vertex that)
    {
        return Integer.compare(this.distance, that.distance);
    }
}
