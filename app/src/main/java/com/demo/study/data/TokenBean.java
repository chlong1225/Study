package com.demo.study.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * create by chenglong on 6/29/21
 * description :
 */
@Entity(tableName = "token")
public class TokenBean {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String productId;
    private String token;
    private long time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TokenBean{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", token='" + token + '\'' +
                ", time=" + time +
                '}';
    }
}
