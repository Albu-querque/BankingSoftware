package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.Operation;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandExecutor {
    private final static Map<Operation, Command> allKnownCommandsMap;

    private CommandExecutor() {}

    static {
        allKnownCommandsMap = new HashMap<>();
        allKnownCommandsMap.put(Operation.LOGIN, new LoginCommand());
        allKnownCommandsMap.put(Operation.DEPOSIT, new DepositCommand());
        allKnownCommandsMap.put(Operation.INFO, new InfoCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
        allKnownCommandsMap.put(Operation.WITHDRAW, new WithdrawCommand());
    }

    public static final void execute(Operation operation) throws InterruptOperationException {
        if(operation == null)
            return;

        if(allKnownCommandsMap.containsKey(operation))
            allKnownCommandsMap.get(operation).execute();
    }

}
