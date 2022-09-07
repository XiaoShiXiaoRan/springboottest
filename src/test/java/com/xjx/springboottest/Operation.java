package com.xjx.springboottest;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
public class Operation {
    public static void main(String[] args) throws AWTException {
        String str = "测试Java代码";//轰炸内容
        Robot robot = new Robot();
        robot.delay(5000);
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        String[] authors = str.split("[,]");
        for (int j = 0; j < 100; j++){//轰炸次数
            for (int i = 0; i < authors.length; i++) {
                String str1 = authors[i];
                Transferable text = new StringSelection(str1+i);
                clip.setContents(text, null);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.delay(100);
                robot.keyPress(KeyEvent.VK_ENTER);
            }
        }
    }
}