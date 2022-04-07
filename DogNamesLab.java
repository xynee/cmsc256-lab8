package cmsc256;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DogNamesLab {
	private static String promptForFileName() {
		System.out.println("Enter the file name: ");
		@SuppressWarnings("resource")
		Scanner keyIn = new Scanner(System.in);
		return keyIn.next();
	}

	private static Scanner openFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		while (!file.exists()) {
			file = new File(promptForFileName());
		}
		return new Scanner(file);
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		// Read data file to build data structure
		ArrayList<Dog> doglist = new ArrayList<>();
		
		try {
			// verify file and create file Scanner
			 Scanner fileReader = openFile("Dog_Names.csv");

			//  Discard header line
			 fileReader.nextLine();
			 
			 while(fileReader.hasNextLine()) {
			 	String line = fileReader.nextLine();
			 	int commaIndex = line.indexOf(',');
			 	String name = line.substring(0, commaIndex).trim();
			 	int count = Integer.parseInt(line.substring(commaIndex+1).trim());
			 	doglist.add(new Dog(name, count));
			 }
			 fileReader.close();
		}
		catch(FileNotFoundException noFile){
			System.out.println("There was an error opening or reading from the file.");
			System.exit(0);
		}

		Scanner readInput = new Scanner(System.in);
		String prompt = "\nWhat do you want to do?\n" 
				+ "\t1. Check a dog name\n" + "\t2. See all the dog names\n"
				 + "\t3. Play a game\n" + "\t4. Exit"
				 		+ ".\n"
				+ "Enter the number corresponding to your choice.";
		
		System.out.println(prompt);
		int option = readInput.nextInt();
		
		switch(option) {
		case 1:
			System.out.println("Enter a dog’s name?");
			String name = in.nextLine();
			int nameCount = getCountForDog(doglist, name);
			System.out.println(name + " is registered " + nameCount + " times.");
			break;
		case 2:
			System.out.println(getDogNamesAlphabetically(doglist));
			break;
		case 3:
			playGuessingGame(doglist, in);
			break;
		default: System.out.println("Invalid option.");
		}
		in.close();
	}

	public static int getCountForDog(ArrayList<Dog> dogs, String name) {
		// TODO: 
		// search the list for the Dog named name 
		// display dogs name and the number of registrations for that name

		int count = 0;
		for (int i = 0; i < dogs.size(); i++) {
			if (dogs.get(i).getDogName().equalsIgnoreCase(name)) {
				count = dogs.get(i).getCount();
				System.out.println(dogs.get(i).getDogName() + " is registered " + dogs.get(i).getCount() + " times.");
			}
		}
		return count;
	}
	
	public static String getDogNamesAlphabetically(ArrayList<Dog> dogs) {
		// TODO Sort the list of Dog by name return
		Collections.sort(dogs);
		String order = "";
		for (Dog dog : dogs) {
			order += " " + dog.getDogName();
		}
		return order;
	}

	public static void playGuessingGame(ArrayList<Dog> dogs, Scanner readIn) {
		// TODO: implement the guessing game.
		  // while not done playing
			// pull two random Dogs from the list
			// display the names and prompt player to pick the more popular name
		    // read player input
			// increment total number of guesses
			// check registration counts to determine if player is correct
				// if correct, respond and increment number of correct answers
				// if wrong, respond
			// ask user if they want to quit
				// if yes, display number of correct out of total number of guesses
				// if no, continue
		int first, second;
		int popFirst, popSecond;
		int numGuess = 0, numCorrect = 0;
		int MaxRandom = dogs.size();
		String userInput, userGameChoice;
		do {
			first = (int) (Math.random() * MaxRandom + 1);
			second = (int) (Math.random() * MaxRandom + 1);
			System.out.println("Which dog is more popular for Anchorage dogs? (Type 1 or 2");
			System.out.print("1. " + dogs.get(first).getDogName() + "\n" + "2. " + dogs.get(second).getDogName());
			userInput = readIn.nextLine();
			popFirst = getCountForDog(dogs, dogs.get(first).getDogName());
			popSecond = getCountForDog(dogs, dogs.get(first).getDogName());
			numGuess++;
			if(popFirst == popSecond){
				System.out.println("The dogs are equally popular.");
			}
			if(userInput.equals("1")) {
				if (popFirst > popSecond) {
					System.out.println("Yes, that's right.");
					numCorrect++;
				}
				else {
					System.out.println("Nope, the more popular dog name is "
							+ dogs.get(second).getDogName() + ".");
				}
			}
			else {
				if (popFirst < popSecond){
					System.out.println("Yes, that's right.");
					numCorrect++;
				}
				else {
					System.out.println("Nope, the more popular dog name is " +
							dogs.get(first).getDogName() + ".");
				}
			}
			System.out.println("Do you want to play again? (Y/N)");
			userGameChoice = readIn.nextLine();

		} while (userGameChoice.equals("Y"));

		System.out.println("You guessed correctly " + numCorrect + " out of " + numGuess + " times.");
	}


}
