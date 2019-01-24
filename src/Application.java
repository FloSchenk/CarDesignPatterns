import bridge.CarM1;
import bridge.CarM2;
import bridge.ICar;
import observer.ElectronicKey;
import observer.Fingerprint;
import observer.FingerprintReader;
import observer.KeyDetector;
import tools.PrimDecider;

import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {

        Fingerprint fingerprint = new Fingerprint();

        FingerprintReader fingerprintReader = new FingerprintReader(fingerprint);
        KeyDetector keyDetector = new KeyDetector("XZU7KKN6ZHGP");
        ICar car = new CarM1(fingerprintReader, keyDetector);
        ElectronicKey electronicKey = new ElectronicKey(car,"XZU7KKN6ZHGP");

        electronicKey.addListener(keyDetector);
        fingerprintReader.addListener(electronicKey);
        keyDetector.addListener(fingerprintReader);

        //Guy enters the car!
        car.driverAndKeyEntered(electronicKey);
        //Press the Finger on the Button!
        System.out.println("Person pressed Finger on Reader!");
        fingerprintReader.checkFingerprint(fingerprint);

    }
}
