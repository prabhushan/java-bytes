package com.prabhu.designpatterns.command;

import com.prabhu.designpatterns.command.receiver.Garage;

public class GarageOpenCommand implements Command {

	private Garage garage;

	public GarageOpenCommand(Garage garage) {
		this.garage = garage;
	}
	
	@Override
	public void execute() {
		garage.open();

	}


}
