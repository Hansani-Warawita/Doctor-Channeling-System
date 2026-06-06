class Queue {               //Queue class. This class implements a Queue data structure using an array.  
    //these are the variables or attributes of the Queue class
    private int[] queueArray; //array to store queue elements
    private int front;        //index of the front element (where dequeue happens).
    private int rear;         //index of the rear element (where enqueue happens).
    private int maxSize;        //maximum size of the queue
    private int currentSize;   //current number of elements in the queue
    
    // Constructor
    public Queue(int size) {  //Constructor to initialize the queue
        maxSize = size;    //Creates an array with given size.
        queueArray = new int[maxSize]; //
        front = 0;   //front = 0 means queue starts from the beginning.
        rear = -1;  //rear starts at -1 because no elements are in the queue initially.
        currentSize = 0;  // currentSize = 0 → initially empty.
    }
    
    // Check if queue is empty
    public boolean isEmpty() {   //Returns true if empty, else false.
        return currentSize == 0;
    }
    
    // Check if queue is full
    public boolean isFull() {
        return currentSize == maxSize;}  //Returns true if full
    
    // Add element to rear of queue
    public boolean enqueue(int item) {
        if (isFull()) {
            return false;     //If queue is full → return false.
        }
        rear = (rear + 1) % maxSize; //else, This updates the rear pointer (where the new element will be added)., rear + 1 moves it to the next index.
                                    //% maxSize ensures it wraps around when it reaches the end of the array (circular queue).
        queueArray[rear] = item;  //Place the new item in queueArray[rear].
        currentSize++;           //Increase currentSize by 1.
        return true;             //Returns true if successfully added.
    }
    
    // Remove element from front of queue
    public int dequeue() {
        if (isEmpty()) {  //First, it checks if the queue is empty.If currentSize == 0, then there’s nothing to remove.Returns -1 as a failure signal.
            return -1;    //If queue is empty → return -1 (indicating failure).
        }
        int temp = queueArray[front];   //else, store the front element in a temp variable.
        front = (front + 1) % maxSize;  //Update front pointer (circular behavior).
        currentSize--;         //Decrease currentSize by 1.
        return temp;            //Return the removed element.
    }
    
    // Peek front element
    public int peek() {  //Returns the element at the front without removing it.
        if (isEmpty()) {
            return -1;    //If queue is empty → return -1 (indicating failure).
        }
        return queueArray[front];
    }
    
    // Get current size
    public int size() {   //Returns how many elements are currently in the queue.
        return currentSize;
    }
    
    // Display queue contents
    public void display() {       //Displays all elements in queue order.
        if (isEmpty()) {
            System.out.println("Queue is empty");  //if it is empty, print message and return.
            return;
        }
        
        System.out.print("Queue contents: ");   //else, iterate from front to rear and print each element.
        for (int i = 0; i < currentSize; i++) {
            int index = (front + i) % maxSize;
            System.out.print(queueArray[index] + " ");
        }
        System.out.println();
    }
    
    // Remove specific element from queue
    public boolean remove(int item) {       //Removes a specific element (not just front) from the queue.
        if (isEmpty()) {              //If queue is empty → return false.
            return false;
        }
        
        // Create temporary array to store remaining elements
        int[] tempArray = new int[maxSize];   //tempArray → a helper array to store all elements except the one we want to remove.
        int tempSize = 0;                       //tempSize → counts how many valid elements we store in tempArray.
        boolean found = false;  //found → tracks if we found the item to remove.    
        
        // Dequeue all elements except the one to remove
        while (!isEmpty()) {    //While queue is not empty, keep removing (dequeue()) elements one by one.
            int current = dequeue();  //current → the element we just removed.
            if (current == item && !found) {  // If it matches the item to remove and we haven't found it yet
                found = true;               //set found to true and skip adding it to tempArray (effectively removing it).
            } else {
                tempArray[tempSize++] = current; //Otherwise, add it to tempArray and increment tempSize.
            }
        }
        
        // Re-enqueue remaining elements
        for (int i = 0; i < tempSize; i++) { //Now, put all elements from tempArray back into the original queue using enqueue().
            enqueue(tempArray[i]);      //This restores the queue but without the removed item.
        }
        
        return found;   //Return true if we found and removed the item, else false.
    }
}
