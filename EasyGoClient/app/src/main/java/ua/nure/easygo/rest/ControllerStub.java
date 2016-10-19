package ua.nure.easygo.rest;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import java.util.Arrays;
import java.util.LinkedList;

import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.MapList;
import ua.nure.easygo.model.Point;

/**
 * Created by Oleg on 18.10.2016.
 */

public class ControllerStub implements Controller {

    static MapList mapList;
    static {
        mapList = new MapList();
        mapList.setMaps(new LinkedList<Map>());

        mapList.getMaps().add(new Map(Arrays.asList(new Point(6, 3, "Nastya"), new Point(50, 36, "kh")), "New map", BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_delete)));
        mapList.getMaps().add(new Map(Arrays.asList(new Point(45, 13, "Hebvet"), new Point(50, 26, "mos"), new Point(12, 50, "hunf")), "Simple map", BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_dialog_alert)));
        mapList.getMaps().add(new Map(Arrays.asList(new Point(0, 0, "Center"), new Point(76, 0, "Semsh")), "Feat map", BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_menu_save)));
    }

    @Override
    public Map getMap(int id) {
        return mapList.getMaps().get(id);
    }

    @Override
    public MapList getMaps() {
        return mapList;
    }
}
