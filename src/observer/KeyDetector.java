package observer;

import java.util.ArrayList;

public class KeyDetector implements IElectronicKeyListener {

    private String keySerial;
    private ArrayList<IKeyDetectorListener> listeners;

    public KeyDetector(String keySerial){
        this.keySerial = keySerial;
        listeners = new ArrayList<>();
    }

    @Override
    public void keyDetected(String keySerial) {
        if (this.keySerial.equals(keySerial)){
            System.out.println("Key has been accepted!");
            for (IKeyDetectorListener l: listeners) {
                l.activateFingerprintReader();
            }
        } else {
            System.out.println("Wrong Key!");
        }
    }

    public void addListener(IKeyDetectorListener listener){
                listeners.add(listener);
    }

    public void removeListener(IKeyDetectorListener listener){
        listeners.remove(listener);
    }

    public String getKeySerial() {
        return keySerial;
    }

    public ArrayList<IKeyDetectorListener> getListeners() {
        return listeners;
    }
}
