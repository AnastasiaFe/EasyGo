package ua.nure.easygo.rest;

import java.util.Arrays;

import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.Point;

/**
 * Created by Oleg on 18.10.2016.
 */

public class ControllerStub implements Controller {
    @Override
    public Map getMap() {

        Map map = new Map(Arrays.asList(new Point(6, 3, "Nastya"), new Point(50, 36, "kh")), "New map");

        return map;
    }
}
