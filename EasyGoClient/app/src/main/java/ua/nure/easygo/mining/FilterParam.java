package ua.nure.easygo.mining;

/**
 * Created by Oleg on 07.12.2016.
 */

public class FilterParam {
    //index of map attribute
    private int mapAttrIndex;
    //min and max values. Can be null
    private Object min, max;

    public FilterParam(int mapAttrIndex) {
        this.mapAttrIndex = mapAttrIndex;
    }

    public int getMapAttrIndex() {
        return mapAttrIndex;
    }

    public void setMapAttrIndex(int mapAttrIndex) {
        this.mapAttrIndex = mapAttrIndex;
    }

    public Object getMin() {
        return min;
    }

    public void setMin(Object min) {
        this.min = min;
    }

    public Object getMax() {
        return max;
    }

    public void setMax(Object max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "FilterParam{" +
                "mapAttrIndex=" + mapAttrIndex +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
