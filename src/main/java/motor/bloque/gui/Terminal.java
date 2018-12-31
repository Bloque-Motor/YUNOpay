package motor.bloque.gui;

import java.util.Scanner;

//For testing purposes only. TO be replaced by GUI
public class Terminal {

    public static void mainMenu(){
        boolean exit = false;
        while(!exit) {
            System.out.println("YUNO menu");
            System.out.println("---------------");
            System.out.println("Select an operation:");
            System.out.println("1. Buy a card");
            System.out.println("2. Pay");
            System.out.println("3. Recharge money");
            System.out.println("4. Change PIN");
            System.out.println("5. Consult balance");
            System.out.println("6. Consult movements");
            System.out.println("7. Exit");

            Scanner sc = new Scanner(System.in);
            String option = sc.nextLine();

            switch (option) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    exit = true;
                    break;
                default:
                    break;

            }
        }

    }

}
