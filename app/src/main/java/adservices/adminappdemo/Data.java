package adservices.adminappdemo;

/**
 * Created by Akshay on 10/16/2016.
 */

public class Data {
    private String name;
    private String desc;

    Data(String name,String desc){
        this.setName(name);
        this.setDesc(desc);
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
