package com.example.student.readingroomdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    String user="",pass="",con_pass="";
    EditText reg_username,reg_password,confirm_pass;
    TextView tv;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        register=(Button) findViewById(R.id.btn_register);
        tv=(TextView) findViewById(R.id.textView2);
        this.setTitle("Create News-Room Account");
    }

    @Override
    protected void onResume() {
        register.setOnClickListener(this);
        super.onResume();
    }

//    public class Data2 extends AsyncTask<String,Integer,String>{
//
//        int response,dataLength;
//        String msg,msg2;
//        HttpURLConnection myConn;
//        byte[] postData;
//        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//        public void usingToken(){
//            try {
////                String urlParameters  = "param1=data1&param2=data2&param3=data3";
////                byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
////                int postDataLength = postData.length;
////                conn.setRequestProperty("Content-Length", Integer.toString(postDataLength ));
//
//                String urlParameters="Email=bob22232@immedia.co.za&Password=Password1!&ConfirmPassword=Password1!";
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//                    postData=urlParameters.getBytes(StandardCharsets.UTF_8);
//                    dataLength=postData.length;
//                }
//
//                URL myUrl=new URL("http://reading-room.azurewebsites.net/api/Account/Register");
//                myConn=(HttpURLConnection)myUrl.openConnection();
//                myConn.setRequestMethod("POST");
//
////                myConn.setRequestProperty("Content-Type","application/json");
////                myConn.setRequestProperty("Accept","application/json");
////                myConn.setRequestProperty("charset", "utf-8");
////                myConn.setRequestProperty("Content-Length",Integer.toString(dataLength));
//////                myConn.setRequestProperty("Email","bob22232@immedia.co.za");
//////                myConn.setRequestProperty("Password","Password1&#33;");
//////                myConn.setRequestProperty("ConfirmPassword","Password1&#33;");
////
////                try(DataOutputStream wr=new DataOutputStream(myConn.getOutputStream())){
////                    wr.write(postData);
////                    msg2=postData.toString();
////                }
////                catch (IOException e){
////                    e.printStackTrace();
////                }
////                response=myConn.getResponseCode();
////                msg=myConn.getResponseMessage();
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//        @Override
//        protected String doInBackground(String... strings) {
//            usingToken();
//            return null;
//        }
//        @Override
//        protected void onPostExecute(String s) {
//
////            msg2=myConn.getErrorStream().toString();
//            if(response==200)
//                Toast.makeText(SignUp.this, response,Toast.LENGTH_LONG).show();
//            else
//                Toast.makeText(SignUp.this, msg+"\n"+response,Toast.LENGTH_LONG).show();
//
//            super.onPostExecute(s);
//        }
//    }

    @Override
    public void onClick(View view) {
        if(view==register){
//           new Like(122).execute();
        }
    }
}
