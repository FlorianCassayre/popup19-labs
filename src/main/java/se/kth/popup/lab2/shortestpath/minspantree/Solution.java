/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */

package se.kth.popup.lab2.shortestpath.minspantree;

import java.util.Collections;
import java.util.List;


public final class Solution
{
    public final List<Edge> edges;
    public final int weight;

    public Solution(List<Edge> edges, int weight)
    {
        Collections.sort(edges);
        this.edges = Collections.unmodifiableList(edges);
        this.weight = weight;
    }
}
