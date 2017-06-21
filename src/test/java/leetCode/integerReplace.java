package leetCode;

public class integerReplace {
	static volatile int count2 = 1;

	public static void main(String[] args) {
		/*long startMili = System.currentTimeMillis();
		int result = integerReplacement(2147483647);
		long MiddleMili = System.currentTimeMillis();

		System.out.println("第一个函数运行时间：" + (MiddleMili - startMili));
		
		
		long startMili2 = System.currentTimeMillis();
		int result2 = integerReplacement2(2147483647);
		long MiddleMili2 = System.currentTimeMillis();
		System.out.println("第二个函数运行时间：" + (MiddleMili2 - startMili2));*/
		
		/*
		 * int n=2147483647; if (1 == (1 & n)) { n--; } System.out.println();
		 */
		
		int result = integerReplacement(65535);
		System.out.println(result);
	}

	public static int integerReplacement(int n) {
		int count = 0;
		while (n != 1) {
			if (1 == (1 & n)) {
				n--;
			} else {
				n = n >> 1;
			}
			count++;
		}
		return count;
	}

	public static int integerReplacement2(int n) {

		if (n == 1) {
			return count2;
		}
		if (1 == (1 & n)) {
			n--;
		} else {
			n = n >> 1;
		}
		count2++;
		return integerReplacement(n);

	}
}
