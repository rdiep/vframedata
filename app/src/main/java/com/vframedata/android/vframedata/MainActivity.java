package com.vframedata.android.vframedata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vframedata.android.vframedata.Pojo.Zangief.Moves.Normal.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.vframedata.android.vframedata.Utility.readJsonFile;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    List<Character> listCharacter;
    SearchView searchView;
    RecyclerViewAdapter myAdapter;
    RecyclerView recyclerView;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCharacter = new ArrayList<>();
        makeCharacterList(listCharacter);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
        myAdapter = new RecyclerViewAdapter(this, listCharacter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(myAdapter);
        recyclerView.hasFixedSize();
        activity = (Activity) this;
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return false;
            }
        });


        searchView = (SearchView) findViewById(R.id.searchview_id);
        searchView.setOnQueryTextListener(this);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });

    }



    private void makeCharacterList(List<Character> list) {
        list.add(new Character("Abigail", R.drawable.abigail));
        list.add(new Character("Akuma", R.drawable.akuma));
        list.add(new Character("Alex", R.drawable.alex));
        list.add(new Character("Balrog", R.drawable.balrog));
        list.add(new Character("Birdie", R.drawable.birdie));
        list.add(new Character("Blanka", R.drawable.blanka));
        list.add(new Character("Cammy", R.drawable.cammy));
        list.add(new Character("Chun-Li", R.drawable.chunli));
        list.add(new Character("Cody", R.drawable.cody));
        list.add(new Character("Dhalsim", R.drawable.dhalsim));
        list.add(new Character("Ed", R.drawable.ed));
        list.add(new Character("Falke", R.drawable.falke));
        list.add(new Character("Fang", R.drawable.fang));
        list.add(new Character("G", R.drawable.g));
        list.add(new Character("Guile", R.drawable.guile));
        list.add(new Character("Ibuki", R.drawable.ibuki));
        list.add(new Character("Juri", R.drawable.juri));
        list.add(new Character("Karin", R.drawable.karin));
        list.add(new Character("Ken", R.drawable.ken));
        list.add(new Character("Kolin", R.drawable.kolin));
        list.add(new Character("Laura", R.drawable.laura));
        list.add(new Character("M.Bison", R.drawable.mbison));
        list.add(new Character("Menat", R.drawable.menat));
        list.add(new Character("Nash", R.drawable.nash));
        list.add(new Character("Necalli", R.drawable.necalli));
        list.add(new Character("Rashid", R.drawable.rashid));
        list.add(new Character("R.Mika", R.drawable.rmika));
        list.add(new Character("Ryu", R.drawable.ryu));
        list.add(new Character("Sagat", R.drawable.sagat));
        list.add(new Character("Sakura", R.drawable.sakura));
        list.add(new Character("Urien", R.drawable.urien));
        list.add(new Character("Vega", R.drawable.vega));
        list.add(new Character("Zangief", R.drawable.zangief));
        list.add(new Character("Zeku (Old)", R.drawable.zeku));
        list.add(new Character("Zeku (Young)", R.drawable.zeku));
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String searchName = s.toLowerCase();
        List<Character> newList = new ArrayList<>();

        for (Character character : listCharacter) {
            if (character.getName().toLowerCase().contains(searchName)) {
                newList.add(character);
            }

        }
        myAdapter.updateList(newList);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
