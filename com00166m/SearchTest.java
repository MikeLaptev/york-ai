package com.mlaptev.sandbox.com00166m.week2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SearchTest {

  private final Map<String, List<Path>> graph = new HashMap<>();
  private final Map<String, Map<String, Integer>> costOfTravelingToGoal = new HashMap<>();
  private Search sut;

  @Before
  public void setUp() {
    sut = new Search();

    // Initializing graph
    graph.clear();

    // A, B, C, D, E, F, G, H, I, J, K
    graph.put("A", new ArrayList<>());
    graph.put("B", new ArrayList<>());
    graph.put("C", new ArrayList<>());
    graph.put("D", new ArrayList<>());
    graph.put("E", new ArrayList<>());
    graph.put("F", new ArrayList<>());
    graph.put("G", new ArrayList<>());
    graph.put("H", new ArrayList<>());
    graph.put("I", new ArrayList<>());
    graph.put("J", new ArrayList<>());
    graph.put("K", new ArrayList<>());

    // edges
    graph.get("A").add(new Path("A", "G", 50));
    graph.get("A").add(new Path("A", "F", 25));
    graph.get("A").add(new Path("A", "C", 35));
    graph.get("A").add(new Path("A", "B", 70));

    graph.get("B").add(new Path("B", "A", 70));
    graph.get("B").add(new Path("B", "D", 30));

    graph.get("C").add(new Path("C", "A", 35));
    graph.get("C").add(new Path("C", "E", 15));
    graph.get("C").add(new Path("C", "D", 40));

    graph.get("D").add(new Path("D", "C", 40));
    graph.get("D").add(new Path("D", "E", 35));
    graph.get("D").add(new Path("D", "B", 30));
    graph.get("D").add(new Path("D", "K", 30));

    graph.get("E").add(new Path("E", "F", 25));
    graph.get("E").add(new Path("E", "C", 15));
    graph.get("E").add(new Path("E", "I", 15));
    graph.get("E").add(new Path("E", "D", 35));
    graph.get("E").add(new Path("E", "K", 50));

    graph.get("F").add(new Path("F", "A", 25));
    graph.get("F").add(new Path("F", "G", 20));
    graph.get("F").add(new Path("F", "H", 20));
    graph.get("F").add(new Path("F", "E", 25));
    graph.get("F").add(new Path("F", "I", 35));

    graph.get("G").add(new Path("G", "A", 50));
    graph.get("G").add(new Path("G", "F", 25));
    graph.get("G").add(new Path("G", "H", 35));

    graph.get("H").add(new Path("H", "G", 35));
    graph.get("H").add(new Path("H", "F", 20));
    graph.get("H").add(new Path("H", "I", 10));
    graph.get("H").add(new Path("H", "J", 35));
    graph.get("H").add(new Path("H", "K", 40));

    graph.get("I").add(new Path("I", "F", 35));
    graph.get("I").add(new Path("I", "H", 10));
    graph.get("I").add(new Path("I", "E", 15));
    graph.get("I").add(new Path("I", "K", 35));

    graph.get("J").add(new Path("J", "H", 35));
    graph.get("J").add(new Path("J", "K", 20));

    graph.get("K").add(new Path("K", "H", 40));
    graph.get("K").add(new Path("K", "E", 50));
    graph.get("K").add(new Path("K", "I", 35));
    graph.get("K").add(new Path("K", "J", 20));
    graph.get("K").add(new Path("K", "D", 30));


    costOfTravelingToGoal.put("K", new HashMap<>());
    costOfTravelingToGoal.get("K").put("A", 150);
    costOfTravelingToGoal.get("K").put("B", 100);
    costOfTravelingToGoal.get("K").put("C", 110);
    costOfTravelingToGoal.get("K").put("D", 30);
    costOfTravelingToGoal.get("K").put("E", 50);
    costOfTravelingToGoal.get("K").put("F", 110);
    costOfTravelingToGoal.get("K").put("G", 140);
    costOfTravelingToGoal.get("K").put("H", 40);
    costOfTravelingToGoal.get("K").put("I", 35);
    costOfTravelingToGoal.get("K").put("J", 20);
    costOfTravelingToGoal.get("K").put("K", 0);
  }

  @Test
  public void dfs() {
    // Arrange & Act
    Result actual = sut.dfs(graph, "A", "K");

    // Assert
    assertEquals("ABDK", actual.processedNodes());
    assertEquals("ABDK", actual.path());
  }

  @Test
  public void bfs() {
    // Arrange & Act
    Result actual = sut.bfs(graph, "A", "K");

    // Assert
    assertEquals("AGFCBHK", actual.processedNodes());
    assertEquals("AGHK", actual.path());
  }

  @Test
  public void greedy() {
    // Arrange & Act
    Result actual = sut.greedy(graph, "A", "K", costOfTravelingToGoal);

    // Assert
    assertEquals("ABDK", actual.processedNodes());
    assertEquals("ABDK", actual.path());
  }

  @Test
  public void aStar() {
    // Arrange & Act
    Result actual = sut.aStar(graph, "A", "K", costOfTravelingToGoal);

    // Assert
    assertEquals("AFHK", actual.processedNodes());
    assertEquals("AFHK", actual.path());
  }
}