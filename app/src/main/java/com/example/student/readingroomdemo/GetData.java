package com.example.student.readingroomdemo;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.student.readingroomdemo.MainActivity.myArticles;

/**
 * Created by student on 2017/10/02.
 */

public class GetData extends AsyncTask<String,Integer,String>{
    static String parsedRes;
    ProgressBar progressBar;



    @Override
    protected String doInBackground(String... strings) {
        String requestUrl="http://reading-room.azurewebsites.net/api/reading/GetPosts";

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
    protected void onPreExecute() {

//        progressBar.setVisibility(View.VISIBLE);
//        progressBar.setMax(100);
        super.onPreExecute();
    }

//    public void progr(int process){
//        pBar.setProgress(process);
//        Thread thread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();
//    }

    @Override
    protected void onProgressUpdate(Integer... values) {
//        onProgressUpdate(values[0]);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
//        progressBar.setVisibility(View.GONE);
        if(result==null)
            result="ERROR OCCURED";
//        pBar.setVisibility(View.GONE);
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
           // sData.append(x+". Title: "+article.title + "\n"+"Description: "+article.description+"\n------------------------------------------------\n");
            Log.d("",result);
        }
    }
    public class vHolder{
        ProgressBar pro;
    }

}

