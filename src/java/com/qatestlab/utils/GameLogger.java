package java.com.qatestlab.utils;

import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by user on 07.06.2017.
 * This is a logging all game actions class
 */
public final class GameLogger {
    private static FileHandler fileHandler;
    private static Logger logger;

    /**
     * logger initialization and log file creation here
     */
    static {
        try {
            File file = new File("logs");
            if(!file.exists()) {
                file.mkdir();
            }
            fileHandler = new FileHandler("./logs/qaTestLab.log");
            logger = Logger.getLogger("qaTestLabLog");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * To log game action
     * @param message - text of game action
     */
    public static void log(String message) {
        System.out.println(message);
        logger.info(message);
    }
}

