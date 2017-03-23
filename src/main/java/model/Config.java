package model;

public class Config {
    public String app_url;
    public String realtime_url;

    public Config() {
        app_url = "https://api.tago.io";
        realtime_url = "https://realtime.tago.io";
        
        if (System.getenv("TAGO_API") != null) {
            app_url = System.getenv("TAGO_API");
        }
        if (System.getenv("TAGO_REALTIME") != null) {
            app_url = System.getenv("TAGO_REALTIME");
        }
    }


}




