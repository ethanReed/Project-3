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
     * Add an element to end of the list. If element is null,
     * it will NOT add it and return false.  Otherwise, it
     * will add it and return true. Updates isSorted to false.
     */
    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        else if (next == a.length) {
            int newA = a.length * 2;
            T[] b = (T[]) new Comparable[newA];
            int i = 0;
            while (i < a.length) {
                b[i] = a[i];
            }
            a = b;
        }
        a[next] = element;
        next += 1;
        isSorted = false;
        return true;
    }

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
    public boolean add(int index, T element) {
        if (index < 0 || index >= a.length) {
            int newLength = a.length * 2;
            T[] b = (T[]) new Comparable[newLength];
            int i = 0;
            while (i < a.length) {
                b[i] = a[i];
            }
            a = b;
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
    }

    /*
     * Return true if element is in the list and false
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
     *  Return the element at given index. If index is
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
     * Return the first index of element in the list. If element
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
            if (a[i].equals(element)) {
                return i;
            } else if (isSorted == true && a[i].compareTo(element) < 0) {
                return -1;
            }
        }
        return -1;
    }

    /*
     * Replace the element at index with element and return the
     * element that was previously at index. If index is
     * out-of-bounds or element is null, do nothing with element
     * and return null.
     */
    public T set(int index, T element) {
        if (index < 0 || index > a.length) {
            return null;
        }
        T original = a[index];
        a[index] = element;
        return original;
    }

    /*
     * Return the number of elements in the list. For example, if
     * 4 elements added to a list, size will return 4, while the
     * last index in the list will be 3.
     */
    public int size() {
        return a.length;
    }

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
       }
   }


    /*
     * Remove the first instance of element from the list. This
     * method should also shifts any subsequent elements to the
     * left (subtracts one from their indices). If successful,
     * return true. If element is not found in the list, return
     * false.
     */
    public boolean remove(T element){
        int i;
        for (i = 0; i < next; i++) {
            if (a[i].equals(element)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /*
     * Remove whatever is at index index in the list and return
     * it. If index is out-of-bounds, return null. Shift the
     * indices as in the other remove.
     */
    public T remove(int index) {
        if (index < a.length || index > a.length) {
            return null;
        }
        else {
            T holder = a[index];
            for (int i = index; i < size() - 1; i++) {
                a[i] = a[i+1];
            }
            a[a.length-1] = null;
            return holder;
        }
    }

    /*
     * Note that this method exists for debugging purposes.
     * It calls the toString method of the underlying elements in
     * the list in order to create a String representation of the list.
     * The format of the toString should appear as follows:
     * Element1
     * Element2
     * .
     * .
     * .
     * Elementn
     * Watch out for extra spaces or carriage returns. Each element
     * should be printed on its own line.
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