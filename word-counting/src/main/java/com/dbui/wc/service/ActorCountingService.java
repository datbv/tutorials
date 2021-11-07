package com.dbui.wc.service;

import com.dbui.wc.util.CountUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ActorCountingService implements CharacterCountingService {

    private final ExecutorService readExecutor;
    private final CountingExecutor countingExecutor = new CountingExecutor();

    public ActorCountingService(final int worker) {
        readExecutor = Executors.newFixedThreadPool(worker);
    }

    @Override
    public Map<Character, Long> count(final String dir) {
        countingExecutor.start();
        final var filePaths = CountUtil.getFilePaths(dir);
        filePaths.forEach(path -> readExecutor.execute(() -> {
            try {
                final var lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                countingExecutor.push(lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        readExecutor.shutdown();
        try {
            readExecutor.awaitTermination(10, TimeUnit.MINUTES);
            countingExecutor.terminate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return countingExecutor.getCharToCount();
    }

    @Override
    public long getExecutedTimeInNanos() {
        return countingExecutor.getExecutedTimeInNanos();
    }

    private static final class CountingExecutor extends Thread {

        private final Map<Character, Long> charToCount = new HashMap<>();
        private final BlockingDeque<Collection<String>> tasks = new LinkedBlockingDeque<>();
        private long executedTimeInNanos;

        public Map<Character, Long> getCharToCount() {
            return charToCount;
        }

        private final AtomicBoolean terminate = new AtomicBoolean(false);
        private final CountDownLatch countDownLatch = new CountDownLatch(1);

        @Override
        public void run() {
            while (true) {
                final var lines = tasks.poll();
                if (lines == null) {
                    if (terminate.get()) {
                        break;
                    }
                    continue;
                }
                final var start = System.nanoTime();
                CountUtil.countCharacter(lines, charToCount);
                executedTimeInNanos += System.nanoTime() - start;
            }
            countDownLatch.countDown();
        }

        public void push(Collection<String> lines) {
            tasks.add(lines);
        }

        public long getExecutedTimeInNanos() {
            return executedTimeInNanos;
        }

        public void terminate() throws InterruptedException {
            terminate.set(true);
            countDownLatch.await();
        }


    }

}
