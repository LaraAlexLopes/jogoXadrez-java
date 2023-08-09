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
	private void posicaoNovaPeça(char coluna, int linha,PeçaXadrez peça){
		tabuleiro.posicaoPeça(peça, new PosicaoXadrez(coluna,linha).toPosicao());
	}
	
	private void inicioPartida(){
		posicaoNovaPeça('b',6,new Torre(tabuleiro,Cor.Branco));
		posicaoNovaPeça('e',8,new Rei(tabuleiro,Cor.Branco));
		posicaoNovaPeça('e',1,new Rei(tabuleiro,Cor.Preto));
	}

}
