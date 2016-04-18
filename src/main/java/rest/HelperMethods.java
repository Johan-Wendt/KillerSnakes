package rest;

public final class HelperMethods {
	

	public static int[] intConcatenator(int[] array1, int[] array2) {
	int[] array1and2 = new int[array1.length + array2.length];
	System.arraycopy(array1, 0, array1and2, 0, array1.length);
	System.arraycopy(array2, 0, array1and2, array1.length, array2.length);
	return array1and2;
	}

}
