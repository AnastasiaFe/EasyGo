package ua.nure.easygo.dao;
import ua.nure.easygo.DBconnect.MySqlConnector;
import ua.nure.easygo.model.Mark;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

/**
 * Created by Анна on 23.11.2016.
 */
public class MarkDAO implements IMarkDAO {
    public final String DB_NAME = "stub";
    public final String TABLE_NAME = "marks";
    /* Fields:
    public final int id;
	public final double x;
	public final double y;
	public final String name;
	public final int map_id;
	public final String description;*/


    public Mark createMark(Mark mark){
       //no such mark in DB
        if (getMark(mark.id)==null){


                final String queryInsert = "INSERT INTO " + DB_NAME+"."+TABLE_NAME+
                " (id, x, y, name, map_id, description) values '"+
                mark.id +"','" + mark.x+ "','"+mark.y+
                "','" + mark.name+"','" + mark.map_id+"','"+mark.description+"';";
        try {
            MySqlConnector.execute(queryInsert);
            return mark;
        }
        catch(SQLException e) {
            e.printStackTrace();

        }}
        //mark already exists
       return null;
    }
    public Mark getMark(int id){
        final String query = "SELECT * from" + DB_NAME+"."+TABLE_NAME+" where id='"+mark.id+"';";
        ArrayList<Mark> list = new ArrayList<>();
        try {
            list = MySqlConnector.selectMark(query);}
        catch (SQLException e){
            e.printStackTrace();
        }
        if(list!=null) {
            //such mark exists
            return list.get(0);
        }
        return null;
    }
   public Mark updateMark(Mark mark){
       if (getMark(mark.id)==null){


           final String queryUpdate = String.format("UPDATE %s.%s" +
                   " SET x=%d, y=%d, name=%s, map_id=%d, description=%s where id=%d",
                   DB_NAME,TABLE_NAME,
                   mark.x, mark.y,mark.name,
                   mark.map_id, mark.description);
           try {
               MySqlConnector.execute(queryUpdate);
               return mark;
           }
           catch(SQLException e) {
               e.printStackTrace();

           }}
       //mark already exists
       return null;
   }

    public  Mark changeMap(int newId){
        return null;
    }

   public boolean removeMark(int id) {
        return false;
    }
    public List<Mark> getMarksList(String parameters) {
        List<Mark> list = new ArrayList<>();
        return list;
    }
}
