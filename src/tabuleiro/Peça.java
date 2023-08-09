package tabuleiro;
public abstract class  Peça {
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Peça(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}	
	
	public abstract boolean[][] possiveisMovimentos();
	
	public boolean possiveisMovimento(Posicao posicao) {
		return possiveisMovimentos()[posicao.getLinhas()][posicao.getColunas()];
	}
	
	public boolean existePossivelMovimento() {
		boolean[][] mat =  possiveisMovimentos();
		for(int i = 0;i<mat.length; i++) {
			for(int j = 0; j<mat.length; j++){
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

}
