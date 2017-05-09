/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.physics;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import yoshimaker.global.Entity;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.time.Instant;
/**
 *
 * @author punpun
 */
public class Timer implements Runnable {

    /**
     *
     */
    public static final Map<Long, Entity> TEMPORARY = new LinkedHashMap<>();
    
    public static void add(Entity e, int dt) {
        //Timestamp
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            long time = timestamp.getTime();
            
            TEMPORARY.put(time + (long) dt, e);
    }
    
    @Override
    public void run() {
        try {
            //Timestamp
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            long time = timestamp.getTime();
            
            while (true) {

                Set<Entry<Long, Entity>> data = TEMPORARY.entrySet();
                Iterator<Entry<Long, Entity>> it2 = data.iterator();
                while(it2.hasNext()){
                   Entry<Long, Entity> e = it2.next();
                   if (e.getKey() > time) { it2.remove(); e.getValue().destroy(); }
                }
                
                
                Thread.sleep(1000);
            }
            
        } catch (Exception e) {
            
        }
    }
    
}