package pkj;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CharactersInPlay {
	
	private ArrayList<String> charNames;
	private ArrayList<Integer> counts;
	
	public CharactersInPlay() {
		charNames = new ArrayList<String>();
		counts = new ArrayList<Integer>();
	}
	
	public void update(String person) {
		int indx = charNames.indexOf(person);
		if(indx == -1) {
			charNames.add(person);
			counts.add(1);
		}
		else {
			int value = counts.get(indx);
			counts.set(indx,++value);
		}
	}
	
	public void findAllCharacters() throws FileNotFoundException{
		
		charNames.clear();
		counts.clear();
		
		DirectoryResource dr = new DirectoryResource();
		Scanner sc = null;
		for(File f : dr.selectedFiles()) {
			sc =  new Scanner(f);
			while(sc.hasNext()) {
				String s = sc.nextLine();
				s = s.trim();
				int length = s.length();
				int indx = s.indexOf(".");
				if(indx != -1) {
					s = s.substring(0,indx);
					if(s.length()+1 == length)
						continue;
					update(s);
				}
			}
		}
	}
	
	public void tester() throws FileNotFoundException {
		findAllCharacters();
		int counter = 0;
		for(int i = 0 ; i <charNames.size();i++) {
			if(counts.get(i) > 200) {
			System.out.println(charNames.get(i) + " is repeated " + counts.get(i));
			counter++;
			}
		}
		System.out.println("Number of Characters: " + counter);
		
		int max = findIndexOfMax();
		System.out.println("The Main Character is: " + charNames.get(max) + " " + counts.get(max));
		
		charactersWithNumParts(10,15);
	}
	
	private int findIndexOfMax() {
		int max = 0;
		for(int i = 1 ; i<counts.size(); i++) {
			if(counts.get(i) > counts.get(max)) {
				max = i;
			}
		}
		return max;
	}
	
	public void charactersWithNumParts(int num1,int num2) {
		int counter = 0;
		for(int i = 0 ; i <charNames.size();i++) {
			if(counts.get(i) >= num1 && counts.get(i) <= num2) {
				System.out.println("Character where greater than or equal " + num1 + " and less than or equal " + num2
						+ " is " + charNames.get(i) + " and is repeated " + counts.get(i));
				counter++;
			}
		}
		System.out.println("Number of this Characters is: " + counter);
	}
	
}
