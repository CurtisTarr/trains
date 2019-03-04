import java.awt.*;

// CTrain class that extends Train, for controlling trains that follow the C route
class CTrain extends Train {

    private final static int[] ROUTE = new int[]{17, 18, 9, 8, 7, 19, 20};
    private final static int LOOPS = 1;

    /**
     * Constructor for CTrain extends Train
     *
     * @param activity the activity class for logging train position
     * @param track    a reference to the track the train uses
     * @param name     the name of the train
     * @param color    the color of the train
     */
    CTrain(Activity activity, QuietSemaphore[] track, String name, Color color) {
        super(ROUTE, activity, LOOPS, track, name, color);
    }

    @Override
    public void run() {
        // Loop through the route for the specified number of times
        for (int i = 0; i < loops; i++) {
            moveThroughRoute(i);
        }
        // Return back to the starting section to exit the route
        track[currentSection].release();
        activity.addMovedTo(-1, currentSection, name, color);
        activity.addMessage("Train " + name + " finished and has left the route");
    }
}
