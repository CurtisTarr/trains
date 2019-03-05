import java.awt.*;
import java.util.concurrent.TimeUnit;

public class RunTrains {

    private final static Color[] COLORS = new Color[]{Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.RED};

    public static void main(String[] args) {
        // Make GUI
        TrainTrack trainTrack = new TrainTrack();

        // Create track using array of semaphores
        QuietSemaphore[] track = new QuietSemaphore[21];
        for (int i = 0; i < 21; i++) {
            track[i] = new QuietSemaphore(1);
        }

        // Create activity
        Activity activity = new Activity(trainTrack.getTrackSlots());

        // Create trains
        Train[] trains = new Train[15];
        int i = 0;
        for (int j = 0; j < 5; j++) {
            trains[i] = new ATrain(activity, track, "A" + (j + 1), COLORS[j]);
            i++;
            trains[i] = new BTrain(activity, track, "B" + (j + 1), COLORS[j]);
            i++;
            trains[i] = new CTrain(activity, track, "C" + (j + 1), COLORS[j]);
            i++;
        }

        // Start trains
        for (Train train : trains) {
            train.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }

        // Wait for trains to finish
        try {
            for (Train train : trains) {
                train.join();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        activity.printActivities();
    }
}
