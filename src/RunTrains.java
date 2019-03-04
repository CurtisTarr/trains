import javax.swing.*;

public class RunTrains {

    public static void main(String[] args) {
        // Make GUI
        JFrame frame = new JFrame("Trains");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setText("");

        frame.getContentPane().add(textArea);
        frame.setVisible(true);

        // Create track using array of semaphores
        QuietSemaphore[] track = new QuietSemaphore[21];
        for (int i = 0; i < 21; i++) {
            track[i] = new QuietSemaphore(1);
        }

        // Create activity
        Activity activity = new Activity(
                new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}, frame, textArea
        );

        // Create trains
        Train A1 = new ATrain(activity, track, "A1");
        Train A2 = new ATrain(activity, track, "A2");
        Train A3 = new ATrain(activity, track, "A3");
        Train B1 = new BTrain(activity, track, "B1");
        Train B2 = new BTrain(activity, track, "B2");
        Train B3 = new BTrain(activity, track, "B3");
        Train C1 = new CTrain(activity, track, "C1");
        Train C2 = new CTrain(activity, track, "C2");
        Train C3 = new CTrain(activity, track, "C3");

        // Start trains
        A1.start();
        A2.start();
        A3.start();
        B1.start();
        B2.start();
        B3.start();
        C1.start();
        C2.start();
        C3.start();

        // Wait for trains to finish
        try {
            A1.join();
            A2.join();
            A3.join();
            B1.join();
            B2.join();
            B3.join();
            C1.join();
            C2.join();
            C3.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        activity.printActivities();
    }
}
