package com.prabhu.designpatterns.command.invoker;

import com.prabhu.designpatterns.command.Command;

public class CommandInvoker {

	Command[] command;
	public void setCommand(Command[] command){
		this.command = command;
	}
	
	public void invoke(){
		for (int i = 0; i < command.length; i++) {
			command[i].execute();
		}
		
	}
}
