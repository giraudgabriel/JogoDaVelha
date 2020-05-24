package edu.fatec.sjc.lp2.jogo;

import java.util.Scanner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class JogoDaVelha {
	private Ocupacao[][] grade;
	private int[][] posicoes;
	private int contador;

	public JogoDaVelha() {
		setGrade(new Ocupacao[][] { { Ocupacao.vazia, Ocupacao.vazia, Ocupacao.vazia },
				{ Ocupacao.vazia, Ocupacao.vazia, Ocupacao.vazia },
				{ Ocupacao.vazia, Ocupacao.vazia, Ocupacao.vazia }, });
		setPosicoes(new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } });
	}

	public String verificaGrade() {
		if (contador >= 3) {
			for (int i = 0; i < 3; i++) {
				if (grade[i][0] != Ocupacao.vazia && grade[i][0] == grade[i][1] && grade[i][1] == grade[i][2]) {
					return grade[i][0].name() + " ganhou!";
				}

				if (grade[0][i] != Ocupacao.vazia && grade[0][i] == grade[1][i] && grade[1][i] == grade[2][i]) {
					return grade[0][i].name() + " ganhou!";
				}
			}
			if (grade[0][0] != Ocupacao.vazia && grade[0][0] == grade[1][1] && grade[1][1] == grade[2][2]) {
				return grade[0][0].name() + " ganhou!";
			}
			if (grade[0][2] != Ocupacao.vazia && grade[0][2] == grade[1][1] && grade[1][1] == grade[2][0]) {
				return grade[0][2].name() + " ganhou!";
			}
			if (contador == 9) {
				return "Empate";
			}
		}
		return null;
	}

	public int verificarNumero(int numero) {
		if (numero >= 0 && numero <= 2) {
			return 0;
		} else if (numero >= 3 && numero <= 5) {
			return 1;
		} else if (numero >= 6 && numero <= 8) {
			return 2;
		}
		return -1;
	}

	public int verificarPosicaoNumero(int indice, int numero) {
		int[] array = getPosicoes()[indice];
		for (int i = 0; i < array.length; i++)
			if (array[i] == numero)
				return i;
		return -1;
	}

	public boolean efetuarJogada(int numero, Ocupacao jogador) {
		int indice = verificarNumero(numero);
		if (indice != -1) {
			int posicao = verificarPosicaoNumero(indice, numero);
			if (posicao != -1) {
				grade[indice][posicao] = jogador;
				contador++;
				return true;
			}
		}
		System.out.println("Posição inválida! Escolha outra... \n");
		return false;
	}

	public void jogar() {
		setContador(0);
		Ocupacao jogador = Ocupacao.jogador1;
		Scanner s = new Scanner(System.in);
		while (verificaGrade() == null) {
			boolean inputOk = false;
			exibirGrade();

			System.out.print("Vez do jogador: " + jogador.name() + "\n");
			System.out.print("Digite um número do jogo para preencher:\n");

			while (!inputOk) {
				String numero = s.nextLine();
				if (numero.chars().allMatch(Character::isDigit))
					inputOk = efetuarJogada(Integer.parseInt(numero), jogador);
				else
					System.out.println("Digite um valor valido! \n");
			}

			jogador = jogador.equals(Ocupacao.jogador1) ? Ocupacao.jogador2 : Ocupacao.jogador1;
		}
		exibirGrade();
		System.out.println("\nResultado Final: \n");
		System.out.println(verificaGrade());
		s.close();
	}

	public void exibirGrade() {
		for (int i = 0; i < 3; i++) {
			String p1 = grade[i][0].equals(Ocupacao.vazia) ? Integer.toString(posicoes[i][0]) : grade[i][0].name();
			String p2 = grade[i][1].equals(Ocupacao.vazia) ? Integer.toString(posicoes[i][1]) : grade[i][1].name();
			String p3 = grade[i][2].equals(Ocupacao.vazia) ? Integer.toString(posicoes[i][2]) : grade[i][2].name();
			System.out.println("\t" + p1 + "\t|\t" + p2 + "\t|\t" + p3 + "\t");
			if (i != 2) {
				System.out.println("-------------------------------------------------\t");
			}
		}
		System.out.println("\n");
	}
}
