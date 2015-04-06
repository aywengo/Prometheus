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

    public static <E> HashMap<E, Integer> depthFirstSearch(Graph<E> g, E s) {
        int k = 1;
        Stack<E> stack = new Stack<>();
        stack.push(s);
        HashMap<E, Integer>  dfs = new HashMap<>();
        dfs.put(s, k);

        while (!stack.empty()){
            E v = stack.pop();
            Vertex<E> vertex = g.Vertexes.get(v);

            for (E head : vertex.OutConnections.keySet())
            {
                if (dfs.containsKey(head))
                    continue;

                dfs.put(head, ++k);
                stack.add(head);
            }

            for (E head : vertex.InConnections.keySet())
            {
                if (dfs.containsKey(head))
                    continue;

                dfs.put(head, ++k);
                stack.add(head);
            }
        }

        return dfs;
    }
}