package bridge;

import observer.ElectronicKey;
import observer.FingerprintReader;
import observer.KeyDetector;

public class CarM2 implements ICar{

    private FingerprintReader fingerprintReader;
    private KeyDetector keyDetector;

    public CarM2(FingerprintReader fingerprintReader, KeyDetector keyDetector){
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
        System.out.println("Car with motor variant M2 has been started!");
    }

    @Override
    public void driverAndKeyEntered(ElectronicKey electronicKey) {
        electronicKey.enteredCar();
    }
}
