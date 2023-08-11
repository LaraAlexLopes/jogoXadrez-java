package xadrez.peças;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaXadrez;
public class Bispo extends PeçaXadrez {

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "B";
	}
	
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()]; 
		Posicao p = new Posicao(0,0);
		//noroeste
		p.setValores(posicao.getLinhas() - 1, posicao.getColunas() - 1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().peçaExiste(p)){
			mat[p.getLinhas()][p.getColunas()] = true;
			p.setValores(p.getLinhas() - 1, p.getColunas() - 1);
		}
		if(getTabuleiro().posicaoExiste(p) && existePeçaOponente(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		//nordeste
		p.setValores(posicao.getLinhas() - 1, posicao.getColunas()  + 1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().peçaExiste(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
			p.setValores(p.getLinhas() - 1, p.getColunas() + 1);
		}
		if(getTabuleiro().posicaoExiste(p) && existePeçaOponente(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		
		//sudeste
		p.setValores(posicao.getLinhas() + 1, posicao.getColunas()  + 1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().peçaExiste(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
			p.setValores(p.getLinhas() + 1, p.getColunas() + 1);
		}
		if(getTabuleiro().posicaoExiste(p) && existePeçaOponente(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		//sudoestee
		p.setValores(posicao.getLinhas() + 1, posicao.getColunas() - 1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().peçaExiste(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
			p.setValores(p.getLinhas() + 1, p.getColunas() - 1);
		}
		if(getTabuleiro().posicaoExiste(p) && existePeçaOponente(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		return mat;
	}

}
