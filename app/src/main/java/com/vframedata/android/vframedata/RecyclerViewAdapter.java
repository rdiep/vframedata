package com.vframedata.android.vframedata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vframedata.android.vframedata.Colors.Colors;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.MyViewHolder>{


    private final Context mContext;
    private List<Character> mData;

    public RecyclerViewAdapter(Context mContext, List<Character> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_character, viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tv_character_name.setText(mData.get(i).getName());
        //myViewHolder.il_character_thumbnail.setImageResource(mData.get(i).getThumbnail());
        Glide.with(mContext).load(mData.get(i).getThumbnail()).into(myViewHolder.il_character_thumbnail);
        myViewHolder.cardView.setBackgroundResource(R.drawable.cardview_border_default);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView tv_character_name;
        ImageView il_character_thumbnail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            tv_character_name = (TextView) itemView.findViewById(R.id.character_id);
            il_character_thumbnail = (ImageView) itemView.findViewById(R.id.image_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cardView.setBackgroundResource(R.drawable.cardview_border);
                    String Character = tv_character_name.getText().toString();
                    characterSelect(Character,view);
                    //change color of cardview and then change back.
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            cardView.setBackgroundResource(R.drawable.cardview_border_default);
                        }
                    }, 500L);    // 0.5 Seconds

                }
            });
        }

        public void characterSelect(String character, View view){
            Intent intent = null;
            switch(character){
                case "Abigail":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Abigail");
                    intent.putExtra("NUMBER",0);
                    break;
                case "Akuma":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Akuma");
                    intent.putExtra("NUMBER",1);
                    break;
                case "Alex":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Alex");
                    intent.putExtra("NUMBER",2);
                    break;
                case "Balrog":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Balrog");
                    intent.putExtra("NUMBER",3);
                    break;
                case "Birdie":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Birdie");
                    intent.putExtra("NUMBER",4);
                    break;
                case "Blanka":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Blanka");
                    intent.putExtra("NUMBER",5);
                    break;
                case "Cammy":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Cammy");
                    intent.putExtra("NUMBER",6);
                    break;
                case "Chun-Li":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Chun-Li");
                    intent.putExtra("NUMBER",7);
                    break;
                case "Cody":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Cody");
                    intent.putExtra("NUMBER",8);
                    break;
                case "Dhalsim":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Dhalsim");
                    intent.putExtra("NUMBER",9);
                    break;
                case "Ed":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Ed");
                    intent.putExtra("NUMBER",10);
                    break;
                case "Falke":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Falke");
                    intent.putExtra("NUMBER",11);
                    break;
                case "Fang":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "F.A.N.G");
                    intent.putExtra("NUMBER",12);
                    break;
                case "G":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "G");
                    intent.putExtra("NUMBER",13);
                    break;
                case "Guile":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Guile");
                    intent.putExtra("NUMBER",14);
                    break;
                case "Ibuki":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Ibuki");
                    intent.putExtra("NUMBER",15);
                    break;
                case "Juri":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Juri");
                    intent.putExtra("NUMBER",16);
                    break;
                case "Karin":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Karin");
                    intent.putExtra("NUMBER",17);
                    break;
                case "Ken":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Ken");
                    intent.putExtra("NUMBER",18);
                    break;
                case "Kolin":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Kolin");
                    intent.putExtra("NUMBER",19);
                    break;
                case "Laura":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Laura");
                    intent.putExtra("NUMBER",20);
                    break;
                case "M.Bison":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "M.Bison");
                    intent.putExtra("NUMBER",21);
                    break;
                case "Menat":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Menat");
                    intent.putExtra("NUMBER",22);
                    break;
                case "Nash":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Nash");
                    intent.putExtra("NUMBER",23);
                    break;
                case "Necalli":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Necalli");
                    intent.putExtra("NUMBER",24);
                    break;
                case "Rashid":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Rashid");
                    intent.putExtra("NUMBER",25);
                    break;
                case "R.Mika":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "R.Mika");
                    intent.putExtra("NUMBER",26);
                    break;
                case "Ryu":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Ryu");
                    intent.putExtra("NUMBER",27);
                    break;
                case "Sagat":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Sagat");
                    intent.putExtra("NUMBER",28);
                    break;
                case "Sakura":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Sakura");
                    intent.putExtra("NUMBER",29);
                    break;
                case "Urien":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Urien");
                    intent.putExtra("NUMBER",30);
                    break;
                case "Vega":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Vega");
                    intent.putExtra("NUMBER",31);
                    break;
                case "Zangief":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Zangief");
                    intent.putExtra("NUMBER",32);
                    break;
                case "Zeku (Old)":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Zeku (Old)");
                    intent.putExtra("NUMBER",33);
                    break;
                case "Zeku (Young)":
                    intent  = new Intent(view.getContext(),TestPage.class);
                    intent.putExtra("NAME", "Zeku (Young)");
                    intent.putExtra("NUMBER",33);
                    break;

                default:
                    break;
            }
            view.getContext().startActivity(intent);
            ((Activity) view.getContext()).overridePendingTransition(R.anim.slide_in_up, R.anim.stay);

        }
    }

    public void updateList(List<Character> newList){
        mData = new ArrayList<>();
        mData.addAll(newList);
        notifyDataSetChanged();

    }



}
