package ComputerScience.OperatingSystem;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosopherProblem {

    enum State {
        THINKING, HUNGRY, EATING
    }

    public static void main(String[] args) {
        int numOfPhils = 5;
        DiningPhilosopherMonitor monitor = new DiningPhilosopherMonitor(numOfPhils);
        for (int i = 0; i < numOfPhils; i++) {
            new Thread(new Philosopher(i, monitor)).start();
        }
    }

    static class Philosopher implements Runnable {
        private int id;
        private DiningPhilosopherMonitor monitor;

        public Philosopher(int id, DiningPhilosopherMonitor monitor) {
            this.id = id;
            this.monitor = monitor;
        }

        @Override
        public void run() {
            while (true) {
                think();
                monitor.pickup(id);
                eat();
                monitor.putDown(id);
            }
        }

        private void think() {
            try {
                System.out.println("id = " + id + " , 생각 중입니다. ");
                Thread.sleep((long) (Math.random() * (500)));
            } catch (InterruptedException ignored) {
            }
        }

        private void eat() {
            try {
                System.out.println("id = " + id + " , 식사 중입니다. ");
                Thread.sleep((long) (Math.random() * (50)));
            } catch (InterruptedException e) {
            }
        }

    }

    static class DiningPhilosopherMonitor {
        private int numOfPhils;
        private State[] state;
        private Condition[] self;
        private Lock lock;

        public DiningPhilosopherMonitor(int num) {
            numOfPhils = num;
            state = new State[num];
            self = new Condition[num];
            lock = new ReentrantLock();
            Arrays.fill(state, State.THINKING);
            Arrays.fill(self, lock.newCondition());
        }

        private int leftOf(int i) {
            return (i + numOfPhils - 1) % numOfPhils;
        }

        private int rightOf(int i) {
            return (i + 1) % numOfPhils;
        }

        private void test(int i) {
            if (state[i]==State.HUNGRY &&
                    state[leftOf(i)]!=State.EATING &&
                    state[rightOf(i)]!=State.EATING
            ) {
                state[i] = State.EATING;
                self[i].signal();
            }
        }

        public void pickup(int id) {
            lock.lock();
            try {
                state[id] = State.HUNGRY;
                test(id);
                if (state[id]!=State.EATING) self[id].await();
            } catch (InterruptedException ignored) {

            } finally {
                lock.unlock();
            }
        }

        public void putDown(int id) {
            lock.lock();
            try {
                state[id] = State.THINKING;
                test(leftOf(id));
                test(rightOf(id));
            } finally {
                lock.unlock();
            }
        }
    }

}
