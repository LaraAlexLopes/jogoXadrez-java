package xadrez;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peça;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.peças.Bispo;
import xadrez.peças.Peao;
import xadrez.peças.Rei;
import xadrez.peças.Torre;
public class PartidaXadrez {
	private int turno;
	private Cor jogador;
	private Tabuleiro tabuleiro;
	private boolean cheque;
	private boolean chequeMate;
	private List<Peça> peçasNoTabuleiro = new ArrayList<>();
	private List<Peça> peçasCapturadas = new ArrayList<>();

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
		turno = 1;
		jogador = Cor.Branco;
		inicioPartida();
	}
	
	public int getTurno() {
		return turno;
	}
	public Cor getJogador() {
		return jogador;
	}
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
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
	public boolean getCheque() {
		return cheque;
	}
	public boolean getChequeMate() {
		return chequeMate;
	}
	public boolean[][] movimentosPossivveis(PosicaoXadrez posicaoOrigem){
		Posicao posicao = posicaoOrigem.toPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peça(posicao).possiveisMovimentos() ;
		
	}	
	public PeçaXadrez perfomaceMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino){
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem,destino);
		Peça peçaCapturada = movimentacao(origem,destino);
		if(testCheque(jogador)){
			desfazerMovimento(origem,destino,peçaCapturada);
			throw new XadrezException("Você não pode se colocar em cheque");
		}
		cheque = (testCheque(oponente(jogador))) ? true: false;
		if(testChequeMate(oponente(jogador))){
			chequeMate = true;
		}
		else {
			proximoTurno();
		}
		return (PeçaXadrez) peçaCapturada;
	}
	private Peça movimentacao(Posicao origem,Posicao destino) {
		PeçaXadrez p = (PeçaXadrez)tabuleiro.removePeça(origem);
		p.incrementarContagemDeMovimento();
		Peça peçaCapturada = tabuleiro.removePeça(destino);
		tabuleiro.posicaoPeça(p, destino);
		
		if(peçaCapturada != null) {
			peçasNoTabuleiro.remove(peçaCapturada);
			peçasCapturadas.add(peçaCapturada);
		}
		return peçaCapturada;
	}
	private void desfazerMovimento(Posicao origem,Posicao destino, Peça peçaCapturada) {
		PeçaXadrez p =(PeçaXadrez)tabuleiro.removePeça(destino);
		p.decrementarContagemDeMovimento();
		tabuleiro.posicaoPeça(p, origem);
		
		if(peçaCapturada != null) {
			tabuleiro.posicaoPeça(peçaCapturada, destino);
			peçasCapturadas.remove(peçaCapturada);
			peçasNoTabuleiro.add(peçaCapturada);
		}
	}
	private void validarPosicaoOrigem(Posicao posicao){
		if(!tabuleiro.peçaExiste(posicao)) {
			throw new XadrezException("Não existe peça na posição de origem");
		}
		if(jogador != (((PeçaXadrez)tabuleiro.peça(posicao)).getCor())){
			throw new XadrezException("A peça não é sua");
		}
		if(!tabuleiro.peça(posicao).existePossivelMovimento()){
			throw new XadrezException("Não existe movimentos possíveis para a peça escolhida");
		}
	}
	private void validarPosicaoDestino(Posicao origem,Posicao destino){
		if(!tabuleiro.peça(origem).possiveisMovimento(destino)) {
			throw new XadrezException("A peça escolhida não pode ser movida para a posição de destino");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogador = (jogador == Cor.Branco) ? Cor.Preto : Cor.Branco;
	}
	private Cor oponente(Cor cor) {
		return (cor == Cor.Branco) ? Cor.Preto : Cor.Branco;
	}
	private PeçaXadrez rei(Cor cor) {
		List<Peça> list = peçasNoTabuleiro.stream().filter(x -> ((PeçaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Peça p : list) {
			if( p instanceof Rei) {
				return (PeçaXadrez) p;
			}
		}
		throw new IllegalStateException("Não tem rei da cor " + cor + " no tabuleiro");
	}
	private boolean testCheque(Cor cor) {
		Posicao posicaoRei = rei(cor).getPosicaoXadrez().toPosicao();
		List<Peça> peçasOponentes = peçasNoTabuleiro.stream().filter(x -> ((PeçaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peça p : peçasOponentes) {
			boolean[][] mat = p.possiveisMovimentos();
			if(mat[posicaoRei.getLinhas()][posicaoRei.getColunas()]) {
				return true;
			}
		}
		return false;
	}
	private boolean testChequeMate(Cor cor) {
		if(!testCheque(cor)) {
			return false;
		}
		List<Peça> list = peçasNoTabuleiro.stream().filter(x -> ((PeçaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peça p :list) {
			boolean[][] mat = p.possiveisMovimentos();
			for(int i = 0;i<tabuleiro.getLinhas(); i ++){
				for(int j = 0;j<tabuleiro.getColunas();j++) {
					if(mat[i][j]) {
						Posicao origem = ((PeçaXadrez)p).getPosicaoXadrez().toPosicao();
						Posicao destino = new Posicao(i,j);
						Peça peçaCapturada = movimentacao(origem,destino);
						boolean testCheque = testCheque(cor);
						desfazerMovimento(origem,destino,peçaCapturada);
						if(!testCheque) {
							return false;
						}
					}
				}
				
			}
		}
		return true;

	}

	private void posicaoNovaPeça(char coluna, int linha,PeçaXadrez peça){
		tabuleiro.posicaoPeça(peça, new PosicaoXadrez(coluna,linha).toPosicao());
		peçasNoTabuleiro.add(peça);
	}
	
	private void inicioPartida(){
		posicaoNovaPeça('a', 1, new Torre(tabuleiro, Cor.Branco));
		posicaoNovaPeça('c', 1, new Bispo(tabuleiro, Cor.Branco));
		posicaoNovaPeça('e', 1, new Rei(tabuleiro, Cor.Branco));
		posicaoNovaPeça('f', 1, new Bispo(tabuleiro, Cor.Branco));
		posicaoNovaPeça('h', 1, new Torre(tabuleiro, Cor.Branco));
		posicaoNovaPeça('a', 2, new Peao(tabuleiro, Cor.Branco));
		posicaoNovaPeça('b', 2, new Peao(tabuleiro, Cor.Branco));
		posicaoNovaPeça('c', 2, new Peao(tabuleiro, Cor.Branco));
		posicaoNovaPeça('d', 2, new Peao(tabuleiro, Cor.Branco));
		posicaoNovaPeça('e', 2, new Peao(tabuleiro, Cor.Branco));
		posicaoNovaPeça('f', 2, new Peao(tabuleiro, Cor.Branco));
		posicaoNovaPeça('g', 2, new Peao(tabuleiro, Cor.Branco));
		posicaoNovaPeça('h', 2, new Peao(tabuleiro, Cor.Branco));
		
		
		posicaoNovaPeça('a', 8, new Torre(tabuleiro, Cor.Preto));
		posicaoNovaPeça('c', 8, new Bispo(tabuleiro, Cor.Preto));
		posicaoNovaPeça('e', 8, new Rei(tabuleiro, Cor.Preto));
		posicaoNovaPeça('f', 8, new Bispo(tabuleiro, Cor.Preto));
		posicaoNovaPeça('h', 8, new Torre(tabuleiro, Cor.Preto));
		posicaoNovaPeça('a', 7, new Peao(tabuleiro, Cor.Preto));
		posicaoNovaPeça('b', 7, new Peao(tabuleiro, Cor.Preto));
		posicaoNovaPeça('c', 7, new Peao(tabuleiro, Cor.Preto));
		posicaoNovaPeça('d', 7, new Peao(tabuleiro, Cor.Preto));
		posicaoNovaPeça('e', 7, new Peao(tabuleiro, Cor.Preto));
		posicaoNovaPeça('f', 7, new Peao(tabuleiro, Cor.Preto));
		posicaoNovaPeça('g', 7, new Peao(tabuleiro, Cor.Preto));
		posicaoNovaPeça('h', 7, new Peao(tabuleiro, Cor.Preto));
	}

}
