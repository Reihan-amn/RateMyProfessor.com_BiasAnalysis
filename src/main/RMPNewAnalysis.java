package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RMPNewAnalysis {
	
	public static ArrayList<String> Personality_Related = new ArrayList <String>();
	public static ArrayList<String> Personality_NOT_Related = new ArrayList <String>();
	public static ArrayList<String> Adjectives = new ArrayList <String>();
	public static ArrayList<String> StopWords = new ArrayList <String>();
	public static int[] tf = new int[10000];
	
	public static void AdjectiveReader() throws FileNotFoundException,
			IOException {

		File folder = new File("./Adjective");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			String path = file.getPath();

			BufferedReader input = new BufferedReader(new FileReader(path)); // input

			String thisLine = null;
			while ((thisLine = input.readLine()) != null) {
				String[] temp = null;
				temp = thisLine.split("\n");

				for (int i = 0; i < temp.length; i++) {
					if (!Adjectives.contains(temp[i]))
						Adjectives.add(temp[i].toString().toLowerCase());
				}
			}
		}
	}
	
	public static void StopWordsList(String fileName)
			throws FileNotFoundException, IOException {

		BufferedReader input = new BufferedReader(new FileReader(fileName)); // input

		String thisLine = null;
		while ((thisLine = input.readLine()) != null) {
			String[] temp = null;
			temp = thisLine.split("\n");

			for (int i = 0; i < temp.length; i++) {
				if(temp[i].trim().length() > 0)
				StopWords.add(temp[i].toLowerCase());
			}
		}
	}
	
	
	public static void Put_in_theList(String path) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(path)); // input

		String pattern2 = "[0-9]+";
		Pattern r2 = Pattern.compile(pattern2);

		String thisLine = null;
		while ((thisLine = input.readLine()) != null) {
			String[] temp = null;
			temp = thisLine.trim().split(" ");

			for (int i = 0; i < temp.length; i++) {
				Matcher m2 = r2.matcher(temp[i]);
				if (!m2.find()) {
					temp[i] = temp[i].replaceAll("\\W", "");
					if (!StopWords.contains(temp[i])) {
						if (!Personality_Related.contains(temp[i])) {
							Personality_Related.add(temp[i]);
							//int index = Personality_Related.indexOf(temp[i]);
							//tf[index]++;
						} 
//						else {
//							int index = Personality_Related.indexOf(temp[i]);
//							tf[index]++;
//						}
					}
				}
			}
		}
	}
	
	public static void Not_PR_Reader(String path) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(path)); // input

		String pattern2 = "[0-9]+";
		Pattern r2 = Pattern.compile(pattern2);

		String thisLine = null;
		while ((thisLine = input.readLine()) != null) {
			String[] temp = null;
			temp = thisLine.trim().split(" ");

			for (int i = 0; i < temp.length; i++) {
				Matcher m2 = r2.matcher(temp[i]);
				if (!m2.find()) {
					temp[i] = temp[i].replaceAll("\\W", "");
					if (!StopWords.contains(temp[i])) {
						if (!Personality_NOT_Related.contains(temp[i])) {
							Personality_NOT_Related.add(temp[i]);
							//int index = Personality_NOT_Related.indexOf(temp[i]);
							//tf[index]++;
						}
						
//						else {
//							int index = Personality_Related.indexOf(temp[i]);
//							tf[index]++;
//						}
					}
				}
			}
		}
	}
	
	
