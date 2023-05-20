package com.github;

import com.github.services.Delete;
import com.github.services.Insert;
import com.github.services.Populate;
import com.github.services.Select;
import com.github.services.Update;
import java.util.Scanner;

public class DemoApplication {

    public static void main(final String[] args) {
        Populate.populate();
        final Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Please select an operation:");
            System.out.println("1. INSERT");
            System.out.println("2. SELECT");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    System.out.println("INSERT operation selected");
                    Insert.insert();
                }
                case 2 -> {
                    System.out.println("SELECT operation selected");
                    Select.select();
                }
                case 3 -> {
                    System.out.println("UPDATE operation selected");
                    Update.update();
                }
                case 4 -> {
                    System.out.println("DELETE operation selected");
                    Delete.delete();
                }
                case 0 -> System.out.println("Exiting program...");
                default -> System.out.println("Invalid option! Please try again.");
            }

            System.out.println();
        } while (option != 0);

        scanner.close();
    }

}



