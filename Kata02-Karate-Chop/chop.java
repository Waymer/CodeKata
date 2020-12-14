import java.util.Scanner; 

class Chop {
    public static void main(String[] args) {
        Chop search = new Chop();

        // Empty array
        assert search.binarySearch(new int[]{}, 3) == -1;
        assert search.binarySearchR(new int[]{}, 3) == -1;

        assert search.binarySearch(new int[]{1}, 3) == -1;
        assert search.binarySearchR(new int[]{1}, 3) == -1;

        assert search.binarySearch(new int[]{1,2,3}, 3) == 2;
        assert search.binarySearchR(new int[]{1,2,3}, 3) == 2;

        assert search.binarySearch(new int[]{1,2,3}, 1) == 0;
        assert search.binarySearchR(new int[]{1,2,3}, 1) == 0;

        assert search.binarySearch(new int[]{1,2,3}, 2) == 1;
        assert search.binarySearchR(new int[]{1,2,3}, 2) == 1;

        assert search.binarySearch(new int[]{1,2,3,4,7,9,11,12}, 3) == 2;
        assert search.binarySearchR(new int[]{1,2,3,4,7,9,11,12}, 3) == 2;

        assert search.binarySearch(new int[]{1,2,3,4,7,9,11,12}, 13) == -1;
        assert search.binarySearchR(new int[]{1,2,3,4,7,9,11,12}, 13) == -1;

        assert search.binarySearch(new int[]{1,2,3,4,7,9,11,12}, 0) == -1;
        assert search.binarySearchR(new int[]{1,2,3,4,7,9,11,12}, 0) == -1;

        assert search.binarySearch(new int[]{1,2,3,4,7,9,11,12}, 12) == 7;
        assert search.binarySearchR(new int[]{1,2,3,4,7,9,11,12}, 12) == 7;

        assert search.binarySearch(new int[]{1,2,3,4,7,9,11,12}, 1) == 0;
        assert search.binarySearchR(new int[]{1,2,3,4,7,9,11,12}, 1) == 0;

        assert search.binarySearch(new int[]{1,2,3,4,7,9,11,12}, 9) == 5;
        assert search.binarySearchR(new int[]{1,2,3,4,7,9,11,12}, 9) == 5;

        assert search.binarySearch(new int[]{1,2,3,4,7,9,11,12}, 4) == 3;
        assert search.binarySearchR(new int[]{1,2,3,4,7,9,11,12}, 4) == 3;

        assert search.binarySearch(new int[]{1,2,3,4,7,9,11,12}, 7) == 4;
        assert search.binarySearchR(new int[]{1,2,3,4,7,9,11,12}, 7) == 4;

        System.out.println("Passed all tests");
    }

    int binarySearch(int arr[], int x)
    {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
  
            if (x > arr[middle]) {
                left = middle + 1;
            }
            if (x < arr[middle]) {
                right = middle - 1;
            }
            else {
                return middle;
            }
        }
        return -1; 
    } 

    int binarySearchR(int arr[], int x) {
        return binarySearchRecursiveHelper(arr, 0, arr.length - 1, x);
    }

    int binarySearchRecursiveHelper(int arr[], int left, int right, int x)
    {
        int middle = (left + right) / 2;
        if (left < right) {
            return -1;
        }
        if (x < arr[middle]) {
            return binarySearchRecursiveHelper(arr, left, middle - 1, x);
        }
        if (x > arr[middle]) {
            return binarySearchRecursiveHelper(arr, middle + 1, right, x);
        }
        if (x == arr[middle]) {
            return middle;
        }
        return -1;
    } 
}