//	public static void fileWriter(String path) throws IOException
//	{
//		File newFile = new File(path);
//		if(! newFile.exists())
//			newFile.createNewFile();
//		
//		FileWriter fw = new FileWriter(newFile.getParentFile());
//		BufferedWriter bw = new BufferedWriter(fw);
//		for (int i = 0; i < Personality_Related.size(); i++) {
//			bw.write(Personality_Related.get(i) + "\n");
//		}
//		
//	}
	
	
	public static void testIdea(String fileName) throws IOException {

		// fileName is the address of a directory contains many text files
		File dir = new File(fileName);
		File[] files = dir.listFiles();
		int totalDocs = files.length;

		int personalityCount = 0;
		double sum = 0;
	
		int[] personalityCounter = new int[20];	
		
		for (int i = 0; i < files.length; i++) {

			//for each file/Professor we created a instance
			
			String filename = files[i].toString();
			sum += simpleMetric(filename);
			
			double x = simpleMetric(filename);
			System.out.println(x);
			

			if ( (x == 0) || (x < 0.05) ) {
				personalityCounter[0]++;	
				System.out.println("        huraa");
			}
		
			if ((x == 0.05) || ((x > 0.05) && (x < 0.1))) {
				personalityCounter[1]++;
				
			}
			
			if ((x == 0.1) || ((x > 0.1) && (x < 0.15))) {
				personalityCounter[2]++;	
				System.out.println("        2huraa");
			}
			if ((x == 0.15) || ((x > 0.15) && (x < 0.2)) ) {
				personalityCounter[3]++;	
				System.out.println("        3huraa");
			}
			if ( (x == 0.2) || ((x > 0.2) && (x < 0.25)) ) {
				personalityCounter[4]++;			
			}
			if ( (x == 0.25) || ((x > 0.25) && (x < 0.3)) ) {
				personalityCounter[5]++;			
			}
			if ((x == 0.3) || ((x > 0.3) && (x < 0.35)) ) {
				personalityCounter[6]++;			
			}
			if ((x == 0.35) || ((x > 0.35) && (x < 0.4)) ) {
				personalityCounter[7]++;			
			}
			if ( (x == 0.4) || ((x > 0.4) && (x < 0.45)) ) {
				personalityCounter[8]++;			
			}
			if ( (x == 0.45) || ((x > 0.45) && (x < 0.5)) ) {
				personalityCounter[9]++;			
			}
			if ( (x == 0.5) || ((x > 0.5) && (x < 0.55)) ) {
				personalityCounter[10]++;			
			}
			if ( (x == 0.55) || ((x > 0.55) && (x < 0.6)) ) {
				personalityCounter[11]++;			
			}
			if ( (x == 0.6) || ((x > 0.6) && (x < 0.65)) ) {
				personalityCounter[12]++;			
			}
			if ( (x == 0.65) || ((x > 0.65) && (x < 0.7)) ) {
				personalityCounter[13]++;			
			}
			if ( (x == 0.7) || ((x > 0.7) && (x < 0.75)) ) {
				personalityCounter[14]++;			
			}
			if ( (x == 0.75) || ((x > 0.75) && (x < 0.8)) ) {
				personalityCounter[15]++;			
			}
			if ( (x == 0.8) || ((x > 0.8) && (x < 0.85)) ) {
				personalityCounter[16]++;			
			}
			if ( (x == 0.85) || ((x > 0.85) && (x < 0.9)) ) {
				personalityCounter[17]++;			
			}
			if ( (x == 0.9) || ((x > 0.9) && (x < 0.95)) ) {
				personalityCounter[18]++;			
			}
			if ( (x == 0.95) || ((x > 0.95) && (x < 1)) ) {
				personalityCounter[19]++;	
				
			}
			if (  x == 1) {
				personalityCounter[19]++;			
			}
			
		}
		
		//System.out.println(personalityCount + " out of " + totalDocs);
		System.out.println(personalityCounter[0] + " out of " + totalDocs + " in [0.0 , 0.05]");
		System.out.println(personalityCounter[1] + " out of " + totalDocs + " in [0.5 , 0.1]");
		System.out.println(personalityCounter[2] + " out of " + totalDocs + " in [0.1 , 0.15]");
		System.out.println(personalityCounter[3] + " out of " + totalDocs + " in [0.15 , 0.2]");
		System.out.println(personalityCounter[4] + " out of " + totalDocs + " in [0.2 , 0.25]");
		System.out.println(personalityCounter[5] + " out of " + totalDocs + " in [0.25 , 0.3]");
		System.out.println(personalityCounter[6] + " out of " + totalDocs + " in [0.3 , 0.35]");
		System.out.println(personalityCounter[7] + " out of " + totalDocs + " in [0.35 , 0.4]");
		System.out.println(personalityCounter[8] + " out of " + totalDocs + " in [0.4 , 0.45]");
		System.out.println(personalityCounter[9] + " out of " + totalDocs + " in [0.45 , 0.5]");
		System.out.println(personalityCounter[10] + " out of " + totalDocs + " in [0.5 , 0.55]");
		System.out.println(personalityCounter[11] + " out of " + totalDocs + " in [0.55 , 0.6]");
		System.out.println(personalityCounter[12] + " out of " + totalDocs + " in [0.6 , 0.65]");
		System.out.println(personalityCounter[13] + " out of " + totalDocs + " in [0.65 , 0.7]");
		System.out.println(personalityCounter[14] + " out of " + totalDocs + " in [0.7 , 0.75]");
		System.out.println(personalityCounter[15] + " out of " + totalDocs + " in [0.75 , 0.8]");
		System.out.println(personalityCounter[16] + " out of " + totalDocs + " in [0.8 , 0.85]");
		System.out.println(personalityCounter[17] + " out of " + totalDocs + " in [0.85 , 0.9]");
		System.out.println(personalityCounter[18] + " out of " + totalDocs + " in [0.9 , 0.95]");
		System.out.println(personalityCounter[19] + " out of " + totalDocs + " in [0.95 , 1]");
		
		
		
		System.out.println("average is " + (sum / totalDocs));
		
		for (int i = 0; i < personalityCounter.length; i++) {
			System.out.println(personalityCounter[i]);
		}
	}
	
	public static double simpleMetric(String fileName) throws IOException {
		
		int count = 0;
		int words = 0;

		BufferedReader input = new BufferedReader(new FileReader(fileName)); // input

		String pattern2 = "[0-9]+";
		Pattern r2 = Pattern.compile(pattern2);

		String thisLine = null;
		while ((thisLine = input.readLine()) != null) {
			String[] temp = null;
			temp = thisLine.trim().split(" ");

			for (int i = 0; i < temp.length; i++) {
				words++;
				Matcher m2 = r2.matcher(temp[i]);
				if (!m2.find()) {
					temp[i] = temp[i].replaceAll("\\W", "");
					if (!StopWords.contains(temp[i])) {
						if (Adjectives.contains(temp[i].toString().toLowerCase())) {
							count++;
							if ( Personality_Related.contains(temp[i].toString().toLowerCase()) ) {
								count++;
							}
//							if ( Personality_NOT_Related.contains(temp[i].toString().toLowerCase()) ) {
//								count--;
//							}
						}
			
					}
				}
			}
		}
				
		input.close();
		return count / (float) words;
	}
	
	//-----------------------------------------
	
	public static void main(String[] args) throws IOException
	{
		
	
		StopWordsList("./StopWords.txt");
		AdjectiveReader();
		String path_PR = "./RMP_About";
		String path_Not_PR = "./RMP_NotAbout";
		String path_adjective = "./RMP_NotAbout";
		
		File dir = new File(path_PR);
		File[] list_of_file = dir.listFiles(); 
		for(File file : list_of_file)
		{
			String file_path = file.getPath();
			Put_in_theList(file_path);
		}
		
		File dir2 = new File(path_Not_PR);
		File[] list_of_file2 = dir2.listFiles(); 
		for(File files : list_of_file2)
		{
			String file_path = files.getPath();
			Not_PR_Reader(file_path);
		}
		
		
		
		for (int i = 0; i < StopWords.size(); i++) {
			System.out.print(i + " " + StopWords.get(i) + " , ");
		}
		System.out.println("");
		
		for (int i = 0; i < Adjectives.size(); i++) {
			System.out.print(i + "   " + Adjectives.get(i) + " , ");
		}

		System.out.println("");
		System.out.println("Personality_Related.size : " + Personality_Related.size());		
	
		for (int i = 0; i < Personality_Related.size(); i++) {
			System.out.print(i + " " + Personality_Related.get(i) + ", ");
		}
		System.out.println(Personality_Related.size());

		System.out.println("");

		System.out.println("Personality_NOT_Related.size : " + Personality_NOT_Related.size());	
		for (int i = 0; i < Personality_NOT_Related.size(); i++) {
			System.out.print(i + "  " + Personality_NOT_Related.get(i));
		}
		
		

		testIdea("C:/Users/Reihan/Google Drive/E/Prof. Michelle Cheatham/RateMyProfessor.com/DataBase/RateMyProfessor/1.Harvard-MIT-UCLA/Edu.PubRel.Antrop.Arthis.Bio.Chem(Female)/Male");
	}

}
