package com.example.student.readingroomdemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_list_item_1;
import static com.example.student.readingroomdemo.MainActivity.myArticles;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    ListView lv;
    TextView tv;
    EditText txtPost;
    String title,descripton,date,jSon;
    ImageButton imgButton;
    SwipeRefreshLayout refreshLayout;
    ArrayList<String> imageArray2;

    static ProgressBar pBar;
    Integer progress=0;
//   String[] itemname={"king of th jungle: Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
//            "Lovely birds: Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
//            "Great tiger: Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
//            "Endless railway: Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."};
//    Integer[] imgId={R.drawable.image,R.drawable.image2,R.drawable.image3,R.drawable.image4};
     // Integer[] imgId=new Integer[myArticles.size()];

    //GetData getDt=new GetData();
    ArrayList<String> titles =new ArrayList<>();
    ArrayList<String> desc = new ArrayList<>();
    ArrayList<String> imgUrl = new ArrayList<>();

    ArrayList<Integer> lk=new ArrayList<>();
    ArrayList<Integer> pId=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.setTitle("Read Articles");
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        android.support.v7.app.ActionBar bar=getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#373737")));

        imgButton=(ImageButton) findViewById(R.id.btn_send);
        txtPost=(EditText) findViewById(R.id.txt_post);
        imgButton.setOnClickListener(this);
       refreshLayout=(SwipeRefreshLayout) findViewById(R.id.refresh_list);

        pBar=(ProgressBar) findViewById(R.id.progressBar);
//        pBar.setMax(100);


//        new GetData().execute();
//        loadFromApi();

        //loadFromApi();
        //lv=(ListView) findViewById(R.id.lv_aricles);
        //loadImgAndText();

    }
    public void progress(final int process){
//        simpleProgressBar.setProgress(progress);
//        // thread is used to change the progress value
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                setProgressValue(progress + 10);
//            }
//        });
//        thread.start();

        pBar.setProgress(process);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progress(process+25);
            }
        });
        thread.start();

    }

    @Override
    protected void onResume() {
        SharedPreferences.Editor e= PreferenceManager.getDefaultSharedPreferences(this).edit();
        e.putString("last_actvity",getClass().getSimpleName());
        e.commit();

        new GetData().execute();
        loadFromApi();

        Intent intent=getIntent();
        String action=intent.getAction();
        String type=intent.getType();

        if(Intent.ACTION_SEND.equals(action) && type!=null){
            if("text/plain".equals(type)){
                String sharedTxt=intent.getStringExtra(Intent.EXTRA_TEXT);
                txtPost.setText(sharedTxt);
            }
        }

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetData().execute();
                loadFromApi();
                refreshLayout.setRefreshing(false);
            }
        });
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void loadLv(){
        final List<String> listItems =new ArrayList<>();
        int count=1;
        for (int x = 0; x < myArticles.size(); x++) {
            MyArticle article = myArticles.get(x);
            listItems.add(count+" :"+article.title);
            count++;
            //sData.append(x+". Title: "+article.title + "\n"+"Description: "+article.description+"\n------------------------------------------------\n");
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(Main2Activity.this,simple_list_item_1, listItems);
       lv.setAdapter(adapter);
    }

    public void dialog(){
    final AlertDialog.Builder alertBuilder=new AlertDialog.Builder(this,R.style.MyDialogTheme);
    alertBuilder.setTitle("Logout");
    alertBuilder.setMessage("Are you sure you want to logout")
            .setCancelable(false)
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent=new Intent(Main2Activity.this,LoginActivity.class);
                    startActivity(intent);
                    Main2Activity.this.finish();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
        }
    });
    AlertDialog alert=alertBuilder.create();
    alert.show();

}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_main2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    int id =item.getItemId();
        if(id==R.id.action_logout){
            dialog();
        }
        return true;
    }

    public void loadFromApi(){

        lv=(ListView) findViewById(R.id.lv_aricles);
        titles.clear();
        desc.clear();
        imgUrl.clear();

        for(int i=0;i<myArticles.size();i++){
            MyArticle article=myArticles.get(i);
            titles.add(article.title);
            desc.add(article.description);
            imgUrl.add(article.imageLink);
            lk.add(article.likes);
            pId.add(article.id);
            if(i==3){
                String k = "";
            }
            else if (i == 4)
            {
                String k = "";
            }


        }
        ListAdapter lAdapter=new ListAdapter(this,titles,desc,imgUrl,lk,pId);

        lv.setAdapter(lAdapter);
    }
    public void post(){
        RequestQueue queue= Volley.newRequestQueue(this);
        String endPoint="http://reading-room.azurewebsites.net/api/reading/PostAnonToDb?UserId=123&Url="+txtPost.getText().toString();
        boolean check=false;
        String empty=txtPost.getText().toString();

       if(empty.isEmpty() || empty.length()<4){
           Toast.makeText(Main2Activity.this, "did not reach minimum number of input characters",Toast.LENGTH_LONG).show();
       }else {
           String httpCeck= txtPost.getText().toString();
           String s=httpCeck.substring(0,4);
           if(s.equals("http")){
               check=true;
           }
           if(check){
               StringRequest request=new StringRequest(Request.Method.POST, endPoint,
                       new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {
                               Toast.makeText(Main2Activity.this, "Successfully posted to Reading-Room",Toast.LENGTH_LONG).show();
                               new GetData().execute();
                               txtPost.setText("");
                           }
                       }, new Response.ErrorListener(){

                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Toast.makeText(Main2Activity.this, "Something went wrong. Post was not successful",Toast.LENGTH_LONG).show();
                   }
               });
               queue.add(request);
           }else
               Toast.makeText(Main2Activity.this, "Make sure your link begins with http!!",Toast.LENGTH_LONG).show();
       }
       }

    @Override
    public void onClick(View view) {
        if(view==imgButton){
            post();
        }
    }

    static int response;


}



