package com.prabhu.designpatterns.behavioral.command;

import java.util.ArrayList;
import java.util.List;
/**
 * Invoker is aggregator of Command Objects.
 * Invoker has no idea of the Receiver / processor which is going to process the Command Objects
 * 
 * @author prabhu
 *
 */
public class Invoker {

	List<Command> listCommand = new ArrayList<>();

	public void acceptCommand(Command command) {
		listCommand.add(command);
	}

	public void invoke() {
		for (Command command : listCommand) {
			command.execute();

		}
	}
}
