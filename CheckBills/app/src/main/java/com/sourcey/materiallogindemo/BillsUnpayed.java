package com.sourcey.materiallogindemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by F_ALI on 03/08/2015.
 */
public class BillsUnpayed extends Fragment {


    String myJSON;

    private static final String TAG_RESULTS="result";
    private static final String TAG_IDSC = "id";
    private static final String TAG_NAMESC = "name";
    private static final String TAG_PRICE ="price";
    private static final String TAG_DATELM ="datelimit";

    JSONArray bills = null;

    ArrayList<HashMap<String, String>> billsList;

    ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.billsunpayed, container, false);
        list = (ListView) rootView.findViewById(R.id.listView2);
        // list.setBackgroundColor (getResources().getColor(R.color.colorPrimaryLight));
        billsList = new ArrayList<HashMap<String,String>>();
        getData();
        return rootView;
    }


    public void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            bills = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<bills.length();i++){
                JSONObject c = bills.getJSONObject(i);
                String id = c.getString(TAG_IDSC);
                String name = c.getString(TAG_NAMESC);
                String price = c.getString(TAG_PRICE);
                String datepayement = c.getString(TAG_DATELM);

                HashMap<String,String> bill = new HashMap<String,String>();

                bill.put(TAG_IDSC,id);
                bill.put(TAG_NAMESC,name);
                bill.put(TAG_PRICE,price);
                bill.put(TAG_DATELM ,datepayement);

                billsList.add(bill);
            }
            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), billsList, R.layout.listitem2,
                    new String[]{TAG_IDSC,TAG_NAMESC,TAG_PRICE,TAG_DATELM},
                    new int[]{R.id.id2, R.id.name2,R.id.price, R.id.datelimit}
            );

            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://192.168.200.1/get-billsunpayed.php");

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }


            public void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
