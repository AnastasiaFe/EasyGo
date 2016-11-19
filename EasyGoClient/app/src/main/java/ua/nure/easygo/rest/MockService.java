package ua.nure.easygo.rest;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.MapList;
import ua.nure.easygo.model.Point;
import ua.nure.easygo.model.User;
import ua.nure.easygo.model.attributes.AttributeType;
import ua.nure.easygo.model.attributes.AttributeValue;
import ua.nure.easygo.model.attributes.AttributeValues;
import ua.nure.easygo.model.attributes.MapAttribute;
import ua.nure.easygo.model.attributes.MapAttributes;

/**
 * Created by Oleg on 27.10.2016.
 */

public class MockService implements EasyGoService {

    static MapList mapList;
    static List<User> users;

    static {
        users = new LinkedList<>();
        users.add(new User("Oleh Havrysh", "havrysh@nure.ua", "qwerty", null));
        users.add(new User("Nastya Fedorenko", "fedorenko@nure.ua", "123456", null));


        mapList = new MapList(new LinkedList<Map>());
        Map.id = 0;
        Point.id = 0;
        mapList.maps.add(new Map(Arrays.asList(new Point(6, 3, "Nastya", new AttributeValues(Arrays.asList(new AttributeValue(0, "6.88"), new AttributeValue(1, "4"))), 0), new Point(50, 36, "kh", new AttributeValues(Arrays.asList(new AttributeValue(0, "3.45"))), 0)), "New map", BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_delete), new MapAttributes(Arrays.asList(new MapAttribute("price", AttributeType.DOUBLE), new MapAttribute("Quality", AttributeType.RATING)))));
        Point.id = 0;
        mapList.maps.add(new Map(Arrays.asList(new Point(45, 13, "Hebvet", new AttributeValues(Arrays.asList(new AttributeValue(0, ""))), 1), new Point(50, 26, "mos", new AttributeValues(Arrays.asList(new AttributeValue(0, ""))), 1), new Point(12, 50, "hunf", new AttributeValues(Arrays.asList(new AttributeValue(0, ""))), 1)), "Simple map", BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_dialog_alert), new MapAttributes(Arrays.asList(new MapAttribute("Date", AttributeType.DATE_TIME)))));
        Point.id = 0;
        mapList.maps.add(new Map(Arrays.asList(new Point(0, 0, "Center", new AttributeValues(Arrays.asList(new AttributeValue(0, "3"))), 2), new Point(76, 0, "Semsh", new AttributeValues(Arrays.asList(new AttributeValue(0, "3"))), 2)), "Feat map", BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_menu_save), new MapAttributes(Arrays.asList(new MapAttribute("Level", AttributeType.INTEGER)))));
        Point.id = 0;
        mapList.maps.add(new Map(Arrays.asList(
                new Point(50.0051497f, 36.23341299999993f, "Інтертоп магазин", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(49.996543f, 36.23335199999997f, "Талисман - магазин рок-атрибутики", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(49.998176f, 36.223854000000074f, "БОТАНИКА, АВТОЦЕНТР", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(49.99481149999999f, 36.230798400000026f, "SPORT FACTOR, МАГАЗИН СПОРТИВНОГО ПИТАНИЯ", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(49.99570139999999f, 36.23556640000004f, "ORIGINAL STORE, МАГАЗИН ОДЕЖДЫ", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(50.00120109999999f, 36.23889489999999f, "ТЕХНОточка", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(50.0060343f, 36.22466669999994f, "Компьютеры 57", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(49.999549f, 36.220286999999985f, "Світ дублянок та шкіри", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(50.0059566f, 36.23407420000001f, "Дилайт", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(49.9970842f, 36.23855460000004f, "Chicco", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(49.99422509999999f, 36.235989399999994f, "AlAnTUR магазин туристического, велосипедного и лыжного снаряжения.", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(50.0051497f, 36.23341299999993f, "SYMBOL PLAZA, МАГ.", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(49.998159f, 36.23211700000002f, "Круглосуточный Копировальный Центр NONSTOP", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(50.00036f, 36.21991700000001f, "Водяной", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(49.995811f, 36.235185f, "Extreme", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(49.99508100000001f, 36.23432300000002f, "FTshop", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(49.99724849999999f, 36.233612900000026f, "Fashion Bride", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(49.99757699999999f, 36.23672899999997f, "R.B. SPA & BEAUTY", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(49.9964f, 36.22950300000002f, "Харьков-Проф", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3),
                new Point(49.991417f, 36.231295000000046f, "Роял Фото", new AttributeValues(Arrays.asList(new AttributeValue(0, "4"))), 3)
        ), "Bigggggggggggggggggggggg map", BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_dialog_map), new MapAttributes(Arrays.asList(new MapAttribute("rating", AttributeType.RATING)))));
    }

    MockService() {
    }

    @Override
    public Call<MapList> getMaps() {
        return new BaseCall<>(mapList);
    }

    @Override
    public Call<String> saveMaps(MapList mapList) {
        this.mapList = mapList;
        return new BaseCall<>("Ok");
    }

    @Override
    public Call<User> getUser(String login) {
        for (User u : users) {
            if (login.equals(u.login)) {
                return new BaseCall<>(u);
            }
        }
        return new BaseCall<>();
    }


}
