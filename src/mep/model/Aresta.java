package mep.model;

public class Aresta {
	private Vertice destino;
	private int perigo;
	private int distancia;
	
	public Aresta(Vertice destino, int perigo, int distancia) {
		super();
		this.destino = destino;
		this.perigo = perigo;
		this.distancia = distancia;
	}

	public Vertice getDestino() {
		return destino;
	}

	public int getPerigo() {
		return perigo;
	}

	public int getDistancia() {
		return distancia;
	}

    @Override
    public String toString() {
        return "[" + destino + ", " + perigo + ", " + distancia + "]";
    }
}
