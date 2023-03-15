package banking;

//ctl alt L

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {

    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:/Users/shokhinabonutojieva/Desktop/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static int[] accNumber = new int[16];
    public static int[] accPin = new int[4];
    public static ArrayList<Account> accounts = new ArrayList<Account>();
    public static Scanner scn = new Scanner(System.in);
    public static Account currentAcc;

    private static void CreateAccount() {
        Random rand = new Random();
        String accN = "", accP = "";
        int chksm = 0;

        for (int i = 0; i < accNumber.length - 1; i++) {
            if (i == 0) {
                accNumber[i] = 4;
                accN = accN + 4;
            } else if (i > 0 && i < 6) {
                accNumber[i] = 0;
                accN = accN + 0;
            } else {
                accNumber[i] = rand.nextInt(10);
                accN = accN + accNumber[i];
            }

        }

        chksm = checksum(accNumber);
        accNumber[15] = chksm;
        accN = accN + String.valueOf(chksm);

        for (int b : accPin) {
            b = rand.nextInt(10);
            accP = accP + b;
        }

        accounts.add(new Account(accN, accP));

        System.out.println("Your card has been created\n" +
                "Your card number:\n" +
                accN + "\n" +
                "Your card PIN:\n" +
                accP);
    }

    private static int checksum(int[] list) {

        int sum = 0;
        int checksum = 0;
        int[] accNumber2 = new int[15];

        for (int i = 0; i < accNumber2.length; i++) {
            accNumber2[i] = list[i];
        }

        for (int i = 0; i < accNumber2.length; i++) {
            if (i == 0 || (i % 2) == 0) {
                accNumber2[i] = accNumber2[i] * 2;
            }

            if (accNumber2[i] > 9) {
                accNumber2[i] = accNumber2[i] - 9;
            }
      System.out.print(accNumber2[i]+" ");
            sum += accNumber2[i];
        }


        while (sum % 10 != 0) {
            checksum++;
            sum++;

        }

        return checksum;

    }

    private static boolean LogIn() {
        System.out.println("Enter your card number:");
        String accN = scn.nextLine();
        System.out.println("Enter your PIN::");
        String accP = scn.nextLine();

        for (Account acc : accounts) {
            if (acc.accountNumber.equals(accN) && acc.accountPin.equals(accP)) {
                System.out.println("You have successfully logged in!");
                currentAcc = acc;
                return true;
            }
        }
        System.out.println("Wrong card number or PIN!");
        return false;

    }

    private static boolean LogOut() {
        currentAcc = null;
        System.out.println("You have successfully Logged Out!");
        return true;

    }

    private static void showBalance() {
        System.out.println("Balance: " + currentAcc.balance);
    }


    public static void main(String[] args) {

        createNewDatabase("test.db");

//        boolean quit1 = false, quit2 = false, quit3 = false;
//
//        while (!quit3) {
//            while (!quit1) {
//                System.out.println(
//                        "\n1. Create an account\n" +
//                                "2. Log into account\n" +
//                                "0. Exit");
//
//                int chosenAction = Integer.parseInt(scn.nextLine());
//
//                switch (chosenAction) {
//                    case 1:
//                        CreateAccount();
//                        break;
//                    case 2:
//                        quit1 = LogIn();
//                        quit2 = !quit1;
//                        break;
//                    case 0:
//                        quit1 = true;
//                        quit2 = true;
//                        quit3 = true;
//                        System.out.println("Bye!");
//                        break;
//                    default:
//                        System.out.println("Wrong Command was Entered");
//                }
//            }
//
//            while (!quit2) {
//                System.out.println(
//                        "\n1. Balance\n" +
//                                "2. Log out\n" +
//                                "0. Exit");
//
//                int chosenAction = Integer.parseInt(scn.nextLine());
//
//                switch (chosenAction) {
//                    case 1:
//                        showBalance();
//                        break;
//                    case 2:
//                        quit2 = LogOut();
//                        quit1 = !quit2;
//                        break;
//                    case 0:
//                        quit1 = true;
//                        quit2 = true;
//                        quit3 = true;
//                        System.out.println("Bye!");
//                        break;
//                    default:
//                        System.out.println("Wrong Command was Entered");
//                }
//            }
//        }


    }
}
