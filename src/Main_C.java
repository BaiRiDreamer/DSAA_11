import java.util.Scanner;

public class Main_C {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        MinHeap minHeap = new MinHeap(N);

        /**
         * 数据读入并进行建堆操作
         */
        Node prev = null;
        Node node = null;
        for (int i = 0; i < N; i++) {
            node = new Node();
            node.index = i;
            node.value = in.nextInt();
            node.prev = prev;
            if (node.prev != null) {
                node.prev.next = node;
            }
            minHeap.insert_Minheap(node);
            prev = node;
        }

        /**
         * 相等大小的值的队列，其中保证index最小的在最前
         */
        Node[] queueMinAhead = new Node[N + 5];
        for (int i = 0; i < N + 5; i++) {
            queueMinAhead[i] = new Node();
        }
        int front = 0;
        int rear = 0;


        while (minHeap.size != 1) {
            front = 0;
            rear = 1;
            queueMinAhead[0] = minHeap.delete_Minheap();

            /**
             * 获得最小值序列，最开头的index最小
             */
            Node temp = null;
            do {
                while (temp == null || temp.isAlive == false) {
                    temp = minHeap.delete_Minheap();
                }
                if (temp.value == queueMinAhead[0].value) {
                    queueMinAhead[rear++] = temp;
                    if (queueMinAhead[rear - 1].index < queueMinAhead[0].index) {
                        queueMinAhead[rear - 1] = queueMinAhead[0];
                        queueMinAhead[0] = temp;
                    }
                }
            } while (temp.value == queueMinAhead[0].value);
            minHeap.insert_Minheap(temp);   //将不符合的temp重新放回

            Node node_select = queueMinAhead[0];
            Node node_select_partner;

            if (node_select.prev == null || node_select.next == null){
                //node_select 为边际节点
                if (node_select.prev != null){
                    //左边可配对,将左端配对
                    node_select_partner = node_select.prev;
                    node_select_partner.isAlive = false;    //将其打上标记
                    Node node_insert = new Node();
                    node_insert.value = node_select.value ^ node_select_partner.value + 1;
                    node_insert.index = node_select_partner.index;
                    node_insert.prev = node_select_partner.prev;
                    node_insert.next = node_select.next;

                    /**
                     * 将最终结果插入进去
                     */
                    minHeap.insert_Minheap(node_insert);
                }
                else {
                    //右边可配对
                    node_select_partner = node_select.next;
                    node_select_partner.isAlive = false;
                    Node node_insert = new Node();
                    node_insert.value =node_select.value ^ node_select_partner.value + 1;
                    node_insert.index = node_select.index;
                    node_insert.prev = node_select.prev;
                    node_insert.next = node_select_partner.next;

                    /**
                     * 将最终结果插入进去
                     */
                    minHeap.insert_Minheap(node_insert);
                }
            }
            else {
                //node_select 不为边际节点
                int a = node_select.prev.value ^ node_select.value + 1;
                int b = node_select.next.value ^ node_select.value + 1;
                if (a >= b){
                    node_select_partner = node_select.prev;
                }
                else {
                    node_select_partner = node_select.next;

                }

                node_select_partner.isAlive = false;
                Node node_insert = new Node();
                node_insert.value = node_select.value ^ node_select_partner.value + 1;
                node_insert.index = node_select.index;
                node_insert.prev = a >= b ? node_select_partner.prev : node_select.prev;
                node_insert.next = a >= b ? node_select.next : node_select_partner.next;

                /**
                 * 将最终结果插入进去
                 */
                minHeap.insert_Minheap(node_insert);
            }

            for (int i = 1; i < rear; i++) {
                minHeap.insert_Minheap(queueMinAhead[i]);
            }



        }

        System.out.printf("%d\n",minHeap.delete_Minheap().value);
    }
}


class Node {
    Node prev;      //用来判断前节点
    Node next;      //用来判断后节点
    int index;      //用来判断下标数据，因为在数值相同时，需要比较下标的大小来判断前或者后
    int value;      //当前的数值
    boolean isAlive;//当前数据是否存活

    public Node() {
        prev = null;
        next = null;
        index = -1;
        value = -1;
        isAlive = true;
    }
}

