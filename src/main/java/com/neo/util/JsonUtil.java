package com.neo.util;


import com.google.gson.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Created by amit on 26/8/16.
 */
@Component
public class JsonUtil {



    public JSONObject createJsonFromString(String input) throws JsonSyntaxException{
        try {

            JSONObject jsonObject = new JSONObject(input);
            return jsonObject;
        }catch (JsonSyntaxException e){
            e.printStackTrace();
            throw e;
        }
    }


    public Object getValue(JSONObject json,String path) throws Exception {

        String[] paths = path.split("\\.");
        Object result = json;
        for (String key : paths) {

            if (result instanceof JSONObject) {
                result = ((JSONObject) result).get(key);
            } else if (result instanceof JSONArray) {
                result = ((JSONArray) result).get(Integer.parseInt(key));
            } else {


            }

        }
        return result;
    }

}
