//REEDx500 Johal004

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class LinkedList<T extends Comparable<T>> implements List<T> {

    //T is generic type

    private Node <T> head;
    private boolean isSorted;
    public LinkedList(){

        head = new Node <T> ();
        isSorted = true;
    }
    public boolean add(T element){
        /*
         * Add an element to end of the list. If element is null,
         * it will NOT add it and return false.  Otherwise, it
         * will add it and return true. Updates isSorted to false.
         */

        if (element == (null)){
            return false;
        }
        Node updatedHead = head;

        Node <T> x = new Node(element, null);
        while(updatedHead.getNext() != null){
            updatedHead = updatedHead.getNext();
            }
        updatedHead.setNext(x);
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
        if (element == null || index >= this.size() || index < 0 || head.getNext() == null) {
            return false;
        } else if (index == 0) {
            //add node at head
            Node y = new Node(element, head.getNext());
            head.setNext(y);
            isSorted = false;
            return true;
        }else {
            int count = 0;
            Node current = head.getNext();
            Node trailer = current;
            while (count != index) {
                trailer = current;
                current = current.getNext();
                count++;
            }
            if (count == index) {
                Node position = new <T>Node(element, current);
                trailer.setNext(position);
                isSorted = false;
                return true;
            }
        }
        return false;
    }
    public void clear() {

        /*
         * Remove all elements from the list.
         */
        head.setNext(null);
        isSorted = true;

    }

    public boolean contains(T element) {
        /*
         * Return true if element is in the list and false
         * otherwise. If isSorted is true, uses the ordering
         * of the list to increase the efficiency of the search.
         */

        if (isSorted == true) {
            Node updatedHead = head.getNext();
            while (updatedHead != null) {
                if (element.equals(updatedHead.getData())) {
                    return true;
                }
                updatedHead = updatedHead.getNext();
            }
        } else {
            Node updatedHead = head.getNext();
            while (updatedHead != null) {
                if (updatedHead.getData().equals(element)) {
                    return true;
                } else {
                    updatedHead = updatedHead.getNext();
                }
            }
        }
        return false;
    }

    public T get(int index) {
        /*
         *  Return the element at given index. If index is
         * out-of-bounds, it will return null.
         *
         */
        if (index >= this.size() || index < 0 || head.getNext() == null) {
            return null;
        }else if (index == 0 && head.getNext() != null){
            return head.getNext().getData();
        }else{
            Node updatedHead = head.getNext();
            int count = 0;
            while (count != index) {
                updatedHead = updatedHead.getNext();
                count++;
                }
            if(count == index){

                T data = (T) updatedHead.getData();
                return data;
            }
        }
        return null;
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
        } else if(isSorted == true){
            Node updatedHeadSort = head;
            int countForSorted = 0;
            while(updatedHeadSort.getNext() != null){
                if(updatedHeadSort.getData().equals(element)){
                    return countForSorted;
                }else{
                    updatedHeadSort = updatedHeadSort.getNext();
                    countForSorted++;
                }
            }
        }else{
            Node updatedHead = head.getNext();
            int count = 0;

            while (updatedHead != null) {
                if (updatedHead.getData().equals(element)) {
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
            if (head.getNext() == null){
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
        if(element == null){
            return -1;
        }
        if(isSorted == true && head.getNext() != null){
            Node updatedHead = head.getNext();
            int count = 0;
            while(updatedHead == null) {
                if (updatedHead.getData().compareTo(element) > 0) {
                    return -1;
                }else if(updatedHead.getData().compareTo(element) == 0){
                    return count;
                }else{
                    updatedHead.getNext();
                    count++;
                }
            }
        }
        if (head.getNext() == null) {
            return 0;
        } else {
            Node updatedHead = head.getNext();
            int count = 0;
            int lastIndex = 0;
            while (updatedHead != null) {
                if (updatedHead.getData().compareTo(element) == 0) {
                    lastIndex = count;
                    updatedHead = updatedHead.getNext();
                    count++;
                }else{
                    updatedHead = updatedHead.getNext();
                    count++;
                }
            }
            if(lastIndex == 0){
                return -1;
            }else {
                return lastIndex;
            }
        }
    }

    public T set(int index, T element) {
            /*
             * Replace the element at index with element and return the
             * element that was previously at index. If index is
             * out-of-bounds or element is null, do nothing with element
             * and return null.
             */
            if(head.getNext() == null || index >= this.size() || element == null || index < 0){
                return null;
            }else if(index == 0){
                Node swappedNode = head.getNext();
                T previousData = (T) swappedNode.getData();
                swappedNode.setData(element);
                return previousData;

            }else{
                int count = 1;
                Node updatedHead = head.getNext().getNext();

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


    public int size() {
        /*
         * Return the number of elements in the list. For example, if
         * 4 elements added to a list, size will return 4, while the
         * last index in the list will be 3.
         */
        if (head.getNext() == null) {
            return 0;
        } else {
            Node updatedHead = head.getNext();
            int size = 0;

            while (updatedHead != null) {
                updatedHead = updatedHead.getNext();
                size++;
            }
            return size;
        }
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
        Node current = head.getNext();
        Node trailer = head.getNext();
        Node temp;

        if (order == true && isSorted != true) {//sort  alphabetically
            while (current != null) {
                while (trailer != null) {
                    if (current.getData().compareTo(trailer.getData()) > 0) {
                        T currentData = (T) current.getData();
                        T trailerData = (T) trailer.getData();

                        current.setData(trailerData);
                        trailer.setData(currentData);
                    } else {
                        trailer = trailer.getNext();
                    }
                }
                current = current.getNext();
                trailer = current;
            }
        } else if(order != true || isSorted != true) {// reverse sort alphabetically
           /* for(int i = 0; i < sizeOfLL; i++){
                for(int j = 0; j < sizeOfLL; j++){
                    if(current.getNext() != null){*/
            //do while loops
            while (current != null) {
                while (trailer != null) {
                    if (current.getData().compareTo(trailer.getData()) < 0) {
                        T currentData = (T) current.getData();
                        T trailerData = (T) trailer.getData();

                        current.setData(trailerData);
                        trailer.setData(currentData);
                    } else {
                        trailer = trailer.getNext();
                    }
                }
                current = current.getNext();
                trailer = current;
            }
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
        if (head.getNext() == null){
            return false;
        }
        Node updatedHead = head.getNext();
        Node trailer = head;
        while(updatedHead != null){
            if(updatedHead.getNext() == null && updatedHead.getData().equals(element)){
                updatedHead = null;
                trailer.setNext(null);
                return true;
            }else if(updatedHead.getData().equals(element)){
                trailer.setNext(updatedHead.getNext());
                return true;
            }else{
                trailer = trailer.getNext();
                updatedHead = updatedHead.getNext();
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
        if(head.getNext() == null ||index >= this.size() || index < 0){
            return null;
        }else if(index == 0) {
            T dataOfIndex1 = (T) head.getNext().getData();
            head.setNext(head.getNext().getNext());
            return dataOfIndex1;
        }else {
            Node updatedHead = head.getNext();
            Node trailer = head;
            int count = 0;
            while (count != index) {
                trailer = trailer.getNext();
                updatedHead = updatedHead.getNext();
                count++;
            }
                if (index == this.size() && count == index) {
                    T lastIndex = (T) updatedHead.getData();
                    trailer.setNext(null);
                    return lastIndex;

                } else if (count == index) {
                    Node newPTR = updatedHead.getNext();
                    trailer.setNext(newPTR);
                    return (T) updatedHead.getData();
                }
            }
        return null;
    }

    public String toString(){
            String result = "";
            Node currNode = head.getNext();
            int currIndex = 0;
            while( currNode != null ){
                result += currNode.getData();
                currNode = currNode.getNext();
                currIndex++;

                // Only add a comma if this isn't the last element
                if( currNode != null ){ result += ", "; }
            }
            return result;
    }

    public static void main(String[] args) {
    }
}
