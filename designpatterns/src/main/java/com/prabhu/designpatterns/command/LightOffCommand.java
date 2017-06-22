package com.prabhu.designpatterns.command;

import com.prabhu.designpatterns.command.receiver.Light;

public class LightOffCommand implements Command {

	private Light light;

	public LightOffCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.off();

	}

}
