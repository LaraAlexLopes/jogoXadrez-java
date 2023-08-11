package xadrez;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peça;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.peças.Bispo;
import xadrez.peças.Cavalo;
import xadrez.peças.Peao;
import xadrez.peças.Rainha;
import xadrez.peças.Rei;
import xadrez.peças.Torre;
public class PartidaXadrez {
	private int turno;
	private Cor jogador;
	private Tabuleiro tabuleiro;
	private boolean cheque;
	private boolean chequeMate;
	private PeçaXadrez vulnerabilidadeEnPassant;
	private List<Peça> peçasNoTabuleiro = new ArrayList<>();
	private List<Peça> peçasCapturadas = new ArrayList<>();

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
		turno = 1;
		jogador = Cor.Branco;
		inicioPartida();
	}
	public PeçaXadrez getVulnerabilidadeEnPassant() {
		return vulnerabilidadeEnPassant;
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
		PeçaXadrez peçaMovimentada = (PeçaXadrez) tabuleiro.peça(destino);
		
		cheque = (testCheque(oponente(jogador))) ? true: false;
		if(testChequeMate(oponente(jogador))){
			chequeMate = true;
		}
		else {
			proximoTurno();
		}
		// en Passant
		if(peçaMovimentada instanceof Peao && (destino.getLinhas() == origem.getLinhas() - 2 || destino.getLinhas() == origem.getLinhas() + 2)) {
			vulnerabilidadeEnPassant = peçaMovimentada;
		}
		else {
			vulnerabilidadeEnPassant = null;
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
		//roque pequeno
		if(p instanceof Rei && destino.getColunas() == origem.getColunas() + 2) {
			Posicao origemTorre = new Posicao(origem.getLinhas(),origem.getColunas() + 3);
			Posicao destinoTorre = new Posicao(origem.getLinhas(),origem.getColunas() + 1);
			PeçaXadrez roque = (PeçaXadrez)tabuleiro.removePeça(origemTorre);
			tabuleiro.posicaoPeça(roque, destinoTorre);
			roque.incrementarContagemDeMovimento();
		}
		//roque grande
		if(p instanceof Rei && destino.getColunas() == origem.getColunas() - 2) {
			Posicao origemTorre = new Posicao(origem.getLinhas(),origem.getColunas() - 4);
			Posicao destinoTorre = new Posicao(origem.getLinhas(),origem.getColunas() - 1);
			PeçaXadrez roque = (PeçaXadrez)tabuleiro.removePeça(origemTorre);			
			tabuleiro.posicaoPeça(roque, destinoTorre);
			roque.incrementarContagemDeMovimento();
		}
		//en Passant
		if(p instanceof Peao) {
			if(origem.getColunas() != destino.getColunas() && peçaCapturada == null) {
				Posicao posicaoPeao;
				if(p.getCor() == Cor.Branco) {
					posicaoPeao = new Posicao(destino.getLinhas() + 1,destino.getColunas());
				}
				else {
					posicaoPeao = new Posicao(destino.getLinhas() - 1,destino.getColunas());
				}
				peçaCapturada = tabuleiro.removePeça(posicaoPeao);
				peçasCapturadas.add(peçaCapturada);
				peçasNoTabuleiro.remove(peçaCapturada);
			}
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
		
		//roque pequeno
		if(p instanceof Rei && destino.getColunas() == origem.getColunas() + 2) {
			Posicao origemTorre = new Posicao(origem.getLinhas(),origem.getColunas() + 3);
			Posicao destinoTorre = new Posicao(origem.getLinhas(),origem.getColunas() + 1);
			PeçaXadrez roque = (PeçaXadrez)tabuleiro.removePeça(destinoTorre);				
			tabuleiro.posicaoPeça(roque, origemTorre);
			roque.decrementarContagemDeMovimento();
		}
		//roque grande
		if(p instanceof Rei && destino.getColunas() == origem.getColunas() - 2) {
			Posicao origemTorre = new Posicao(origem.getLinhas(),origem.getColunas() - 4);
			Posicao destinoTorre = new Posicao(origem.getLinhas(),origem.getColunas() - 1);
			PeçaXadrez roque = (PeçaXadrez)tabuleiro.removePeça(destinoTorre);				
			tabuleiro.posicaoPeça(roque, origemTorre);
			roque.incrementarContagemDeMovimento();
		}
		
		//en Passant
		if(p instanceof Peao) {
			if(origem.getColunas() != destino.getColunas() && peçaCapturada == vulnerabilidadeEnPassant){
				PeçaXadrez peao = (PeçaXadrez)tabuleiro.removePeça(destino);
				Posicao posicaoPeao;
				if(p.getCor() == Cor.Branco) {
					posicaoPeao = new Posicao(3,destino.getColunas());
				}
				else {
					posicaoPeao = new Posicao(4,destino.getColunas());
				}
				tabuleiro.posicaoPeça(peao,posicaoPeao);
			}
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
		posicaoNovaPeça('b', 1, new Cavalo(tabuleiro, Cor.Branco));
		posicaoNovaPeça('c', 1, new Bispo(tabuleiro, Cor.Branco));
		posicaoNovaPeça('d', 1, new Rainha(tabuleiro, Cor.Branco));
		posicaoNovaPeça('e', 1, new Rei(tabuleiro, Cor.Branco,this));
		posicaoNovaPeça('f', 1, new Bispo(tabuleiro, Cor.Branco));
		posicaoNovaPeça('g', 1, new Cavalo(tabuleiro, Cor.Branco));
		posicaoNovaPeça('h', 1, new Torre(tabuleiro, Cor.Branco));
		posicaoNovaPeça('a', 2, new Peao(tabuleiro, Cor.Branco,this));
		posicaoNovaPeça('b', 2, new Peao(tabuleiro, Cor.Branco,this));
		posicaoNovaPeça('c', 2, new Peao(tabuleiro, Cor.Branco,this));
		posicaoNovaPeça('d', 2, new Peao(tabuleiro, Cor.Branco,this));
		posicaoNovaPeça('e', 2, new Peao(tabuleiro, Cor.Branco,this));
		posicaoNovaPeça('f', 2, new Peao(tabuleiro, Cor.Branco,this));
		posicaoNovaPeça('g', 2, new Peao(tabuleiro, Cor.Branco,this));
		posicaoNovaPeça('h', 2, new Peao(tabuleiro, Cor.Branco,this));
		
		
		posicaoNovaPeça('a', 8, new Torre(tabuleiro, Cor.Preto));
		posicaoNovaPeça('b', 8, new Cavalo(tabuleiro, Cor.Preto));
		posicaoNovaPeça('c', 8, new Bispo(tabuleiro, Cor.Preto));
		posicaoNovaPeça('d', 8, new Rainha(tabuleiro, Cor.Preto));
		posicaoNovaPeça('e', 8, new Rei(tabuleiro, Cor.Preto,this));
		posicaoNovaPeça('f', 8, new Bispo(tabuleiro, Cor.Preto));
		posicaoNovaPeça('g', 8, new Cavalo(tabuleiro, Cor.Preto));
		posicaoNovaPeça('h', 8, new Torre(tabuleiro, Cor.Preto));
		posicaoNovaPeça('a', 7, new Peao(tabuleiro, Cor.Preto,this));
		posicaoNovaPeça('b', 7, new Peao(tabuleiro, Cor.Preto,this));
		posicaoNovaPeça('c', 7, new Peao(tabuleiro, Cor.Preto,this));
		posicaoNovaPeça('d', 7, new Peao(tabuleiro, Cor.Preto,this));
		posicaoNovaPeça('e', 7, new Peao(tabuleiro, Cor.Preto,this));
		posicaoNovaPeça('f', 7, new Peao(tabuleiro, Cor.Preto,this));
		posicaoNovaPeça('g', 7, new Peao(tabuleiro, Cor.Preto,this));
		posicaoNovaPeça('h', 7, new Peao(tabuleiro, Cor.Preto,this));
	}

}
