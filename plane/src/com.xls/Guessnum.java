package com.xls;

import java.util.Scanner;

public class Guessnum {
    public static void main(String[] args) {
       //猜数，随机生成1-100的数
        double r = Math.random();
        int num = (int)(r*100+1);
        System.out.println(num);
        Scanner s = new Scanner(System.in);
        while (true) {
            //读取输入的数
            int i = s.nextInt();
//        生成数大于输入数
            if (num > i) {
                System.out.println("猜小了");
            } else if (num < i) {
                System.out.println("猜大了");
            } else if (num == i) {
                System.out.println("恭喜猜对了");
                break;
            }
        }
    }
}
