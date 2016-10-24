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
        Map.id = 0;
        Point.id = 0;
        mapList.getMaps().add(new Map(Arrays.asList(new Point(6, 3, "Nastya"), new Point(50, 36, "kh")), "New map", BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_delete)));
        Point.id = 0;
        mapList.getMaps().add(new Map(Arrays.asList(new Point(45, 13, "Hebvet"), new Point(50, 26, "mos"), new Point(12, 50, "hunf")), "Simple map", BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_dialog_alert)));
        Point.id = 0;
        mapList.getMaps().add(new Map(Arrays.asList(new Point(0, 0, "Center"), new Point(76, 0, "Semsh")), "Feat map", BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_menu_save)));
        Point.id = 0;
        mapList.getMaps().add(new Map(Arrays.asList(
                new Point(50.0051497f, 36.23341299999993f, "Інтертоп магазин"),
                new Point(49.996543f, 36.23335199999997f, "Талисман - магазин рок-атрибутики"),
                new Point(49.998176f, 36.223854000000074f, "БОТАНИКА, АВТОЦЕНТР"),
                new Point(49.99481149999999f, 36.230798400000026f, "SPORT FACTOR, МАГАЗИН СПОРТИВНОГО ПИТАНИЯ"),
                new Point(49.99570139999999f, 36.23556640000004f, "ORIGINAL STORE, МАГАЗИН ОДЕЖДЫ"),
                new Point(50.00120109999999f, 36.23889489999999f, "ТЕХНОточка"),
                new Point(50.0060343f, 36.22466669999994f, "Компьютеры 57"),
                new Point(49.999549f, 36.220286999999985f, "Світ дублянок та шкіри"),
                new Point(50.0059566f, 36.23407420000001f, "Дилайт"),
                new Point(49.9970842f, 36.23855460000004f, "Chicco"),
                new Point(49.99422509999999f, 36.235989399999994f, "AlAnTUR магазин туристического, велосипедного и лыжного снаряжения."),
                new Point(50.0051497f, 36.23341299999993f, "SYMBOL PLAZA, МАГ."),
                new Point(49.998159f, 36.23211700000002f, "Круглосуточный Копировальный Центр NONSTOP"),
                new Point(50.00036f, 36.21991700000001f, "Водяной"),
                new Point(49.995811f, 36.235185f, "Extreme"),
                new Point(49.99508100000001f, 36.23432300000002f, "FTshop"),
                new Point(49.99724849999999f, 36.233612900000026f, "Fashion Bride"),
                new Point(49.99757699999999f, 36.23672899999997f, "R.B. SPA & BEAUTY"),
                new Point(49.9964f, 36.22950300000002f, "Харьков-Проф"),
                new Point(49.991417f, 36.231295000000046f, "Роял Фото")
        ), "Big map", BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_dialog_map)));
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
