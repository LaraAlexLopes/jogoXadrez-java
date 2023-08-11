package xadrez.peças;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaXadrez;

public class Peao extends PeçaXadrez{

	public  Peao(Tabuleiro tabuleiro,Cor cor){
		super(tabuleiro,cor);
	}
	
	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		if(getCor() == Cor.Branco) {
			p.setValores(posicao.getLinhas() - 1, posicao.getColunas());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().peçaExiste(p)) {
				mat[p.getLinhas()][p.getColunas()] = true;
			}
			p.setValores(posicao.getLinhas() - 2, posicao.getColunas());
			Posicao p2 = new Posicao(posicao.getLinhas() - 1,posicao.getColunas());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().peçaExiste(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().peçaExiste(p2)  && getContagemDeMovimento() == 0) {
				mat[p.getLinhas()][p.getColunas()] = true;
			}
			p.setValores(posicao.getLinhas() - 1, posicao.getColunas() - 1);
			if(getTabuleiro().posicaoExiste(p) && existePeçaOponente(p)) {
				mat[p.getLinhas()][p.getColunas()] = true;
			}
			p.setValores(posicao.getLinhas() - 1, posicao.getColunas() + 1);
			if(getTabuleiro().posicaoExiste(p) && existePeçaOponente(p)) {
				mat[p.getLinhas()][p.getColunas()] = true;
			}
		}
		else {
				p.setValores(posicao.getLinhas() + 1, posicao.getColunas());
				if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().peçaExiste(p)) {
					mat[p.getLinhas()][p.getColunas()] = true;
				}
				p.setValores(posicao.getLinhas() + 2, posicao.getColunas());
				Posicao p2 = new Posicao(posicao.getLinhas() + 1,posicao.getColunas());
				if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().peçaExiste(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().peçaExiste(p2)  && getContagemDeMovimento() == 0) {
					mat[p.getLinhas()][p.getColunas()] = true;
				}
				p.setValores(posicao.getLinhas() + 1, posicao.getColunas() - 1);
				if(getTabuleiro().posicaoExiste(p) && existePeçaOponente(p)) {
					mat[p.getLinhas()][p.getColunas()] = true;
				}
				p.setValores(posicao.getLinhas() + 1, posicao.getColunas() + 1);
				if(getTabuleiro().posicaoExiste(p) && existePeçaOponente(p)) {
					mat[p.getLinhas()][p.getColunas()] = true;
				}
		}
		return mat;

	}
}
