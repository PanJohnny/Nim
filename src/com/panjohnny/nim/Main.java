package com.panjohnny.nim;

import java.util.Random;
import java.util.Scanner;

public class Main {
	public static int stoneCount=0;
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		start();
	}

	public static void start() {
		System.out.println("-------------Nim-------------\nRules:\nThere are 15-30 stones at the beggining.\nYou can take 1-3 stones per round.\nPlayer that tooks last stone looses.");
		stoneCount=getRandomNumBetween(15, 30);
		nextUserTurn();
	}

	public static int getRandomNumBetween(int low, int high) {
		Random random = new Random();
		int num = random.nextInt(high+1);
		while(num<low) num=random.nextInt(high+1);
		return num;
	}


	public static int drawStones() {
		return getRandomNumBetween(1, 3);
	}

	public static boolean isValidEntry(String num) {
		int numStones=0;
		try {
			numStones=Integer.parseInt(num);
		} catch(NumberFormatException ex) {
			return false;
		}
		return numStones>=1 && numStones<=3 && stoneCount-numStones>=0;
	}

	public static void nextUserTurn() {
		System.out.print("There are "+stoneCount+" stones remaining. How many would you like to take? ");
		String input = sc.nextLine();
		if(isValidEntry(input)) {
			int stones = Integer.parseInt(input);
			if(stoneCount-stones==0) {
				System.out.println("You lose");
				System.exit(0);
			} else {
				stoneCount-=stones;
				nextAITurn();
			}
		} else {
			System.out.println("Couldn't get "+input+" stones");
			nextUserTurn();
		}
			
	}

	public static void nextAITurn() {
		int stones = drawStones();
		while(!isValidEntry(stones+"")) stones=drawStones();
		if(stones==1)
			System.out.println("There are "+stoneCount+" stones remaining. The computer takes "+stones+" stone.");
		else
			System.out.println("There are "+stoneCount+" stones remaining. The computer takes "+stones+" stones.");
		if(stoneCount-stones==0) {
			System.out.println("You win");
			System.exit(0);
		} else {
			stoneCount-=stones;
			nextUserTurn();
		}
	}
}
