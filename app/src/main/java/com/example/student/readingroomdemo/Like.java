package com.example.student.readingroomdemo;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.student.readingroomdemo.Main2Activity.response;

/**
 * Created by student on 2017/10/12.
 */


class Like extends AsyncTask<Integer,Integer,String> {

    int postId;
    String endpoint;
       public Like(String endpoint,int postId){
           this.postId=postId;
           this.endpoint=endpoint;
       }

        boolean status=false;
        public void likeArticle(){
            try {
                URL url=new URL(endpoint+""+postId);
                HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Authorization","bearer DkWDjVOced5co2f3GbuM_nT236TT4aU2rbmdssphxLy1hkHYYv_sPEpP4APB51e3JWCD76Qm1e0UZwuwOvYeBwDQFpUbzAbpyNBcRgm-IgUnv_fF5-CxctUx2eJuy3MvMAKR-Vt-0DAKZ1iL_9-Z4LMb9YGmVu9V-GM-1oVnrWoLDbKOq9wWCc7PiFlPu8KCG0iBqDO0K8fuMN0Mpp8Jkc3ssPV38AktpHSMKirj-dbLxyLY4OFkbdKabtDJ7QyNvAEczoJw_GssTkN9D-yriatA0bq2GbZPOhYyvY2Nkspb9P7TGzFH-7lq21ohB-COgmuqvNAPC133N7VG2g4n5SN7MPzo2nXkLXhOOIRNY0rjib9grbDu5P8zj3RhhWMUe37dr8FrSSOB8gT6LUQjaZXib-aH32twMt_9oyoNY38LXPykpEyYXz8XP99PvOnqu4sjLJ8KXiWlbmBtOM95b0bF5tggCA1vvYhMtH0MQIC9K7bqgxiya9DwxtO7n9ZO");
                conn.setRequestProperty("Accept","application/json");
                conn.setRequestMethod("POST");
                response=conn.getResponseCode();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        protected String doInBackground(Integer... integers) {
            likeArticle();

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            if(response==200) {
                status=true;
            }
            if(status){
//                Toast.makeText(SignUp.this,response+" ",Toast.LENGTH_LONG).show();
            }
            else
//                Toast.makeText(SignUp.this,response+" ",Toast.LENGTH_LONG).show();

            super.onPostExecute(s);
        }
    }

