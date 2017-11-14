package lk.sliit.transport.publicTransportService.services;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by dinukshakandasamanage on 11/14/17.
 */

public class DistanceCalculationService {

    /**
     * Returns a random integer as the distance
     * Used to mock the behaviour of the GPS
     *
     * @return
     */
    public static int getDistance(){
        return ThreadLocalRandom.current().nextInt(1, 20 + 1);
    }
}
