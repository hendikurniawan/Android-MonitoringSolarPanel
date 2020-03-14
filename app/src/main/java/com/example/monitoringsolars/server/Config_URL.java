package com.example.monitoringsolars.server;

//This class is for storing all URLs as a model of URLs

public class Config_URL
{
    public static String base_URL           = "http://system.solarmeter.id";
//    public static String base_URL           = "http://172.32.1.128:9000";


    //api yang di get dari ip dan nganu
    public static String dataHistory           = base_URL + "/sensors/datahistory";
    public static String dataRealtime          = base_URL + "/sensors/datarealtime";
    public static String dataTarif             = base_URL + "/sensors/totalkwh";

}