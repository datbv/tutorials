package com.dbui.wc;

import com.dbui.wc.service.ActorCountingService;
import com.dbui.wc.service.CharacterCountingService;
import com.dbui.wc.service.ForkJoinCountingService;
import com.dbui.wc.service.SingleThreadCountingService;

public class Application {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println("New round ------------------------------------ ");
            execute(new SingleThreadCountingService(), args[0], "Single thread: \t\t\t");
            execute(new ForkJoinCountingService(2), args[0], "ForkJoin 02 thread: \t");
            execute(new ForkJoinCountingService(4), args[0], "ForkJoin 04 thread: \t");
            execute(new ForkJoinCountingService(8), args[0], "ForkJoin 08 thread: \t");
            execute(new ForkJoinCountingService(16), args[0], "ForkJoin 16 thread: \t");
            execute(new ActorCountingService(2), args[0], "Actor 02 thread: \t\t");
            execute(new ActorCountingService(4), args[0], "Actor 04 thread: \t\t");
            execute(new ActorCountingService(8), args[0], "Actor 08 thread: \t\t");
            execute(new ActorCountingService(16), args[0], "Actor 16 thread: \t\t");
        }
    }

    private static void execute(CharacterCountingService service, String dir, String message) {
        service.count(dir);
        System.out.printf("%s %15d %n", message, service.getExecutedTimeInNanos());
    }

}
