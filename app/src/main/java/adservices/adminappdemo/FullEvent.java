package adservices.adminappdemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.HashMap;

public class FullEvent extends AppCompatActivity implements View.OnClickListener {
    EditText name, desc, dspl, fspl, chrg, lav, single, couple, mix, dcode, dt, ftime, ttime;
    String nm, dte;

    Button update,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_event);
        name = (EditText) findViewById(R.id.v1);
        desc = (EditText) findViewById(R.id.v2);
        dt = (EditText) findViewById(R.id.v11);

        dspl=(EditText) findViewById(R.id.v3);
        fspl=(EditText) findViewById(R.id.v4);
        chrg=(EditText) findViewById(R.id.v5);
        lav=(EditText) findViewById(R.id.v6);
        single=(EditText) findViewById(R.id.v7);
        couple=(EditText) findViewById(R.id.v8);
        mix=(EditText) findViewById(R.id.v9);
        dcode=(EditText) findViewById(R.id.v10);
        ftime=(EditText) findViewById(R.id.v12);
        ttime=(EditText) findViewById(R.id.v13);

        nm = getIntent().getStringExtra("name");
        dte = getIntent().getStringExtra("dt");

        name.setText(nm);
        desc.setText(getIntent().getStringExtra("desc"));
        dt.setText(dte);

        update=(Button)findViewById(R.id.update);
        delete=(Button)findViewById(R.id.delete);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);


            getFullEvent();
    }

    private void getFullEvent() {
        class GetFullEvent extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(FullEvent.this, "Fetching...", "Wait...", false, false);
            }
            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(ConfigRequest.URL_GET_LIST, nm);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showList(s);
            }


        }
        GetFullEvent gfe = new GetFullEvent();
        gfe.execute();
    }

    private void showList(String json) {
        try {

            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject c = result.getJSONObject(0);
            String dspll = c.getString(ConfigRequest.TAG_DSP);
            String foodspl = c.getString(ConfigRequest.TAG_FSP);
            String charges = c.getString(ConfigRequest.TAG_CHRG);
            String lvalue=c.getString(ConfigRequest.TAG_LAV);
            String sngl=c.getString(ConfigRequest.TAG_SINGLE);
            String cpl=c.getString(ConfigRequest.TAG_COUPLE);
            String mx=c.getString(ConfigRequest.TAG_MIX);
            String dcd=c.getString(ConfigRequest.TAG_DCODE);
            String f=c.getString(ConfigRequest.TAG_FT);
            String t=c.getString(ConfigRequest.TAG_TT);


            dspl.setText(dspll);
            fspl.setText(foodspl);
            chrg.setText(charges);
            lav.setText(lvalue);
            single.setText(sngl);
            couple.setText(cpl);
            mix.setText(mx);
            dcode.setText(dcd);
            ftime.setText(f);
            ttime.setText(t);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateList(){
        final String nam = name.getText().toString().trim();
        final String des = desc.getText().toString().trim();
        final String d = dspl.getText().toString().trim();
        final String f=fspl.getText().toString().trim();
        final String ch=chrg.getText().toString().trim();
        final String lv=lav.getText().toString().trim();
        final String dc=dcode.getText().toString().trim();
        final String si=single.getText().toString().trim();
        final String co=couple.getText().toString().trim();
        final String mi=mix.getText().toString().trim();
        final String dat=dt.getText().toString().trim();
        final String ftm=ftime.getText().toString().trim();
        final String ttm=ttime.getText().toString().trim();

        class UpdateList extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(FullEvent.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(FullEvent.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("name",nam);
                hashMap.put("event_name",des);
                hashMap.put(ConfigRequest.TAG_DSP,d);
                hashMap.put(ConfigRequest.TAG_FSP,f);
                hashMap.put(ConfigRequest.TAG_CHRG,ch);
                hashMap.put(ConfigRequest.TAG_LAV,lv);
                hashMap.put(ConfigRequest.TAG_SINGLE,si);
                hashMap.put(ConfigRequest.TAG_COUPLE,co);
                hashMap.put(ConfigRequest.TAG_MIX,mi);
                hashMap.put(ConfigRequest.TAG_DCODE,dc);
                hashMap.put("date",dat);
                hashMap.put(ConfigRequest.TAG_FT,ftm);
                hashMap.put(ConfigRequest.TAG_TT,ttm);


                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(ConfigRequest.URL_UPDATE_LIST,hashMap,nm);

                return s;
            }
        }

        UpdateList ul = new UpdateList();
        ul.execute();
    }

    private void deleteList(){
        class DeleteList extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(FullEvent.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(FullEvent.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(ConfigRequest.URL_DELETE_LIST, nm);
                return s;
            }
        }

        DeleteList dl = new DeleteList();
        dl.execute();
    }

    private void confirmDeleteList(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this Record?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteList();
                        startActivity(new Intent(FullEvent.this,EventsList.class));
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == update){
            updateList();
        }

        if(v == delete){
            confirmDeleteList();
        }
    }
}

