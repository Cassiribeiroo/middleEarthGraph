package mep.graph;

import java.util.*;

import mep.model.Aresta;
import mep.model.Vertice;

public class Grafo {
	// Estrutura de adjacência: cada vértice aponta para suas arestas
	private Map<Vertice, List<Aresta>> adjacencias;

	public Grafo() {
		this.adjacencias = new HashMap<>();
	}

	// Adiciona um vértice ao grafo, se ainda não existir
	public void adicionarVertice(String nome) {
		Vertice v = new Vertice(nome);
		adjacencias.putIfAbsent(v, new ArrayList<>());
	}

	// Adiciona uma aresta entre dois vértices
	public void adicionarAresta(String origemNome, String destinoNome, int perigo, int distancia) {
		Vertice origem = new Vertice(origemNome);
		Vertice destino = new Vertice(destinoNome);
		Aresta aresta = new Aresta(destino, perigo, distancia);

		// Garante que o vértice origem exista
		adjacencias.putIfAbsent(origem, new ArrayList<>());
		adjacencias.get(origem).add(aresta);
	}

	// Exibe o grafo completo
	public void exibirGrafo() {
		for (Vertice v : adjacencias.keySet()) {
			System.out.print(v + " -> ");
			System.out.println(adjacencias.get(v));
		}
	}
	
    // Getter opcional para uso posterior
    public Map<Vertice, List<Aresta>> getAdjacencias() {
        return adjacencias;
    }
}
