package com.dbui.wc.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CountUtil {

    private CountUtil() {
    }

    public static boolean isOnAlphabet(char c) {
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }

    public static List<Path> getFilePaths(String dir) {
        try (Stream<Path> paths = Files.walk(Paths.get(dir))) {
            return paths.filter(Files::isRegularFile).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public static void countCharacter(final Path path, final Map<Character, Long> charToCount) {
        try {
            var lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            countCharacter(lines, charToCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void countCharacter(final Collection<String> lines, Map<Character, Long> charToCount) {
        lines.forEach(string -> {
            final var chars = string.toCharArray();
            for (final char c : chars) {
                if (CountUtil.isOnAlphabet(c)) {
                    charToCount.compute(c, (val, currentCount) -> {
                        if (currentCount == null) {
                            return 1L;
                        }
                        return ++currentCount;
                    });
                }
            }
        });
    }

}
