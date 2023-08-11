package xadrez.peças;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;
public class Rei extends PeçaXadrez{
	private PartidaXadrez partidaXadrez;
	public Rei(Tabuleiro tabuleiro, Cor cor,PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}
	@Override
	public String toString() {
		return "K";
	}
	private boolean consegueMover(Posicao posicao) {
		PeçaXadrez p = (PeçaXadrez) getTabuleiro().peça(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testRoque(Posicao posicao){
		PeçaXadrez p = (PeçaXadrez)getTabuleiro().peça(posicao);
		return p!= null && p instanceof Torre && p.getCor() == getCor() && p.getContagemDeMovimento() == 0;
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
		
		//Roque
		if(getContagemDeMovimento() == 0 && !partidaXadrez.getCheque()) {
			//roque pequeno
			Posicao posicaoTorre1 = new Posicao(posicao.getLinhas(),posicao.getColunas() + 3);
			if(testRoque(posicaoTorre1)) {
				Posicao p1 = new Posicao(posicao.getLinhas(),posicao.getColunas() + 1);
				Posicao p2 = new Posicao(posicao.getLinhas(),posicao.getColunas() + 2);
				if(getTabuleiro().peça(p1) == null && getTabuleiro().peça(p2) == null) {
					mat[posicao.getLinhas()][posicao.getColunas() + 2] = true;
				}
			}
			//roque grande
			Posicao posicaoTorre2 = new Posicao(posicao.getLinhas(),posicao.getColunas() - 4);
			if(testRoque(posicaoTorre2)) {
				Posicao p1 = new Posicao(posicao.getLinhas(),posicao.getColunas() - 1);
				Posicao p2 = new Posicao(posicao.getLinhas(),posicao.getColunas() - 2);
				Posicao p3 = new Posicao(posicao.getLinhas(),posicao.getColunas() - 3);
				if(getTabuleiro().peça(p1) == null && getTabuleiro().peça(p2) == null && getTabuleiro().peça(p3) == null) {
					mat[posicao.getLinhas()][posicao.getColunas() - 2] = true;
				}
				
			}
		}
		
		return mat;
	}
}
