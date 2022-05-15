package controllers;

import java.util.*;
import enums.*;
import models.*;

public class ShortestPath {
    Database database = Database.getInstance();

    public List<List<Node>> createGraph(Tile[][] tiles, int n) {
        List<List<Node>> adj = new ArrayList<List<Node>>();

        for (int i = 0; i < n; i++) {
            ArrayList<Node> list = new ArrayList<Node>();
            for (int j = 0; j < 6; j++) {
                Tile neighbor = database
                        .getNeighbor(tiles[i / tiles[0].length][i % tiles[0].length], j);
                if (neighbor != null) {
                    if (neighbor.getLandType() != LandType.MOUNTAIN && neighbor.getLandType() != LandType.OCEAN
                            && neighbor.getFeature() != Feature.ICE && !(neighbor.getHasRiver()[j] &&
                                    (!tiles[i / tiles[0].length][i % tiles[0].length].getHasRoad() ||
                                            !neighbor.getHasRoad()))) {
                        list.add(new Node(neighbor.getCoordinates()[0] * tiles[0].length + neighbor.getCoordinates()[1],
                                neighbor.getMovementCost()));
                    }
                }
            }
            adj.add(list);
        }

        return adj;
    }

    public int[] dijkstra(List<List<Node>> adj, int src, int[] parent) {
        int n = adj.size();
        int[] dist = new int[n];
        Set<Integer> selected = new HashSet<Integer>();
        PriorityQueue<Node> heap = new PriorityQueue<Node>(n, new Node());

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        parent[src] = src;
        dist[src] = 0;
        heap.add(new Node(src, 0));

        while (!heap.isEmpty()) {
            int u = heap.remove().node;
            if (selected.contains(u)) {
                continue;
            }
            selected.add(u);
            for (int i = 0; i < adj.get(u).size(); i++) {
                Node v = adj.get(u).get(i);
                if (!selected.contains(v.node)) {
                    if (dist[u] + v.cost < dist[v.node]) {
                        dist[v.node] = dist[u] + v.cost;
                        parent[v.node] = u;
                    }
                    heap.add(new Node(v.node, dist[v.node]));
                }
            }
        }
        return dist;
    }

    public int[][] getShortestPaths(Tile[][] tiles, int x, int y, Tile[][] parent) {
        int n = tiles.length;
        int m = tiles[0].length;
        int src = x * m + y;
        int[][] d = new int[n][m];
        List<List<Node>> adj = createGraph(tiles, m * n);
        int[] pi = new int[m * n];
        int[] dist = dijkstra(adj, src, pi);

        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                d[i][j] = dist[counter];
                parent[i][j] = tiles[pi[counter] / m][pi[counter] % m];
                counter++;
            }
        }

        return d;
    }
}

class Node implements Comparator<Node> {
    public int node;
    public int cost;

    public Node(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    public Node() {
    }

    public int compare(Node node1, Node node2) {
        return (node1.cost < node2.cost) ? -1 : (node1.cost > node2.cost) ? 1 : 0;
    }
}
