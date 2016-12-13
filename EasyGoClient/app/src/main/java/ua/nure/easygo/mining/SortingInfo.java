package ua.nure.easygo.mining;

/**
 * Created by Oleg on 12.12.2016.
 */

public class SortingInfo {
    private int attributeIndex;
    private boolean ascending;

    public SortingInfo() {
    }

    public SortingInfo(int attributeIndex, boolean ascending) {
        this.attributeIndex = attributeIndex;
        this.ascending = ascending;
    }

    public int getAttributeIndex() {
        return attributeIndex;
    }

    public void setAttributeIndex(int attributeIndex) {
        this.attributeIndex = attributeIndex;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }


    @Override
    public String toString() {
        return "SortingInfo{" +
                "attributeIndex=" + attributeIndex +
                ", ascending=" + ascending +
                '}';
    }
}
