package adservices.adminappdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EventsList extends AppCompatActivity {


    ProgressDialog progressDialog;
    JSONObject jsonObject;

    JSONArray array;
    String url1="http://192.168.0.4:80/clubit/fetch.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_list);

        sendRequest();




    }

    private void sendRequest() {

        class sendRequest extends AsyncTask<Void, Data, Void> {
            Context ctx;
            Activity a;
            private RecyclerView mRecyclerView;
            private RecyclerView.Adapter mAdapter;
            private RecyclerView.LayoutManager mLayoutManager;
            ArrayList<Data> d=new ArrayList<>();
            public sendRequest(Context ctx){
                this.ctx=ctx;
                a=(Activity)ctx;

            }

            @Override
            protected void onPreExecute() {
                mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
                mLayoutManager = new LinearLayoutManager(ctx);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setHasFixedSize(true);
                mAdapter = new MyAdapter(d);
                mRecyclerView.setAdapter(mAdapter);
                progressDialog = ProgressDialog.show(EventsList.this, "Fetching Data", "Please wait...", false, false);

            }



            @Override
            protected Void doInBackground(Void... voids) {
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(url1);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    con.disconnect();
                    String jstring=sb.toString().trim();

                    jsonObject = new JSONObject(jstring);
                    array = jsonObject.getJSONArray("result");
                    int count=0;
                    while(count<array.length()){
                        JSONObject jso=array.getJSONObject(count);
                        count++;
                        Data data=new Data(jso.getString("name"),jso.getString("event_name"));
                        publishProgress(data);
                        Log.d("data",""+data);
                        Thread.sleep(1000);
                    }



                } catch (Exception e) {
                    return null;
                }
                return null;
            }


            @Override
            protected void onProgressUpdate(Data... values) {
                d.add(values[0]);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            protected void onPostExecute(Void avoid) {
                super.onPostExecute(avoid);
                progressDialog.dismiss();


            }

        }
        sendRequest sr = new sendRequest(this);
        sr.execute();
    }



}





