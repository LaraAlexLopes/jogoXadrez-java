package xadrez.peças;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaXadrez;
public class Cavalo extends PeçaXadrez {

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	@Override
	public String toString() {
		return "C";
	}
	private boolean consegueMover(Posicao posicao) {
		PeçaXadrez p = (PeçaXadrez) getTabuleiro().peça(posicao);
		return p == null || p.getCor() != getCor();
	}
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		p.setValores(posicao.getLinhas() - 1, posicao.getColunas() - 2);
		if(getTabuleiro().posicaoExiste(p) && consegueMover(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		
		p.setValores(posicao.getLinhas() - 2, posicao.getColunas() - 1);
		if(getTabuleiro().posicaoExiste(p) && consegueMover(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		
		p.setValores(posicao.getLinhas() - 2, posicao.getColunas() + 1);
		if(getTabuleiro().posicaoExiste(p) && consegueMover(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		
		p.setValores(posicao.getLinhas() - 1, posicao.getColunas() + 2);
		if(getTabuleiro().posicaoExiste(p) && consegueMover(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		
		p.setValores(posicao.getLinhas() + 1, posicao.getColunas() + 2);
		if(getTabuleiro().posicaoExiste(p) && consegueMover(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		
		p.setValores(posicao.getLinhas() + 2, posicao.getColunas() + 1);
		if(getTabuleiro().posicaoExiste(p) && consegueMover(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		
		p.setValores(posicao.getLinhas() + 2, posicao.getColunas() - 1);
		if(getTabuleiro().posicaoExiste(p) && consegueMover(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		//sudeste
		p.setValores(posicao.getLinhas() + 1, posicao.getColunas() - 2);
		if(getTabuleiro().posicaoExiste(p) && consegueMover(p)) {
			mat[p.getLinhas()][p.getColunas()] = true;
		}
		
		return mat;
	}

}
