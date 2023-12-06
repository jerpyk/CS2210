public class Trisearch {

	public static void main(String[] args) {
	    int[] arr = {20, 15, 10, 7, 7, 3, 2, 1};
	    int x = 1;
	    int result = triSearch(arr, 0, arr.length - 1, x);

	    System.out.println(2/3);
	    if (result != -1) {
	        System.out.println("Element " + x + " found at index " + result);
	    } else {
	        System.out.println("Element " + x + " not found in the array.");
	    }
	}

	public static int triSearch(int[] L, int first, int last, int x) {
		if (first > last) {
			return -1;
		} else {
			int numValues = last - first + 1;
			int third = first + numValues / 3;

			if (x == L[third]) {
				return third;
			} else if (x > L[third]) {
				return triSearch(L, first, third - 1, x);
			} else {
				int twoThird = first + 2 * (numValues / 3);

				if (x == L[twoThird]) {
					return twoThird;
				} else if (x > L[twoThird]) {
					return triSearch(L, third + 1, twoThird - 1, x);
				} else {
					return triSearch(L, twoThird + 1 , last, x);
				}
			}
		}
	}
	
	

}
