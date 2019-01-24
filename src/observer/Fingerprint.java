package observer;

import tools.PrimDecider;

public class Fingerprint {
    private int[][] frictionRidge = new int[100][100];
    private PrimDecider primDecider = new PrimDecider();

    public Fingerprint(){
        fillFrictionRidge();
    }

    private void fillFrictionRidge() {
        for (int i = 0; i < 100; i++){
            for (int j = 0; j < 100; j++){
                if (primDecider.isPrim(i * 100 + j + 1) )
                    frictionRidge[i][j] = 1;
                else
                    frictionRidge[i][j] = 0;
            }
        }
    }

    public int[][] getFrictionRidge() {
        return frictionRidge;
    }

}
