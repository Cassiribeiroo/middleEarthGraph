package mep.search;

import mep.graph.Grafo;
import mep.model.Aresta;
import mep.model.Vertice;

import java.util.*;

public class Dijkstra {

	 public static List<Vertice> caminhoMaisCurtoPorDistancia(Grafo grafo, String origemNome, String destinoNome) {
	        Vertice origem = new Vertice(origemNome);
	        Vertice destino = new Vertice(destinoNome);
	        
	        // Guarda a menor distância já encontrada até cada vértice
	        Map<Vertice, Integer> distancias = new HashMap<>();
	        // Armazena o vértice anterior no caminho (usado para reconstruir o caminho final)
	        Map<Vertice, Vertice> anteriores = new HashMap<>();
	        // Guarda os vértices que já foram processados
	        Set<Vertice> visitados = new HashSet<>();

	        // Cria uma fila de prioridade, que sempre retorna o vértice com menor distância acumulada
	        PriorityQueue<Vertice> fila = new PriorityQueue<>(Comparator.comparingInt(distancias::get));

	        for (Vertice v : grafo.getAdjacencias().keySet()) {
	            distancias.put(v, Integer.MAX_VALUE); // Inicia todas as distâncias como "infinito"
	        }

	        distancias.put(origem, 0); // A origem tem distância zero
	        fila.add(origem);

	        while (!fila.isEmpty()) {
	            Vertice atual = fila.poll();
	            if (!visitados.add(atual)) continue; // Ignora se já foi visitado

	            // Para cada vizinho do atual calculamos a nova distância acumulada até esse vizinho
	            for (Aresta aresta : grafo.getAdjacencias().getOrDefault(atual, new ArrayList<>())) {
	                Vertice vizinho = aresta.getDestino();
	                int novaDistancia = distancias.get(atual) + aresta.getDistancia();

	                // Se esse novo caminho é melhor (mais curto) do que o que tínhamos antes
	                if (novaDistancia < distancias.getOrDefault(vizinho, Integer.MAX_VALUE)) {
	                    distancias.put(vizinho, novaDistancia);
	                    anteriores.put(vizinho, atual);
	                    fila.add(vizinho);
	                }
	            }
	        }

	        // Reconstrói o caminho
	        List<Vertice> caminho = new ArrayList<>();
	        Vertice atual = destino;
	        // Enquanto houver um vértice anterior no caminho
	        while (atuaisPredecessorExiste(atuaisAnterior -> anteriores.get(atuaisAnterior), atual)) {
	            caminho.add(atual);
	            atual = anteriores.get(atual);
	        }

	        if (!atual.equals(origem)) return Collections.emptyList(); // Sem caminho

	        caminho.add(origem);
	        Collections.reverse(caminho); // Coloca na ordem correta

	        // Exibe o resultado no console
	        System.out.println("Caminho mais curto: " + caminho);
	        System.out.println("Distância acumulada: " + distancias.get(destino));

	        return caminho;
	    }

	 	// Verifica se o vértice atual possui um anterior registrado
	    private static boolean atuaisPredecessorExiste(java.util.function.Function<Vertice, Vertice> anteriores, Vertice atual) {
	        return atual != null && anteriores.apply(atual) != null;
	    }
	    
	    
	 // Método que encontra o caminho com menor nível de perigo entre duas regiões
	    public static List<Vertice> caminhoMaisSeguroPorPerigo(Grafo grafo, String origemNome, String destinoNome) {
	        // Cria objetos Vertice com base nos nomes informados
	        Vertice origem = new Vertice(origemNome);
	        Vertice destino = new Vertice(destinoNome);

	        // Mapa para guardar o perigo mínimo até cada vértice
	        Map<Vertice, Integer> perigos = new HashMap<>();

	        // Mapa para reconstruir o caminho ao final (guarda o "pai" de cada vértice)
	        Map<Vertice, Vertice> anteriores = new HashMap<>();

	        // Conjunto de vértices já visitados
	        Set<Vertice> visitados = new HashSet<>();

	        // Fila de prioridade baseada no perigo acumulado até o vértice
	        PriorityQueue<Vertice> fila = new PriorityQueue<>(Comparator.comparingInt(perigos::get));

	        // Inicializa todos os vértices com perigo "infinito"
	        for (Vertice v : grafo.getAdjacencias().keySet()) {
	            perigos.put(v, Integer.MAX_VALUE);
	        }

	        // O vértice de origem tem perigo acumulado igual a zero
	        perigos.put(origem, 0);
	        fila.add(origem); // Começamos por ele
	        
	        while (!fila.isEmpty()) {
	            Vertice atual = fila.poll(); 
	            if (!visitados.add(atual)) continue; // Pula se já foi visitado

	            // Para cada vizinho do vértice atual
	            for (Aresta aresta : grafo.getAdjacencias().getOrDefault(atual, new ArrayList<>())) {
	                Vertice vizinho = aresta.getDestino();
	                int novoPerigo = perigos.get(atual) + aresta.getPerigo(); // Soma o perigo acumulado

	                // Se encontramos um caminho menos perigoso até esse vizinho
	                if (novoPerigo < perigos.getOrDefault(vizinho, Integer.MAX_VALUE)) {
	                    perigos.put(vizinho, novoPerigo); // Atualiza o menor perigo até ele
	                    anteriores.put(vizinho, atual);   // Guarda quem levou até esse vértice
	                    fila.add(vizinho);                // Adiciona à fila para ser processado
	                }
	            }
	        }

	        // Reconstrói o caminho percorrido (de trás pra frente)
	        List<Vertice> caminho = new ArrayList<>();
	        Vertice atual = destino;

	        // Enquanto o atual não for nulo e tiver um anterior (origem ainda não chegou)
	        while (atuaisPredecessorExiste(anteriores::get, atual)) {
	            caminho.add(atual);
	            atual = anteriores.get(atual); // Anda um passo para trás
	        }

	        // Se não conseguimos chegar até a origem, então não há caminho
	        if (!atual.equals(origem)) return Collections.emptyList();

	        caminho.add(origem); // Adiciona a origem no final
	        Collections.reverse(caminho); // Inverte para ficar da origem até o destino

	        // Exibe no console o resultado
	        System.out.println("Caminho mais seguro (menor perigo): " + caminho);
	        System.out.println("Perigo acumulado: " + perigos.get(destino));

	        return caminho;
	    }
}
