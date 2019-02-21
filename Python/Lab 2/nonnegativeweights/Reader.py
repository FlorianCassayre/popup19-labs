############################################################
#                                                          #
#  DD2458 Problem Solving and Programming Under Pressure   #
#            Lab 2 - Non Negative Weights                  #
#          Eduardo Rodes Pastor (9406031931)               #
#                                                          #
############################################################

import sys
from Dijkstra import dijkstra 
from Graph import Graph

def getPath(d_node):
    path = []
    if distances.get(d_node) is not None:
        path.append(d_node)
        while (d_node != int(first_line[3])):
            parent = parents[d_node]
            path = [parent] + path
            d_node = parent
    return path

while (True):
    first_line = sys.stdin.readline().split()
    edges = {}
    neighbors = {}
    if (int(first_line[0]) == 0): break

    for i in range(int(first_line[1])):
        edge = sys.stdin.readline().split()
        o_edge = int(edge[0])
        d_edge = int(edge[1])
        weight = int(edge[2])
        sides = (o_edge, d_edge)
        if (o_edge != d_edge):
            if edges.get(sides) is None or weight < edges[sides]:
                edges[sides] = weight
            if neighbors.get(o_edge) is None:
                neighbors[o_edge] = [d_edge]
            else:
                neighbors[o_edge].append(d_edge)
    parents, distances = dijkstra(Graph(int(first_line[0]), edges, neighbors, int(first_line[3])))
    for i in range(int(first_line[2])):
        d_node = int(sys.stdin.readline())
        print distances[d_node] if distances.get(d_node) is not None else 'Impossible'
        #print getPath(d_node)
    print ''
