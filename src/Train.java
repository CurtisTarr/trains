public class Train extends Thread {

    private int[] track;
    private Activity activity;
    private int loops;
    private MageeSemaphore[] trackSemaphore;
    private String name;

    public Train(int[] track, Activity activity , int loops, MageeSemaphore[] trackSemaphore, String name) {
        this.track = track;
        this.activity = activity;
        this.loops = loops;
        this.trackSemaphore = trackSemaphore;
        this.name = name;
    }

    @Override
    public void run() {
        int currentSection = -1;
        for(int i = 0; i < this.loops; i++) {
            for (int nextSection: track) {
                this.trackSemaphore[nextSection].P();
                if (currentSection != -1) {
                    this.trackSemaphore[currentSection].V();
                }
                currentSection = nextSection;
                this.activity.addMovedTo(nextSection, name);
            }
            this.activity.addMessage("Train " + this.name + " finished loop" + i);
        }
        this.trackSemaphore[currentSection].V();
        this.activity.addMessage("Train " + this.name + " finished " + this.loops + " loops and has left the track");
        this.activity.printActivities();
    }
}
