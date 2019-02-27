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
     */
    CTrain(Activity activity, MageeSemaphore[] track, String name) {
        super(ROUTE, activity, LOOPS, track, name);
    }

    @Override
    void moveThroughRoute(int loop) {
        // Overriding as a C train exits at the end of its route and doesn't
        // need to move back to its starting section like A and B trains
        for (int nextSection : route) {
            moveSection(nextSection);
        }
        activity.addMessage("Train " + name + " finished loop " + (loop + 1));
    }
}
