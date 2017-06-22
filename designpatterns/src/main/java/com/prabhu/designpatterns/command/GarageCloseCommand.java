package com.prabhu.designpatterns.command;

import com.prabhu.designpatterns.command.receiver.Garage;

public class GarageCloseCommand implements Command {

	private Garage garage;

	public GarageCloseCommand(Garage garage) {
		this.garage = garage;
	}
	
	@Override
	public void execute() {
		garage.close();

	}

}
