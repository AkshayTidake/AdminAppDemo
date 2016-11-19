package adservices.adminappdemo;

/**
 * Created by Akshay on 11/16/2016.
 */

public class ConfigRequest {


        //Address of our scripts of the CRUD
        public static final String URL_ADD="http://192.168.0.3/clubit/insert.php";
        public static final String URL_GET_ALL = "http://192.168.0.3/clubit/fetch.php";
        public static final String URL_GET_LIST = "http://192.168.0.3/clubit/selectuser.php?name=";
        public static final String URL_UPDATE_LIST = "http://192.168.0.3/clubit/updateadmin.php?name=";
        public static final String URL_DELETE_LIST = "http://192.168.0.3/clubit/deleteentry.php?name=";
        public static final String URL_LOGIN = "http://192.168.0.3/clubit/adminlogin.php";



        //JSON Tags
        public static final String TAG_JSON_ARRAY="result";
        public static final String TAG_DSP = "drinks_spl";
        public static final String TAG_FSP = "food_spl";
        public static final String TAG_CHRG = "cover_charges";
        public static final String TAG_LAV = "location_accomodation_value";
        public static final String TAG_SINGLE = "single";
        public static final String TAG_COUPLE = "couple";
        public static final String TAG_MIX = "mix";
        public static final String TAG_DCODE = "dress_code";
        public static final String TAG_FT = "from_time";
        public static final String TAG_TT = "to_time";




    }
