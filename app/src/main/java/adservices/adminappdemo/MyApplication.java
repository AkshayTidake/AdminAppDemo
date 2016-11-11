package adservices.adminappdemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Akshay on 10/24/2016.
 */
public class MyApplication extends Application {

    private RequestQueue mRequestQueue;
    private static MyApplication mInstance ;
    private static Context mctx;

    private MyApplication(Context context){
        mctx=context;
        mRequestQueue=getReqQueue();
    }

    public static synchronized MyApplication getInstance(Context context) {
        if(mInstance==null){
            mInstance=new MyApplication(context);
        }
        return mInstance;

    }

    public RequestQueue getReqQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mctx.getApplicationContext());
        }

        return mRequestQueue;
    }



    public <T> void addToReqQueue(Request<T> req) {
        mRequestQueue.add(req);
        Log.d("tag","insertion done");
    }



}
