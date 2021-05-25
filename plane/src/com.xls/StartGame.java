package com.xls;

import sun.awt.HToolkit;

import javax.swing.*;
import java.awt.*;

public class StartGame extends JFrame {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setTitle("飞机大作战");

        //获取屏幕宽度
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //设置窗体大小及位置
        jf.setBounds((width - 800)/2,(height - 500)/2,600,550);
        //创建面板
        GamePanel gp = new GamePanel();
        jf.add(gp);
        //窗体不可调
        jf.setResizable(false);
//        关闭窗口
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
