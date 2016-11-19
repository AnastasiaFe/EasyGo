package ua.nure.easygo.utils;

/**
 * Created by Oleg on 19.11.2016.
 */

public class ArrayUtil {
    public static boolean contains(int[] array, int val) {
        for (Integer i : array) {
            if (i.equals(val)) {
                return true;
            }
        }
        return false;
    }
}
