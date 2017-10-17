package com.example.student.readingroomdemo;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by student on 2017/09/22.
 */

public class ListAdapter extends ArrayAdapter<String>{

    private  Activity context;
    private ArrayList<String> lvTitle, lvImgUrl, lvDesc;
    private ArrayList<Integer> like,pId;
    int clickCount;
    public ListAdapter(Activity context, ArrayList<String> lvTitle, ArrayList<String> lvImgUrl, ArrayList<String> lvDesc,ArrayList<Integer> like,ArrayList<Integer> pId) {
        super(context, R.layout.from_api_list, lvImgUrl);
        this.context=context;
        this.lvTitle=lvTitle;
        this.lvImgUrl=lvImgUrl;
        this.lvDesc=lvDesc;
        this.like=like;
        this.pId=pId;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.from_api_list, null, true);

            final ViewHolder viewHolder=new ViewHolder();

            TextView txtTitle=(TextView) convertView.findViewById(R.id.txt_title);
            //TextView txtDesc=(TextView) convertView.findViewById(R.id.txt_discr);
            final TextView lks=(TextView) convertView.findViewById(R.id.num_like);
            TextView txtUrl=(TextView) convertView.findViewById(R.id.txt_url);
            ImageView img=(ImageView )convertView.findViewById(R.id.imageView1);
            viewHolder.toggleButton=(ToggleButton) convertView.findViewById(R.id.toggleButton2);


            txtUrl.setText(lvImgUrl.get(position));
            txtTitle.setText(lvTitle.get(position));
            lks.setText("Likes "+like.get(position).toString());
            Picasso.with(context)
                    .load(lvDesc.get(position))
                    .placeholder(R.drawable.newss)
                    .into(img);
            viewHolder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        new Like("http://reading-room.azurewebsites.net/api/reading/LikePost?PostId=",pId.get(position)).execute();
                        int liked=like.get(position);
                        liked=liked+1;
                        lks.setText("Liked "+liked);
                    }
                    else{
                        new Like("http://reading-room.azurewebsites.net/api/reading/UnlikePost?PostId=",pId.get(position)).execute();
                        int unLike=like.get(position);
                        unLike=unLike-1+1;
                        lks.setText("Likes "+unLike);
                    }
                }
            });
        }
        return convertView;
    }


    public void clearItems() {
        lvTitle.clear();
        lvImgUrl.clear();
        lvDesc.clear();
        notifyDataSetChanged();
    }

    private class ViewHolder{
        ToggleButton toggleButton;
    }
}