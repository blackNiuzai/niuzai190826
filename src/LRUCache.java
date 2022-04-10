import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

public class LRUCache {

    private int capacity;

    private int size;

    private Map<Integer, Node> cache = new HashMap<>();

    private Node dummyHead, dummyTail;

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,0);
        cache.put(2,2);
        int i = cache.get(1);
        cache.put(3,3);
        int i1 = cache.get(2);
        cache.put(4,4);
        int i2 = cache.get(1);
        int i3 = cache.get(3);
        int i4 = cache.get(4);

    }


    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        dummyHead = new Node();
        dummyTail = new Node();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        Node integerNode = cache.get(key);
        // 将节点放到头部
        moveNodeToHead(integerNode);
        return integerNode.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)){
            Node node = cache.get(key);
            node.value = value;
            moveNodeToHead(node);
        }else if (cache.size() < capacity){
            Node node = new Node(key,value);
            cache.put(key, node);
            addNodeToHead(node);
            size ++;
        }else {
            delLast();
            Node node = new Node(key, value);
            cache.put(key, node);
            addNodeToHead(node);
        }
    }


    private void delLast(){
        cache.remove(dummyTail.prev.key);
        deleteNode(dummyTail.prev);
    }

    private void moveNodeToHead(Node e){
        deleteNode(e);
        addNodeToHead(e);
    }

    private void deleteNode(Node e){
        Node next = e.next;
        Node prev = e.prev;
        prev.next = next;
        next.prev = prev;
    }

    private void addNodeToHead(Node e){
        Node next = dummyHead.next;
        dummyHead.next = e;
        e.prev = dummyHead;
        e.next = next;
        next.prev = e;
    }

    private static class Node {
        int key;
        int value;
        Node next;
        Node prev;

        Node(Node prev, int key,int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
        Node(){}

        Node(int key, int value){
            this.key  = key;
            this.value = value;
        }
    }


}
