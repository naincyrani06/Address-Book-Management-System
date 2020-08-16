package mainpackage;

import java.util.Arrays;
import java.util.Scanner;

import mainpackage.Print;
import mainpackage.Add;
import mainpackage.Search;
import mainpackage.Change;
import mainpackage.Delete;

import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.charset.Charset;

public class Main {
	public static void main(String[] args) throws IOException {

		Scanner input = new Scanner(System.in);
		int exit = 0;
		int answer;

		do {
			System.out.println("Do you want to print contacts, add contact, search for contact, edit contact or delete contact?");
			System.out.println("Answer with '1', '2', '3', '4', '5' or '0' to exit application.");
			try {
				answer = input.nextInt();
			} catch (NumberFormatException e)
				answer = -1;
			}
			if(answer == 1)
				Print.show_contacts();
			else if(answer == 2)
				Add.add_contact();
			else if(answer == 3)
				Search.choose_field();
			else if(answer == 4)
				Change.choose_field();
			else if(answer == 5)
				Delete.choose_field();


		}while(answer != exit);
		System.out.println("Application terminating...");
	}
}
