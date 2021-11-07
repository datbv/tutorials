package com.dbui.wc.service;

import java.util.Map;

public interface CharacterCountingService {

    Map<Character, Long> count(String dir);

    long getExecutedTimeInNanos();

}
