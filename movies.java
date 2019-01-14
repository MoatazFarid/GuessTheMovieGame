import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class movies {
	private static final boolean DEBUG =true;// flag to set debug mode 
	/**
	 * instance variable for the class
	 */
	private static movies instance ;// instance variable for Singleton pattern
	
	/**
	 * Private Constructor
	 */
	private movies() {}
	
	public static ArrayList<String> moviesList ;
	private static int wrongTries;
	private static String guessedName;
	private static String chosenName;
	/**
	 * 
	 * @param filePath file Path
	 * @return
	 * @throws IOException
	 */
	public static void readMoviesFile(FileInputStream filePath) throws IOException {
		String inMovieName ;
		int i=0;
		DataInputStream inputFile = new DataInputStream(filePath); // reading file
		if(DEBUG)System.out.println("Reading from file ...");
		
		while((inMovieName = inputFile.readLine()) != null) {
			/// inMoviename now holds the movie name 
			moviesList.add(inMovieName);//adding a movie to the list
			if(DEBUG)System.out.println("\tAdding "+moviesList.get(i)+" to List of movies ..");
			if(DEBUG)i++;
			
		}
		if(DEBUG)System.out.println("Finished Adding movies...");
	}
	/**
	 * replace all length of the movie name with _ for letters and space for space
	 * @param chosenName that will be converted to symbols
	 * @return
	 */
	public static String movieNameInit(String chosenName) {
		String temp = ""; 
		for(int i=0 ;i<chosenName.length();i++) {
			if(chosenName.charAt(i) == ' ') { // if space
				temp+=' ';
			}else {//if a normal char
				temp+='_';
			}
		}
		movies.setGuessedName(temp);
		return temp;
		
	}
	/**
	 * check if the user guessing is right , this should replace the _ with the guessed character if exist
	 * @param guessedChar the guessed character from user 
	 * @return true if found and replaced , false if not 
	 */
	public static boolean checkGuess(String guessedChar) {
		if(movies.getChosenName().contains(guessedChar)) {
			if(replaceCharacters(guessedChar)) {
				return true;
			}else {
				System.err.println("Error in character Replacement");
				return true;//it won't really matter if the character is replaces or not , if returned the app is buggy
			}
		}else {
			movies.setWrongTries(movies.getWrongTries()+1);
		}
		
		return false;
		
	}
	public static boolean replaceCharacters(String guessedChar) {
		String temp="";
		boolean replacedChar =false;
		for(int i =0;i<movies.getChosenName().length();i++) {
			if(movies.getChosenName().charAt(i)==guessedChar.charAt(0)) {
				replacedChar = true;
				temp+=guessedChar.charAt(0);
			}else {
				temp+=movies.getGuessedName().charAt(i);
			}
		}
		if(replacedChar) {
			movies.setGuessedName(temp);
			return true;
		}
		return false;
	}
	/**
	 * used to get a instance of the class and create one if not existed 
	 * @return movies Object
	 */
	public static movies getInstance() { 
		if(instance == null) {//first time 
			instance= new movies();
			moviesList = new ArrayList<String>();
		}
		return instance;
	}
	public static int getWrongTries() {
		return wrongTries;
	}
	public static void setWrongTries(int wrongTries) {
		movies.wrongTries = wrongTries;
	}
	public static String getGuessedName() {
		return guessedName;
	}
	public static void setGuessedName(String guessedName) {
		movies.guessedName = guessedName;
	}
	public static String getChosenName() {
		return chosenName;
	}
	public static void setChosenName(String chosenName) {
		movies.chosenName = chosenName;
	}

	
}
