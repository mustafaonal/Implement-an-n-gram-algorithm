package cme4408_assigment_1_java;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class nlp {
	
	public static String file_read(String file_name) throws FileNotFoundException, IOException{
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file_name), "ISO-8859-9"));) {

            String line;
            String data = "";
            
            while ((line = br.readLine()) != null) {
            	data = data + line + "\n";
            }
            return data;
        }
    }
	
	public static String[] split_words(String data) {
		data = data.replaceAll("[^a-zA-Zzçðöþüýîâ1-9 ]", "");
		data = data.replaceAll("  ", " ");
		data = data.replaceAll("  ", " ");
		return data.split(" ");
	}
	
	public static void find_n_grams(String[] words, int n) {
		System.out.println("Top 100 items in " + n + "-gram");
		List<n_gram_array> n_grams = new ArrayList<n_gram_array>();
		for(int i = 0; i < (words.length - n + 1); i++) {
			boolean flag = true;
			String n_gram = "";
			if(n == 1) {
				n_gram = words[i];
			}else if(n == 2) {
				n_gram = String.join(" ", words[i], words[i+1]);
			}else if(n == 3) {
				n_gram = String.join(" ", words[i], words[i+1], words[i+2]);
			}
			for(int j = 0; j < n_grams.size(); j++) {
				if(n_grams.get(j).getN_gram().equals(n_gram)) {
					n_grams.get(j).setValue(n_grams.get(j).getValue() + 1);
					flag = false;
				}
				if(!flag) {
					break;
				}
			}
			if(flag) {
				n_grams.add(new n_gram_array(n_gram, 1));
			}
		}
		
		for(int k = 0; k < 100; k++) {
			int max_value = 0;
			int max_id = 0;
			for(int j = 0; j < n_grams.size(); j++) {
				if(n_grams.get(j).getValue() > max_value) {
					max_value = n_grams.get(j).getValue();
					max_id = j;
				}
			}
			System.out.println(k+1 + ": " + n_grams.get(max_id).getN_gram() + " - " +  n_grams.get(max_id).getValue());
			n_grams.remove(max_id);
		}
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		long start1 = System.currentTimeMillis();
		String text = file_read("UNUTULMUÞ DÝYARLAR.txt").toLowerCase() + file_read("BÝLÝM ÝÞ BAÞINDA.txt").toLowerCase() 
				+ file_read("BOZKIRDA.txt").toLowerCase() + file_read("DEÐÝÞÝM.txt").toLowerCase() + file_read("DENEMELER.txt").toLowerCase();
		String[] words = split_words(text);
		
		long start2 = System.currentTimeMillis();
		find_n_grams(words, 1);
		long stop1 = System.currentTimeMillis();
		find_n_grams(words, 2);
		long stop2 = System.currentTimeMillis();
		find_n_grams(words, 3);
		long stop3 = System.currentTimeMillis();
		System.out.println("Running time for 1-gram calculation: " + (stop1 - start2) / 1000 + " second\n"
				+ "Running time for 2-gram calculation: " + (stop2 - stop1) / 1000 + " second\n"
				+ "Running time for 3-gram calculation: " + (stop3 - stop2) / 1000 + " second");
		System.out.println("Runtime for reading data and spliting words: " + (start2 - start1) / 1000 + " second");
		System.out.println("Total runtime: " + (stop3 - start1) / 1000 + " second");
		System.out.println("Total word count: " + words.length);
		
		
		
	}

}
