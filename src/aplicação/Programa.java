package aplicação;
import java.util.Scanner;
import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {
	public static void main(String[] args) {
		PartidaXadrez partida = new PartidaXadrez();
		Scanner sc = new Scanner(System.in);
		while(true) {
			UI.printTabuleiro(partida.getPeça());
			System.out.println();
			System.out.println("Origem: ");
			PosicaoXadrez origem = UI.lerPosicaoPeça(sc);
			
			System.out.println();
			System.out.println("Destino: ");
			PosicaoXadrez destino = UI.lerPosicaoPeça(sc);
			
			PeçaXadrez peçaCapturada = partida.perfomaceMovimentoXadrez(origem,destino);
		}
	}
}
