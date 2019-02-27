// Train class for controlling movement of train throughout a route
public class Train extends Thread {

    int[] route;
    MageeSemaphore[] track;
    String name;
    Activity activity;

    private int loops;
    private int currentSection;

    /**
     * Constructor for Train
     *
     * @param route the sections of track that the train moves through
     * @param activity the activity class for logging train position
     * @param loops number of times the train loops its route
     * @param track a reference to the track the train uses
     * @param name the name of the train
     */
    Train(int[] route, Activity activity, int loops, MageeSemaphore[] track, String name) {
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
        track[currentSection].V();
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
        track[section].P();
        activity.addMovedTo(section, name);
        postMoveSection(section);
    }

    void postMoveSection(int section) {
        // Release the previous section if there was on (-1 means it wasn't in a section)
        if (currentSection != -1) {
            track[currentSection].V();
        }
        // Set the currentSection to be the new section
        currentSection = section;
        sleepTrain();
    }

    private void sleepTrain() {
        try {
            sleep(50);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
