package me.cyh2.solarchat.SolarUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class IPInfo {
    @SerializedName("country")
    private String country;

    @SerializedName("regionName")
    private String regionName;

    @SerializedName("city")
    private String city;

    @SerializedName("isp")
    private String isp;

    public String getCountry() {
        return country;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getCity() {
        return city;
    }

    public String getIsp() {
        return isp;
    }

    public static IPInfo requestIPInfo(String ip) throws IOException {
        String api_url = "http://ip-api.com/json/" + ip;
        URL url = new URL(api_url);
        URLConnection conn = url.openConnection();
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        Gson gson = new Gson();
        IPInfo ip_info = gson.fromJson(rd, IPInfo.class);
        rd.close();
        return ip_info;
    }
}