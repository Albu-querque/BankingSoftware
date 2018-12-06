package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.class.getPackage().getName() + ".resources.withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currency = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currency);
        ConsoleHelper.writeMessage(res.getString("specify.amount"));
        String sum;
        do {
            ConsoleHelper.writeMessage("Введите необходимую сумму: ");
            sum = ConsoleHelper.readString();
            if(!sum.matches("\\d++")) {
                ConsoleHelper.writeMessage("Ошибка! Повторите ввод: ");
                sum = null;
            } else if(!cm.isAmountAvailable(Integer.parseInt(sum))) {
                sum = null;
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            }
        }while(sum == null);

        boolean available = false;
        do {
            try {
                if (Integer.parseInt(sum) < 0)
                    throw new NumberFormatException();
                    ConsoleHelper.writeMessage("Сумма изымается...");
                    Map<Integer, Integer> map = cm.withdrawAmount(Integer.parseInt(sum));
                    ConsoleHelper.writeMessage("Вывод:");
                    map.entrySet().forEach(i -> System.out.println("\t" + i.getKey() + " - " + i.getValue()));
                    available = true;
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, cm.getCurrencyCode()));
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            } catch (NotEnoughMoneyException ex) {
                available = true;
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        } while(!available);


    }
}
