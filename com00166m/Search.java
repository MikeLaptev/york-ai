package com.mlaptev.sandbox.com00166m.week2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Search {

  public Result dfs(Map<String, List<Path>> graph, String from, String to) {
    System.out.println("DFS");
    if (!(graph.containsKey(from) && graph.containsKey(to))) {
      return new Result("", "");
    }

    StringBuilder processed = new StringBuilder();
    StringBuilder path = new StringBuilder();

    Map<String, String> backtracking = new HashMap<>();

    LinkedList<Node> stack = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    stack.addLast(new Node(from, 0));
    visited.add(from);
    backtracking.put(from, null);
    while (!stack.isEmpty()) {
      System.out.println(stack);
      Node current = stack.pollLast();
      if (!graph.containsKey(current.node())) {
        continue;
      }

      processed.append(current.node());

      for (Path p : graph.get(current.node())) {
        if (visited.contains(p.to())) {  // ignoring visited nodes
          continue;
        }
        if (p.to().equals(to)) {
          backtracking.put(p.to(), p.from());
          processed.append(to);
          stack.clear();
          break;
        }
        backtracking.put(p.to(), p.from());
        stack.addLast(new Node(p.to(), 0));
        visited.add(p.to());
      }
    }

    String b = to;
    while (b != null) {
      path.append(b);
      b = backtracking.get(b);
    }

    return new Result(processed.toString(), path.reverse().toString());
  }

  public Result bfs(Map<String, List<Path>> graph, String from, String to) {
    System.out.println("BFS");
    if (!(graph.containsKey(from) && graph.containsKey(to))) {
      return new Result("", "");
    }

    StringBuilder processed = new StringBuilder();
    StringBuilder path = new StringBuilder();

    Map<String, String> backtracking = new HashMap<>();

    LinkedList<Node> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    queue.addLast(new Node(from, 0));
    visited.add(from);
    backtracking.put(from, null);
    while (!queue.isEmpty()) {
      System.out.println(queue);
      Node current = queue.pollFirst();
      if (!graph.containsKey(current.node())) {
        continue;
      }

      processed.append(current.node());

      for (Path p : graph.get(current.node())) {
        if (visited.contains(p.to())) {  // ignoring visited nodes
          continue;
        }
        if (p.to().equals(to)) {
          backtracking.put(p.to(), p.from());
          processed.append(to);
          queue.clear();
          break;
        }
        backtracking.put(p.to(), p.from());
        queue.addLast(new Node(p.to(), 0));
        visited.add(p.to());
      }
    }

    String b = to;
    while (b != null) {
      path.append(b);
      b = backtracking.get(b);
    }

    return new Result(processed.toString(), path.reverse().toString());
  }

  public Result greedy(Map<String, List<Path>> graph, String from, String to, Map<String, Map<String, Integer>> costOfTravelingToGoal) {
    System.out.println("Greedy");
    if (!(graph.containsKey(from) && graph.containsKey(to))) {
      return new Result("", "");
    }

    StringBuilder processed = new StringBuilder();
    StringBuilder path = new StringBuilder();

    Map<String, String> backtracking = new HashMap<>();

    PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::weight));
    Map<String, Integer> visited = new HashMap<>();
    queue.add(new Node(from, costOfTravelingToGoal.get(to).get(from)));
    visited.put(from, 0);
    backtracking.put(from, null);

    while (!queue.isEmpty()) {
      System.out.println(queue);
      Node current = queue.poll();
      if (!graph.containsKey(current.node())) {
        continue;
      }

      processed.append(current.node());

      for (Path p : graph.get(current.node())) {
        int cost = costOfTravelingToGoal.get(to).get(p.to());
        if (visited.containsKey(p.to()) && visited.get(p.to()) < cost) {  // ignoring visited nodes which cost is higher
          continue;
        }
        if (p.to().equals(to)) {
          backtracking.put(p.to(), p.from());
          processed.append(to);
          queue.clear();
          break;
        }
        if (!backtracking.containsKey(p.to())) {
          backtracking.put(p.to(), p.from());
        }
        queue.add(new Node(p.to(), cost));
        visited.put(p.to(), cost);
      }
    }

    String b = to;
    while (b != null) {
      path.append(b);
      b = backtracking.get(b);
    }

    return new Result(processed.toString(), path.reverse().toString());
  }

  public Result aStar(Map<String, List<Path>> graph, String from, String to, Map<String, Map<String, Integer>> costOfTravelingToGoal) {
    System.out.println("A*");
    if (!(graph.containsKey(from) && graph.containsKey(to))) {
      return new Result("", "");
    }

    StringBuilder processed = new StringBuilder();
    StringBuilder path = new StringBuilder();

    Map<String, String> backtracking = new HashMap<>();

    PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::weight));
    Map<String, Integer> visited = new HashMap<>();
    queue.add(new Node(from, costOfTravelingToGoal.get(to).get(from)));
    visited.put(from, 0);
    backtracking.put(from, null);

    while (!queue.isEmpty()) {
      System.out.println(queue);
      Node current = queue.poll();
      if (!graph.containsKey(current.node())) {
        continue;
      }

      processed.append(current.node());

      for (Path p : graph.get(current.node())) {
        int cost = costOfTravelingToGoal.get(to).get(p.to()) + p.weight();
        if (visited.containsKey(p.to()) && visited.get(p.to()) < cost) {  // ignoring visited nodes which cost is higher
          continue;
        }
        if (p.to().equals(to)) {
          backtracking.put(p.to(), p.from());
          processed.append(to);
          queue.clear();
          break;
        }
        if (!backtracking.containsKey(p.to())) {
          backtracking.put(p.to(), p.from());
        }
        queue.add(new Node(p.to(), cost));
        visited.put(p.to(), cost);
      }
    }

    String b = to;
    while (b != null) {
      path.append(b);
      b = backtracking.get(b);
    }

    return new Result(processed.toString(), path.reverse().toString());
  }
}
