package xadrez.peças;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaXadrez;
public class Rainha extends PeçaXadrez{
	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	@Override
	public String toString() {
		return "Q";
	}
	
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()]; 
		Posicao p = new Posicao(0,0);
		//cima
		p.setValores(posicao.getLinhas() - 1, posicao.getColunas());
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().peçaExiste(p)){
			mat[p.getLinhas()][p.getColunas()] = true;
			p.setLinhas(p.getLinhas() - 1);
		}
		if(getTabuleiro().posicaoExiste(p) && existePeçaOponente(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		//esquerda
		p.setValores(posicao.getLinhas(), posicao.getColunas()  - 1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().peçaExiste(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
			p.setColunas(p.getColunas() - 1);
		}
		if(getTabuleiro().posicaoExiste(p) && existePeçaOponente(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		
		//direita
		p.setValores(posicao.getLinhas(), posicao.getColunas()  + 1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().peçaExiste(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
			p.setColunas(p.getColunas() + 1);
		}
		if(getTabuleiro().posicaoExiste(p) && existePeçaOponente(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		//baixo
		p.setValores(posicao.getLinhas() + 1, posicao.getColunas());
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().peçaExiste(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
			p.setLinhas(p.getLinhas() + 1);
		}
		if(getTabuleiro().posicaoExiste(p) && existePeçaOponente(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
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
