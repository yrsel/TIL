package ComputerScience.OperatingSystem;

public class SyncMonitor {
    static class Counter {
        //        private static final Object obj = new Object();
        public static int count = 0;

        //        synchronized public static void increase() {
//            count++;
//        }
//        public static void increase() {
//            synchronized (obj) {
//                count++;
//            }
//        }
        public void increase() {
            synchronized (this) {
                count++;
            }
        }
    }

    //    static class CustomRunnable implements Runnable {
//        @Override
//        public void run() {
//            for (int i = 0; i < 10_000; i++) {
//                Counter.increase();
//            }
//        }
//    }

    static class CustomRunnable implements Runnable {
        Counter counter;

        public CustomRunnable(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10_000; i++) {
                counter.increase();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        Counter counter = new Counter();
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new CustomRunnable(counter));
            threads[i].start();
        }

        for (int i = 0; i < 5; i++) {
            threads[i].join();
        }

        System.out.println("Counter.count = " + Counter.count);
    }
}
