package bridge;

import observer.ElectronicKey;
import observer.FingerprintReader;
import observer.KeyDetector;

public interface ICar {
    void startMotor();
    void driverAndKeyEntered(ElectronicKey electronicKey);
    FingerprintReader getFingerprintReader();
    KeyDetector getKeyDetector();
}
