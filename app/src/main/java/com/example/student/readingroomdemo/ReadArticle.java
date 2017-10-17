package com.example.student.readingroomdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReadArticle extends AppCompatActivity {
    TextView txtTitle,txtArticle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_article);
        txtTitle=(TextView) findViewById(R.id.txt_title);
        txtArticle=(TextView) findViewById(R.id.txt_article);

        Bundle extras = getIntent().getExtras();
        String title=extras.getString("title");
        String description=extras.getString("description");
        txtTitle.setText(title);
        txtArticle.setText(description);
    }
}
