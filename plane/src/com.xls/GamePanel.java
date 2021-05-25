package com.xls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel  extends JPanel {
    //飞机位置
    int planeX ;
    int planeY;
    //判断飞机生死
    boolean isDie = false;
    //存放十个炮弹
    int[] shellXs = new int[10];
    int[] shellYs = new int[10];
    //每一个炮弹弧度
    double[] degrees = new double[10];

    long startTime;
    long endTime;
    //游戏默认暂停状态
    boolean isStart = false;
    //飞机方向
    boolean up,down,left,right;

    //定时器
    Timer timer;

    //初始化炮弹
    public void init(){
        planeX = 250;
        planeY = 450;
        //炮弹坐标
        for (int i = 0; i <10 ; i++) {
            shellXs[i] = 100;
            shellYs[i] = 100;
            //每一炮弹随机弧度
            degrees[i] = (int)(Math.random()*2*Math.PI);

        }

    }
    //构造方法初始化飞机、炮弹
    public GamePanel(){
       init();
       //游戏开始时间
       startTime = System.currentTimeMillis();
       //将焦点放在面板上
        this.setFocusable(true);
        //添加监听器
        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                if(keyCode==KeyEvent.VK_SPACE){
                    if(isDie){
                        init();
                        isDie = false;
                    }else {
                        isStart = !isStart;
                        //重新绘制画板
                        repaint();
                    }
                }
                if(keyCode==KeyEvent.VK_UP){
                    up = true;
                }
                if(keyCode==KeyEvent.VK_DOWN){
                    down = true;
                }
                if(keyCode==KeyEvent.VK_LEFT){
                    left = true;
                }
                if(keyCode==KeyEvent.VK_RIGHT){
                    right= true;
                }
                repaint();
                //System.out.println(keyCode);
            }

            //按键抬起

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int keyCode = e.getKeyCode();
                if(keyCode==KeyEvent.VK_UP){
                    up = false;
                }
                if(keyCode==KeyEvent.VK_DOWN){
                    down = false;
                }
                if(keyCode==KeyEvent.VK_LEFT){
                    left = false;
                }
                if(keyCode==KeyEvent.VK_RIGHT){
                    right= false;
                }
                repaint();
            }
        });

        //初始化定时器
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //游戏开始才动
                if (isStart == true&&isDie==false) {
                    if (left) {
                        planeX -= 7;
                    }
                    if (right) {
                        planeX += 7;
                    }
                    if (up) {
                        planeY -= 7;
                    }
                    if (down) {
                        planeY += 7;
                    }

                    //炮弹按弧度运动
                    for (int i = 0; i <10 ; i++) {
                        shellXs[i] += Math.cos(degrees[i])*7;
                        shellYs[i] += Math.sin(degrees[i])*7;
                        //炮弹到边界后从另一边回来
                        if(shellXs[i]<=0){
                            shellXs[i] = 600;
                        }
                        if(shellXs[i]>600){
                            shellXs[i] = 0;
                        }
                        if(shellYs[i]<=0){
                            shellYs[i] = 550;
                        }
                        if(shellYs[i]>550){
                            shellYs[i] = 0;
                        }
                        //炮弹和飞机碰撞intersects检测碰撞
                        boolean flag =  new Rectangle(planeX,planeY,70,51).
                                intersects(new Rectangle(shellXs[i],shellYs[i],45,27));
                        //结束时间
                        endTime = System.currentTimeMillis();
                        //碰撞飞机就死
                        if(flag){
                            isDie = true;
                        }
                    }


                    repaint();
                }
            }
        });
        //启动定时器
        timer.start();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //this.setBackground(new Color(111, 160,255));
        Images.bgImg.paintIcon(this,g,0,0);
        Images.planeImg.paintIcon(this,g,planeX,planeY);
        //画炮弹
        for (int i = 0; i <10 ; i++) {
            Images.shellImg.paintIcon(this,g,shellXs[i],shellYs[i]);
        }
        //游戏默认关闭
        if(isStart==false){
            g.setColor(new Color(0x6262FC));
            //样式，加粗效果，大小
            g.setFont(new Font("微软雅黑",Font.BOLD,20));
            //写字
            g.drawString("游戏开始",250,200);
        }
        //游戏默认关闭
        if(isDie==true){
            g.setColor(new Color(0x6262FC));
            //样式，加粗效果，大小
            g.setFont(new Font("微软雅黑",Font.BOLD,20));
            //写字
            g.drawString("游戏结束",250,200);
            g.drawString("历时"+(endTime-startTime)/1000+"秒",250,250);
        }
    }
}
