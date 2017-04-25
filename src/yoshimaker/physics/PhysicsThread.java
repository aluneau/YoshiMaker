/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.physics;

import static java.lang.Thread.sleep;

/**
 *
 * @author punpun
 */
public class PhysicsThread extends Physics implements Runnable {
    public static int FPS;
    
    
    @Override
    public void run(){
        try {
            while(true) {
                
                if(FPS>0) {
                    double millis = (1.0 / (double)FPS) * 1000;
                    float m = (1.0f / FPS) * 1000;
                    update(m);
                    long sleepTime = Math.round(millis);
                    //sleep(sleepTime);
                } else {
                    update();
                }
            }
        }
        catch(Exception ignore){

        }
    }
}
