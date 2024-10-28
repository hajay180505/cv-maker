package com.cvmaker.cvmaker.mapper;


import org.bson.Document;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type List mapper.
 *
 * @param <T> the type parameter
 */
public class ListMapper<T> {
    /**
     * The Class name.
     */
    Class className;

    /**
     * Instantiates a new List mapper.
     *
     * @param className the class name
     */
    public ListMapper(Class<T> className) {
        this.className = className;
    }


    /**
     * Map list.
     *
     * @param documents the documents
     * @return the list
     * @throws NoSuchMethodException     the no such method exception
     * @throws InvocationTargetException the invocation target exception
     * @throws IllegalAccessException    the illegal access exception
     */
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
