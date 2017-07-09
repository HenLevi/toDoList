package db;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import model.*;

/**
 *
 * @author chen
 */
public class DAL {

    public static String mySqlConnectionString = "jdbc:mysql://localhost/list?user=root&password=1";

    /**
     * Get all task from database as Json array
     *
     * @return Json array that contains all the users in database
     */
    public static JsonArray allTasks() {
        String query = "Select * FROM `todotable`";
        DataBase db = DataBase.getInstance();
        JsonArray rslt = db.getRSasJsonArray(query);
        return rslt;
    }

    /**
     * Insert new item into the DataBase
     *
     * @param l - list
     * @return New ID
     */
    public static String insert(list l) {
        String output;
        String query = "INSERT INTO `todotable` ( `name`, `check` ) "
                + "VALUES ( '" + l.getName() + "', '" + l.getCheck() + "')";
        DataBase db = DataBase.getInstance();
        output = String.valueOf(db.Insert(query));
        l.setId(output);
        return output;
    }

    /**
     * Get list by a given list id
     *
     * @param id
     * @return list object (with a given id), of null if user with given id was
     * not found
     */
    public static list getListById(String id) {
        list output = null;
        String query = "Select * FROM `todotable` WHERE `id` = '" + id + "'";
        DataBase db = DataBase.getInstance();
        JsonArray rslt = db.getRSasJsonArray(query);
        if (rslt.size() > 0) {
            JsonObject o = (JsonObject) rslt.get(0);
            output = new list(o.get("id").getAsString(), o.get("name").getAsString(), o.get("check").getAsString());
        }
        return output;
    }

    /**
     * Get post by a given post id
     *
     * @param id
     * @return Post or null if post with such id doesn't exist
     */
    public static JsonArray getTaskById(String id) {

        String query = "Select * FROM `todotable` WHERE `id`='" + id + "'";

        DataBase db = DataBase.getInstance();
        JsonArray rslt = db.getRSasJsonArray(query);
        return rslt;
    }

    /**
     * Delete post by a given list id
     *
     * @param id
     * @return number of rows deleted or -1 if fails.
     */
    public static String deleteTaskById(String id) {
        String output;
        String query = "DELETE FROM `todotable` WHERE `id` = '" + id + "'";
        DataBase db = DataBase.getInstance();
        output = String.valueOf(db.Delete(query));
        return output;
    }

    public static String updateTodo(list l) {
        String output;
        String query = "UPDATE `todotable` SET "
                + "`name`= '" + l.getName() + "' ,"
                + "`check`= '" + l.getCheck() + "' "
                + "WHERE `id` = '" + l.getId() + "'";
        DataBase db = DataBase.getInstance();
        output = String.valueOf(db.Update(query));
        return output;
    }

    public static JsonObject getTodoById(String id) {
        String query = "SELECT * FROM `todotable` WHERE `id` = '" + id + "'";
        DataBase db = DataBase.getInstance();
        JsonArray rsltArr = db.getRSasJsonArray(query);
        JsonObject rslt;
        if (rsltArr.size() > 0) {
            rslt = rsltArr.get(0).getAsJsonObject();
        } else {
            rslt = new JsonObject();
        }
        return rslt;
    }
}
