package com.liangjinhai.studydemo.sortDemo;

/**
 * 选择排序法
 * 时间复杂度为：O(n^2)
 */
public class SelectionSort {

    public static void selectionSort(int[] arr){
        System.out.println("交换之前：");
        for(int num:arr){
            System.out.print(num+" ");
        }
        //选择排序的优化
        for(int i = 0; i < arr.length - 1; i++) {// 做第i趟排序
            int k = i;
            for(int j = k + 1; j < arr.length; j++){// 选最小的记录
                if(arr[j] < arr[k]){
                    k = j; //记下目前找到的最小值所在的位置
                }
            }
            //在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换
            if(i != k){  //交换a[i]和a[k]
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
        }
        System.out.println();
        System.out.println("交换后：");
        for(int num:arr){
            System.out.print(num+" ");
        }
    }

    private static void printArray(int[] source) {
        for (int i = 0; i < source.length; i++) {
            System.out.print("\t" + source[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int source[] = new int[] { 53, 27, 36, 15, 69, 42 };
        System.out.print("初始关键字：");
        printArray(source);
        System.out.println("");
        selectionSort(source);

        System.out.print("\n\n排序后结果：");
        printArray(source);
    }
}
