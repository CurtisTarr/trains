// Train class for controlling movement of train throughout a route
public class Train extends Thread {

    private int[] route;

    int loops;
    int currentSection;
    QuietSemaphore[] track;
    String name;
    Activity activity;

    /**
     * Constructor for Train
     *
     * @param route    the sections of track that the train moves through
     * @param activity the activity class for logging train position
     * @param loops    number of times the train loops its route
     * @param track    a reference to the track the train uses
     * @param name     the name of the train
     */
    Train(int[] route, Activity activity, int loops, QuietSemaphore[] track, String name) {
        this.route = route;
        this.activity = activity;
        this.loops = loops;
        this.track = track;
        this.name = name;
        this.currentSection = -1;
    }

    @Override
    public void run() {
        // Loop through the route for the specified number of times
        for (int i = 0; i < loops; i++) {
            moveThroughRoute(i);
        }
        // Return back to the starting section to exit the route
        moveSection(route[0]);
        track[currentSection].release();
        activity.addMovedTo(-1, currentSection, name);
        activity.addMessage("Train " + name + " finished and has left the route");
    }

    void moveThroughRoute(int loop) {
        // For each section in the route move to it
        for (int nextSection : route) {
            moveSection(nextSection);
        }
        activity.addMessage("Train " + name + " finished loop " + (loop + 1));
    }

    void moveSection(int section) {
        // Try to acquire the next section of track
        track[section].acquire();
        activity.addMovedTo(section, currentSection, name);
        postMoveSection(section);
    }

    void postMoveSection(int section) {
        // Release the previous section if there was on (-1 means it wasn't in a section)
        if (currentSection != -1) {
            track[currentSection].release();
        }
        // Set the currentSection to be the new section
        currentSection = section;
        sleepTrain();
    }

    private void sleepTrain() {
        try {
            sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
