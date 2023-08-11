package xadrez.peças;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;

public class Peao extends PeçaXadrez{
	private PartidaXadrez partidaXadrez;
	public  Peao(Tabuleiro tabuleiro,Cor cor,PartidaXadrez partidaXadrez){
		super(tabuleiro,cor);
		this.partidaXadrez = partidaXadrez;
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
			//en Passant
			if(posicao.getLinhas() == 3){
				Posicao esquerda = new Posicao(posicao.getLinhas(),posicao.getColunas() - 1);
				if(getTabuleiro().posicaoExiste(esquerda) && existePeçaOponente(esquerda) && getTabuleiro().peça(esquerda) == partidaXadrez.getVulnerabilidadeEnPassant()) {
					mat[esquerda.getLinhas()-1][esquerda.getColunas()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinhas(),posicao.getColunas() + 1);
				if(getTabuleiro().posicaoExiste(direita) && existePeçaOponente(direita) && getTabuleiro().peça(direita) == partidaXadrez.getVulnerabilidadeEnPassant()) {
					mat[direita.getLinhas()-1][direita.getColunas()] = true;
				}
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
				//en Passant
				if(posicao.getLinhas() == 4){
					Posicao esquerda = new Posicao(posicao.getLinhas(),posicao.getColunas() - 1);
					if(getTabuleiro().posicaoExiste(esquerda) && existePeçaOponente(esquerda) && getTabuleiro().peça(esquerda) == partidaXadrez.getVulnerabilidadeEnPassant()) {
						mat[esquerda.getLinhas() + 1][esquerda.getColunas()] = true;
					}
					Posicao direita = new Posicao(posicao.getLinhas(),posicao.getColunas() + 1);
					if(getTabuleiro().posicaoExiste(direita) && existePeçaOponente(direita) && getTabuleiro().peça(direita) == partidaXadrez.getVulnerabilidadeEnPassant()) {
						mat[direita.getLinhas() + 1][direita.getColunas()] = true;
					}
				}
		}
		return mat;

	}
}
