package banking;

public class Account {
    String accountNumber, accountPin;
    int balance;

    Account(String accN, String accP){
        accountNumber=accN;
        accountPin=accP;
        balance=0;
    }
}