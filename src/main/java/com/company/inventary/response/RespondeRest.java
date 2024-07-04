package com.company.inventary.response;

import java.util.ArrayList;
import java.util.HashMap;

public class RespondeRest {

    private ArrayList<HashMap<String,String>> metadatos =new ArrayList<>();

    public ArrayList<HashMap<String, String>> getMetadatos() {
        return metadatos;
    }

    public void setMetada(String type, String code ,String date) {
       HashMap<String,String> map=new HashMap<String,String>();
        map.put("type",type);
        map.put("code",code);
        map.put("data",date);
        metadatos.add(map);
    }

}
