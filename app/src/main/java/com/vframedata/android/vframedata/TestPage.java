package com.vframedata.android.vframedata;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vframedata.android.vframedata.Colors.Colors;
import com.vframedata.android.vframedata.DynamicTableLayout.HorizontalScroll;
import com.vframedata.android.vframedata.DynamicTableLayout.VerticalScroll;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.Character;
import java.util.Iterator;

import static com.vframedata.android.vframedata.Utility.readJsonFile;

public class TestPage extends AppCompatActivity implements HorizontalScroll.ScrollViewListener, VerticalScroll.ScrollViewListener{

    String[] header = {"startup","active","recovery","onBlock","onHit","damage","stun"};

    TextView stun, health;

    private static int SCREEN_HEIGHT;
    private static int SCREEN_WIDTH;
    RelativeLayout relativeLayoutMain;

    RelativeLayout relativeLayoutA;
    RelativeLayout relativeLayoutB;
    RelativeLayout relativeLayoutC;
    RelativeLayout relativeLayoutD;

    TableLayout tableLayoutA;
    TableLayout tableLayoutB;
    TableLayout tableLayoutC;
    TableLayout tableLayoutD;

    TableRow tableRow;
    TableRow tableRowB;

    HorizontalScroll horizontalScrollViewB;
    HorizontalScroll horizontalScrollViewD;

    VerticalScroll scrollViewC;
    VerticalScroll scrollViewD;


    /*
     This is for counting how many columns are added in the row.
*/
    int tableColumnCountB= 0;

    /*
         This is for counting how many row is added.
    */
    int tableRowCountC= 0;
    GradientDrawable gd;
    Colors charColor;
    String Name;
    int Num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Name = getIntent().getStringExtra("NAME");
        Num = getIntent().getIntExtra("NUMBER",0);
        charColor = new Colors(this);
        gd = new GradientDrawable();
        gd.setStroke(1,charColor.listColors[Num]);
        setContentView(R.layout.activity_character_page);
        // Inflate your custom layout
        // Set up your ActionBar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(charColor.listColors[Num]));
        stun = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.Stun);
        health = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.Health);
        TextView characterName = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.character_id);
        characterName.setText(Name);

        getSupportActionBar().getCustomView().setOnTouchListener( new Swipe_listener(getApplicationContext()){
            public void onSwipeDown() {
                finish();
            }

            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });


        relativeLayoutMain= (RelativeLayout)findViewById(R.id.relativeLayoutMain);

        getScreenDimension();
        initializeRelativeLayout();
        initializeScrollers();
        initializeTableLayout();

        horizontalScrollViewB.setScrollViewListener( this);
        horizontalScrollViewD.setScrollViewListener(this);
        scrollViewC.setScrollViewListener(this);
        scrollViewD.setScrollViewListener(this);

        addRowToTableA();
        initializeRowForTableB();
                /*
            Till Here.
         */


        //header add
        createHeader();

        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute();






        /*  There is two unused functions
            Have a look on these functions and try to recreate and use it.
            createCompleteColumn();
            createCompleteRow();
        */
