package aplicação;
import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Programa {
	public static void main(String[] args) {
		PartidaXadrez partida = new PartidaXadrez();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			try {
				UI.limparTela();
				UI.printTabuleiro(partida.getPeça());
				System.out.println();
				System.out.println("Origem: ");
				PosicaoXadrez origem = UI.lerPosicaoPeça(sc);					
				System.out.println();
				System.out.println("Destino: ");
				PosicaoXadrez destino = UI.lerPosicaoPeça(sc);			
				PeçaXadrez peçaCapturada = partida.perfomaceMovimentoXadrez(origem,destino);
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
	}
}


