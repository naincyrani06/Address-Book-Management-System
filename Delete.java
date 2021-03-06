package mainpackage;

import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Delete {
	public static void choose_field() throws FileNotFoundException, IOException {
		Scanner input = new Scanner(System.in);
		int exit = 0;
		int answer;

		do {
			System.out.println("Do you want to delete a contact based on the name or the phone number?");
			System.out.println("Give '1', '2' or '0' to go back to main menu.");
			try {
				answer = input.nextInt();
			} catch (Exception e) {
				answer = 0;
			}
			if(answer == 1)
				name_search();
			else if(answer == 2)
				number_search();

		}while(answer != exit);
	}

	public static void name_search() throws IOException, FileNotFoundException{
		Scanner input= new Scanner(System.in);
		String f1,f2;
		System.out.println("Give Name: ");
		f1 = input.nextLine();
		System.out.println("Give Surname: ");
		f2 = input.nextLine();
		File file = new File(System.getProperty("user.dir")");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine;
		boolean first = false;
		String[] fields = new String[0];
		List<String> lines = new ArrayList<String>();
		while((currentLine = reader.readLine()) !=null) {
			if(!first) {
				fields = currentLine.split(",");
				first = true;
			}
			else {
				String[] info=currentLine.split(",");
				if(info[0].equals(f1) && info[1].equals(f2)) {
					System.out.println("There is a contact for the information you gave");
					for (int i = 0; i < fields.length; i++ ) {
						System.out.println(fields[i] +": "+ info[i]);
					}
					lines.add(currentLine);
				}
				else if(info[0].equals(f1) && !info[1].equals(f2)) {
					System.out.println("There is a contact for the Name you gave");
					for (int i = 0; i < fields.length; i++ ) {
						System.out.println(fields[i] +": "+ info[i]);
					}
					System.out.println("Name ans Surname must be valid");
				}
				else if(!info[0].equals(f1) && info[1].equals(f2)) {
					System.out.println("There is a contact for the Surname you gave");
					for (int i = 0; i < fields.length; i++ ) {
						System.out.println(fields[i] +": "+ info[i]);
					}
					System.out.println("Name and Surname must be valid");
				}

			}
		}

		reader.close();
		for(Object str:lines){
			contact_delete(str.toString());
		}
		choose_field();
	}

	public static void number_search() throws IOException, FileNotFoundException{
		Scanner input= new Scanner(System.in);
		int f1 = -1;
		int f2 = -1;
		boolean valid;
		System.out.println("Give Phone number: ");
		do {
			valid = true;
			try {
			    f1 = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {

				valid = false;
			}
		}while(valid == false);
		System.out.println("Give Mobile number: ");
		do {
			valid = true;
			try {
			    f2 = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {

				valid = false;
			}
		}while(valid == false);
		File file = new File(System.getProperty("user.dir"));
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine;
		boolean first = false;
		String[] fields = new String[0];
		if(f1 == -1 && f2 == -1) {

			System.out.println("You gave wrong information.");
		}
		else {
			while((currentLine = reader.readLine()) !=null) {
				if(!first) {
					fields = currentLine.split(",");
					first = true;
				}
				else {
					String[] info=currentLine.split(",");
					if(f1 == -1 && f2 != -1) {
						if(info[3].equals(String.valueOf(f2))) {
							System.out.println("There is a contact for the Mobile number you gave");
							for (int i = 0; i < fields.length; i++ ) {
								System.out.println(fields[i] +": "+ info[i]);
							}
							System.out.println("Phone and Mobile numbers must be valid");
						}
					}
					else if(f1 != -1 && f2 == -1) {
						if(info[2].equals(String.valueOf(f1))) {
							System.out.println("There is a contact for the Phone number you gave");
							for (int i = 0; i < fields.length; i++ ) {
								System.out.println(fields[i] +": "+ info[i]);
							}
							System.out.println("Phone and Mobile numbers must be valid");
						}
					}
					else if (f1 != -1 && f2 != -1) {
						if(info[2].equals(String.valueOf(f1)) && info[3].equals(String.valueOf(f2))) {
							System.out.println("There is a contact for the information you gave");
							for (int i = 0; i < fields.length; i++ ) {
								System.out.println(fields[i] +": "+ info[i]);
							}
							contact_delete(currentLine);
						}
						else if(info[2].equals(String.valueOf(f1)) && !info[3].equals(String.valueOf(f2))) {
							System.out.println("There is a contact for the Phone number you gave");
							for (int i = 0; i < fields.length; i++ ) {
								System.out.println(fields[i] +": "+ info[i]);
							}
							System.out.println("Phone and Mobile numbers must be valid");
						}
						else if(!info[2].equals(String.valueOf(f1)) && info[3].equals(String.valueOf(f2))) {
							System.out.println("There is a contact for the Mobile number you gave");
							for (int i = 0; i < fields.length; i++ ) {
								System.out.println(fields[i] +": "+ info[i]);
							}
							System.out.println("Phone and Mobile numbers must be valid");
						}
					}
				}
			}
		}


		reader.close();
		choose_field();
	}

	public static void contact_delete(String line)  throws IOException, FileNotFoundException{
		File file1 = new File(System.getProperty("user.dir"));
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));
		String currentLine1;
		boolean first = false;
		String[] fields = new String[0];
		File file2 = new File(System.getProperty("user.dir")+"/src/contactstemp.txt");//i create a temporary file to save the changes
		BufferedWriter writer = new BufferedWriter(new FileWriter(file2));
		while((currentLine1 = reader1.readLine()) !=null) {
			if(!first) {
				fields = currentLine1.split(",");
				writer.write(currentLine1 + "\n");
				first = true;
			}
			else if(!currentLine1.equals(line)){
				writer.write(currentLine1 + "\n");
			}
		}
		reader1.close();
		writer.close();
		file1.delete();
		file2.renameTo(file1);
		System.out.println("Information was valid, deletion completed successfully");
	}


}
