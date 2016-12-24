package ua.nure.easygo.rest;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.easygo.dao.GoMapDAOImpl;
import ua.nure.easygo.dao.PointDAOImpl;
import ua.nure.easygo.dao.UserDAOImpl;
import ua.nure.easygo.model.GoMap;
import ua.nure.easygo.model.Point;
import ua.nure.easygo.model.User;
import ua.nure.easygo.util.ImageHelper;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;



@RestController
public class MyController {


    @RequestMapping("maps")
    public List<GoMap> getMaps(@RequestParam(value = "query", required = false) String query, HttpServletRequest request) throws SQLException {
        List<GoMap> maps = new GoMapDAOImpl().getAllMaps();
        if (query != null && !query.equals("")) {
            for (int i = 0; i < maps.size(); i++) {
                if (!maps.get(i).name.toLowerCase().contains(query.toLowerCase())) {
                    maps.remove(i);
                    i--;
                }
            }
        }
        return maps;

    }

    @RequestMapping("maps/{id}")
    public GoMap getMap(@PathVariable("id") long id) throws SQLException {
        return new GoMapDAOImpl().getMap(id);
    }

    @RequestMapping("maps/{id}/points")
    public List<Point> getPoints(@PathVariable("id") long id) throws SQLException {
        return new GoMapDAOImpl().getPointsOfMap(id);
    }

    @RequestMapping("points/{id}")
    public Point getPoint(@PathVariable("id") long id) throws SQLException {
        return new PointDAOImpl().getPoint(id);
    }

    @RequestMapping(value = "pointsids", method = RequestMethod.POST)
    public List<Point> getPointsByIds(@RequestBody long[] ids) throws SQLException {
        return new PointDAOImpl().getPoints(ids);
    }

    @RequestMapping(value = "points", method = RequestMethod.POST)
    public boolean postPoint(@RequestBody Point p) throws Exception {
        return new PointDAOImpl().postPoint(p);
    }

    @RequestMapping(value = "maps", method = RequestMethod.POST)
    public boolean postMap(@RequestBody GoMap m) throws Exception {

        return new GoMapDAOImpl().postGoMap(m);
    }

    @RequestMapping(value = "maps/{id}", method = RequestMethod.DELETE)
    public boolean deleteMap(@PathVariable("id") long id){
        return new GoMapDAOImpl().removeMap(id);
    }

    @RequestMapping("users/{login}")
    public User getUser(@PathVariable("login") String login) throws SQLException {

        return new UserDAOImpl().getUser(login);
    }

    @RequestMapping(value = "users", method = RequestMethod.POST)
    public boolean postUser(@RequestBody User u) throws Exception {
        return new UserDAOImpl().postUser(u);
    }

    @RequestMapping("auth")
    public String getToken() {
        return "";
    }


    @RequestMapping(value = "/image/{path}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable("path") String path) throws IOException {
        return ImageHelper.load(path);
    }

    @RequestMapping(value = "/image/{path}", method = RequestMethod.POST)
    public String uploadImage(@RequestParam("img") MultipartFile img, @PathVariable("path") String path) throws IOException {
        ImageHelper.save(path, img.getBytes());
        return "OK";
    }


}
