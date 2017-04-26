package com.rojao.tvlive.network.backlookdetail;

/**
 * filename: FormatTransfer.java
 */

/**
 * 高�?低字节之间的转换
 * 
 * @author Sheldon Chen
 * @createdDate: Nov 1, 2010
 * @createdTime: 11:22:11 AM
 * 
 */
public class FormatTransfer {

	/**
	 * 从byte数组中，截取子byte数组
	 * 
	 * @param n
	 * @return
	 */
	public static byte[] subBytes(byte[] array, int offset, int count) {
		if (offset < 0 || count <= 0) {
			return null;
		} else if (null == array || array.length == 0) {
			return null;
		} else if (offset > array.length || (offset + count) > array.length) {
			return null;
		} else {
			byte[] newArray = new byte[count];
			for (int i = offset; i < offset + count; i++) {
				newArray[i - offset] = array[i];
			}
			return newArray;
		}
	}

	/**
	 * 将int转为低字节在前，高字节在后的byte数组
	 * 
	 * @param n
	 * @return
	 */
	public static byte[] toLH(int n) {
		byte[] b = new byte[4];
		b[0] = (byte) (n & 0xff);
		b[1] = (byte) (n >> 8 & 0xff);
		b[2] = (byte) (n >> 16 & 0xff);
		b[3] = (byte) (n >> 24 & 0xff);
		return b;
	}

	/**
	 * 将int转为高字节在前，低字节在后的byte数组
	 * 
	 * @param n
	 * @return
	 */
	public static byte[] toHH(int n) {
		byte[] b = new byte[4];
		b[3] = (byte) (n & 0xff);
		b[2] = (byte) (n >> 8 & 0xff);
		b[1] = (byte) (n >> 16 & 0xff);
		b[0] = (byte) (n >> 24 & 0xff);
		return b;
	}

	/**
	 * 将short转为低字节在前，高字节在后的byte数组
	 * 
	 * @param n
	 * @return
	 */
	public static byte[] toLH(short n) {
		byte[] b = new byte[2];
		b[0] = (byte) (n & 0xff);
		b[1] = (byte) (n >> 8 & 0xff);
		return b;
	}

	/**
	 * 将short转为高字节在前，低字节在后的byte数组
	 * 
	 * @param n
	 * @return
	 */
	public static byte[] toHH(short n) {
		byte[] b = new byte[2];
		b[1] = (byte) (n & 0xff);
		b[0] = (byte) (n >> 8 & 0xff);
		return b;
	}

	/**
	 * 将float转为低字节在前，高字节在后的byte数组
	 */
	public static byte[] toLH(float f) {
		return toLH(Float.floatToRawIntBits(f));
	}

	/**
	 * 将float转为高字节在前，低字节在后的byte数组
	 */
	public static byte[] toHH(float f) {
		return toHH(Float.floatToRawIntBits(f));
	}

	/**
	 * 将String转为byte数组
	 */
	public static byte[] stringToBytes(String s, int length) {
		while (s.getBytes().length < length) {
			s += " ";
		}
		return s.getBytes();
	}

	/**
	 * 将字节数组转换为String
	 * 
	 * @param b
	 * @return
	 */
	public static String bytesToString(byte[] b) {
		StringBuffer result = new StringBuffer("");
		int length = b.length;
		for (int i = 0; i < length; i++) {
			char c = (char) (b[i] & 0xff);
			if (c != '\0') {
				result.append(c);
			}
		}
		return result.toString();
	}

	/**
	 * 将字符串转换为byte数组
	 * 
	 * @param s
	 * @return
	 */
	public static byte[] stringToBytes(String s) {
		return s.getBytes();
	}

	/**
	 * 将高字节数组转换为int
	 * 
	 * @param b
	 * @return
	 */
	public static int hBytesToInt(byte[] b) {
		int s = 0;
		for (int i = 0; i < 3; i++) {
			if (b[i] >= 0) {
				s = s + b[i];
			} else {
				s = s + 256 + b[i];
			}
			s = s * 256;
		}
		if (b[3] >= 0) {
			s = s + b[3];
		} else {
			s = s + 256 + b[3];
		}
		return s;
	}

	/**
	 * 将低字节数组转换为int
	 * 
	 * @param b
	 * @return
	 */
	public static int lBytesToInt(byte[] b) {
		int s = 0;
		for (int i = 0; i < 3; i++) {
			if (b[3 - i] >= 0) {
				s = s + b[3 - i];
			} else {
				s = s + 256 + b[3 - i];
			}
			s = s * 256;
		}
		if (b[0] >= 0) {
			s = s + b[0];
		} else {
			s = s + 256 + b[0];
		}
		return s;
	}

