package com.chl.common.json;


import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create on 2022/6/13
 *
 * @author chenglong
 * description :
 */
public class GsonDefaultAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        TypeAdapter<T> adapter = gson.getDelegateAdapter(this, type);
        return new NullToEmptyStringTypeAdapter<T>(adapter);
    }

    private static class NullToEmptyStringTypeAdapter<T> extends TypeAdapter<T>{

        private TypeAdapter<T> mAdapter;

        public NullToEmptyStringTypeAdapter(TypeAdapter<T> adapter) {
            mAdapter = adapter;
        }

        @Override
        public void write(JsonWriter out, T value) throws IOException {
            mAdapter.write(out, value);
        }

        @Override
        public T read(JsonReader in) throws IOException {
            T data = mAdapter.read(in);
            if (data == null) {
                return null;
            }
            Field[] fields = data.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                try {
                    if (field.get(data) == null) {
                        if (field.getType() == String.class) {
                            field.set(data, "");
                        }
                        if (field.getType() == Integer.class || field.getType() == Long.class || field.getType() == Short.class) {
                            field.set(data, 0);
                        }
                        if (field.getType() == Boolean.class) {
                            field.set(data, false);
                        }
                        if (field.getType() == Float.class || field.getType() == Double.class) {
                            field.set(data, 0);
                        }
                        if (field.getType() == List.class) {
                            field.set(data, new ArrayList<>());
                        }
                        if (field.getType() == Map.class) {
                            field.set(data, new HashMap<>());
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return data;
        }
    }
}
