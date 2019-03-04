import java.awt.*;

// BTrain class that extends Train, for controlling trains that follow the B route
class BTrain extends Train {

    private final static int[] ROUTE = new int[]{16, 15, 14, 13, 12, 11, 10, 9, 8, 7};
    private final static int LOOPS = 5;

    /**
     * Constructor for BTrain extends Train
     *
     * @param activity the activity class for logging train position
     * @param track    a reference to the track the train uses
     * @param name     the name of the train
     * @param color    the color of the train
     */
    BTrain(Activity activity, QuietSemaphore[] track, String name, Color color) {
        super(ROUTE, activity, LOOPS, track, name, color);
    }
}
