package com.example.student.readingroomdemo;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_spinner_item;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//https://stackoverflow.com/questions/21662673/how-to-display-fetched-json-data-into-listview-using-baseadapter
    public  static Button btnFetch,btnTabbed;
    public  static TextView txtFetchedData,sData;
    public  static ProgressBar pBar;
    public  static Spinner spDate;
    public  static ListView list;

    public  static String title2,description,date,parsedRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        txtFetchedData=(TextView) findViewById(R.id.text_display_fetched);
//        sData=(TextView) findViewById(R.id.specific_data);
      //  btnFetch=(Button) findViewById(R.id.btn_fetch);
        btnTabbed=(Button) findViewById(R.id.btn_load_list);
        pBar=(ProgressBar) findViewById(R.id.pb_progress);
        spDate=(Spinner)findViewById(R.id.date_created);
        //list=(ListView) findViewById(R.id.listView);


        if(isInternetOn()){
            new GetPosts().execute();
            //btnFetch.setOnClickListener(this);
            btnTabbed.setOnClickListener(this);
        }else {
            Toast.makeText(this, " no internet connection available ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        if(view==btnFetch){
            //new getPosts().execute();
        }
        if(view==btnTabbed){
            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }
    }

    public static List<MyArticle> myArticles = new ArrayList<>();
    //yArticle article;
    public class GetPosts extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... strings) {
           String requestUrl="http://reading-room.azurewebsites.net//api/reading/GetPosts";
            try {
                URL url=new URL(requestUrl);
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();

                connection.setRequestProperty("Authorization","bearer DkWDjVOced5co2f3GbuM_nT236TT4aU2rbmdssphxLy1hkHYYv_sPEpP4APB51e3JWCD76Qm1e0UZwuwOvYeBwDQFpUbzAbpyNBcRgm-IgUnv_fF5-CxctUx2eJuy3MvMAKR-Vt-0DAKZ1iL_9-Z4LMb9YGmVu9V-GM-1oVnrWoLDbKOq9wWCc7PiFlPu8KCG0iBqDO0K8fuMN0Mpp8Jkc3ssPV38AktpHSMKirj-dbLxyLY4OFkbdKabtDJ7QyNvAEczoJw_GssTkN9D-yriatA0bq2GbZPOhYyvY2Nkspb9P7TGzFH-7lq21ohB-COgmuqvNAPC133N7VG2g4n5SN7MPzo2nXkLXhOOIRNY0rjib9grbDu5P8zj3RhhWMUe37dr8FrSSOB8gT6LUQjaZXib-aH32twMt_9oyoNY38LXPykpEyYXz8XP99PvOnqu4sjLJ8KXiWlbmBtOM95b0bF5tggCA1vvYhMtH0MQIC9K7bqgxiya9DwxtO7n9ZO");
                connection.setRequestProperty("Accept","application/json");
                connection.setRequestMethod("GET");

                BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder builder=new StringBuilder();
                String line;
                while((line=reader.readLine())!=null){
                    builder.append(line).append("\n");
                }
                reader.close();
                connection.disconnect();
                return builder.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if(result==null)
                result="ERROR OCCURED";
            pBar.setVisibility(View.GONE);
            //txtFetchedData.setText(result);
            parsedRes=result;
            super.onPostExecute(result);
            //Articles art=gson.fromJson(result,Articles.class);
            //sData.setText("title: "+art.getTitle());

            //get photo starts here

//            FileInputStream in;
//            BufferedInputStream read;
//
//            try {
//                in=new FileInputStream(new MyArticle().url);
//                read=new BufferedInputStream(in);
//                Bitmap bMap= BitmapFactory.decodeStream(read);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }

            //get photo ends here

            Gson gson=new Gson();

            myArticles = gson.fromJson(result, new TypeToken<ArrayList<MyArticle>>(){}.getType());

            for (int x = 0; x < myArticles.size(); x++) {
                MyArticle article = myArticles.get(x);
                //sData.append(x+". Title: "+article.title + "\n"+"Description: "+article.description+"\n------------------------------------------------\n");
            }
            spinnerSetUp();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            pBar.setVisibility(View.VISIBLE);
            pBar.setProgress(values[0]);
            super.onProgressUpdate(values);
        }
    }
    public void spinnerSetUp(){
        final List<String> spinnerItems=new ArrayList<>();
        spinnerItems.add("--Select date for the article to read--");
        for(int x=0; x<myArticles.size();x++){
            MyArticle article = myArticles.get(x);
            spinnerItems.add(article.dateCreated);
        }
        //check
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,simple_spinner_item,spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDate.setAdapter(adapter);

        spDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item=spDate.getSelectedItem().toString();
                boolean temp=false;
                int count=0;
                for(int k=0;k<myArticles.size();k++){
                    MyArticle article=myArticles.get(k);
                    if(item.equals(article.dateCreated)) {
                        title2=article.title;
                        description=article.description;
                        temp=true;
                        break;
                    }

                }

                if(temp){
                    Toast.makeText(MainActivity.this,"data was found "+item,Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,ReadArticle.class);
                    intent.putExtra("title",title2);
                    intent.putExtra("description",description);
                    intent.putExtra("DateCreated",date);
                    startActivity(intent);
                    temp=false;
                }else
                    Toast.makeText(MainActivity.this,"spinner not loaded",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


public final boolean isInternetOn(){
    ConnectivityManager connect=(ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
    if(connect.getNetworkInfo(0).getState()==android.net.NetworkInfo.State.CONNECTED ||
            connect.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
            connect.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
            connect.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED){

            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;
    }else if(
        connect.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                connect.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            return false;
    }
        return false;


}}


// String response=result.toString();
//            try {
//                JSONArray jArray=new JSONArray(response);
//                //JSONObject obj=jArray.getJSONObject(1);
//
//               txtFetchedData.append(jArray.getString(1).toString());
//
//               for(int i=0;i<jArray.length();i++){
//                    title_array.add(jArray.getString(i).toString());
//
//               }
//
//            String response=result.toString();
//            try {
//              JSONArray jArray=new JSONArray(response);
//               for(i=0;i<jArray.length();i++){
//                   JSONObject obj=jArray.getJSONObject(i);
//                   String dt=obj.getString("Title");
//                    sData.append(i+": "+dt+"\n");
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }