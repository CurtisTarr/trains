public class BTrain extends Train {

    private final static int[] TRACK = new int[]{16, 15, 14, 13, 12, 11, 10, 9, 8, 7};
    private final static int LOOPS = 5;

    BTrain(Activity activity, MageeSemaphore[] trackSemaphore, String name) {
        super(TRACK, activity, LOOPS, trackSemaphore, name);
    }
}
