package pkj;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordFrequencies {
	
		private ArrayList<String> myWords;
		private ArrayList<Integer> myFreqs;
		
		public WordFrequencies() {
			myWords = new ArrayList<String>();
			myFreqs = new ArrayList<Integer>();
		}
		
		public void findUnique() throws FileNotFoundException{
			myWords.clear();
			myFreqs.clear();
			DirectoryResource dr = new DirectoryResource();
			Scanner sc = null;
			for(File f : dr.selectedFiles()) {
				sc =  new Scanner(f);
				while(sc.hasNext()) {
					String s = sc.next();
					s = s.toLowerCase();
					int indx = myWords.indexOf(s);
					if(indx == -1) {
						myWords.add(s);
						myFreqs.add(1);
					}
					else {

						int value = myFreqs.get(indx);
						myFreqs.set(indx,++value);
					}
				}
			}
			sc.close();
		}
		
		public void tester() throws FileNotFoundException{
			findUnique();
			System.out.println("Number of unique words: " + myWords.size());
			for(int i = 0 ; i <myWords.size();i++) {
				if(myFreqs.get(i) >= 400)
				System.out.println("The word " + myWords.get(i) + " is repeated " + myFreqs.get(i));
			}
			int max = findIndexOfMax();
			System.out.println("The word that occurs most often and its count are: " + myWords.get(max) + " " + myFreqs.get(max));
		}
		
		public int findIndexOfMax() {
			int max = 0;
			for(int i = 1 ; i<myFreqs.size(); i++) {
				if(myFreqs.get(i) > myFreqs.get(max)) {
					max = i;
				}
			}
			return max;
		}
		
}
