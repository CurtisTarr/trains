public class Train extends Thread {

    int currentSection;
    int[] track;

    MageeSemaphore[] trackSemaphore;
    String name;
    Activity activity;

    private int loops;

    Train(int[] track, Activity activity, int loops, MageeSemaphore[] trackSemaphore, String name) {
        this.track = track;
        this.activity = activity;
        this.loops = loops;
        this.trackSemaphore = trackSemaphore;
        this.name = name;
        this.currentSection = -1;
    }

    @Override
    public void run() {
        for (int i = 0; i < loops; i++) {
            moveThroughTrack(i);
        }
        moveSection(track[0]);
        trackSemaphore[currentSection].V();
        activity.addMessage("Train " + name + " finished and has left the track");
    }

    void moveThroughTrack(int loop) {
        for (int nextSection : track) {
            moveSection(nextSection);
        }
        activity.addMessage("Train " + name + " finished loop" + (loop + 1));
    }

    void moveSection(int section) {
        trackSemaphore[section].P();
        activity.addMovedTo(section, name);
        if (currentSection != -1) {
            trackSemaphore[currentSection].V();
        }
        currentSection = section;
        sleepTrain();
    }

    void sleepTrain() {
        try {
            sleep(50);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
