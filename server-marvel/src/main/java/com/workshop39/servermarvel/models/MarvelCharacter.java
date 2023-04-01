package com.workshop39.servermarvel.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class MarvelCharacter {
    private Integer id;
    private String name;
    private String description;
    private String thumbnail;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesc() {
        return description;
    }
    public void setDesc(String desc) {
        this.description = desc;
    }
    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "MarvelCharacter [id=" + id + ", name=" + name + ", description=" + description + ", thumbnail=" + thumbnail + "]";
    }

    // converts from API call
    public static MarvelCharacter toMarvelChar(JsonObject obj) {
        MarvelCharacter mc = new MarvelCharacter();
        mc.setId(getIntValue("id", obj));
        mc.setName(getStringValue("name", obj));
        mc.setDesc(getStringValue("description", obj));
        mc.setThumbnail(getThumbnail("thumbnail", obj));
        return mc;
    }

    // create MC from redis
    public static MarvelCharacter create(JsonObject o) {
        MarvelCharacter mc = new MarvelCharacter();
        mc.setId(o.getInt("id"));
        mc.setName(o.getString("name"));
        mc.setDesc(o.getString("description"));
        mc.setThumbnail(o.getString("thumbnail"));
        return mc;
    }

    public static Integer getIntValue(String label, JsonObject o) {
        // #1.check for key, #2.check for null in key
        if (o.containsKey(label) && !o.isNull(label)) {
            return o.getInt(label);
        }
        return 0;
    }

    public static String getStringValue(String label, JsonObject o) {
        if(o.containsKey(label) && !o.isNull(label)) {
            return o.getString(label);
        }
        return "No value";
    }

    public static String getThumbnail(String label, JsonObject o) {
        JsonObject tn = o.getJsonObject("thumbnail");
        // check if key & value of key present
        if (tn.containsKey("path") && !tn.isNull("path")) {
            return tn.getString("path") + "." + tn.getString("extension");
        }
        return "No Image";
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("thumbnail", thumbnail)
                .build();
    }
    
}
