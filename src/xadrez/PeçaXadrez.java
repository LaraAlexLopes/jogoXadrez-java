package xadrez;
import tabuleiro.Peça;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
public abstract class PeçaXadrez extends Peça {
	private Cor cor;
	private int contagemDeMovimento;
	public PeçaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}
	public int getContagemDeMovimento() {
		return contagemDeMovimento;
	}
	public Cor getCor() {
		return cor;
	}
	public void incrementarContagemDeMovimento() {
		contagemDeMovimento++;
	}
	public void decrementarContagemDeMovimento() {
		contagemDeMovimento--;
	}
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.posicao(posicao);
	}
	protected boolean existePeçaOponente(Posicao posicao) {
		PeçaXadrez p = (PeçaXadrez)getTabuleiro().peça(posicao);
		return p != null && p.getCor() != cor;
	}
}