	/**
	 * 高字节数组到short的转�?
	 * 
	 * @param b
	 * @return
	 */
	public static short hBytesToShort(byte[] b) {
		int s = 0;
		if (b[0] >= 0) {
			s = s + b[0];
		} else {
			s = s + 256 + b[0];
		}
		s = s * 256;
		if (b[1] >= 0) {
			s = s + b[1];
		} else {
			s = s + 256 + b[1];
		}
		short result = (short) s;
		return result;
	}

	/**
	 * 低字节数组到short的转�?
	 * 
	 * @param b
	 * @return
	 */
	public static short lBytesToShort(byte[] b) {
		int s = 0;
		if (b[1] >= 0) {
			s = s + b[1];
		} else {
			s = s + 256 + b[1];
		}
		s = s * 256;
		if (b[0] >= 0) {
			s = s + b[0];
		} else {
			s = s + 256 + b[0];
		}
		short result = (short) s;
		return result;
	}

	/**
	 * 高字节数组转换为float
	 * 
	 * @param b
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static float hBytesToFloat(byte[] b) {
		int i = 0;
		Float f = new Float(0.0);
		i = ((((b[0] & 0xff) << 8 | (b[1] & 0xff)) << 8) | (b[2] & 0xff)) << 8 | (b[3] & 0xff);
		return f.intBitsToFloat(i);
	}

	/**
	 * 低字节数组转换为float
	 * 
	 * @param b
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static float lBytesToFloat(byte[] b) {
		int i = 0;
		Float F = new Float(0.0);
		i = ((((b[3] & 0xff) << 8 | (b[2] & 0xff)) << 8) | (b[1] & 0xff)) << 8 | (b[0] & 0xff);
		return F.intBitsToFloat(i);
	}

	/**
	 * 将byte数组中的元素倒序排列
	 * 
	 * @param b
	 * @return
	 */
	public static byte[] bytesReverseOrder(byte[] b) {
		int length = b.length;
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++) {
			result[length - i - 1] = b[i];
		}
		return result;
	}

	/**
	 * 打印byte数组
	 */
	public static void printBytes(byte[] bb) {
		int length = bb.length;
		for (int i = 0; i < length; i++) {
			//System.out.print(bb + " ");
		}
		//System.out.println("");
	}

	public static void logBytes(byte[] bb) {
		int length = bb.length;
		String out = "";
		for (int i = 0; i < length; i++) {
			out = out + bb + " ";
		}
	}

	/**
	 * 将int类型的�?转换为字节序颠�?过来对应的int�?
	 * 
	 * @param i
	 * @return
	 */
	public static int reverseInt(int i) {
		int result = FormatTransfer.hBytesToInt(FormatTransfer.toLH(i));
		return result;
	}

	/**
	 * 将short类型的�?转换为字节序颠�?过来对应的short�?
	 * 
	 * @param s
	 * @return
	 */
	public static short reverseShort(short s) {
		short result = FormatTransfer.hBytesToShort(FormatTransfer.toLH(s));
		return result;
	}

	/**
	 * 将float类型的�?转换为字节序颠�?过来对应的float�?
	 * 
	 * @param f
	 * @return
	 */
	public static float reverseFloat(float f) {
		float result = FormatTransfer.hBytesToFloat(FormatTransfer.toLH(f));
		return result;
	}

	/**
	 * 长整型字节高低位转换后转换为byte数组
	 */
	public static byte[] reverseLong(long l) {
		long num = Long.reverseBytes(l);
		byte[] b = new byte[8];
		for (int i = b.length - 1; i > -1; i--) {
			b[i] = new Long(num & 0xff).byteValue();
			num = num >> 8;
		}
		return b;
	}

	/**
	 * byte转long
	 * 
	 * @param bb
	 * @return
	 */
	public static long toLong(byte[] bb) {
		long r = 0;
		for (int i = bb.length - 1; i >= 0; i--) {
			r <<= 8;
			r |= (bb[i] & 0x000000000ff);
		}
		return r;
	}

	public static int bytes2Integer(byte[] byteVal) {
		int result = 0;
		for (int i = 0; i < byteVal.length; i++) {
			int tmpVal = (byteVal[i] << (8 * (3 - i)));
			switch (i) {
			case 0:
				tmpVal = tmpVal & 0xFF000000;
				break;
			case 1:
				tmpVal = tmpVal & 0x00FF0000;
				break;
			case 2:
				tmpVal = tmpVal & 0x0000FF00;
				break;
			case 3:
				tmpVal = tmpVal & 0x000000FF;
				break;
			}
			result = result | tmpVal;
		}
		return result;
	}

	public static void main(String[] args) {
		long l = 1;
		//System.out.println(Long.reverseBytes(l));
	}

}
