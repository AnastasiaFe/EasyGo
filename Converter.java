package ua.nure.easygo.server;
import java.util.ArrayList;
//import com.google.gson.*;
import build.classes.com.google.gson.*;
import ua.nure.easygo.model.*;

public class Converter {
	public static String toJSON(ArrayList<Entity> list) {
		 build.classes.com.google.gson.Gson gson = new Gson();
	    StringBuilder sb = new StringBuilder();
	    for(Entity e : list) {
	        sb.append(gson.toJson(e));
	        
	    }
	    return sb.toString();
	}

}
