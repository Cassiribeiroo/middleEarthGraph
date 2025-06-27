package mep.app;

import java.util.Scanner;

import mep.graph.Grafo;
import mep.search.Buscador;

public class Main {

	public static void main(String[] args) {
        // Cria o grafo
        Grafo grafo = new Grafo();

        // Carrega os dados dos arquivos CSV
        grafo.carregarVertices("data/vertices.csv");
        grafo.carregarArestas("data/arestas.csv");

        // Exibe o grafo completo em ordem alfabética
        grafo.exibirGrafo();

        // Cria o scanner para entrada de dados
        Scanner scanner = new Scanner(System.in);

        // Solicita os nomes ao usuário
        System.out.print("\nDigite a região de origem: ");
        String origem = scanner.nextLine().trim();

        System.out.print("Digite a região de destino: ");
        String destino = scanner.nextLine().trim();

        // Busca em profundidade
        boolean dfs = Buscador.buscaProfundidade(grafo, origem, destino);
        System.out.println("Busca em Profundidade: Existe caminho de " + origem + " para " + destino + "? " + dfs);

        // Busca em largura
        boolean bfs = Buscador.buscaLargura(grafo, origem, destino);
        System.out.println("Busca em Largura: Existe caminho de " + origem + " para " + destino + "? " + bfs);

        // Fecha o scanner
        scanner.close();
    }
}
