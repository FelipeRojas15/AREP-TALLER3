/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rojas
 */
public class Request {
    private String path;
    private String method;
    private String body;
    private Map<String, String> headers;
    public Request(){
        this.path="";
        this.method ="";
        this.body="";
        this.headers= new HashMap<String, String>();
    }
    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    
           
}
