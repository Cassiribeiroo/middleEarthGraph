package mep.app;

import mep.graph.Grafo;

public class Main {

	public static void main(String[] args) {
		// Cria uma instância do grafo
		Grafo grafo = new Grafo();

		// Carrega os vértices a partir do arquivo CSV
		grafo.carregarVertices("data/vertices.csv");

		// Carrega as arestas (conexões entre regiões) a partir do arquivo CSV
		grafo.carregarArestas("data/arestas.csv");

		// Exibe o grafo completo no console
		grafo.exibirGrafo();
	}
}
