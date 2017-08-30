package com.zscat.storm.recommend.test.mongodb;
 
 
import com.mongodb.Cursor;
 
/**
 * Created by superman on 2014/9/15.
 */
public interface CursorHandle {
    public void handle(Cursor cursor);
}