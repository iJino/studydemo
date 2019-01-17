package com.liangjinhai.studydemo.sortDemo;


/**
 * 冒泡排序
 * 每次都会找到最大值然后排到最后，平均时间复杂度为：O(n^2)
 */
public class BubbleSort {

    public static void sort(int[] arr){
        System.out.println("排序前数组为：");
        for (int num : arr){
            System.out.print(num+" ");
        }
        for(int i=0;i<arr.length-1;i++){//外层循环控制排序趟数
            for(int j=0;j<arr.length-1-i;j++){//内层循环控制每一趟排序多少次
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        System.out.println();
        System.out.println("排序后的数组为：");
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
        sort(source);

        System.out.print("\n\n排序后结果：");
        printArray(source);
    }
}
