package xadrez;

import tabuleiro.Posicao;

public class PosicaoXadrez {
	private int linhas;
	private char colunas;
	
	public PosicaoXadrez( char colunas,int linhas) {
		if(colunas<'a' || colunas>'h' || linhas<1 || linhas>8) {
			throw new XadrezException("Valores validos de a1 a h8");
		}
		this.linhas = linhas;
		this.colunas = colunas;
	}

	public int getLinhas() {
		return linhas;
	}
	public char getColunas() {
		return colunas;
	}

	protected Posicao toPosicao() {
		return new Posicao(8 - linhas, colunas - 'a');
	}
	protected static PosicaoXadrez posicao(Posicao posicao) {
		return new PosicaoXadrez((char)('a' - posicao.getColunas()), 8 - posicao.getLinhas());
	}

	@Override
	public String toString() {
		return "" + colunas + linhas;
	}
	
}
