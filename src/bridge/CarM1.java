package bridge;

import observer.ElectronicKey;
import observer.FingerprintReader;
import observer.KeyDetector;

public class CarM1 implements ICar{

    private FingerprintReader fingerprintReader;
    private KeyDetector keyDetector;

    public CarM1(FingerprintReader fingerprintReader, KeyDetector keyDetector){
        this.fingerprintReader = fingerprintReader;
        this.keyDetector = keyDetector;
    }

    @Override
    public FingerprintReader getFingerprintReader() {
        return fingerprintReader;
    }

    @Override
    public KeyDetector getKeyDetector() {
        return keyDetector;
    }

    @Override
    public void startMotor() {
        System.out.println("Car with motor variant M1 has been started!");
    }

    @Override
    public void driverAndKeyEntered(ElectronicKey electronicKey) {
        electronicKey.enteredCar();
    }

}
