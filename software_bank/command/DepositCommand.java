package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Enumeration;
import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.class.getPackage().getName() + ".resources.deposit_en");


    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());
        String[] s = ConsoleHelper.getValidTwoDigits(cm.getCurrencyCode());
        try {
            int amount = Integer.parseInt(s[0]) * Integer.parseInt(s[1]);
            CurrencyManipulatorFactory.getManipulatorByCurrencyCode(cm.getCurrencyCode()).addAmount(Integer.parseInt(s[0]), Integer.parseInt(s[0]));
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, cm.getCurrencyCode()));
        } catch(NumberFormatException e) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
        cm.addAmount(Integer.parseInt(s[0]),Integer.parseInt(s[1]));
    }
}
