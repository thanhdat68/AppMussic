package com.example.appmussic.Service;

public class APIService {
    private static String base_url= "https://dat09pt14355.000webhostapp.com/SerVer/";

    public static  DataSevicer getService(){
        return APIRetrofiClinet.getClinet(base_url).create(DataSevicer.class);

    }
}
