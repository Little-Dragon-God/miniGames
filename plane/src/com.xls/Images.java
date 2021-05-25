package com.xls;

import jdk.nashorn.internal.ir.CallNode;
import sun.management.snmp.jvminstr.JvmRTInputArgsEntryImpl;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;

public class Images {
    //先把图片的地址封装为一个具体的对象：
    static URL shellURL = Images.class.getResource("/images/炸弹.png");
    //把图片封装为一个对象：
    static ImageIcon shellImg = new ImageIcon(shellURL);
    //先把图片的地址封装为一个具体的对象：
    static URL bgURL = Images.class.getResource("/images/背景.png");
    //把图片封装为一个对象：
    static ImageIcon bgImg = new ImageIcon(bgURL);
    //先把图片的地址封装为一个具体的对象：
    static URL planeURL = Images.class.getResource("/images/飞机.png");
    //把图片封装为一个对象：
    static ImageIcon planeImg = new ImageIcon(planeURL);

}
