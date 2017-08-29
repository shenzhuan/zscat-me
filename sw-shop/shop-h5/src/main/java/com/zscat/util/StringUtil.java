package com.zscat.util;

public class StringUtil {

	public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
		if ((str1 == null) || (str2 == null))
			return (str1 == str2);
		if (str1 == str2)
			return true;
		if (str1.length() != str2.length()) {
			return false;
		}
		return regionMatches(str1, true, 0, str2, 0,
				str1.length());
	}


	public static String trim(String str) {
		return ((str == null) ? null : str.trim());
	}
	static boolean regionMatches(CharSequence cs, boolean ignoreCase,
			int thisStart, CharSequence substring, int start, int length) {
		if ((cs instanceof String) && (substring instanceof String)) {
			return ((String) cs).regionMatches(ignoreCase, thisStart,
					(String) substring, start, length);
		}
		int index1 = thisStart;
		int index2 = start;
		int tmpLen = length;

		while (tmpLen-- > 0) {
			char c1 = cs.charAt(index1++);
			char c2 = substring.charAt(index2++);

			if (c1 == c2) {
				continue;
			}

			if (!(ignoreCase)) {
				return false;
			}

			if ((Character.toUpperCase(c1) != Character.toUpperCase(c2))
					&& (Character.toLowerCase(c1) != Character.toLowerCase(c2))) {
				return false;
			}
		}

		return true;
	}
	 public static boolean isBlank(String str) {
	        int strLen;
	        if(str != null && (strLen = str.length()) != 0) {
	            for(int i = 0; i < strLen; ++i) {
	                if(!Character.isWhitespace(str.charAt(i))) {
	                    return false;
	                }
	            }
	            return true;
	        } else {
	            return true;
	        }
	    }


	public static boolean isBlank(Long str) {
        if(str != null) {
            return false;
        } else {
            return true;
        }
	}


	public static boolean isNoneBlank(String str) {
		 if(str != null) {
	            return true;
	        } else {
	            return false;
	        }
	}
}
