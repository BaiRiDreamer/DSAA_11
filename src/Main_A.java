//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Scanner;
//
//public class Main_A {
//
//     int cnt = 0;
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int N = in.nextInt();
//        int M = in.nextInt();
//        int K = in.nextInt();
//
//        int cnt = 0;
//        long[] resultArr = new long[K];
//
//        int[] arr_A = new int[N];
//        int[] arr_B = new int[M];
//
//        for (int i = 0; i < N; i++) {
//            arr_A[i] = in.nextInt();
//        }
//        for (int i = 0; i < M; i++) {
//            arr_B[i] = in.nextInt();
//        }
//
//        Arrays.sort(arr_A);
//
//        heap minHeap = new heap(2*M);
//
//        for (int i = 0; i < M; i++) {
//            Node node = new Node();
//            node.arr_A_index = 0;
//            node.arr_B_index = i;
//            node.product = (long)arr_A[0] * (long)arr_B[i];
//            minHeap.insert_Minheap(node);
//        }
//
//        int A_index = 0;
//        int B_index = 0;
//
//        while (cnt < K){
//            Node minNode = minHeap.delete_Minheap();
//            resultArr[cnt++] = minNode.product;
//
//            A_index = minNode.arr_A_index;
//            B_index = minNode.arr_B_index;
//
//            if (A_index < N-1){
//                Node node = new Node();
//                node.arr_A_index = A_index+1;
//                node.arr_B_index = B_index;
//                node.product = (long)arr_A[node.arr_A_index] * (long)arr_B[node.arr_B_index];
//                minHeap.insert_Minheap(node);
//            }
//        }
//
//        for (int i = 0; i < K; i++) {
//            System.out.printf("%d ", resultArr[i]);
//        }
//    }
//}
//
//class heap {
//    Node heap_arr[];
//    int size = 0;
//
//    public heap(int n) {
//        heap_arr = new Node[n + 5];
//    }
//
//
//    void insert_Minheap(Node node){
//        size ++;
//        heap_arr[size] = node;
//
//        int cur = size;
//
//        while (cur > 1){
//            if (heap_arr[cur].product < heap_arr[cur/2].product){
//                Node temp = heap_arr[cur];
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
//    Node delete_Minheap(){
//        /**
//         * ??????????????????????????????
//         * ???????????????
//         * while???????????????{
//         * if?????????????????????{
//         *  ????????????
//         *  ????????????
//         *  ????????????????????????????????????
//         * }
//         * else{
//         *  ??????????????????
//         * }
//         */
//
//        Node Return = heap_arr[1];
//
//        Node temp1 = heap_arr[1];
//        heap_arr[1] = heap_arr[size];
//        heap_arr[size] = temp1;
//        size --;
//
//        int cur = 1;//cur??????????????????????????????????????????
//
//        loop:
//        while (cur * 2 <= size){
//            //??????????????????
//            if (cur * 2 + 1 <= size){
//                if(heap_arr[cur].product <= heap_arr[cur*2].product && heap_arr[cur].product <= heap_arr[cur*2 + 1].product) {
//                    break loop;
//                }
//                else{
//                    if (heap_arr[cur * 2].product <= heap_arr[cur * 2 + 1].product) {
//                        //?????????????????????????????????
//                        Node temp2 = heap_arr[cur];
//                        heap_arr[cur] = heap_arr[cur * 2];
//                        heap_arr[cur * 2] = temp2;
//                        cur = cur*2;
//                    }
//                    else{
//                        //?????????????????????????????????
//                        Node temp2 = heap_arr[cur];
//                        heap_arr[cur] = heap_arr[cur * 2 + 1];
//                        heap_arr[cur * 2 + 1] = temp2;
//                        cur = cur*2 + 1;
//                    }
//                }
//            }
//            //?????????????????? ?????????????????????
//            else{
//                if (heap_arr[cur].product <= heap_arr[cur*2].product){
//                    break loop;
//                }
//                else{
//                    //???????????????????????????????????????
//                    Node temp2 = heap_arr[cur];
//                    heap_arr[cur] = heap_arr[cur * 2];
//                    heap_arr[cur * 2] = temp2;
//                    cur = cur*2;
//                }
//            }
//        }
//        return Return;
//    }
//}
//
//class Node{
//    int arr_A_index = -1;
//    int arr_B_index = -1;
//    long product    = 0;
//}
//
