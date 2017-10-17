package com.example.student.readingroomdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    Button signIn,signUp;
    static EditText username,password;
//    TextView tv;

    @Override
    protected void onResume() {

        SharedPreferences.Editor e= PreferenceManager.getDefaultSharedPreferences(this).edit();
        e.putString("last_actvity",getClass().getSimpleName());
        e.commit();

        signIn.setOnClickListener(this);
//        signUp.setOnClickListener(this);
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        android.support.v7.app.ActionBar bar=getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#373737")));

        signIn = (Button) findViewById(R.id.btn_sign_in);
//        signUp=(Button) findViewById(R.id.btn_sign_up);
        username = (EditText) findViewById(R.id.txt_username);
        password = (EditText) findViewById(R.id.txt_password);
//        tv=(TextView) findViewById(R.id.textView3) ;
        username.setText("immediatesters@gmail.com");
        password.setText("Pass");
       GetData dg=new GetData();
//        tv.setText(dg.parsedRes+"--\n");

        this.setTitle("Login Reading-Room");
//        for(int i=0;i<myArticles.size();i++){
//            MyArticle article=myArticles.get(i);
//            tv.append(article.title+"\n");
//        }
//        connection.setRequestProperty("Authorization","bearer 4QHobVkAp8eG6sCDkIm46cltfWYlm7Bym73CAwwxivmMrUNHUuUdqoQxMOKUQ3ifnhGfQCgoKYvNmLn4SRdUHSafJQInV-pDt5bhRWsuiAPiP_53FMkO8DcUhkphSzza0mm5DGxNrcwnvDQ3gGmlYgaMSKMZtaylDPpPftVwB9IHReVKPpN4KnJuG-1eeT2Br-n7-19hfs8Z3_5UE-K9IPdclShuvHz3nnCvrxaHnM1XKG4d95T3bO0LaUrLJgwH7XXfEGKgUwqmIyLjwMG2DuBGGaYDwrrtXAsHmnAUcij9OjproGxcOi0832f8U29w_jDpOAd-PGIJoXfxcs5w0HuQu7DNQQp_14x8TwzxREWGIGKgqbvpUY7ZVQZJVwUntd2hzimUwFxWxhQCkimfoOshgamzFp8o769M3wSNs1FdZgeG-DKPz3jlwn-6AxJjq1n7Q1Ed-hca8qzmhUiMNwof2JSIGV-y0tmWVZrhvxpa_DSTuP_a2jlR5VufYZm8");
//
//        connection.setUseCaches(false);
//        connection.setDoInput(true);
//        connection.setDoOutput(true);
//        username.setText("immediatesters@gmail.com");
//        password.setText("Password1!");
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest sr = new StringRequest(Request.Method.POST, "http://reading-room.azurewebsites.net/token", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(LoginActivity.this,"successful:"+response,Toast.LENGTH_LONG).show();
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(LoginActivity.this,"not successful:"+error.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> header = new HashMap<String, String>();
//                header.put("Content-Type","application/x-www-form-urlencoded");
//                return super.getHeaders();
//            }
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("username","immediatesters@gmail.com");
//                params.put("password","Password1!");
//                params.put("grant_type","password");
//                return super.getParams();
//            }
//        };
//        queue.add(sr);
      }



//    public class Data2 extends AsyncTask<String,Integer,String> {
//
//        int response;
//        String msg;
//        public void usingToken(){
//            try {
//                URL myUrl=new URL("http://reading-room.azurewebsites.net/api/reading/GetPosts");
//                HttpURLConnection myConn=(HttpURLConnection)myUrl.openConnection();
//                myConn.setRequestProperty("Authorization","bearer DkWDjVOced5co2f3GbuM_nT236TT4aU2rbmdssphxLy1hkHYYv_sPEpP4APB51e3JWCD76Qm1e0UZwuwOvYeBwDQFpUbzAbpyNBcRgm-IgUnv_fF5-CxctUx2eJuy3MvMAKR-Vt-0DAKZ1iL_9-Z4LMb9YGmVu9V-GM-1oVnrWoLDbKOq9wWCc7PiFlPu8KCG0iBqDO0K8fuMN0Mpp8Jkc3ssPV38AktpHSMKirj-dbLxyLY4OFkbdKabtDJ7QyNvAEczoJw_GssTkN9D-yriatA0bq2GbZPOhYyvY2Nkspb9P7TGzFH-7lq21ohB-COgmuqvNAPC133N7VG2g4n5SN7MPzo2nXkLXhOOIRNY0rjib9grbDu5P8zj3RhhWMUe37dr8FrSSOB8gT6LUQjaZXib-aH32twMt_9oyoNY38LXPykpEyYXz8XP99PvOnqu4sjLJ8KXiWlbmBtOM95b0bF5tggCA1vvYhMtH0MQIC9K7bqgxiya9DwxtO7n9ZO");
//                myConn.setRequestProperty("Accept","application/json");
//                myConn.setRequestMethod("GET");
//
//                response=myConn.getResponseCode();
//                msg=myConn.getResponseMessage();
//
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        @Override
//        protected String doInBackground(String... strings) {
//            usingToken();
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//                if(response==200){
////                    Toast.makeText(LoginActivity.this, response+" ",Toast.LENGTH_LONG).show();
//                }
//                else
////                    Toast.makeText(LoginActivity.this, msg+"\nResponse: "+response,Toast.LENGTH_LONG).show();
//
//            super.onPostExecute(s);
//        }
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void login(){


        if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
            Toast.makeText(LoginActivity.this, "One or both text edits are empty. please input some data ",Toast.LENGTH_LONG).show();
        }else
        {
            if(username.getText().toString().equals("immediatesters@gmail.com")&& password.getText().toString().equals("Pass")){
                new GetData().execute();
                Intent i=new Intent(LoginActivity.this,Main2Activity.class);
                startActivity(i);
                LoginActivity.this.finish();
            }else
                Toast.makeText(LoginActivity.this, "Wrong credentials, please try again ",Toast.LENGTH_LONG).show();
        }
        }




    /**/
    private boolean isValidEmail(String email){
        String EMAIL_PARTTEN="^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\"\n" +
                "\t\t\t\t+ \"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$";

        Pattern pattern=Pattern.compile(EMAIL_PARTTEN);
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();

    }
    @Override
    public void onClick(View view) {
        if(view==signIn){
            login();
        }

    }

//        RequestQueue queue= Volley.newRequestQueue(this);
//        GsonRequest request=new GsonRequest(Request.Method.POST,"http://reading-room.azurewebsites.net/", listener,errorListener ){
//        };
//        queue.add(request);
//    }
//
//    Response.ErrorListener errorListener=new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            tv.setText("Error: "+error.getMessage());
//        }
//    };
//    byteArrayResponse listener=new byteArrayResponse() {
//        @Override
//        public void onByteArrayResponse(byte[] response) {
//            String resp=response.toString();
//            tv.setText(resp);
//            Log.d("Response",resp);
//
//        }
//    };
//
//
//
//
//    public interface byteArrayResponse{
//        public void onByteArrayResponse(byte[] response);
//    }
//http://reading-room.azurewebsites.net/token/
    //

}
