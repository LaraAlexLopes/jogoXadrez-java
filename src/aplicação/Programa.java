package aplicação;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;
public class Programa {
	public static void main(String[] args) {
		PartidaXadrez partida = new PartidaXadrez();
		Scanner sc = new Scanner(System.in);
		List<PeçaXadrez> capturadas = new ArrayList<>();
		
		while(!partida.getChequeMate()) {
			try {
				UI.limparTela();
				UI.printPartida(partida,capturadas);
				System.out.println("Origem: ");
				PosicaoXadrez origem = UI.lerPosicaoPeça(sc);	
				
				boolean[][] possiveisMovimentos = partida.movimentosPossivveis(origem);
				UI.limparTela();
				UI.printTabuleiro(partida.getPeça(),possiveisMovimentos);
				System.out.println("Destino: ");
				PosicaoXadrez destino = UI.lerPosicaoPeça(sc);
				PeçaXadrez peçaCapturada = partida.perfomaceMovimentoXadrez(origem,destino);
				
				if(peçaCapturada != null) {
					capturadas.add(peçaCapturada);
				}
				if(partida.getPromovido() != null) {
					System.out.print("Digite peça a ser promovida (B/Q/T/C)");
					String type = sc.nextLine();
					partida.substituiPeçaPromovida(type);
				}
			}
			catch(XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.limparTela();
		UI.printPartida(partida, capturadas);
	}
}


