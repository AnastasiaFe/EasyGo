package ua.nure.easygo.dao;

import ua.nure.easygo.model.Mark;

import java.util.List;

/**
 * Created by Анна on 23.11.2016.
 */
public interface IMarkDAO {
   Mark createMark(Mark mark);
    Mark getMark(int id);
    Mark updateMark(Mark mark);
    boolean removeMark(int id);
    Mark changeMap(int newId);
    List<Mark> getMarksList(String parameters);
}
