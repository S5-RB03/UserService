package com.sevyh.sevyhuserservice.userservice.api.models;

import lombok.Data;

@Data
public class User {
    private String uuid;

    public void setUuid(String id) {
        this.uuid = id;
    }
}
