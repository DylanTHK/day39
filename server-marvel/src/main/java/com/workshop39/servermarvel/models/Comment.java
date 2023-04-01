package com.workshop39.servermarvel.models;

import java.sql.Timestamp;

import org.bson.Document;


import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comment {
    private Timestamp ts;
    private Integer id;
    private String comment;

    public Timestamp getTs() {
        return ts;
    }
    public void setTs(Timestamp ts) {
        this.ts = ts;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer charId) {
        this.id = charId;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment [ts=" + ts + ", id=" + id + ", comment=" + comment + "]";
    }

    // FIXME: Remove if not required
    // public Document toDoc() {
    //     return Document.parse(Json.createObjectBuilder()
    //             .add("ts", 1)
    //             .add("id", id)
    //             .add("comment", comment)
    //             .build().toString());
    // }
    
    public static Comment docToComment(Document d) {
        Comment c = new Comment();
        c.setTs(Timestamp.valueOf(d.getString("ts")));
        c.setId(Integer.valueOf(d.getString("id")));
        c.setComment(d.getString("comment"));
        return c;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("ts", ts.toString())
            .add("id", id)
            .add("comment", comment)
            .build();
    }
    
}
