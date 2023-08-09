package xadrez;

import tabuleiro.Tabuleiro;

public class PartidaXadrez {
	private Tabuleiro tabuleiro;

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
	}
	
	public PeçaXadrez[][] getPeça(){
		PeçaXadrez[][] peça = new PeçaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i<tabuleiro.getLinhas(); i++) {
			for(int j = 0; j<tabuleiro.getColunas(); j++) {
				peça[i][j] = (PeçaXadrez) tabuleiro.peça(i, j);
			}
		}
		return peça;
	}
}
