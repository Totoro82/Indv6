package aed.individual6;


import es.upm.aedlib.positionlist.*;
import es.upm.aedlib.set.*;
import es.upm.aedlib.graph.*;


public class Utils {

  /**
   * Devuelve un conjunto con todos los vertices alcanzables desde AMBOS
   * v1 y v2.
   */
  public static <V> Set<Vertex<V>>
    reachableFromBoth(DirectedGraph<V,Boolean> g, Vertex<V> v1, Vertex<V> v2) {

    return null;
  }


  /**
   * Devuelve un camino (una lista de aristas) que llevan desde from y to,
   * donde la suma de los elementos de las aristas del camino <= limit.
   * Si no existe ningun camino que cumple con esta restriccion se devuelve
   * el valor null. 
   */
  
  public static <V> PositionList<Edge<Integer>> existsPathLess(UndirectedGraph<V,Integer> g, Vertex<V> from, Vertex<V> to, int limit) {
    return null;
  }

}