class MinHeap {
    int size;
    Node[] minHeap;

    public MinHeap(int n) {
        minHeap = new Node[n + 5];
        size = 0;
    }

    void insert_Minheap(Node node) {
        size++;
        minHeap[size] = node;

        int cur = size;

        while (cur > 1) {
            if (minHeap[cur].value < minHeap[cur / 2].value) {
                Node temp = minHeap[cur];
                minHeap[cur] = minHeap[cur / 2];
                minHeap[cur / 2] = temp;
                cur = cur / 2;
            } else {
                break;
            }
        }
    }

    Node delete_Minheap() {
        /**
         * 最后一个叶子和根交换
         * 指针指向跟
         * while（有儿子）{
         * if（有两个儿子）{
         *  左儿子大
         *  右儿子大
         *  指针儿子比指针大，则交换
         * }
         * else{
         *  只有一个儿子
         * }
         */

        Node Return = minHeap[1];

        Node temp1 = minHeap[1];
        minHeap[1] = minHeap[size];
        minHeap[size] = temp1;
        size--;

        int cur = 1;    //cur指向根节点，开始维护堆的操作

        loop:
        while (cur * 2 <= size) {
            //有两个子节点
            if (cur * 2 + 1 <= size) {
                if (minHeap[cur].value <= minHeap[cur * 2].value && minHeap[cur].value <= minHeap[cur * 2 + 1].value) {
                    break loop;
                } else {
                    if (minHeap[cur * 2].value <= minHeap[cur * 2 + 1].value) {
                        //左儿子较小，左儿子前提
                        Node temp2 = minHeap[cur];
                        minHeap[cur] = minHeap[cur * 2];
                        minHeap[cur * 2] = temp2;
                        cur = cur * 2;
                    } else {
                        //右儿子较小，右儿子前提
                        Node temp2 = minHeap[cur];
                        minHeap[cur] = minHeap[cur * 2 + 1];
                        minHeap[cur * 2 + 1] = temp2;
                        cur = cur * 2 + 1;
                    }
                }
            }
            //有一个子节点 并且是左子节点
            else {
                if (minHeap[cur].value <= minHeap[cur * 2].value) {
                    break loop;
                } else {
                    //需要继续维护堆，左儿子较小
                    Node temp2 = minHeap[cur];
                    minHeap[cur] = minHeap[cur * 2];
                    minHeap[cur * 2] = temp2;
                    cur = cur * 2;
                }
            }
        }
        return Return;
    }
}
















//import java.util.Arrays;
//        import java.util.Collection;
//        import java.util.Scanner;
//
//public class Main_A {
//
//    int cnt = 0;
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
//        Node Return = heap_arr[1];
//
//        Node temp1 = heap_arr[1];
//        heap_arr[1] = heap_arr[size];
//        heap_arr[size] = temp1;
//        size --;
//
//        int cur = 1;//cur指向根节点，开始维护堆的操作
//
//        loop:
//        while (cur * 2 <= size){
//            //有两个子节点
//            if (cur * 2 + 1 <= size){
//                if(heap_arr[cur].product <= heap_arr[cur*2].product && heap_arr[cur].product <= heap_arr[cur*2 + 1].product) {
//                    break loop;
//                }
//                else{
//                    if (heap_arr[cur * 2].product <= heap_arr[cur * 2 + 1].product) {
//                        //左儿子较小，左儿子前提
//                        Node temp2 = heap_arr[cur];
//                        heap_arr[cur] = heap_arr[cur * 2];
//                        heap_arr[cur * 2] = temp2;
//                        cur = cur*2;
//                    }
//                    else{
//                        //右儿子较小，右儿子前提
//                        Node temp2 = heap_arr[cur];
//                        heap_arr[cur] = heap_arr[cur * 2 + 1];
//                        heap_arr[cur * 2 + 1] = temp2;
//                        cur = cur*2 + 1;
//                    }
//                }
//            }
//            //有一个子节点 并且是左子节点
//            else{
//                if (heap_arr[cur].product <= heap_arr[cur*2].product){
//                    break loop;
//                }
//                else{
//                    //需要继续维护堆，左儿子较小
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

