package adservices.adminappdemo;

import java.util.Date;

/**
 * Created by Akshay on 10/16/2016.
 */

public class Data {
    private String name;
    private String desc;

    private String dat;

    Data(String name,String desc,String dat){
        this.setName(name);
        this.setDesc(desc);
        this.setDat(dat);
    }




    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



}
