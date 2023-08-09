package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.peças.Rei;
import xadrez.peças.Torre;

public class PartidaXadrez {
	private Tabuleiro tabuleiro;

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
		inicioPartida();
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
	
	private void inicioPartida(){
		tabuleiro.posicaoPeça(new Torre(tabuleiro,Cor.Branco), new Posicao(2,1));
		tabuleiro.posicaoPeça(new Rei(tabuleiro,Cor.Branco), new Posicao(7,4));
		tabuleiro.posicaoPeça(new Rei(tabuleiro,Cor.Preto), new Posicao(0,4));
	}

}
