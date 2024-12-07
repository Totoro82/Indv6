package aed.individual6;


import es.upm.aedlib.positionlist.*;
import es.upm.aedlib.set.*;
import es.upm.aedlib.graph.*;


public class Utils {

  /**
   * Devuelve un conjunto con todos los vertices alcanzables desde AMBOS
   * v1 y v2.
   */
  public static <V> Set<Vertex<V>> reachableFromBoth(DirectedGraph<V,Boolean> g, Vertex<V> v1, Vertex<V> v2) {
    Set<Vertex<V>> result = new HashTableMapSet<>();
    if(g.isEmpty()) return result;
    if(v1.equals(v2)) {
      result.add(v1);
    }
    Set<Vertex<V>> reachableFromV1 = verticesAccesibles(g, v1);
    Set<Vertex<V>> reachableFromV2 = verticesAccesibles(g, v2);
    for(Vertex<V> vertex1 : reachableFromV1) {
      for(Vertex<V> vertex2 : reachableFromV2 ) {
        if(vertex1.equals(vertex2)) {
          result.add(vertex1);
        }
      }
    }
    return result;
  }

  private static <V> Set<Vertex<V>> verticesAccesibles(DirectedGraph<V, Boolean> g, Vertex<V> vertex) {
    Set<Vertex<V>> result = new HashTableMapSet<>();
    result.add(vertex);
    for(Edge<Boolean> edge: g.outgoingEdges(vertex)) {
      if(!edge.element()) continue;
      Vertex<V> endVertex = g.endVertex(edge);
      result.add(endVertex);
      if(!endVertex.equals(vertex)) {
        verticesAccesibles(g, endVertex).forEach(result::add);
      }
    }
    return result;
  }


  /**
   * Devuelve un camino (una lista de aristas) que llevan desde from y to,
   * donde la suma de los elementos de las aristas del camino <= limit.
   * Si no existe ningun camino que cumple con esta restriccion se devuelve
   * el valor null. 
   */
  
  public static <V> PositionList<Edge<Integer>> existsPathLess(UndirectedGraph<V,Integer> g, Vertex<V> from, Vertex<V> to, int limit) {
    if(g.isEmpty()) return null;
    PositionList<Edge<Integer>> caminoResultado = new NodePositionList<>();
    boolean hayCamino = calculatePath(g, from, to, limit, 0, caminoResultado, new HashTableMapSet<>());
    return hayCamino ? caminoResultado : null;
  }

  private static <V> boolean calculatePath(UndirectedGraph<V, Integer> g, Vertex<V> from, Vertex<V> to, int limit, int pesoActual, PositionList<Edge<Integer>> caminoResultado, Set<Edge<Integer>> visitados) {
    if(from.equals(to)) { //si estamos en el nodo y paso las restricciones true
      return true;
    }
    for(Edge<Integer> edge : g.edges(from)) {
      Vertex<V> nextVertex = g.opposite(from, edge);
      if(visitados.contains(edge)) continue; //saltamos aristas ya visitadas
      int peso = pesoActual + edge.element(); // si el camino explorandose pesa mas que el maximo lo ignoramos
      if (peso >= limit) continue;//pese a que la hoja de instrucciones pone que la suma de los elementos de las aristas sea < limit, el comentario precediendo al metodo y para pasar los tests es necesrio que sea <=. De querer que fuese
                                  // como en la hoja de la guia, tendria que reemplazarse este >= por el comparador >
      visitados.add(edge);
      caminoResultado.addLast(edge);
      if(calculatePath(g, nextVertex, to, limit, peso, caminoResultado, visitados)) { //llamada recursiva para ver si por esta arista se encuentra el camino buscado, con los datos actualizados
        return true;
      }
      caminoResultado.remove(caminoResultado.last());//si por este camino no es, "desactualizamos" y saltamos a la siguiente arista
      visitados.remove(edge);
    }
    return false;
  }

}

