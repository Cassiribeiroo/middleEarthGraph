package mep.graph;

import java.util.*;

import mep.model.Aresta;
import mep.model.Vertice;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
	    // Cria uma lista com os vértices (chaves do mapa)
	    List<Vertice> verticesOrdenados = new ArrayList<>(adjacencias.keySet());

	    // Ordena os vértices pelo nome, ignorando maiúsculas
	    verticesOrdenados.sort(Comparator.comparing(Vertice::getNome, String.CASE_INSENSITIVE_ORDER));

	    // Usa a lista ordenada no laço
	    for (Vertice v : verticesOrdenados) {
	        System.out.print(v + " -> ");
	        System.out.println(adjacencias.get(v));
	    }
	}
	
    // Getter 
    public Map<Vertice, List<Aresta>> getAdjacencias() {
        return adjacencias;
    }
    
    // Lê arquivo vertices.csv
    public void carregarVertices(String caminho) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
        	String linha = br.readLine(); // Pula o cabeçalho
            while ((linha = br.readLine()) != null) {
                String nome = linha.trim();
                if (!nome.isEmpty()) {
                    adicionarVertice(nome);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de vértices: " + e.getMessage());
        }
    }
    
 // Lê arquivo arestas.csv
    public void carregarArestas(String caminho) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
        	String linha = br.readLine(); // Pula o cabeçalho
        	
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 4) {
                    String origem = partes[0].trim();
                    String destino = partes[1].trim();
                    int perigo = Integer.parseInt(partes[2].trim());
                    int distancia = Integer.parseInt(partes[3].trim());
                    adicionarAresta(origem, destino, perigo, distancia);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de arestas: " + e.getMessage());
        }
    }

}
