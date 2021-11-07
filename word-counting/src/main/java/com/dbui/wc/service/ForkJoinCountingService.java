package com.dbui.wc.service;

import com.dbui.wc.util.CountUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ForkJoinCountingService implements CharacterCountingService {

    private final ExecutorService executor;
    private long executedTimeInNanos;

    public ForkJoinCountingService(final int worker) {
        executor = Executors.newFixedThreadPool(worker);
    }

    @Override
    public Map<Character, Long> count(final String dir) {
        final var start = System.nanoTime();
        final var filePaths = CountUtil.getFilePaths(dir);
        final var futureResults = filePaths.stream()
                .map(path -> CompletableFuture.supplyAsync(() -> {
                    final var charToCount = new HashMap<Character, Long>();
                    CountUtil.countCharacter(path, charToCount);
                    return charToCount;
                }, executor)).toList();

        var finalCharToCount = new HashMap<Character, Long>();
        futureResults.forEach(r -> {
            try {
                final var charToCount = r.get();
                charToCount.forEach((k, v) -> finalCharToCount.compute(k, (character, aLong) -> {
                    if (aLong == null) {
                        return v;
                    }
                    return aLong + v;
                }));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        executedTimeInNanos = System.nanoTime() - start;
        executor.shutdown();
        return finalCharToCount;
    }

    @Override
    public long getExecutedTimeInNanos() {
        return executedTimeInNanos;
    }

}
