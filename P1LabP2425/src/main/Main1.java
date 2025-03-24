package main;

import java.util.Scanner;

import types.Code;
import types.Colour;
import types.Mastermind;

public class Main1 {

	public static void main(String[] args) {
		Colour[] multicolour = Colour.values();
		Scanner sc = new Scanner(System.in);
		boolean jogarNovamente = true;

		System.out.println("Bem-vindo ao Mastermind! \n");
		System.out.println("Cores disponíveis:");

		for (Colour c : multicolour) {
			System.out.print(c + " "); 
		}
		System.out.println("\n");
		int seed = 0;
		int size = 0;
		boolean entradaValida = false;

		while (!entradaValida) {
		    System.out.println("Introduza a seed: ");
		    if (sc.hasNextInt()) {
		        seed = sc.nextInt();
		        entradaValida = true;
		    } 
		    else {
		        System.out.println("Por favor, insira um número inteiro válido.");
		        sc.next(); 
		    }
		}

		entradaValida = false; 

		
		while (!entradaValida) {
		    System.out.println("Introduza o tamanho do código: ");
		    if (sc.hasNextInt()) {
		        size = sc.nextInt();
		        entradaValida = true;
		    } 
		    else {
		        System.out.println("Por favor, insira um número inteiro válido.");
		        sc.next(); 
		    }
		}



		Mastermind jogo = new Mastermind(seed, size, multicolour);
		while (jogarNovamente) {
			jogo.startNewRound();

			while (!jogo.isRoundFinished()) {
				System.out.println("Introduza as " + size + " cores:");
				Colour[] trial = new Colour[size];

				int i = 0;
				while (i < size) {
				    String input = sc.next().toUpperCase();
				    boolean valida = false;

				    for (Colour c : multicolour) {
				        if (c.toString().equals(input)) {
				            valida = true;
				            trial[i] = c;
				            i++; 
				            break;
				        }
				    }

				    if (!valida) {
				        System.out.println("Input invalido, por favor insira novamente:");
				    }
				}

				Code tentativa = new Code(trial);
				jogo.play(tentativa);

				System.out.println(jogo.toString());
			}

			System.out.println("Fim do jogo!");
			if (jogo.wasSecretRevealed()) {
				System.out.println("Parabéns! Descobriu o código secreto!");
			} 
			else {
				System.out.println("Tentativas esgotadas, perdeste!");
			}

			System.out.println("Quer jogar novamente? (S/N)");
			String resposta = sc.next().trim().toUpperCase();

			while (!resposta.equals("S") && !resposta.equals("N")) {
				System.out.println("Entrada inválida! Digite 'S' para jogar novamente ou 'N' para sair.");
				resposta = sc.next().trim().toUpperCase();
			}

			jogarNovamente = resposta.equals("S");
		}
		System.out.println("Obrigado por jogar! Até à próxima.");
		sc.close();
	}

}
