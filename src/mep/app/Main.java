package mep.app;

import mep.graph.Grafo;
import mep.model.Aresta;
import mep.model.Vertice;

public class Main {

	public static void main(String[] args) {
		// Criando vértices
		Vertice v1 = new Vertice("Gondor");
        Vertice v2 = new Vertice("gondor");
        Vertice v3 = new Vertice("Rohan");

        // Testando toString()
        System.out.println("v1: " + v1); // Gondor
        System.out.println("v2: " + v2); // gondor
        System.out.println("v3: " + v3); // Rohan
        
        // Testando equals
        System.out.println("v1.equals(v2): " + v1.equals(v2)); // true
        System.out.println("v1.equals(v3): " + v1.equals(v3)); // false

        // Testando hashCode
        System.out.println("v1.hashCode(): " + v1.hashCode());
        System.out.println("v2.hashCode(): " + v2.hashCode());
        System.out.println("v3.hashCode(): " + v3.hashCode());

        // Criando arestas
        Aresta a1 = new Aresta(v3, 4, 55); // destino: Rohan, perigo 4, distância 55
        System.out.println("Aresta a1: " + a1);
        
        //criando Grafo
        Grafo grafo = new Grafo();
        grafo.adicionarVertice("Gondor");
        grafo.adicionarVertice("Rohan");
        grafo.adicionarAresta("Gondor", "Rohan", 3, 55);
        grafo.exibirGrafo();
        
	}

}
