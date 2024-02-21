
public class IncorrectTrySearch {

	public static int triSearch(int[] L, int first, int last, int x) {
		if (first > last) {
			return -1;
		} else {
			int numValues = last - first + 1;
			int third = first + numValues / 3;

			if (x == L[third]) {
				return third;
			} else if (x < L[third]) {
				return triSearch(L, first, third, x);
			} else {
				int twoThird = first + 2 * (numValues / 3);

				if (x == L[twoThird]) {
					return twoThird;
				} else if (x < L[twoThird]) {
					return triSearch(L, third, twoThird, x);
				} else {
					return triSearch(L, twoThird, last, x);
				}
			}
		}
	}

	public static int copies(int[] L, int n, int x) {
		int c = (x == L[0]) ? 1 : 0; // Initialize c based on the first element

		for (int i = 0; i < n; i++) {
			if (x == L[i]) {
				c++; // Increment c for each occurrence of x
			}
		}

		return c; // Return the count of occurrences of x
	}

	public static void main(String[] args) {
		int[] arr = { 2, 3, 4 };
		int x = 5;
		int count = triSearch(arr, 0, arr.length - 1, x);
		System.out.println(count);
	}

}
