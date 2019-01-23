package com.liangjinhai.studydemo.sortDemo;

/**
 * 睡眠排序法
 * 空间复杂度(n)
 * 时间复杂度(n那个数字的大小)
 */
public class SleepSort {

    public static void main(String[] args) {
        int[] arr = {1,4,7,3,8,9,2,6,5};
        //创建指定长度的线程数组
        printArray(arr);
        SortThread[] sortThreads = new SortThread[arr.length];
        //指定每个线程数组的值
        for (int i = 0; i < sortThreads.length; i++) {
            sortThreads[i] = new SortThread(arr[i]);
        }
        //开启每个线程
        for (int i = 0; i < sortThreads.length; i++) {
            sortThreads[i].start();
        }
    }

    private static void printArray(int[] source) {
        for (int i = 0; i < source.length; i++) {
            System.out.print("\t" + source[i]);
        }
        System.out.println();
    }

}
class SortThread extends Thread{
    int s = 0;
    public SortThread(int s){
        this.s = s;
    }
    @Override
    public void run(){
        try {
            sleep(s*10+10);  //睡眠指定的时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出该数
        System.out.println(s);
    }
}