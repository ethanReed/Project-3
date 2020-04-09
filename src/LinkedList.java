//REEDx500 Johal004

public class LinkedList<T extends Comparable<T>> implements List<T> {

    //T is generic type

    private Node head;
    private boolean isSorted;
    public LinkedList(){

        Node head = null;
        isSorted = true;
    }
    public boolean add(T element){
        /*
         * Add an element to end of the list. If element is null,
         * it will NOT add it and return false.  Otherwise, it
         * will add it and return true. Updates isSorted to false.
         */

        if (element == null){
            isSorted = false;
            return false;
        }
        if (head == null){
            head = new Node(element, null);
            isSorted = false;
            return true;
        }
        Node updatedHead = head.getNext();
        Node trailer = updatedHead;

        Node x = new Node(element, null);
        while(updatedHead != null){
            trailer = updatedHead;
            updatedHead = updatedHead.getNext();

        }
        trailer.setNext(x);
        isSorted = false;
        return true;
    }


    public boolean add(int index, T element) {
        /*
         *  Add an element at specific index. This method should
         * also shift the element currently at that position (if
         * any) and any subsequent elements to the right (adds
         * one to their indices). If element is null, or if index
         * index is out-of-bounds (index < 0 or index >= size_of_list),
         * it will NOT add it and return false. Otherwise it will
         * add it and return true. See size() for the definition
         * of size of list. Also updates isSorted variable to false.
         */
        if(element == null || index > this.size() || index < 0){
            return false;
        }else if (index == 0){
            //add node at head
            Node start = new Node(element, head);
            head = start;
            return true;
        }
        int count = 0;
        Node current = head.getNext();
        Node trailer = head;

        while (count != index){
            trailer = current;
            current = current.getNext();
            count ++;
        }
        Node position = new Node(element, current);
        trailer.setNext(position);
        return true;
    }
    public void clear() {

        /*
         * Return true if element is in the list and false
         * otherwise. If isSorted is true, uses the ordering
         * of the list to increase the efficiency of the search.
         */

        head = null;
        isSorted = true;

    }

    public boolean contains(T element) {
        /*
         * Return true if element is in the list and false
         * otherwise. If isSorted is true, uses the ordering
         * of the list to increase the efficiency of the search.
         */

        if (isSorted == true) {
            Node updatedHead = head;
            while (element.equals(updatedHead.getData())) {
                if (element == updatedHead.getData()) {
                    return true;
                }
                updatedHead = updatedHead.getNext();
            }
        } else {
            Node updatedHead = head;
            int count = 0;
            while (updatedHead != null) {
                if (updatedHead.getData() == element) {
                    return true;
                } else {
                    updatedHead = updatedHead.getNext();
                }
                return false;
            }
        }
        return false;
    }

    public T get(int index) {
        //use counter if counter
        if (index > this.size() || index < 0) {
            return null;
        } else {
            Node updatedHead = head;
            int count = 0;
            while (count != index) {
                count++;
                updatedHead = updatedHead.getNext();
            }
            T value = (T) updatedHead.getData();
            return value;
        }
    }

    public int indexOf(T element) {
        /*
         * Return the first index of element in the list. If element
         * is null or not found in the list, return -1. If isSorted is
         * true, uses the ordering of the list to increase the efficiency
         * of the search.
         */

        if (head == null) {
            return -1;
        } else {
            Node updatedHead = head;
            int count = 0;

            while (updatedHead != null) {
                if (updatedHead.getData() == element) {
                    return count;
                } else {
                    updatedHead = updatedHead.getNext();
                    count++;
                }
            }
        }
        return -1;
    }
    public boolean isEmpty(){
            if (head != null){
                return true;
            }else{
                return false;
            }
    }
    public int lastIndexOf(T element) {
        /*
         * Same as indexOf(), except it will return the last index
         * of element. If isSorted is true, uses the ordering
         * of the list to increase the efficiency of the search.
         */

        //do it for sorted list

        if (head == null) {
            return -1;
        } else {
            Node updatedHead = head;
            int count = 0;
            int lastIndex = 0;
            while (updatedHead != null) {
                if (updatedHead.getData() == element) {
                    lastIndex = count;
                } else {
                    updatedHead = updatedHead.getNext();
                    count++;
                }
            }
        }
        return -1;
    }

