package org.bham.aucom.main;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class BusyWorker {
    /**
     * @param args
     */
    public static void intensiveCalc(int numThreads) {
        ArrayList<Thread> threadPool = new ArrayList<Thread>();
        for (int i = 0; i < numThreads; i++) {
            threadPool.add(new Thread(new Runnable() {
                @SuppressWarnings({"InfiniteLoopStatement"}) // Loop forever to destroy system
                @Override
                public void run() {
                    Long fibNumer = 30l;
                    while (true) {
                        System.out.print(fibNumer + " ");
                        System.out.println(BusyWorker.fib(fibNumer));
                        fibNumer++;
                    }

                }
            }));
            threadPool.get(threadPool.size() - 1).start();
        }
    }

    @SuppressWarnings("boxing")
    public static Long fib(Long fib) {
        if (fib == 0)
            return 0L;
        if (fib == 1)
            return 1L;
        return fib(fib - 1) + fib(fib - 2);
    }

    public static void main(String[] args) {
        BusyWorker.intensiveCalc(Integer.valueOf(args[0]));
        long cpuLoadStart = System.currentTimeMillis();
        File f = new File("ResourceStarvation_" + cpuLoadStart + ".txt");
        System.out.println(f.getAbsolutePath());
        try {
            FileWriter fw = new FileWriter(f);
            fw.write("threads\ttimestamp\n");
            fw.write(args[0] + "\t" + String.valueOf(cpuLoadStart));
            fw.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
