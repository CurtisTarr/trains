class CTrain extends Train {

    private final static int[] TRACK = new int[]{17, 18, 9, 8, 7, 19, 20};
    private final static int LOOPS = 1;

    CTrain(Activity activity, MageeSemaphore[] trackSemaphore, String name) {
        super(TRACK, activity, LOOPS, trackSemaphore, name);
    }

    @Override
    void moveThroughTrack(int loop) {
        for (int nextSection : track) {
            moveSection(nextSection);
        }
        activity.addMessage("Train " + name + " finished loop" + (loop + 1));
    }
}
