import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.Random;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class main {
	private final static int MAX_WRONG_TRIES  =10;// set MaxwrongTries = 10 
	private static final boolean DEBUG =true;// flag to set debug mode 

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);//scanner class for reading input from keyboard
		/*
		 * initializing movies app
		 */
		
		movies.getInstance();
//		reading the list of movies from a file 
		try {
			movies.readMoviesFile(new FileInputStream("movies.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Movies txt File is Not Found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//start main Algorithm
		/*
		 * 1- choose a random no from 0 to size-1 of movies array
		 * 2- start guessing mode 
		 * 	2.1 - run movieNameInit function that will replace all length of the movie guessed name with _ for letters and space for space 
		 * 	2.2 - start loop 
		 * 	2.2.1 - if wrongCounter < WrongCounterMax
		 *  2.2.1.1 - get guess from user 
		 *  2.2.1.2 if true replace with proper letter , checkguess function will check and replace and return true or false   
		 *  2.2.1.3 if false ++1 to wrongCounter
		 *  2.2.2 - if wrongcounter became > max then say GameOver
		 */
//		1- choose a random no from 0 to size of movies array
		Random rand = new Random();
		int randNo ;
		boolean playAgain=false; // the default is only one time play
		boolean gameOverflag =false;
		String playagainChoice;

		do {
			// game algorithm
			//prepare new game
			randNo = rand.nextInt(movies.moviesList.size()-1)+0; // getting a number between size-1 and 0
			movies.setWrongTries(0);
			
//			 2.1 - run movieNameInit function that will replace all length of the movie guessed name with _ for letters and space for space 
			movies.setChosenName(movies.moviesList.get(randNo));
			movies.movieNameInit(movies.getChosenName());
			//debug
			if(DEBUG)System.out.println("movie Chosen name is "+movies.getChosenName());
			if(DEBUG)System.out.println("movie Guessed name is "+movies.getGuessedName());
			//2.2 loop
			while(!gameOverflag) {
				System.out.println("Guessed Name Of The Movie is "+movies.getGuessedName());
//				2.2.1 - if wrongCounter < WrongCounterMax
				if(movies.getWrongTries() < MAX_WRONG_TRIES) {
//					 *  2.2.1.1 - get guess from user 
					String temp_guess;
					System.out.print("Guess a letter:");
					temp_guess = sc.next();
					System.out.println("");
//					 *  2.2.1.2 if true replace with proper letter , checkguess function will check and replace and return true or false
					if(movies.checkGuess(temp_guess)) {
						System.out.println("Correct Guess!!");
					}else {
//					 *  2.2.1.3 if false ++1 to wrongCounter
						System.out.println("Wrong Guess !!");
					}
					System.out.println("# of wrong tries ar( "+movies.getWrongTries()+" )");
				}else{//2.2.2 if wrongcounter became > max then say GameOver
					gameOverflag=true;
					System.err.println("Gameover dude :@ ");
					//if(DEBUG)movies.setWrongTries(10);
				}
				if(movies.getChosenName().equalsIgnoreCase(movies.getGuessedName())) {
					System.out.println("Congratuations u have solved it ^_^ ");
					System.out.println("The Movie name is "+movies.getGuessedName());
					gameOverflag=true;
				}
			}
			
			// play again request 
			playAgain=false;
			System.err.println("wanna play again ?! n-> no wanna end , otherwise -> yeb");
			System.out.println("choice: ");
			playagainChoice=sc.next();
			if(((playagainChoice).equalsIgnoreCase("n"))) {
				//if(DEBUG)System.out.println("input to play again is "+playagainChoice);
				playAgain=false;
				gameOverflag=true;
			}else {
				System.out.println("entered other");

				playAgain=true;
				gameOverflag=false;
			}
		}while(playAgain);
		System.out.println("Thanks for using our app ");
	}

}
