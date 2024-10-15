package com.cvmaker.cvmaker.mapper;


import org.bson.Document;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ListMapper<T> {
    Class className;
    public ListMapper(Class<T> className) {
        this.className = className;
    }


    public List<T> map(List<Document> documents) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<T> result = new ArrayList<T>();
        for(Document document : documents ) {
            result.add(
                    (T) className
                            .getDeclaredMethod("map", Document.class)
                            .invoke(null, document)
            );
        }
        return result;
    }
}
