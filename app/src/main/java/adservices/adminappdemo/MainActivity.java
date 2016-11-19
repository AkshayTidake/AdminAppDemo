package adservices.adminappdemo;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.firebase.client.Firebase;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText e1,e2,e3,e4,e5,e6,e8;
    RadioGroup srg,crg,mrg;
    RadioButton srb,crb,mrb;
    int chrg,lav;
    Button b,b1;
    ProgressDialog PD;
    Date d=new Date();
    Date d1=new Date();
    Date d2=new Date();
    SimpleDateFormat sdf=new SimpleDateFormat("hh:mm");
    SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");

    DatePicker dp;
    TimePicker e10,e11;

    String name,desc,dspl,fspl,dcode,dte,dt,ftime,ttime,ft,tt,single,couple,mix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            PD = new ProgressDialog(this);
                PD.setMessage("Loading.....");
                PD.setCancelable(false);

        //Firebase.setAndroidContext(this);

        e1= (EditText) findViewById(R.id.ed1);
        e2= (EditText) findViewById(R.id.ed2);
        e3= (EditText) findViewById(R.id.ed3);
        e4= (EditText) findViewById(R.id.ed4);
        e5= (EditText) findViewById(R.id.ed5);
        e6= (EditText) findViewById(R.id.ed6);
        e8= (EditText) findViewById(R.id.ed8);

        dp= (DatePicker) findViewById(R.id.ed9);

        e10= (TimePicker) findViewById(R.id.ed10);
        e11= (TimePicker) findViewById(R.id.ed11);

        b= (Button) findViewById(R.id.button2);
        b1=(Button) findViewById(R.id.button3);

        b.setOnClickListener(this);
        b1.setOnClickListener(this);
        // Firebase ref= new Firebase(Config.firebase_url);



    }
    public void radioCheck(){

        srg=(RadioGroup)findViewById(R.id.srg);
        crg= (RadioGroup) findViewById(R.id.crg);
        mrg=(RadioGroup)findViewById(R.id.mrg);


        int rbtn1=srg.getCheckedRadioButtonId();
        int rbtn2=crg.getCheckedRadioButtonId();
        int rbtn3=mrg.getCheckedRadioButtonId();
        srb=(RadioButton)findViewById(rbtn1);
        crb=(RadioButton)findViewById(rbtn2);
        mrb=(RadioButton)findViewById(rbtn3);


        if(rbtn1==R.id.sy){
            single="yes";
        }
        else{
            single="no";
        }

        if(rbtn2==R.id.cy){
            couple="yes";
        }
        else{
            couple="no";
        }

        if(rbtn3==R.id.my){
            mix="yes";
        }
        else{
            mix="no";
        }


    }



    public void insert() {
        PD.show();

         name=e1.getText().toString();
         desc=e2.getText().toString();
         dspl=e3.getText().toString();
         fspl=e4.getText().toString();
         chrg=Integer.parseInt(e5.getText().toString());
         lav=Integer.parseInt(e6.getText().toString());
         dcode=e8.getText().toString();

         dte=dp.getYear()+"-"+(dp.getMonth()+1)+"-"+dp.getDayOfMonth();

        ftime=e10.getCurrentHour()+":"+e10.getCurrentMinute();
        ttime=e11.getCurrentHour()+":"+e11.getCurrentMinute();

        radioCheck();

        try {
            d=sdf2.parse(dte);
            d1=sdf.parse(ftime);
            d2=sdf.parse(ttime);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        dt=sdf2.format(d);
        ft=sdf.format(d1);
        tt=sdf.format(d2);


        StringRequest postRequest = new StringRequest(Request.Method.POST, ConfigRequest.URL_ADD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        PD.dismiss();
           Toast.makeText(MainActivity.this,"Data Inserted Successfully", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                PD.dismiss();

                Toast.makeText(getApplicationContext(),
                        "failed to insert"+error, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this,"data not inserted please check your inernet connection",Toast.LENGTH_LONG).show();
                Log.d("error",""+error);
            }
        }) {
            @Override
            protected Map<String, String> getParams()  throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("event_name", desc);
                params.put("drinks_spl", dspl);
                params.put("food_spl", fspl);
                params.put("cover_charges", String.valueOf(chrg));
                params.put("location_accomodation_value", String.valueOf(lav));
                params.put("single", single);
                params.put("couple", couple);
                params.put("mix", mix);
                params.put("dress_code", dcode);
                params.put("date", dt);
                params.put("from_time", ft);
                params.put("to_time", tt);
                Log.d("tag","values inserted");

                return params;
            }
        };

        // Adding request to request queue
        MyApplication.getInstance(MainActivity.this).addToReqQueue(postRequest);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button2:
                insert();
                break;
            case R.id.button3:
                startActivity(new Intent(this,EventsList.class));
                finish();
                break;

        }
    }
}
