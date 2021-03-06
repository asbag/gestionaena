// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.innova4b;

import com.innova4b.EmbarquePK;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect EmbarquePK_Roo_Json {
    
    public String EmbarquePK.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String EmbarquePK.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static EmbarquePK EmbarquePK.fromJsonToEmbarquePK(String json) {
        return new JSONDeserializer<EmbarquePK>()
        .use(null, EmbarquePK.class).deserialize(json);
    }
    
    public static String EmbarquePK.toJsonArray(Collection<EmbarquePK> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String EmbarquePK.toJsonArray(Collection<EmbarquePK> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<EmbarquePK> EmbarquePK.fromJsonArrayToEmbarquePKs(String json) {
        return new JSONDeserializer<List<EmbarquePK>>()
        .use("values", EmbarquePK.class).deserialize(json);
    }
    
}
