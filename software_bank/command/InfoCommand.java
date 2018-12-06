package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.class.getPackage().getName() + ".resources.info_en");

    @Override
    public void execute() throws InterruptOperationException {
        List<CurrencyManipulator> listManipulators = new ArrayList<>(CurrencyManipulatorFactory.getAllCurrencyManipulators());

        if(listManipulators.isEmpty())
            ConsoleHelper.writeMessage(res.getString("no.money"));


        ConsoleHelper.writeMessage(res.getString("before"));
        listManipulators.forEach(i -> {
            if(i.hasMoney())
                ConsoleHelper.writeMessage(i.getCurrencyCode() + " - " + i.getTotalAmount());
            else
                ConsoleHelper.writeMessage(res.getString("no.money"));
        });

    }
}
