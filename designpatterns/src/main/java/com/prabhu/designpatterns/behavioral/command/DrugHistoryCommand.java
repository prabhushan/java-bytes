package com.prabhu.designpatterns.behavioral.command;

public class DrugHistoryCommand implements Command {

	private DrugHistoryProcessor processor;
	
	public DrugHistoryCommand(DrugHistoryProcessor processor) {
		this.processor = processor;
	}
	@Override
	public void execute() {
		System.out.println("Calling Receiver - Drug History Command");
		System.out.println(processor.drugHxProcessor());
	}

}
