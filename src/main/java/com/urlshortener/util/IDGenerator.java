package com.urlshortener.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Felipe
 *
 * Responsible to generate shortener id Base62
 * We create a Map with reference to base62(all alphabet possibilities)
 * 1) A lower case alphabet [‘a’ to ‘z’], total 26 characters
 * 2) An upper case alphabet [‘A’ to ‘Z’], total 26 characters
 * 3) A digit [‘0’ to ‘9’], total 10 characters 
 * also convert base64 to base10 or inverse 
 * 
 * So using the id 1234 will replace to t4
 * Getting the decimal int 
 * Ej. 1234 % 64 = 56 save 56 index is 4
 * 1234 / 64 = 19  19 % 64 = 19 save 19 index is t
 * 19 / 64 = 0 
 * 
 */
public class IDGenerator {
    
    private static final String HTTP_URL_SUFFIX = "http://gaio/";

	static {
        initializeCharToIndexTable();
        initializeIndexToCharTable();
    }

    private static HashMap<Character, Integer> charToIndexTable;
    private static List<Character> indexToCharTable;

    private static void initializeCharToIndexTable() {
        charToIndexTable = new HashMap<>();
        // 0->a, 1->b, ..., 25->z, ..., 52->0, 61->9
        for (int i = 0; i < 26; ++i) {
            char c = 'a';
            c += i;
            charToIndexTable.put(c, i);
        }
        for (int i = 26; i < 52; ++i) {
            char c = 'A';
            c += (i-26);
            charToIndexTable.put(c, i);
        }
        for (int i = 52; i < 62; ++i) {
            char c = '0';
            c += (i - 52);
            charToIndexTable.put(c, i);
        }
    }

    private static void initializeIndexToCharTable() {
        // 0->a, 1->b, ..., 25->z, ..., 52->0, 61->9
        indexToCharTable = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            char c = 'a';
            c += i;
            indexToCharTable.add(c);
        }
        for (int i = 26; i < 52; ++i) {
            char c = 'A';
            c += (i-26);
            indexToCharTable.add(c);
        }
        for (int i = 52; i < 62; ++i) {
            char c = '0';
            c += (i - 52);
            indexToCharTable.add(c);
        }
    }

    public static Long createUniqueId() {
		try {
			return Math.abs(SecureRandom.getInstance("SHA1PRNG").nextLong());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    
    public static String createUniqueUrlID(Long id) {
        List<Integer> base62ID = convertBase10ToBase62ID(id);
        StringBuilder uniqueURLID = new StringBuilder();
        for (int digit: base62ID) {
            uniqueURLID.append(indexToCharTable.get(digit));
        }
        return uniqueURLID.toString();
    }

    private static List<Integer> convertBase10ToBase62ID(Long id) {
        List<Integer> digits = new LinkedList<>();
        while(id > 0) {
            int remainder = (int)(id % 62);
            ((LinkedList<Integer>) digits).addFirst(remainder);
            id /= 62;
        }
        return digits;
    }

    public static Long getDictionaryKeyFromUniqueID(String uniqueID) {
        List<Character> base62IDs = new ArrayList<>();
        for (int i = 0; i < uniqueID.length(); ++i) {
            base62IDs.add(uniqueID.charAt(i));
        }
        Long dictionaryKey = convertBase62ToBase10ID(base62IDs);
        return dictionaryKey;
    }

    private static Long convertBase62ToBase10ID(List<Character> ids) {
        long id = 0L;
        for (int i = 0, exp = ids.size() - 1; i < ids.size(); ++i, --exp) {
            int base10 = charToIndexTable.get(ids.get(i));
            id += (base10 * Math.pow(62.0, exp));
        }
        return id;
    }
    
    public static String formatNewUrlShortener(String id, String localUrl) {
    	return HTTP_URL_SUFFIX + id;
    }
    
    public static String formatLocalURLFromShortener(String localURL) {
        String[] addressComponents = localURL.split("/");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addressComponents.length - 1; ++i) {
            sb.append(addressComponents[i]);
        }
        sb.append('/');
        return sb.toString();
    }
    
}
