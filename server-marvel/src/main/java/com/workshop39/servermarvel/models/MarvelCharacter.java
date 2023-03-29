package com.workshop39.servermarvel.models;

public class MarvelCharacter {
    private Integer id;
    private String name;
    private String desc;
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
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "MarvelCharacter [id=" + id + ", name=" + name + ", desc=" + desc + ", thumbnail=" + thumbnail + "]";
    }

    // public toJson() {

    // }
    
    
}
