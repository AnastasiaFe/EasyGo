package ua.nure.easygo.server;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.nure.easygo.model.GoMap;

/**
 * Created by Anna on 01.11.2016.
 */
    @Controller
    @RequestMapping("/maps")
    public class JSONController {

        @RequestMapping(value="{id}", method = RequestMethod.GET)
        public @ResponseBody GoMap getMapInJSON(@PathVariable int id) {

            GoMap map = new GoMap(id, 1, "name","data", "tags", "access");

            return map;

        }
        
       
    }

