package tabuleiro;
public class Posicao {
	private int linhas;
	private int colunas;
	
	public Posicao(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
	}

	public int getLinhas() {
		return linhas;
	}
	public void setLinhas(int linha) {
		this.linhas = linha;
	}
	public int getColunas() {
		return colunas;
	}
	public void setColunas(int coluna) {
		this.colunas = coluna;
	}
	public void setValores(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
	}
	@Override
	public String toString() {
		return linhas + "," + colunas;
	}
	
	
	
}
