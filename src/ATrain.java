class ATrain extends Train {

    private final static int[] TRACK = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private final static int LOOPS = 5;

    ATrain(Activity activity, MageeSemaphore[] trackSemaphore, String name) {
        super(TRACK, activity, LOOPS, trackSemaphore, name);
    }

    @Override
    void moveSection(int section) {
        if (section == 7) {
            trackSemaphore[9].P();
            trackSemaphore[8].P();
            trackSemaphore[7].P();
            activity.addMovedTo(section, name);
        } else if (section == 8 || section == 9) {
            // All tracks controlled
        } else {
            trackSemaphore[section].P();
        }
        activity.addMovedTo(section, name);
        if (currentSection != -1) {
            trackSemaphore[currentSection].V();
        }
        currentSection = section;
        sleepTrain();
    }
}
