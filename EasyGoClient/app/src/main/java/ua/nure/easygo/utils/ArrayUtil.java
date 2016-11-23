package ua.nure.easygo.utils;

/**
 * Created by Oleg on 19.11.2016.
 */

public class ArrayUtil {
    public static boolean contains(long[] array, long val) {
        for (long i : array) {
            if (i==val) {
                return true;
            }
        }
        return false;
    }
}
