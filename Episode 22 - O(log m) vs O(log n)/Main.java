class Main {

    static int search(int[] arr, int x) {
        if (x > arr[0]) return -1;

        int left = 0;
        int right = 2;
        while (arr[right] > x) { //move right pointer until x is greater or equal to arr[right]
            left = right; //move left pointer to current right pointer since array is sorted in desc
            right <<= 2;
        }

        while (left <= right) { //binsearch for x
            int mid = (left + right) / 2;
            if (arr[mid] > x) left = mid + 1;
            else if (arr[mid] < x) right = mid - 1;
            else return mid;
        }

        return -1; //not found
    }

    public static void main(String[] args) {
        int[] arr = {13, 12, 10, 5, 4, 3, 1, 0, 0, 0, 0, 0};
        System.out.println(search(arr, 1));
    }
}
