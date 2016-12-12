package ua.nure.easygo.utils;

import android.text.InputType;
import android.widget.TextView;

/**
 * Created by Oleg on 09.12.2016.
 */

public class TextViewMaster {

    public enum TextFormat {
        STRING, INT, DOUBLE
    }

    public static void setInputFormat(TextView view, TextFormat format) {
        switch (format) {
            case STRING:
                break;
            case INT:
                view.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case DOUBLE:
                view.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;
        }
    }
}
