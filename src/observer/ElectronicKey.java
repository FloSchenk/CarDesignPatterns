package observer;

import bridge.ICar;
import command.ICommand;
import command.StartMotorCommand;

import java.util.ArrayList;

public class ElectronicKey implements IFingerprintReaderListener{

    private ICar car;
    private ICommand command;
    private boolean isStartAllowed = false;
    private String keySerial;
    private ArrayList<IElectronicKeyListener> listeners;

    public ElectronicKey(ICar car, String keySerial){
        this.car = car;
        this.command = new StartMotorCommand(car);
        this.keySerial = keySerial;
        listeners = new ArrayList<>();
    }

    public void enteredCar(){
        for (IElectronicKeyListener l: listeners) {
            l.keyDetected(this.keySerial);
        }
    }

    @Override
    public void fingerprintAccepted() {
        isStartAllowed = true;
        sendStartCommand();
    }

    private void sendStartCommand(){
        if (isStartAllowed) {
            command.execute();
            isStartAllowed = false;
        } else {
            System.out.println("Motor Start not allowed!");
        }
    }

    public void addListener(IElectronicKeyListener listener){
        listeners.add(listener);
    }

    public void removeListener(IElectronicKeyListener listener){
        listeners.remove(listener);
    }

    public ICar getCar() {
        return car;
    }

    public ICommand getCommand() {
        return command;
    }

    public boolean isStartAllowed() {
        return isStartAllowed;
    }

    public String getKeySerial() {
        return keySerial;
    }

    public ArrayList<IElectronicKeyListener> getListeners() {
        return listeners;
    }
}
