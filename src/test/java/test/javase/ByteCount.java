package test.javase;

public class ByteCount {

	public static void main(String[] args) {

		/*byte a = 127;		
		int b = (a<<2);
		byte c = (byte)b;
		byte d =-4;
		
		System.out.println(b);
		System.out.println(Integer.toBinaryString(b));
		System.out.println(c);
		System.out.println(Integer.toBinaryString(d));*/
		
		
		byte a;
		byte b;
		byte c;
		a = 127;
		b = 127;
		c = 127;
		a <<= 2;
		System.out.println(a);
		System.out.println(b <<= 2);
		System.out.println(c << 2);
	}

}
