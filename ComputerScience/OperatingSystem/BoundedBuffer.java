package ComputerScience.OperatingSystem;

public class BoundedBuffer {
    public static void main(String[] args) {
        CashBox cashBox = new CashBox(5);
        Thread[] producers = new Thread[5];
        Thread[] consumers = new Thread[5];

        for (int i = 0; i < producers.length; i++) {
            producers[i] = new Thread(new ProdRunner(cashBox));
            producers[i].start();
        }
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Thread(new ConsRunner(cashBox));
            consumers[i].start();
        }
    }

    static class ProdRunner implements Runnable {
        private CashBox cashBox;

        public ProdRunner(CashBox cashBox) {
            this.cashBox = cashBox;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep((long) (Math.random() * 500));
                    int money = ((int) (1 + Math.random() * 9)) * 10_000;
                    cashBox.give(money);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    static class ConsRunner implements Runnable {
        private CashBox cashBox;

        public ConsRunner(CashBox cashBox) {
            this.cashBox = cashBox;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep((long) (Math.random() * 500));
                    int money = cashBox.take();
                }
            } catch (InterruptedException e) {
            }
        }
    }

    static class CashBox {

        private int[] buffer;
        private int count, in, out;

        public CashBox(int bufferSize) {
            buffer = new int[bufferSize];
            count = in = out = 0;
        }

        // synchronized 키워드를 사용해서 모니터락 획득 상호배제를 보장
        synchronized public void give(int money) {
            while (count==buffer.length) {
                try {
                    wait();
                } catch (InterruptedException ignored) {
                }
            }

            //임계 영역
            buffer[in] = money;
            in = (in + 1) % buffer.length;
            count++;
            System.out.printf("용돈 준 금액 : %d원\n", money);

            notify();
        }

        synchronized public int take() {
            //임계 영역
            while (count==0) {
                try {
                    wait();
                } catch (InterruptedException ignored) {
                }
            }

            int money = buffer[out];
            out = (out + 1) % buffer.length;
            count--;
            System.out.printf("용돈 받은 금액 : %d원\n", money);

            notify();
            return money;
        }
    }
}
