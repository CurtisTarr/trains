// ATrain class that extends Train, for controlling trains that follow the A route
class ATrain extends Train {

    private final static int[] ROUTE = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private final static int LOOPS = 5;

    /**
     * Constructor for ATrain extends Train
     *
     * @param activity the activity class for logging train position
     * @param track    a reference to the track the train uses
     * @param name     the name of the train
     */
    ATrain(Activity activity, QuietSemaphore[] track, String name) {
        super(ROUTE, activity, LOOPS, track, name);
    }

    @Override
    void moveSection(int section) {
        if (section == 7) {
            // If section is 7 try to acquire sections 9, 8 and 7 in that order
            // to ensure that the train has complete control of the junction and to
            // ensure that it doesn't block another train that may be inside the junction
            track[9].acquire();
            track[8].acquire();
            track[7].acquire();
        } else if (section == 8 || section == 9) {
            // Means the train is inside junction and will have control of sections 8 and 9 already
        } else {
            // Not in junction just acquire the next section
            track[section].acquire();
        }
        activity.addMovedTo(section, currentSection, name);
        postMoveSection(section);
    }
}
