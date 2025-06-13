package com.xjx.springboottest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.xjx.springboottest.util.DateUtil;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.apache.commons.lang3.StringUtils;

/**
 * learningTime
 *
 * @author xjx
 * @since 2025/4/28 16:38
 */

/**
 * 这个程序会在 3 到 5 分钟的随机时间产生提示音，之后暂停 10 秒钟，当总时长达到 90 分钟时，会提示休息 20 分钟。
 */
public class learningTime {
    private static final int MIN_INTERVAL = 3 * 60 * 1000;
    private static final int MAX_INTERVAL = 5 * 60 * 1000;
    private static final int PAUSE_TIME = 10 * 1000;
    private static final int TOTAL_TIME = 90 * 60 * 1000;
    private static final int REST_TIME = 20 * 60 * 1000;
    private static final String ALERT_SOUND_FILE = "mp3/short.mp3";
    private static final String ALERT_SOUND_FILE_LONG = "mp3/long.mp3";

    private static Timer timer = new Timer();
    private static long startTime = System.currentTimeMillis();
    private static long elapsedTime = 0;

    public static void main(String[] args) {
        scheduleNextAlert();
    }

    private static void scheduleNextAlert() {
        Random random = new Random();
        int interval = random.nextInt(MAX_INTERVAL - MIN_INTERVAL + 1) + MIN_INTERVAL;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                elapsedTime = System.currentTimeMillis() - startTime;
                if (elapsedTime >= TOTAL_TIME) {
                    playSound(ALERT_SOUND_FILE_LONG);
                    System.out.println("已经过去90分钟了，请休息20分钟。");
                    try {
                        Thread.sleep(REST_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startTime = System.currentTimeMillis();
                    elapsedTime = 0;
                } else {
                    playSound(ALERT_SOUND_FILE);
                    System.out.println(DateUtil.dt2Str(null,null));
                    try {
                        Thread.sleep(PAUSE_TIME);
                        playSound(ALERT_SOUND_FILE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                scheduleNextAlert();
            }
        }, interval);
    }

    private static void playSound(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Player player = new Player(fis);
            player.play();
        } catch (IOException | JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
