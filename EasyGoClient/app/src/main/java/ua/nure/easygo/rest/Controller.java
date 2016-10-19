package ua.nure.easygo.rest;

import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.MapList;

/**
 * Created by Oleg on 18.10.2016.
 */

public interface Controller {
    Map getMap(int id);
    MapList getMaps();
}
