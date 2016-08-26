package com.neo.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by amit on 26/8/16.
 */
@Data
public class Response implements Serializable{

    private Object response;
    private Error error;


}
