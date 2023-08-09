package tabuleiro;
public class Tabuleiro {
	private int linhas;
	private int colunas;
	private Peça[][] peças;
	
	public Tabuleiro(int linhas, int colunas) {
		if(linhas<1  || colunas<1) {
			throw new TabuleiroException("Erro na criação do tabuleiro: é necessário ao mínimo 1 linha e 1 coluna");
		} 
		this.linhas = linhas;
		this.colunas = colunas;
		peças = new Peça[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}
	public int getColunas() {
		return colunas;
	}
	
	public Peça peça(int linha, int coluna) {
		if(!posicaoExiste(linha,coluna)){
			throw new TabuleiroException("Posição não existe no tabuleiro");
		}
		return peças[linha][coluna];
	}
	public Peça peça(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posição não existe no tabuleiro");
		}
		return peças[posicao.getLinhas()][posicao.getColunas()];
	}
	public void posicaoPeça(Peça peça, Posicao posicao) {
		if(peçaExiste(posicao)) {
			throw new TabuleiroException("Já existe uma peça na posição" +  posicao);
		}
		peças[posicao.getLinhas()][posicao.getColunas()] = peça;
		peça.posicao = posicao;
	}
	public Peça removePeça(Posicao posicao){
		if(!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posição não existe no tabuleiro");
		}
		if(peça(posicao) == null) {
			return null;
		}
		Peça aux = peça(posicao);
		aux.posicao = null;
		peças[posicao.getLinhas()][posicao.getColunas()] = null;
		return aux;
	}
	private boolean posicaoExiste(int linha, int coluna) {
		return linha>=0 && linha<linhas  && coluna>=0 && coluna<colunas;
	}
	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinhas(),posicao.getColunas());
	}
	public boolean peçaExiste(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posição não existe no tabuleiro");
		}
		return peça(posicao) != null;
	}

}
