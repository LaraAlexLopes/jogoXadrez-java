package xadrez;
import tabuleiro.Peça;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
public abstract class PeçaXadrez extends Peça {
	private Cor cor;
	public PeçaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}
	public Cor getCor() {
		return cor;
	}
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.posicao(posicao);
	}
	protected boolean existePeçaOponente(Posicao posicao) {
		PeçaXadrez p = (PeçaXadrez)getTabuleiro().peça(posicao);
		return p != null && p.getCor() != cor;
	}
}
