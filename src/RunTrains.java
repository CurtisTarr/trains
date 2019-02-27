public class RunTrains {

    public static void main(String[] args) {
        String[] track = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
        MageeSemaphore[] trackSemaphore = new MageeSemaphore[21];

        for (int i = 0; i < 21; i++) {
            trackSemaphore[i] = new MageeSemaphore(1);
        }

        Activity activity = new Activity(track);

        Train A1 = new ATrain(activity, trackSemaphore, "A1");
        Train A2 = new ATrain(activity, trackSemaphore, "A2");
        Train A3 = new ATrain(activity, trackSemaphore, "A3");
        Train B1 = new BTrain(activity, trackSemaphore, "B1");
        Train B2 = new BTrain(activity, trackSemaphore, "B2");
        Train B3 = new BTrain(activity, trackSemaphore, "B3");
        Train C1 = new CTrain(activity, trackSemaphore, "C1");
        Train C2 = new CTrain(activity, trackSemaphore, "C2");
        Train C3 = new CTrain(activity, trackSemaphore, "C3");
        A1.start();
        A2.start();
        A3.start();
        B1.start();
        B2.start();
        B3.start();
        C1.start();
        C2.start();
        C3.start();
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
