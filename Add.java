package mainpackage;

import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class Add extends Main{
	public static void add_contact() throws IOException, FileNotFoundException{
		File file1 = new File(System.getProperty("user.dir"));
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));
		OutputStreamWriter writer1 = new OutputStreamWriter(
                new FileOutputStream(System.getProperty("user.dir"), true), "UTF-8");
		BufferedWriter writer = new BufferedWriter(writer1);
		Scanner input= new Scanner(System.in);
		boolean duplicate, valid;
		String currentLine1;
		String f1 = "";
		String f2 = "";
		String f5 = "";
		String f6 = "";
		String f8 = "";
		int f3 = -1;
		int f4 = -1;
		int f7 = -1;
		int f9 = -1;
		String str;
		System.out.println("Give Name: ");
		f1 = input.nextLine();
		System.out.println("Give Surname: ");
		f2 = input.nextLine();
		do {
			duplicate = false;
			valid = true;
			System.out.println("Give Phone: ");

			try {
			    f3 = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
				valid = false;
				System.out.println("Phone must be number.");

			}
			while((currentLine1 = reader1.readLine()) != null) {
				String[] words1=currentLine1.split(",");
				if(words1[2].equals(String.valueOf(f3))) {
						duplicate=true;
						System.out.println("Phone must be unique among the contacts.");
				}
			}
			reader1 = new BufferedReader(new FileReader(file1));
		}while (duplicate == true || valid == false);
		do {
			duplicate = false;
			valid = true;
			System.out.println("Give Mobile phone: ");

			try {
			    f4 = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {

				System.out.println("Mobile phone must be number.");
				valid = false;
			}
			while((currentLine1 = reader1.readLine()) != null) {
				String[] words1=currentLine1.split(",");
				if(words1[3].equals(String.valueOf(f4))) {
						duplicate=true;
						System.out.println("Mobile Phone must be unique among the contacts.");
				}
			}
			reader1 = new BufferedReader(new FileReader(file1));
		}while (duplicate == true || valid == false);
		do {
			duplicate = false;
			System.out.println("Give E-mail: ");
			f5 = input.nextLine();
			while((currentLine1 = reader1.readLine()) != null) {//check for duplicate
				String[] words1=currentLine1.split(",");
				if(words1[4].equals(f5)) {
						duplicate=true;
						System.out.println("E-mail must be unique among the contacts.");
				}
			}
			reader1 = new BufferedReader(new FileReader(file1));
		}while (duplicate == true);
		System.out.println("Give Street: ");
		f6 = input.nextLine();
		System.out.println("Give street number: ");

		do {
			valid = true;
			try {
			    f7 = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {

				System.out.println("Street number must be a number.");
				valid = false;
			}
		}while(valid == false);
		System.out.println("Give town: ");
		f8 = input.nextLine();
		System.out.println("Give Zip code: ");

		do {
			valid = true;
			try {
			    f9 = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {

				System.out.println("Zip code must be a number.");
				valid = false;
			}
		}while(valid == false);
		if(f1 == "" || f2 == "" || f5 == "" || f6 == "" || f8 == "" || f3 == -1 || f4 == -1 || f7 == -1 || f9 == -1) {
			System.out.println("You gave false inputs, adding new contact wasn't successful: ");
		}
		else {
			str = f1 + "," + f2 + "," + String.valueOf(f3) + "," + String.valueOf(f4) + "," + f5 + "," + f6 + "," + String.valueOf(f7) + "," + f8 + "," + String.valueOf(f9);
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file1, true)));//with these code I add a line at the bottom of the file
			out.println(str);
			out.close();
		}


		writer.close();
		reader1.close();
