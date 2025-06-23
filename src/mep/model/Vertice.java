package mep.model;

public class Vertice {
	private String nome;
	
	public Vertice(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

    @Override
    public String toString() {
        return nome;
    }

    //Comparando se os objetos são iguais
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vertice)) return false;
        Vertice outro = (Vertice) obj;
        return this.nome.equalsIgnoreCase(outro.nome);
    }

 // Garante que vértices com nomes iguais tenham o mesmo código hash
    @Override
    public int hashCode() {
        return nome.toLowerCase().hashCode();
    }
}
