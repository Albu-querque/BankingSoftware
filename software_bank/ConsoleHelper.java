package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.*;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.class.getPackage().getName() + ".resources.common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String s = bis.readLine();
            if(s.equalsIgnoreCase("EXIT"))
                throw new InterruptOperationException();

            return s;
        }catch(IOException exception) { return null; }
    }

    public static String askCurrencyCode() throws InterruptOperationException{
        String currency;

            do {
                System.out.println("Введите нужную валюту: ");
                currency = readString();
            } while(currency.length() != 3);

            return currency.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String data = null;
        while (data == null){
            System.out.println("Введите номинал и количество банкнот");
            data = readString();
            if (data != null)
                if (!data.matches("\\d+ \\d+")){
                    data = null;
                    System.out.println("Некорректные данные!");
                }
        }
        return data.split(" ");
    }

    public static Operation askOperation() throws InterruptOperationException{
        int i;
        while(true) {
                System.out.println("Enter operation: ");
                i = Integer.parseInt(readString());
                if(i < 5 && i > 0)
                    break;
        }
        return Operation.getAllowableOperationByOrdinal(i);
    }
}
