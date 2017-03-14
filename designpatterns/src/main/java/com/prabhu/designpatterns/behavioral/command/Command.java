package com.prabhu.designpatterns.behavioral.command;

/**
 * Command Object that has a Receiver object or Method reference to call the appropriate Receiver Object.
 * Main Advantage is Receiver can be any method and Corresponding Command Implementation will know which receiver to call.
 * Receiver will be of any interface
 * @author prabhu
 *
 */
public interface Command {
	
	void execute();

}
