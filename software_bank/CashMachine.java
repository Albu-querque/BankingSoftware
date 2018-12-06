package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        try {
            System.out.println(CashMachine.class.getPackage().getName());
            Locale.setDefault(Locale.ENGLISH);
            Operation operation;
            CommandExecutor.execute(Operation.LOGIN);
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (Operation.EXIT.compareTo(operation) != 0);
        }catch(InterruptOperationException e) {
            ConsoleHelper.writeMessage("Goodbye!");
        }
    }


}