    public T set(int index, T element) {
            /*
             * Replace the element at index with element and return the
             * element that was previously at index. If index is
             * out-of-bounds or element is null, do nothing with element
             * and return null.
             */
            if(head == null || this.size() < index || index < 0){
                return null;
            }else{
                int count = 0;
                Node updatedHead = head;

                while (updatedHead != null){
                    if(count == index){
                        T previousData = (T) updatedHead.getData();
                        updatedHead.setData(element);
                        return previousData;
                    }else{
                        count ++;
                        updatedHead = updatedHead.getNext();
                    }
                }
            }

        return null;
    }


    public int size(){
        /*
         * Return the number of elements in the list. For example, if
         * 4 elements added to a list, size will return 4, while the
         * last index in the list will be 3.
         */
        Node updatedHead = head.getNext();
        Node trailer = updatedHead;

        int count = 0;

        while(updatedHead != null){
            trailer = updatedHead;
            updatedHead = updatedHead.getNext();
            count ++;
        }

        return count;
    }


    public void sort(boolean order) {

        /*
         * Sort the elements of the list. If order is true, sort the
         * list in increasing (alphabeticaly) order. If order is
         * false, sort the list in decreasing (reverse alphabetical)
         * order. Note: only set isSorted to true if sorted in ASCENDING
         * order.
         * If isSorted is true, and the order is true, do NOT resort.
         * Hint: Since T extends Comparable, you will find it useful
         * to use the public int compareTo(T o) method.
         */

        int sizeOfLL = this.size();
        Node current = head;
        Node trailer = head;
        Node temp;

        if (order == true && isSorted != true) {//sort  alphabetically
            for(int i = 0; i < sizeOfLL; i++){
                for(int j = 0; j < sizeOfLL; j++){
                    if(current.getNext() != null){
                        if (current.getData().compareTo(trailer.getData()) > 1){
                            T currentData = (T) current.getData();
                            T trailerData = (T) trailer.getData();

                            current.setData(trailerData);
                            trailer.setData(currentData);

                            trailer = current;
                            current = current.getNext();
                        }else{
                            trailer = current;
                            current = current.getNext();
                        }
                    }
                }
            }
        } else {// reverse sort alphabetically
            for(int i = 0; i < sizeOfLL; i++){
                for(int j = 0; j < sizeOfLL; j++){
                    if(current.getNext() != null){
                        if (current.getData().compareTo(trailer.getData()) < 1){
                            T currentData = (T) current.getData();
                            T trailerData = (T) trailer.getData();

                            current.setData(trailerData);
                            trailer.setData(currentData);

                            trailer = current;
                            current = current.getNext();
                        }else{
                            trailer = current;
                            current = current.getNext();
                        }
                    }
                }
            }

            int count = 0;
        }
    }
    public boolean remove(T element) {
        /*
         * Remove the first instance of element from the list. This
         * method should also shifts any subsequent elements to the
         * left (subtracts one from their indices). If successful,
         * return true. If element is not found in the list, return
         * false.
         */
        if (head == null){
            return false;
        }
        int count = 0;
        Node updatedHead = head;
        Node trailer = head;
        while(count  < this.size()){
            if(updatedHead.getData().equals(element)){
                Node nextVal = updatedHead.getNext();
                trailer.setNext(nextVal);
                return true;
            }else{
                trailer = updatedHead;
                updatedHead = updatedHead.getNext();
                count++;
            }
        }

        return false;
    }
    public T remove(int index) {
        /*
         * Remove whatever is at index index in the list and return
         * it. If index is out-of-bounds, return null. Shift the
         * indices as in the other remove.
         */
        if(head == null ||index > this.size()){
            return null;
        }

        Node updatedHead = head;
        Node trailer = head;
        int count = 0;
        while (count != index){
            trailer = updatedHead;
            updatedHead = updatedHead.getNext();
            count++;

        }if(count == index){
            T data = (T) updatedHead.getData();
            Node newPTR = updatedHead.getNext();
            trailer.setNext(newPTR);
            return data;
        }
        return null;
    }

    public String toString(){
            String result = "";
            Node currNode = head;
            int currIndex = 0;
            while( currNode != null ){
                result += currNode;
                currNode = currNode.getNext();
                currIndex++;

                // Only add a comma if this isn't the last element
                if( currNode != null ){ result += ", "; }
            }
            return result;
    }
}
