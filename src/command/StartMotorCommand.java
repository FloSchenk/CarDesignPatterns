package command;

import bridge.ICar;

public class StartMotorCommand implements ICommand {

    private ICar car;

    public StartMotorCommand(ICar car){
        this.car = car;
    }

    @Override
    public void execute() {
        System.out.println("StartMotorCommand has been send!");
        car.startMotor();
    }

    public ICar getCar() {
        return car;
    }
}
