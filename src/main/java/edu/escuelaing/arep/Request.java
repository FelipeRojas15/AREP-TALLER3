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
    /**
     * Return the path obtain for the server's request
     * @return String path
     */
    public String getPath() {
        return path;
    }
    /**
     * returns the required method 
     * @return Stirng method
     */
    public String getMethod() {
        return method;
    }
    /**
     * returns the required body 
     * @return Stirng body
     */
    public String getBody() {
        return body;
    }
    /**
     * returns the required header 
     * @return MapStirng header
     */
    public Map<String, String> getHeaders() {
        return headers;
    }
    /**
     * set the required path 
     */
    public void setPath(String path) {
        this.path = path;
    }
    /**
     * set the required method 
     */
    public void setMethod(String method) {
        this.method = method;
    }
    /**
     * set the required body 
     */
    public void setBody(String body) {
        this.body = body;
    }
    /**
     * set the required header 
     */
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    
           
}
