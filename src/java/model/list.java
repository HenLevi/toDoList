
package model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chen
 */
public class list {
    private String id;
    private String name;

    private String check;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }


 

    public list() {
        this.id = "";
        this.name = "";    
        this.check = "";


    }

    
    public list(String id, String name, String check) {
        this.id = id;
        this.name = name;
        this.check = check;
 

    }
    
    public list(String jsonStr) {
        JsonObject o = (JsonObject) new JsonParser().parse(jsonStr);
        this.id=o.get("id").getAsString();
        this.name = o.get("name").getAsString();
        this.check = o.get("check").getAsString();  

    }
    
   
 
     @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
