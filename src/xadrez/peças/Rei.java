package xadrez.peças;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaXadrez;
public class Rei extends PeçaXadrez{

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	@Override
	public String toString() {
		return "R";
	}
	private boolean consegueMover(Posicao posicao) {
		PeçaXadrez p = (PeçaXadrez) getTabuleiro().peça(posicao);
		return p == null || p.getCor() != getCor();
	}
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		//acima
		p.setValores(posicao.getLinhas() - 1, posicao.getColunas());
		if(getTabuleiro().posicaoExiste(p) && consegueMover(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		//baixo
		p.setValores(posicao.getLinhas() + 1, posicao.getColunas());
		if(getTabuleiro().posicaoExiste(p) && consegueMover(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		//esquerda
		p.setValores(posicao.getLinhas(), posicao.getColunas() - 1);
		if(getTabuleiro().posicaoExiste(p) && consegueMover(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		//direita
		p.setValores(posicao.getLinhas(), posicao.getColunas() + 1);
		if(getTabuleiro().posicaoExiste(p) && consegueMover(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		//noroeste
		p.setValores(posicao.getLinhas() - 1, posicao.getColunas() - 1);
		if(getTabuleiro().posicaoExiste(p) && consegueMover(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		//nordeste
		p.setValores(posicao.getLinhas() - 1, posicao.getColunas() + 1);
		if(getTabuleiro().posicaoExiste(p) && consegueMover(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		//sudoeste
		p.setValores(posicao.getLinhas() + 1, posicao.getColunas() - 1);
		if(getTabuleiro().posicaoExiste(p) && consegueMover(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		//sudeste
		p.setValores(posicao.getLinhas() + 1, posicao.getColunas() + 1);
		if(getTabuleiro().posicaoExiste(p) && consegueMover(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		return mat;
	}
}
