############################################################
#                                                          #
#  DD2458 Problem Solving and Programming Under Pressure   #
#            Lab 2 - Non Negative Weights                  #
#          Eduardo Rodes Pastor (9406031931)               #
#                                                          #
############################################################

import heapq
from Status import Status

def dijkstra(graph):
    ''' 
    Calculates the shortest path from a starting node to any other node in a given graph 
    using the Dijkstra algorithm.
    :param int graph.n_vertices: The number of nodes in the graph
    :param dict graph.edges: A dictionary with the form {(edge_start, edge_end): edge_weight}
    :param dict graph.neighbors: A dictionary with the form {node: [adjacent_nodes]}
    :param int graph.origin: The starting node in the graph
    '''
    if (graph.neighbors.get(graph.origin) is None):
        return [graph.origin], {graph.origin: 0}
    status = Status(graph)
    computeOriginNode(graph, status)
    while status.priority_queue:
        computeNextNode(graph, status) 
    return status.parents, status.weights

def computeOriginNode(graph, status):
    '''
    Applies Dijkstra to the starting node and adds it to the visited set.
    :param dict graph.edges: A dictionary with the form {(edge_start, edge_end): edge_weight}
    :param dict graph.neighbors: A dictionary with the form {node: [adjacent_nodes]}
    :param int graph.origin: The starting node in the graph
    :param dict weights: A dictionary with the form {node: current distance to starting node}
    :param list parents: A list of size n_vertices with the current parent (or -1) for every node
    :param set visited: A set with all the visited nodes in the graph by the Dijkstra algorithm
    :param list priority_queue: A list with all the currently unvisited nodes, being the first one
    the most optimal node to explore next
    '''
    for neighbor in graph.neighbors[graph.origin]:
        updateStatus(status, neighbor, graph.edges[(graph.origin, neighbor)], graph.origin)
    status.visited.add(graph.origin)

def computeNextNode(graph, status):
    '''
    Applies Dijkstra to every reachable node but the starting one and adds them to the visited set.
    :param dict graph.edges: A dictionary with the form {(edge_start, edge_end): edge_weight}
    :param dict graph.neighbors: A dictionary with the form {node: [adjacent_nodes]}
    :param int graph.origin: The starting node in the graph
    :param dict weights: A dictionary with the form {node: current distance to starting node}
    :param list parents: A list of size n_vertices with the current parent (or -1) for every node
    :param set visited: A set with all the visited nodes in the graph by the Dijkstra algorithm
    :param list priority_queue: A list with all the currently unvisited nodes, being the first one
    the most optimal node to explore next
    '''
    vertex = heapq.heappop(status.priority_queue)[1]
    if vertex not in status.visited and graph.neighbors.get(vertex) is not None:
        for neighbor in graph.neighbors.get(vertex):
            if (status.weights.get(neighbor) is None or status.weights[neighbor] > graph.edges[(vertex, neighbor)] + status.weights[vertex]):
                updateStatus(status, neighbor, graph.edges[(vertex, neighbor)] + status.weights[vertex], vertex)
    status.visited.add(vertex)

def updateStatus(status, neighbor, new_weight, new_parent):
    '''
    Updates the Dijkstra status variables after exploring a node.
    :param dict weights: A dictionary with the form {node: current distance to starting node}
    :param list parents: A list of size n_vertices with the current parent (or -1) for every node
    :param list priority_queue: A list with all the currently unvisited nodes, being the first one
    the most optimal node to explore next
    :param int neighbor: The current node that we just explored
    :param int new_weight: The new best distance from the starting node to the current node
    :param int new_parent: The new best node we can access the current node from
    '''
    status.weights[neighbor] = new_weight
    status.parents[neighbor] = new_parent
    heapq.heappush(status.priority_queue, [status.weights[neighbor], neighbor])