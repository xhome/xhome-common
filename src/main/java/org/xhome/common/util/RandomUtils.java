package org.xhome.common.util;

/**
 * @project xhome-util
 * @author jhat
 * @email cpf624@126.com
 * @date Feb 4, 20135:58:13 PM
 */
public final class RandomUtils {
	
	private RandomUtils() {}
	
	private final static java.util.Random	rand							= new java.util.Random();
	
	public static final int					RANDOM_STRING_NUMBER			= 0;						// 仅数字
	public static final int					RANDOM_STRING_CHAR				= 1;						// 仅字符
	public static final int					RANDOM_STRING_CHAR_UPPER		= 2;						// 仅字符(大写)
	public static final int					RANDOM_STRING_CHAR_LOWER		= 3;						// 仅字符(小写)
	public static final int					RANDOM_STRING_CHAR_NUMBER		= 4;						// 字符和数字
	public static final int					RANDOM_STRING_CHAR_NUMBER_UPPER	= 5;						// 字符(大写)和数字
	public static final int					RANDOM_STRING_CHAR_NUMBER_LOWER	= 6;						// 字符(小写)和数字
	public static final int					RANDOM_STRING					= 7;
	
	/**
	 * 随机字符窜
	 * 
	 * @param length
	 *            字符窜长度
	 * @param STRING_TYPE
	 *            字符串类型
	 * @return
	 */
	public static String randomString(int length, int STRING_TYPE) {
		if (length <= 0) return "";
		char randChars[] = new char[length];
		switch (STRING_TYPE) {
			case RANDOM_STRING_NUMBER:
				while (length > 0) {
					randChars[--length] = (char) randomInt(48, 57);
				}
				break;
			case RANDOM_STRING_CHAR:
				while (length > 0) {
					int randNum = randomInt(65, 90);
					randChars[--length] = randNum % 2 == 0 ? (char) randNum
							: (char) (randNum + 32);
				}
				break;
			case RANDOM_STRING_CHAR_UPPER:
				while (length > 0) {
					randChars[--length] = (char) randomInt(65, 90);
				}
				break;
			case RANDOM_STRING_CHAR_LOWER:
				while (length > 0) {
					randChars[--length] = (char) randomInt(97, 122);
				}
				break;
			case RANDOM_STRING_CHAR_NUMBER:
				while (length > 0) {
					int randNum = randomInt(3);
					randNum = randNum == 0 ? randomInt(48, 57)
							: randNum == 1 ? randomInt(65, 90) : randomInt(97,
									122);
					randChars[--length] = (char) randNum;
				}
				break;
			case RANDOM_STRING_CHAR_NUMBER_UPPER:
				while (length > 0) {
					randChars[--length] = randomInt() % 2 == 0 ? (char) randomInt(
							48, 57) : (char) randomInt(65, 90);
				}
				break;
			case RANDOM_STRING_CHAR_NUMBER_LOWER:
				while (length > 0) {
					randChars[--length] = randomInt() % 2 == 0 ? (char) randomInt(
							48, 57) : (char) randomInt(97, 122);
				}
				break;
			default:
				while (length > 0) {
					randChars[--length] = (char) randomInt();
				}
				break;
		}
		return new String(randChars);
	}
	
	/**
	 * 随机字符串
	 * 
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public static String randomString(int length) {
		return randomString(length, RANDOM_STRING_CHAR_NUMBER);
	}
	
	/**
	 * 随机字符串
	 * 
	 * @return
	 */
	public static String randomString() {
		return randomString(randomInt(), RANDOM_STRING_CHAR_NUMBER);
	}
	
	public static void randomBytes(byte[] bytes) {
		rand.nextBytes(bytes);
	}
	
	public static double randomGaussian() {
		return rand.nextGaussian();
	}
	
	public static double randomDouble(double start, double end) {
		double rd = randomDouble(end);
		while (rd < start)
			rd = randomDouble(end);
		return rd;
	}
	
	public static double randomDouble(double n) {
		return rand.nextDouble() % n;
	}
	
	public static double randomDouble() {
		return rand.nextDouble();
	}
	
	public static boolean randomBoolean() {
		return rand.nextBoolean();
	}
	
	public static long randomLong(long start, long end) {
		long rl = randomLong(end);
		while (rl < start)
			rl = randomLong(end);
		return rl;
	}
	
	public static long randomLong(long n) {
		return rand.nextLong() % n;
	}
	
	public static long randomLong() {
		return rand.nextLong();
	}
	
	public static float randomFloat(float start, float end) {
		float rf = randomFloat(end);
		while (rf < start)
			rf = randomFloat(end);
		return rf;
	}
	
	public static float randomFloat(float n) {
		return rand.nextFloat() % n;
	}
	
	public static float randomFloat() {
		return rand.nextFloat();
	}
	
	/**
	 * 介于start和end之间的随机数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int randomInt(int start, int end) {
		int rn = rand.nextInt(end);
		while (rn < start)
			rn = rand.nextInt(end);
		return rn;
	}
	
	public static int randomInt() {
		return rand.nextInt();
	}
	
	public static int randomInt(int n) {
		return rand.nextInt(n);
	}
	
	public static void setSeed(long seed) {
		rand.setSeed(seed);
	}
	
}
