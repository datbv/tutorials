package com.dbui.wc.service;

import com.dbui.wc.util.CountUtil;

import java.util.HashMap;
import java.util.Map;

public class SingleThreadCountingService implements CharacterCountingService {

    private long executedTimeInNanos;

    public Map<Character, Long> count(final String dir) {
        final var start = System.nanoTime();
        final var filePaths = CountUtil.getFilePaths(dir);
        final var finalCharToCount = new HashMap<Character, Long>();
        filePaths.forEach(path -> CountUtil.countCharacter(path, finalCharToCount));
        executedTimeInNanos = System.nanoTime() - start;
        return finalCharToCount;
    }

    @Override
    public long getExecutedTimeInNanos() {
        return executedTimeInNanos;
    }

}
