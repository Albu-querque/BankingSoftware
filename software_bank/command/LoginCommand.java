package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(
            CashMachine.class.getPackage().getName() + ".resources.verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.class.getPackage().getName() + ".resources.login_en");

    @Override
    public void execute() throws InterruptOperationException {
        String stringNumber = null;
        String stringPin = null;
        while (stringNumber == null){
            ConsoleHelper.writeMessage("Введите номер карты");
            stringNumber = ConsoleHelper.readString();
            if (!stringNumber.matches("\\d{12}")){
                ConsoleHelper.writeMessage("Данные введены некорректно!");
                stringNumber = null;
            }
            else {
                if (!validCreditCards.containsKey(stringNumber)){
                    ConsoleHelper.writeMessage("Неправильный номер карты!");
                    stringNumber = null;
                }
            }
        }

        while (stringPin == null){
            ConsoleHelper.writeMessage("Введите пин");
            stringPin = ConsoleHelper.readString();
            if (!stringPin.matches("\\d{4}")){
                ConsoleHelper.writeMessage("Данные введены некорректно!");
                stringPin = null;
            }
            else {
                if (!validCreditCards.getString(stringNumber).equals(stringPin)){
                    ConsoleHelper.writeMessage("Неправильный пин!");
                    stringPin = null;
                }
            }
        }

        ConsoleHelper.writeMessage("Успешная идентификация!");
    }
}
