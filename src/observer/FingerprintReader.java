package observer;

import java.util.ArrayList;

public class FingerprintReader implements IKeyDetectorListener   {

    private Fingerprint fingerprint;
    private boolean isReaderActivated = false;
    private ArrayList<IFingerprintReaderListener> listeners;

    public FingerprintReader(Fingerprint fingerprint){
        this.fingerprint = fingerprint;
        listeners = new ArrayList<>();
    }

    @Override
    public void activateFingerprintReader() {
        isReaderActivated = true;
        System.out.println("Fingerprint Reader activated!");
    }

    public void addListener(IFingerprintReaderListener listener){
        listeners.add(listener);
    }

    public void removeListener(IFingerprintReaderListener listener){
        listeners.remove(listener);
    }

    public void checkFingerprint(Fingerprint fingerprintToCheck){
        if (isReaderActivated){
            if (isFingerprintEqual(fingerprintToCheck)){
                System.out.println("Fingerprint accepted!");
                for (IFingerprintReaderListener l:listeners) {
                    l.fingerprintAccepted();
                }
                isReaderActivated = false;
            } else{
                System.out.println("Fingerprint is not accepted");
            }
        } else {
            System.out.println("Fingerprint Reader is not activated!");
        }
    }

    private boolean isFingerprintEqual(Fingerprint fingerprintToCheck){
        for (int i = 0; i < 100; i++){
            for (int j = 0; j < 100; j++){
                if (fingerprint.getFrictionRidge()[i][j] != fingerprintToCheck.getFrictionRidge()[i][j])
                    return false;
            }
        }
        return true;
    }

    public Fingerprint getFingerprint() {
        return fingerprint;
    }

    public boolean isReaderActivated() {
        return isReaderActivated;
    }

    public ArrayList<IFingerprintReaderListener> getListeners() {
        return listeners;
    }
}
