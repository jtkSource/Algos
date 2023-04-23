package jtk.algo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Longest Substring Length with K distinct Characters
 * https://www.youtube.com/watch?v=MK-NZ4hN7rs&t=413s
 */
public class LongestSubstringKDistinct {
    private static final Logger log = LoggerFactory.getLogger(LongestSubstringKDistinct.class);

    public static int findLength(String str, int k){
        int windowStart = 0, maxLength = 0;
        Map<Character,Integer> charFrequency = new HashMap<>();
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            charFrequency.put(rightChar, charFrequency.getOrDefault(rightChar,0) + 1);
            while (charFrequency.size() > k){
                char leftChar = str.charAt(windowStart);
                charFrequency.put(leftChar, charFrequency.get(leftChar) - 1);
                if(charFrequency.get(leftChar) == 0){
                    charFrequency.remove(leftChar);
                }
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }
    public static void main(String[] args) {
        String input = "AAAHHIBC";
        log.info("Longest Substring with K Distinct characters: {}",
                findLength(input, 2));
    }
}
