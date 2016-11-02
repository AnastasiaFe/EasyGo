package ua.nure.easygo;

/**
 * Created by Oleg on 02.11.2016.
 */

public class MockUtil {
    public static final int MASK_MAP = 0xffff0000;
    public static final int MASK_POINT = ~MASK_MAP;


    public static int getMapIndex(long pointId) {
        return (int) ((pointId & MASK_MAP) >>> 16);
    }

    public static int getPointIndex(long pointId) {
        return (int) (pointId & MASK_POINT);
    }
}
