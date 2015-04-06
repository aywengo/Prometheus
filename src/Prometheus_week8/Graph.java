package Prometheus_week8;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class Graph<T> {
    HashSet<Edge<T>> Edges = new HashSet<>();
    HashMap<T,Vertex<T>> Vertexes = new HashMap<>();

    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 1);
    }

    public boolean addEdge(T begin, T end, int weight) {
        Edge<T> toAdd = new Edge<>(begin, end, weight);

        if (Edges.contains(toAdd)){
            return false;
        }

        Edges.add(toAdd);
        return insertUpdateVertex(toAdd);
    }

    private boolean insertUpdateVertex(Edge<T> edge) {
        try {
            if (Vertexes.containsKey(edge.Begin)) {
                Vertexes.get(edge.Begin).addConnection(edge);
            }
            else {
                Vertexes.put(edge.Begin, new Vertex<>(edge.Begin, edge));
            }

            if (Vertexes.containsKey(edge.End)) {
                Vertexes.get(edge.End).addConnection(edge);
            }
            else {
                Vertexes.put(edge.End, new Vertex<>(edge.End, edge));
            }
        } catch (Exception ex) {
            System.out.print(ex.toString());
            return false;
        }

        return true;
    }

    public static <E> int depthFirstSearch(Graph<E> g, E s) {
        int k = 1;
        Stack<E> stack = new Stack<>();
        for(E heap : g.Vertexes.keySet()) {
            if (heap == s)
                continue;
            stack.add(heap);
        }

        while (!stack.empty())
        {
            E v = stack.pop();
            Vertex<E> vertex = g.Vertexes.get(v);

            E n = getUndiscaveredHead(stack, vertex);
            if (n != null){
                k++;
                stack.push(n);
            }
        }

        return k;
    }

    private static <R> R getUndiscaveredHead(Stack<R> stack, Vertex<R> vertex) {
        for (R head : stack)
        {
            if (vertex.OutConnections.containsKey(head))
            {
                return head;
            }
        }

        return null;
    }
}