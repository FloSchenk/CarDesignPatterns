package tests;

import bridge.CarM1;
import bridge.CarM2;
import bridge.ICar;
import command.StartMotorCommand;
import observer.ElectronicKey;
import observer.Fingerprint;
import observer.FingerprintReader;
import observer.KeyDetector;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import tools.PrimDecider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class testSlave {
    private static PrimDecider primDecider;
    private static Fingerprint fingerprint;
    private static FingerprintReader fingerprintReader;
    private static KeyDetector keyDetector;
    private static ICar carM1,carM2;
    private static StartMotorCommand startMotorCommand;
    private static ElectronicKey electronicKey;

    @BeforeClass
    public static void init(){
        primDecider = new PrimDecider();
        fingerprint = new Fingerprint();
        fingerprintReader = new FingerprintReader(fingerprint);
        keyDetector = new KeyDetector("42");
        carM1 = new CarM1(fingerprintReader, keyDetector);
        carM2 = new CarM2(fingerprintReader, keyDetector);
        startMotorCommand = new StartMotorCommand(carM1);
        electronicKey = new ElectronicKey(carM1, "33");
    }

    @Test
    public void testPrimDecider(){
        Assert.assertNotNull(primDecider);
        assertEquals(false, primDecider.isPrim(1));
        assertEquals(true, primDecider.isPrim(2));
        assertEquals(true, primDecider.isPrim(1129));
        assertEquals(false, primDecider.isPrim(4004));

    }

    @Test
    public void testFingerprint(){
        Assert.assertNotNull(fingerprint);
        assertEquals(0, fingerprint.getFrictionRidge()[0][0]);//1 is not a prime number
        assertEquals(1, fingerprint.getFrictionRidge()[0][2]);//3 is a prime number
        assertEquals(1, fingerprint.getFrictionRidge()[0][16]);//17 is a prime number
        assertEquals(1, fingerprint.getFrictionRidge()[11][28]);//1129 is a prime number
        assertEquals(0, fingerprint.getFrictionRidge()[99][99]);//10000 is not a prime number
    }

    @Test
    public void testCar(){
        Assert.assertNotNull(carM1);
        Assert.assertNotNull(carM1.getFingerprintReader());
        Assert.assertNotNull(carM1.getKeyDetector());
        Assert.assertNotNull(carM2);
        Assert.assertNotNull(carM2.getKeyDetector());
        Assert.assertNotNull(carM2.getFingerprintReader());
    }

    @Test
    public void testStartMotorCommand(){
        assertNotNull(startMotorCommand);
        assertNotNull(startMotorCommand.getCar());
    }

    @Test
    public void testElectronicKey(){
        assertNotNull(electronicKey);
        assertNotNull(electronicKey.getCar());
        assertNotNull(electronicKey.getCommand());
        assertNotNull(electronicKey.getListeners());

        assertEquals(false, electronicKey.isStartAllowed());
        electronicKey.fingerprintAccepted();
        assertEquals(false, electronicKey.isStartAllowed());

        assertEquals("33", electronicKey.getKeySerial());

        assertEquals(0, electronicKey.getListeners().size());
        electronicKey.addListener(keyDetector);
        assertEquals(1, electronicKey.getListeners().size());
        electronicKey.removeListener(keyDetector);
        assertEquals(0, electronicKey.getListeners().size());
    }

    @Test
    public void testFingerprintReader(){
        assertNotNull(fingerprintReader);
        assertNotNull(fingerprintReader.getListeners());

        assertEquals(false, fingerprintReader.isReaderActivated());
        fingerprintReader.activateFingerprintReader();
        assertEquals(true, fingerprintReader.isReaderActivated());
        fingerprintReader.checkFingerprint(fingerprint);
        assertEquals(false, fingerprintReader.isReaderActivated());

        assertEquals(0, fingerprintReader.getListeners().size());
        fingerprintReader.addListener(electronicKey);
        assertEquals(1, fingerprintReader.getListeners().size());
        fingerprintReader.removeListener(electronicKey);
        assertEquals(0, fingerprintReader.getListeners().size());
    }

    @Test
    public void testKeyDetector(){
        assertNotNull(keyDetector);
        assertNotNull(keyDetector.getListeners());

        assertEquals("42", keyDetector.getKeySerial());

        assertEquals(0, keyDetector.getListeners().size());
        keyDetector.addListener(fingerprintReader);
        assertEquals(1, keyDetector.getListeners().size());
        keyDetector.removeListener(fingerprintReader);
        assertEquals(0, keyDetector.getListeners().size());
    }
}
