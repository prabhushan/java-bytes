package com.prabhu.designpatterns.command.invoker;

import com.prabhu.designpatterns.command.Command;
import com.prabhu.designpatterns.command.GarageCloseCommand;
import com.prabhu.designpatterns.command.GarageOpenCommand;
import com.prabhu.designpatterns.command.LightOffCommand;
import com.prabhu.designpatterns.command.LightOnCommand;
import com.prabhu.designpatterns.command.receiver.Garage;
import com.prabhu.designpatterns.command.receiver.Light;

public class TestClient {

	public static void main(String[] args) {

		// set the receiver
		Light lightKitchen = new Light("Kitchen");
		Light lightHall = new Light("Hall");
		Garage garage = new Garage();

		// Construct the Command
		LightOnCommand lightOnCommand1 = new LightOnCommand(lightKitchen);
		LightOnCommand lightOnCommand2 = new LightOnCommand(lightHall);

		LightOffCommand lightOffCommand1 = new LightOffCommand(lightKitchen);
		LightOffCommand lightOffCommand2 = new LightOffCommand(lightHall);

		GarageOpenCommand openGarageCommand = new GarageOpenCommand(garage);
		GarageCloseCommand closeGarageCommand = new GarageCloseCommand(garage);

		// setting up the invoker

		CommandInvoker invoker = new CommandInvoker();
		Command[] command = new Command[6];
		command[0] = lightOnCommand1;
		command[1] = lightOnCommand2;
		command[2] = lightOffCommand1;
		command[3] = lightOffCommand2;
		command[4] = openGarageCommand;
		command[5] = closeGarageCommand;

		invoker.setCommand(command);
		invoker.invoke();
	}

}