//
//        for(int i=0; i<20; i++){
//            initializeRowForTableD(i);
//            addRowToTableC("Row"+ i);
//            for(int j=0; j<tableColumnCountB; j++){
//                addColumnToTableAtD(i, "D "+ i + " " + j);
//            }
//        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()){
            overridePendingTransition(R.anim.stay, R.anim.slide_out_down);
        }

    }

    private class AsyncTaskRunner  extends AsyncTask<Void,Void, String>{

        @Override
        protected String doInBackground(Void... voids) {


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            int rownumber = 0;
            try{
                InputStream inputStream = getAssets().open("sfv.json");
                String jsonString = readJsonFile(inputStream);
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONObject gief = jsonObject.getJSONObject(Name);

                JSONObject stats = gief.getJSONObject("stats");
                JSONObject moves = gief.getJSONObject("moves");
                JSONObject normal = moves.getJSONObject("normal");
                JSONObject vtOne = moves.getJSONObject("vtOne");
                JSONObject vtTwo = moves.getJSONObject("vtTwo");

                //Set health and stun
                HealthStun(stats);
                //Iterate through and get all the normal moves////////////////////////////////////////////////
                Gson gson =  new Gson();
                rownumber = addFrameDataRow(normal,rownumber);
                //Iterate through and get all the vtone////////////////////////////////////////////////////////////
                initializeRowForTableD(rownumber);
                addRowToTableC("VtOne", true);
                for(String ss: header){
                    addColumnToTableAtD(rownumber, " ", true, false);
                }
                rownumber++;
                rownumber = addFrameDataRow(vtOne,rownumber);
                //Iterate through and get all the vtTwo////////////////////////////////////////////////////////////
                initializeRowForTableD(rownumber);
                addRowToTableC("VtTwo", true);
                for(String ss: header){
                    addColumnToTableAtD(rownumber, " ", true, false);
                }
                rownumber++;
                rownumber = addFrameDataRow(vtTwo,rownumber);
            }catch (IOException e) {
                e.printStackTrace();
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void HealthStun(JSONObject stats) {
        try {
            health.setText(stats.getString("health"));
            Log.i("STUN",stats.getString("stun") );
            stun.setText(stats.getString("stun"));
        } catch (JSONException e){

        }
    }

    public int addFrameDataRow(JSONObject move, int rownumber ) throws JSONException {
        for(Iterator<String> iter = move.keys(); iter.hasNext();) {
            String key = iter.next();
            initializeRowForTableD(rownumber);
            addRowToTableC(key, false);
            JSONObject vtonemove = move.getJSONObject(key);
            for (String ss: header){
                try {
                    if (ss.equals("onBlock") || ss.equals("onHit")){
                        addColumnToTableAtD(rownumber, vtonemove.getString(ss),false,true);
                    }else {
                        addColumnToTableAtD(rownumber, vtonemove.getString(ss), false,false);
                    }
                }catch(JSONException e){
                    addColumnToTableAtD(rownumber, " ",false,false);
                }
            }
            rownumber++;

        }
        return rownumber;
    }

    public void createHeader(){
        addColumnsToTableB("S");
        addColumnsToTableB("A");
        addColumnsToTableB("R");
        addColumnsToTableB("B");
        addColumnsToTableB("H");
        addColumnsToTableB("DAMAGE");
        addColumnsToTableB("STUN");
//        addColumnsToTableB("VT1C");
//        addColumnsToTableB("VT2C");
//        addColumnsToTableB("INVINCIBILITY");
    }

    @Override
    public void onScrollChanged(HorizontalScroll scrollView, int x, int y, int oldx, int oldy) {
        if(scrollView == horizontalScrollViewB){
            horizontalScrollViewD.scrollTo(x,y);
        }
        else if(scrollView == horizontalScrollViewD){
            horizontalScrollViewB.scrollTo(x, y);
        }

    }

    @Override
    public void onScrollChanged(VerticalScroll scrollView, int x, int y, int oldx, int oldy) {
        if(scrollView == scrollViewC){
            scrollViewD.scrollTo(x,y);
        }
        else if(scrollView == scrollViewD){
            scrollViewC.scrollTo(x,y);
        }
    }

    public void getScreenDimension(){
        WindowManager wm= (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        SCREEN_WIDTH= size.x;
        SCREEN_HEIGHT = size.y;
    }

    public void initializeRelativeLayout(){
        relativeLayoutA= new RelativeLayout(this);
        relativeLayoutA.setId(R.id.relativeLayoutA);
        relativeLayoutA.setPadding(0,0,0,0);

        relativeLayoutB= new RelativeLayout(this);
        relativeLayoutB.setId(R.id.relativeLayoutB);
        relativeLayoutB.setPadding(0,0,0,0);

        relativeLayoutC= new RelativeLayout(this);
        relativeLayoutC.setId(R.id.relativeLayoutC);
        relativeLayoutC.setPadding(0,0,0,0);

        relativeLayoutD= new RelativeLayout(this);
        relativeLayoutD.setId(R.id.relativeLayoutD);
        relativeLayoutD.setPadding(0,0,0,0);

        relativeLayoutA.setLayoutParams(new RelativeLayout.LayoutParams(SCREEN_WIDTH/3,SCREEN_HEIGHT/20));
        relativeLayoutMain.addView(relativeLayoutA);


        RelativeLayout.LayoutParams layoutParamsRelativeLayoutB= new RelativeLayout.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/5), SCREEN_HEIGHT/20);
        layoutParamsRelativeLayoutB.addRule(RelativeLayout.RIGHT_OF, R.id.relativeLayoutA);
        relativeLayoutB.setLayoutParams(layoutParamsRelativeLayoutB);
        relativeLayoutMain.addView(relativeLayoutB);

        RelativeLayout.LayoutParams layoutParamsRelativeLayoutC= new RelativeLayout.LayoutParams(SCREEN_WIDTH/3, SCREEN_HEIGHT - (SCREEN_HEIGHT/20));
        layoutParamsRelativeLayoutC.addRule(RelativeLayout.BELOW, R.id.relativeLayoutA);
        relativeLayoutC.setLayoutParams(layoutParamsRelativeLayoutC);
        relativeLayoutMain.addView(relativeLayoutC);

        RelativeLayout.LayoutParams layoutParamsRelativeLayoutD= new RelativeLayout.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/5), SCREEN_HEIGHT - (SCREEN_HEIGHT/20));
        layoutParamsRelativeLayoutD.addRule(RelativeLayout.BELOW, R.id.relativeLayoutB);
        layoutParamsRelativeLayoutD.addRule(RelativeLayout.RIGHT_OF, R.id.relativeLayoutC);
        relativeLayoutD.setLayoutParams(layoutParamsRelativeLayoutD);
        relativeLayoutMain.addView(relativeLayoutD);

    }

    public void initializeScrollers(){
        horizontalScrollViewB= new HorizontalScroll(this);
        horizontalScrollViewB.setPadding(0,0,0,0);

        horizontalScrollViewD= new HorizontalScroll(this);
        horizontalScrollViewD.setPadding(0,0,0,0);

        scrollViewC= new VerticalScroll(this);
        scrollViewC.setPadding(0,0,0,0);
        scrollViewC.setVerticalScrollBarEnabled(false);
        scrollViewC.setHorizontalScrollBarEnabled(false);

        scrollViewD= new VerticalScroll(this);
        scrollViewD.setPadding(0,0,0,0);
        scrollViewD.setVerticalScrollBarEnabled(false);
        scrollViewD.setHorizontalScrollBarEnabled(false);

        horizontalScrollViewB.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/3), SCREEN_HEIGHT/20));
        scrollViewC.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH/3 ,SCREEN_HEIGHT - (SCREEN_HEIGHT/20)));
        scrollViewD.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/3), SCREEN_HEIGHT - (SCREEN_HEIGHT/20) ));
        horizontalScrollViewD.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/3), SCREEN_HEIGHT - (SCREEN_HEIGHT/20) ));

        relativeLayoutB.addView(horizontalScrollViewB);
        relativeLayoutC.addView(scrollViewC);
        scrollViewD.addView(horizontalScrollViewD);
        relativeLayoutD.addView(scrollViewD);

    }

    public void initializeTableLayout(){
        tableLayoutA= new TableLayout(this);
        tableLayoutA.setPadding(0,0,0,0);
        tableLayoutA.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        tableLayoutB= new TableLayout(this);
        tableLayoutB.setPadding(0,0,0,0);
        tableLayoutB.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        tableLayoutB.setId(R.id.tableLayoutB);
        tableLayoutC= new TableLayout(this);
        tableLayoutC.setPadding(0,0,0,0);
        tableLayoutC.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        tableLayoutD= new TableLayout(this);
        tableLayoutD.setPadding(0,0,0,0);
        tableLayoutD.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        TableLayout.LayoutParams layoutParamsTableLayoutA= new TableLayout.LayoutParams(SCREEN_WIDTH/3, SCREEN_HEIGHT/20);
        tableLayoutA.setLayoutParams(layoutParamsTableLayoutA);
        tableLayoutA.setBackgroundColor(charColor.listColors[Num]);
        relativeLayoutA.addView(tableLayoutA);

        TableLayout.LayoutParams layoutParamsTableLayoutB= new TableLayout.LayoutParams(SCREEN_WIDTH -(SCREEN_WIDTH/5), SCREEN_HEIGHT/20);
        tableLayoutB.setLayoutParams(layoutParamsTableLayoutB);
        tableLayoutB.setBackgroundColor(charColor.listColors[Num]);
        horizontalScrollViewB.addView(tableLayoutB);

        TableLayout.LayoutParams layoutParamsTableLayoutC= new TableLayout.LayoutParams(SCREEN_WIDTH/3, SCREEN_HEIGHT - (SCREEN_HEIGHT/20));
        tableLayoutC.setLayoutParams(layoutParamsTableLayoutC);
        tableLayoutC.setBackgroundColor(getResources().getColor(R.color.white));
        scrollViewC.addView(tableLayoutC);

        TableLayout.LayoutParams layoutParamsTableLayoutD= new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
        tableLayoutD.setLayoutParams(layoutParamsTableLayoutD);
        horizontalScrollViewD.addView(tableLayoutD);

    }


    public void addRowToTableA(){
        tableRow= new TableRow(this);
        tableRow.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(SCREEN_WIDTH/3, SCREEN_HEIGHT/20);
        tableRow.setLayoutParams(layoutParamsTableRow);
        TextView label_date = new TextView(this);
        label_date.setText("Moves");
        label_date.setTextColor(getResources().getColor(R.color.white));
        TextViewCompat.setAutoSizeTextTypeWithDefaults(label_date, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        tableRow.addView(label_date);
        tableLayoutA.addView(tableRow);
    }

    public void initializeRowForTableB(){
        tableRowB= new TableRow(this);
        tableRow.setPadding(0,0,0,0);
        tableRow.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        tableLayoutB.addView(tableRowB);
    }

    public synchronized void addColumnsToTableB(String text){
        tableRow= new TableRow(this);
        tableRow.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT/20);
        tableRow.setPadding(3,3,3,3);
        tableRow.setLayoutParams(layoutParamsTableRow);
        TextView label_date = new TextView(this);
        label_date.setText(text);
        label_date.setTextColor(getResources().getColor(R.color.white));
        TextViewCompat.setAutoSizeTextTypeWithDefaults(label_date, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        label_date.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        tableRow.addView(label_date);
        tableRowB.addView(tableRow);
        tableColumnCountB++;
    }

    public synchronized void addRowToTableC(String text, boolean title){
        TableRow tableRow1= new TableRow(this);
        tableRow1.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        TableRow.LayoutParams layoutParamsTableRow1= new TableRow.LayoutParams(SCREEN_WIDTH/3, SCREEN_HEIGHT/20);
        tableRow1.setPadding(3,3,3,3);
        tableRow1.setLayoutParams(layoutParamsTableRow1);
        TextView label_date = new TextView(this);
        label_date.setText(text);
        if (title == true){
            label_date.setTextColor(getResources().getColor(R.color.white));
        }
        //label_date.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
        TextViewCompat.setAutoSizeTextTypeWithDefaults(label_date, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        label_date.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        tableRow1.addView(label_date);

        TableRow tableRow= new TableRow(this);
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(SCREEN_WIDTH/3, SCREEN_HEIGHT/20);
        tableRow.setPadding(0,0,0,0);
        tableRow.setLayoutParams(layoutParamsTableRow);
        tableRow.addView(tableRow1);
        tableRow.setBackground(gd);
        //tableRow.setBackground((charColor.listCells[Num]));

        //tableRow.setBackgroundColor(charColor.listColors[1]);
        if (title == true) {
            tableRow.setBackgroundColor(charColor.listColors[Num]);
        }
        this.tableLayoutC.addView(tableRow, tableRowCountC);
        tableRowCountC++;
    }

    public synchronized void initializeRowForTableD(int pos){
        TableRow tableRowB= new TableRow(this);
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, SCREEN_HEIGHT/20);
        tableRowB.setPadding(0,0,0,0);
        tableRowB.setLayoutParams(layoutParamsTableRow);
        tableRowB.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        this.tableLayoutD.addView(tableRowB, pos);
    }

    public synchronized void addColumnToTableAtD(final int rowPos, String text, boolean title, boolean color){
        TableRow tableRowAdd= (TableRow) this.tableLayoutD.getChildAt(rowPos);
        tableRow= new TableRow(this);
        tableRow.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT/20);
        tableRow.setPadding(3,3,3,3);

        tableRow.setBackground(gd);
        tableRow.setLayoutParams(layoutParamsTableRow);
        TextView label_date = new TextView(this);
        label_date.setText(text);
        if(color == true){
            if(text.charAt(0) == '-'){
                label_date.setTextColor(getResources().getColor(R.color.Red));
            }else if(Character.isDigit(text.charAt(0))){
                if(Character.getNumericValue(text.charAt(0)) > 0) {
                    label_date.setTextColor(getResources().getColor(R.color.Green));
                }
            }
        }
        if (label_date.getText().toString().equals(" ") && title == false){
            label_date.setText("-");
        }
        TextViewCompat.setAutoSizeTextTypeWithDefaults(label_date, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        label_date.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        tableRow.setTag(label_date);
        this.tableRow.addView(label_date);
        if (title == true){
            tableRow.setBackgroundColor(charColor.listColors[Num]);
        }
        tableRowAdd.addView(tableRow);
    }
}
