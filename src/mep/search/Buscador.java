package mep.search;

import java.util.*;

import mep.model.Vertice;
import mep.model.Aresta;
import mep.graph.Grafo;

public class Buscador {
	 // Busca em profundidade Depth-First Search
    public static boolean buscaProfundidade(Grafo grafo, String origemNome, String destinoNome) {
        Vertice origem = new Vertice(origemNome);
        Vertice destino = new Vertice(destinoNome);
        Set<Vertice> visitados = new HashSet<>();
        return dfs(grafo, origem, destino, visitados);
    }

    private static boolean dfs(Grafo grafo, Vertice atual, Vertice destino, Set<Vertice> visitados) {
        if (atual.equals(destino)) return true;
        //Add como visitado
        visitados.add(atual);
        // Pegamos todos os caminhos que saem do vértice atual. Se ele não tiver vizinhos, devolvemos uma lista vazia 
        List<Aresta> vizinhos = grafo.getAdjacencias().getOrDefault(atual, new ArrayList<>());
        for (Aresta aresta : vizinhos) {
        	// Para cada aresta peca o vertice de destino
            Vertice proximo = aresta.getDestino();
            if (!visitados.contains(proximo)) {
                if (dfs(grafo, proximo, destino, visitados)) return true;
            }
        }

        return false;
    }

    // Busca em largura Breadth-First Search 
    public static boolean buscaLargura(Grafo grafo, String origemNome, String destinoNome) {
        Vertice origem = new Vertice(origemNome);
        Vertice destino = new Vertice(destinoNome);
        Set<Vertice> visitados = new HashSet<>();
        Queue<Vertice> fila = new LinkedList<>();

        fila.add(origem);
        visitados.add(origem);

        while (!fila.isEmpty()) {
            Vertice atual = fila.poll();
            if (atual.equals(destino)) return true;
            // Pega todos os caminhos (arestas) que saem do vértice atual
            List<Aresta> vizinhos = grafo.getAdjacencias().getOrDefault(atual, new ArrayList<>());
            for (Aresta aresta : vizinhos) {
            	//Para cada caminho, pega a próxima região (destino da aresta)
                Vertice proximo = aresta.getDestino();
                if (!visitados.contains(proximo)) {
                    fila.add(proximo);
                    visitados.add(proximo);
                }
            }
        }

        return false;
    }
}
