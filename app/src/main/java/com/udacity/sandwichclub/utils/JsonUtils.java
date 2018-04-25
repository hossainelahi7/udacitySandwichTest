package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private class Key{
        public static final String NAME = "name";
        public static final String MAIN_NAME = "mainName";
        public static final String AKA = "alsoKnownAs";
        public static final String PLACE_OF_ORIGIN = "placeOfOrigin";
        public static final String DESCRIPTION = "description";
        public static final String IMAGE_LINK = "image";
        public static final String INGREDIENTS = "ingredients";
    }

    public static Sandwich parseSandwichJson(String data) {
        JSONObject json;
        Sandwich sandwich = new Sandwich();
        if(data!=null && data.length()>0){
            try {
                json = new JSONObject(data);
                if (json.has(Key.NAME)
                        && json.getJSONObject(Key.NAME) != null
                        && json.getJSONObject(Key.NAME).length()>0){
                           JSONObject name = json.getJSONObject(Key.NAME);
                           if(name.has(Key.MAIN_NAME))
                               sandwich.setMainName(name.getString(Key.MAIN_NAME));
                           if(name.has(Key.AKA)
                                   && name.getJSONArray(Key.AKA)!= null
                                   && name.getJSONArray(Key.AKA).length() >0){
                               JSONArray arr = name.getJSONArray(Key.AKA);
                               List<String> akaList = new ArrayList<>();
                               for (int i =0; i <arr.length(); i++){
                                   akaList.add(arr.get(i).toString());
                               }
                               sandwich.setAlsoKnownAs(akaList);
                           }

                }
                if(json.has(Key.PLACE_OF_ORIGIN)
                        && json.getString(Key.PLACE_OF_ORIGIN) != null)
                    sandwich.setPlaceOfOrigin(json.getString(Key.PLACE_OF_ORIGIN));
                if(json.has(Key.DESCRIPTION)
                        && json.getString(Key.DESCRIPTION) != null)
                    sandwich.setDescription(json.getString(Key.DESCRIPTION));
                if(json.has(Key.IMAGE_LINK)
                        && json.getString(Key.IMAGE_LINK) != null)
                    sandwich.setImage(json.getString(Key.IMAGE_LINK));
                if(json.has(Key.INGREDIENTS)
                        && json.getJSONArray(Key.INGREDIENTS) != null
                        && json.getJSONArray(Key.INGREDIENTS).length() >0){
                    JSONArray array = json.getJSONArray(Key.INGREDIENTS);
                    List<String> ingredients = new ArrayList<>();
                    for (int i =0; i< array.length(); i++){
                        ingredients.add(array.get(i).toString());
                    }
                    sandwich.setIngredients(ingredients);
                }
            } catch (JSONException e) {
                Log.e("JsonUtil", "error triggered during reading json object. "+e.getMessage());
            }
        }
        return sandwich;
    }
}
