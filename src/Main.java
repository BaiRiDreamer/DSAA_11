//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        int T = in.nextInt();
//
//        int Min1;
//        int Min2;
//        long sum = 0;
//
//        for (int i = 0; i < T; i++) {
//            sum = 0;
//
//            int N = in.nextInt();
//            heap minHeap= new heap(N);
//
//            for (int j = 0; j < N; j++) {
//                minHeap.insert_Minheap(in.nextInt());
//            }
//
//            while (minHeap.size >= 2){
//                Min1 = minHeap.delete_Minheap();
//                Min2 = minHeap.delete_Minheap();
//                sum += (Min1 + Min2);
//                minHeap.insert_Minheap(Min1+Min2);
//            }
//
//            System.out.println(sum);
//        }
//    }
//}
//
//class heap {
//    int heap_arr[];
//    int size = 0;
//
//    public heap(int n) {
//        heap_arr = new int[n + 1];
//    }
//
//    void insert_Maxheap(int x) {
//        size++;
//        heap_arr[size] = x;
//
//        int cur = size;
//
//        while (cur > 1) {
//            if (heap_arr[cur] > heap_arr[cur / 2]) {
//                int temp = heap_arr[cur];
//                heap_arr[cur] = heap_arr[cur / 2];
//                heap_arr[cur / 2] = temp;
//            } else {
//                break;
//            }
//        }
//    }
//
//    void insert_Minheap(int x){
//        size ++;
//        heap_arr[size] = x;
//
//        int cur = size;
//
//        while (cur > 1){
//            if (heap_arr[cur] < heap_arr[cur/2]){
//                int temp = heap_arr[cur];
//                heap_arr[cur] = heap_arr[cur/2];
//                heap_arr[cur/2] = temp;
//                cur=cur/2;
//            }
//            else {
//                break;
//            }
//        }
//    }
//
//    int delete_Minheap(){
//        /**
//         * 最后一个叶子和根交换
//         * 指针指向跟
//         * while（有儿子）{
//         * if（有两个儿子）{
//         *  左儿子大
//         *  右儿子大
//         *  指针儿子比指针大，则交换
//         * }
//         * else{
//         *  只有一个儿子
//         * }
//         */
//
//        int Return = heap_arr[1];
//
//        int temp1 = heap_arr[1];
//        heap_arr[1] = heap_arr[size];
//        heap_arr[size] = temp1;
//
//        size --;
//
//        int cur = 1;//cur指向根节点，开始维护堆的操作
//
//
//        loop:
//        while (cur * 2 <= size){
//            //有两个子节点
//            if (cur * 2 + 1 <= size){
//                if(heap_arr[cur] <= heap_arr[cur*2] && heap_arr[cur] <= heap_arr[cur*2 + 1]) {
//                    break loop;
//                }
//                else{
//                    if (heap_arr[cur * 2] <= heap_arr[cur * 2 + 1]) {
//                        //左儿子较小，左儿子前提
//                        int temp2 = heap_arr[cur];
//                        heap_arr[cur] = heap_arr[cur * 2];
//                        heap_arr[cur * 2] = temp2;
//                        cur = cur*2;
//                    }
//                    else{
//                        //右儿子较小，右儿子前提
//                        int temp2 = heap_arr[cur];
//                        heap_arr[cur] = heap_arr[cur * 2 + 1];
//                        heap_arr[cur * 2 + 1] = temp2;
//                        cur = cur*2 + 1;
//                    }
//                }
//            }
//            //有一个子节点 并且是左子节点
//            else{
//                if (heap_arr[cur] <= heap_arr[cur*2]){
//                    break loop;
//                }
//                else{
//                    //需要继续维护堆，左儿子较小
//                    int temp2 = heap_arr[cur];
//                    heap_arr[cur] = heap_arr[cur * 2];
//                    heap_arr[cur * 2] = temp2;
//                    cur = cur*2;
//                }
//            }
//        }
//
//        return Return;
//    }
//}
//
//
//
