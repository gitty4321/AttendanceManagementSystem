public class Queue {
    private Node front;
    private Node rear;

    public Queue() {
        this.front = null;
        this.rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(String data) {
        Node newNode = new Node(data);
        if (rear == null) {
            front = rear = newNode;
        }
        else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public String dequeue() {
        if (isEmpty()) {
            return null;
        }
        String data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return data;
    }

}