package com.prabhu.designpatterns.behavioral.command;

public class EligibilityCommand implements Command {

	private EligibilityProcessor processor;

	public EligibilityCommand(EligibilityProcessor processor) {
		this.processor = processor;
	}

	@Override
	public void execute() {
		System.out.println("Calling Receiver -Eligibility Command");
		System.out.println(processor.getEligilityProcessor());
	}

}
