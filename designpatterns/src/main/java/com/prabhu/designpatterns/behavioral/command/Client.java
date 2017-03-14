package com.prabhu.designpatterns.behavioral.command;

public class Client {
public static void main(String[] args) {
	Invoker invoker = new Invoker();
	Command DHCommand = new DrugHistoryCommand(new DrugHistoryProcessor());
	Command EligCommand = new EligibilityCommand(new EligibilityProcessor());
	
	invoker.acceptCommand(DHCommand);
	invoker.acceptCommand(EligCommand);
	
	invoker.invoke();
	
}
}
