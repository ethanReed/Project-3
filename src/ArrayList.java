public class ArrayList<T extends Comparable<T>> implements List<T> {
    private T[] a;
    private int next;
    private boolean isSorted;

    public ArrayList() {
        a = (T[]) new Comparable[2];
        next = 0;
        isSorted = true;
    }

    /*
     * Adds an element to end of the list. If element is null,
     * it will NOT add it and return false.  Otherwise, it
     * will add it and return true. Updates isSorted to false.
     */
    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        /* if the array is full, it will double the length of the original array,
         * copying over everything from the original array to the new array,
         * and setting the instance variable to the new array.
         */
        else if (next == a.length) {
            int newA = a.length * 2;
            T[] b = (T[]) new Comparable[newA];
            int i = 0;
            while (i < a.length) {
                b[i] = a[i];
                i++;
            }
            a = b;
        }
        a[next] = element;
        next += 1;
        isSorted = false;
        return true;
    }

    /*
     *  Adds an element at specific index and
     * shifts the element currently at that position (if
     * any) and any subsequent elements to the right (adds
     * one to their indices). If element is null, or if index
     * index is out-of-bounds (index < 0 or index >= size_of_list),
     * it will NOT add it and return false. Otherwise it will
     * add it and return true. Also updates isSorted variable to false.
     */
    public boolean add(int index, T element) {
        if (index < 0 || index >= size() || element == null) {
            return false;
        }
        /* if the array is full, it will double the length of the original array,
         * copying over everything from the original array to the new array,
         * and setting the instance variable to the new array.
         */
        else if (size() == a.length) {
            int newLength = size() * 2;
            T[] b = (T[]) new Comparable[newLength];
            int i = 0;
            while (i < a.length) {
                b[i] = a[i];
                i++;
            }
            a = b;
        }
        int i;
        // shifts elements in the position to the right side
        for(i = a.length - 1; i > index; i--) {
            a[i] = a[i - 1];
        }
        a[index] = element;
        index += 1;
        isSorted = false;
        return true;
    }

    /*
     * Remove all elements from the list.
     */
    public void clear() {
        next = 0;
        for (int i = 0; i < a.length; i++) {
            a[i] = null;
        }
        isSorted = false;
    }

    /*
     * Returns true if element is in the list and false
     * otherwise. If isSorted is true, uses the ordering
     * of the list to increase the efficiency of the search.
     */
    public boolean contains(T element) {
        int i;
        for (i = 0; i < next; i++) {
            if (a[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Returns the element at given index. If index is
     * out-of-bounds, it will return null.
     *
     */
    public T get(int index) {
        if (index > a.length || index < 0) {
            return null;
        }
        else {
            return a[index];
        }
    }

    /*
     * Returns the first index of element in the list. If element
     * is null or not found in the list, return -1. If isSorted is
     * true, uses the ordering of the list to increase the efficiency
     * of the search.
     */
    public int indexOf(T element) {
        int i;
        for (i = 0; i < next; i++) {
            if (a[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Return true if the list is empty and false otherwise.
     */
    public boolean isEmpty() {
        if (next == 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Same as indexOf(), except it will return the last index
     * of element. If isSorted is true, uses the ordering
     * of the list to increase the efficiency of the search.
     */
    public int lastIndexOf(T element) {
        int last = 0;
        int i;
        for (i = a.length - 1; i >= 0; i--) {
            if (a[i] != null && a[i].equals(element)) {
                return i;
            } else if (isSorted == true && a[i].compareTo(element) < 0) {
                return -1;
            }
        }
        return -1;
    }

    /*
     * Replaces the element at index with element and returns the
     * element that was previously at index. If index is
     * out-of-bounds or element is null, do nothing with element
     * and return null.
     */
    public T set(int index, T element) {
        if (index < 0 || index > a.length || element == null) {
            return null;
        }
        T original = a[index];
        a[index] = element;
        isSorted = false;
        return original;
    }

    /*
     * Returns the number of elements in the list. For example, if
     * 4 elements added to a list, size will return 4, while the
     * last index in the list will be 3.
     */
    public int size() {
        int i;
        int count = 0;
        for(i=0; i < a.length; i++) {
            if (a[i] == null) {
                return count;
            }
        count++;
        }
        return count;
    }

    /*
     * Uses a bubble sort to sort the elements of the list. If order is true, sorts the
     * list in increasing (alphabeticaly) order and sets isSorted to true. If order is
     * false, sorts the list in decreasing (reverse alphabetical)
     * order.
     */
    public void sort(boolean order) {
       int i, j;
       T temp;
       boolean swapped = true;
       int counter = 0;
       if (order == true) {
           for (i = 0; i < a.length && swapped == true; i++) {
               swapped = false;
               for (j = 1; j < a.length - i; j++) {
                   counter++;
                   if (a[j].compareTo(a[j - 1]) < 0) {
                       swapped = true;
                       temp = a[j];
                       a[j] = a[j - 1];
                       a[j - 1] = temp;
                   }
               }
           }
           isSorted = true;
       }
       else {
           for (i = 0; i < a.length && swapped == true; i++) {
               swapped = false;
               for (j = 1; j < a.length - i; j++) {
                   counter++;
                   if (a[j].compareTo(a[j - 1]) > 0) {
                       swapped = true;
                       temp = a[j];
                       a[j] = a[j - 1];
                       a[j - 1] = temp;
                   }
               }
           }
           isSorted = false;
       }
   }


    /*
     * Removes the first instance of element from the list
     * and also shifts any subsequent elements to the
     * left (subtracts one from their indices). If successful,
     * returns true. If element is not found in the list, returns
     * false.
     */
    public boolean remove(T element){
        int i;
        for (i = 0; i < next; i++) {
            if (a[i].equals(element)) {
                remove(i);
                int j;
                for(j = a.length - 1; j < i; j--) {
                    a[j] = a[j - 1];
                }
                isSorted = false;
                return true;
            }
        }
        return false;
    }

    /*
     * Remove whatever is at index index in the list and returns
     * it. If index is out-of-bounds, returns null. Shifts the
     * indices as in the other remove.
     */
    public T remove(int index) {
        if (index < 0 || index >= a.length) {
            return null;
        }
        else {
            T holder = a[index];
            for (int i = index; i < a.length - 1; i++) {
                a[i] = a[i+1];
            }
            a[a.length-1] = null;
            isSorted = false;
            return holder;
        }
    }

    /*
     * The format of the toString will appear as follows:
     * Element1
     * Element2
     * .
     * .
     * .
     * Elementn
     */
    public String toString() {
        String output = "";
        int i;
        for(i=0; i<a.length;i++) {
            output += a[i].toString() + "\n";
        }
        return output;
    }
}