package ftapps.goodshopping;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private String _shop1_name;
    private String _shop1_md;
    private String _shop1_point;
    private String _shop1_mult;
    private String _shop2_name;
    private String _shop2_md;
    private String _shop2_point;
    private String _shop2_mult;
    private String _shop3_name;
    private String _shop3_md;
    private String _shop3_point;
    private String _shop3_mult;
    private boolean _calflag = false;
    private EditText editPriceText;
    private EditText etext1;
    private EditText etext2;
    private EditText etext3;
    AlertDialog.Builder ad;
    private boolean _initflag = true;

    private int final_pri_1;
    private int final_pri_2;
    private int final_pri_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 金額入力イベント登録 */
        editPriceText = (EditText)findViewById(R.id.input_price);
        etext1 = (EditText) findViewById(R.id.input_shopprice_1);
        etext2 = (EditText) findViewById(R.id.input_shopprice_2);
        etext3 = (EditText) findViewById(R.id.input_shopprice_3);

        final_pri_1 = 0;
        final_pri_2 = 0;
        final_pri_3 = 0;

        editPriceText.addTextChangedListener(watchHandler);

        ad = new AlertDialog.Builder(this);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    @Override
    public void onStart() {
        super.onStart();
        initValueLoad();
        showText();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //店舗登録情報の取得
    public void initValueLoad()
    {
        int value;
        AlertDialog.Builder adguide = new AlertDialog.Builder(this);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        /* 名称 */
        _shop1_name = sharedPreferences.getString("shop1_name", "----");
        _shop2_name = sharedPreferences.getString("shop2_name", "----");
        _shop3_name = sharedPreferences.getString("shop3_name", "----");

        /* 割引 */
        _shop1_md = sharedPreferences.getString("shop1_md", "0");
        if (_shop1_md.isEmpty() == true) _shop1_md = "0";
        value = Integer.parseInt(_shop1_md);
        if (value < 0 || value > 99) _shop1_md = "0";
        else if (value != 0) _initflag = false;

        _shop2_md = sharedPreferences.getString("shop2_md", "0");
        if (_shop2_md.isEmpty() == true) _shop2_md = "0";
        value = Integer.parseInt(_shop2_md);
        if (value < 0 || value > 99) _shop2_md = "0";
        else if (value != 0) _initflag = false;

        _shop3_md = sharedPreferences.getString("shop3_md", "0");
        if (_shop3_md.isEmpty() == true) _shop3_md = "0";
        value = Integer.parseInt(_shop3_md);
        if (value < 0 || value > 99) _shop3_md = "0";
        else if (value != 0) _initflag = false;

        /* ﾎﾟｲﾝﾄ単価 */
        _shop1_point = sharedPreferences.getString("shop1_point", "0");
        if (_shop1_point.isEmpty() == true) _shop1_point = "0";
        value = Integer.parseInt(_shop1_point);
        if (value < 0 || value > 999) _shop1_point = "0";
        else if (value != 0) _initflag = false;

        _shop2_point = sharedPreferences.getString("shop2_point", "0");
        if (_shop2_point.isEmpty() == true) _shop2_point = "0";
        value = Integer.parseInt(_shop2_point);
        if (value < 0 || value > 999) _shop2_point = "0";
        else if (value != 0) _initflag = false;

        _shop3_point = sharedPreferences.getString("shop3_point", "0");
        if (_shop3_point.isEmpty() == true) _shop3_point = "0";
        value = Integer.parseInt(_shop3_point);
        if (value < 0 || value > 999) _shop3_point = "0";
        else if (value != 0) _initflag = false;

        /* ﾎﾟｲﾝﾄ倍数 */
        _shop1_mult = sharedPreferences.getString("shop1_mult", "0");
        if (_shop1_mult.isEmpty() == true) _shop1_mult = "0";
        value = Integer.parseInt(_shop1_mult);
        if (value < 0 || value > 99) _shop1_mult = "0";
        else if (value != 0) _initflag = false;

        _shop2_mult = sharedPreferences.getString("shop2_mult", "0");
        if (_shop2_mult.isEmpty() == true) _shop2_mult = "0";
        value = Integer.parseInt(_shop2_mult);
        if (value < 0 || value > 99) _shop2_mult = "0";
        else if (value != 0) _initflag = false;

        _shop3_mult = sharedPreferences.getString("shop3_mult", "0");
        if (_shop3_mult.isEmpty() == true) _shop3_mult = "0";
        value = Integer.parseInt(_shop3_mult);
        if (value < 0 || value > 99) _shop3_mult = "0";
        else if (value != 0) _initflag = false;

        /* スタートアップガイド */
        if (_initflag == true)
        {
            _initflag = false;
            adguide.setTitle("\nスタートガイドのお知らせ\n");
            adguide.setMessage("\n本アプリの使い方について\n①設定画面（右上ﾀｯﾌﾟ）へ移動して下さい\n②各店舗情報の登録を行って下さい\n　(％割引、ﾎﾟｲﾝﾄ単価、ﾎﾟｲﾝﾄ倍数)\n③金額を入力して下さい\n④[計算]ボタンを押して下さい\n⑤計算結果が表示されます\n\n\n店舗情報の登録後、スタートガイドは表示されなくなります");
            adguide.setPositiveButton("ＯＫ", null);
            adguide.show();
        }
    }

    //店舗情報
    public void show_shopinfo(int index)
    {
        TextView vtext;
        TextView rtext;
        EditText etext;
        CheckBox cbox1;
        CheckBox cbox2;
        String st_pri = "";
        String text = "";
        String text2 = "";
        String shopname = "";
        int pri = 0;
        int pri2 = 0;
        int pri3 = 0;
        int pri4 = 0;
        int md = 0;
        int point = 0;
        int point2 = 0;
        int mult = 0;

        switch(index)
        {
            default:
            case 1:
                vtext = (TextView) findViewById(R.id.text_shopinfo_1);
                rtext = (TextView) findViewById(R.id.text_shopresult_1);
                etext = (EditText) findViewById(R.id.input_shopprice_1);
                cbox1 = (CheckBox) findViewById(R.id.check_mdshop_1);
                cbox2 = (CheckBox) findViewById(R.id.check_pointshop_1);
                md = Integer.parseInt(_shop1_md);
                point = Integer.parseInt(_shop1_point);
                mult = Integer.parseInt(_shop1_mult);
                shopname = _shop1_name;
                break;
            case 2:
                vtext = (TextView) findViewById(R.id.text_shopinfo_2);
                rtext = (TextView) findViewById(R.id.text_shopresult_2);
                etext = (EditText) findViewById(R.id.input_shopprice_2);
                cbox1 = (CheckBox) findViewById(R.id.check_mdshop_2);
                cbox2 = (CheckBox) findViewById(R.id.check_pointshop_2);
                md = Integer.parseInt(_shop2_md);
                point = Integer.parseInt(_shop2_point);
                mult = Integer.parseInt(_shop2_mult);
                shopname = _shop2_name;
                break;
            case 3:
                vtext = (TextView) findViewById(R.id.text_shopinfo_3);
                rtext = (TextView) findViewById(R.id.text_shopresult_3);
                etext = (EditText) findViewById(R.id.input_shopprice_3);
                cbox1 = (CheckBox) findViewById(R.id.check_mdshop_3);
                cbox2 = (CheckBox) findViewById(R.id.check_pointshop_3);
                md = Integer.parseInt(_shop3_md);
                point = Integer.parseInt(_shop3_point);
                mult = Integer.parseInt(_shop3_mult);
                shopname = _shop3_name;
                break;
        }


        //  日本語

        text = "▶店名：" + shopname + "\n";

        /* 計算なし */
        if (_calflag == false) {
            text += "▶支払：-----\n";
            text += "▶還元：-----\n";
        }
        /* 計算あり */
        else {
            /* ------- 支払 ------- */
            st_pri = etext.getText().toString();
            if (st_pri.isEmpty() == false) {
                pri = Integer.parseInt(st_pri);
                pri2 = pri;
            }

            if (st_pri.isEmpty() == true || pri == 0) {
                text += "▶支払：-----\n";
            } else {
                pri = Integer.parseInt(st_pri);
                text += "▶支払：";
                if (cbox1.isChecked() == true && (md > 0 && md < 100)) {
                    pri2 = (pri * md) / 100;
                    pri2 = pri - pri2;
                    text += String.valueOf(pri2) + "円 (割引 -";
                    text += String.valueOf(pri - pri2) + "円)\n";
                } else {
                    text += st_pri + "円 (割引なし)\n";
                }
            }
            /* ------- 還元 ------- */
            if (point > 0 && pri2 > 0) {
                pri3 = pri2;
                pri3 = pri3 / point;
                if (cbox2.isChecked() == true && mult > 0) {
                    pri3 = pri3 * mult;
                }
                text += "▶還元：" + String.valueOf(pri3) + "ﾎﾟｲﾝﾄ (=";
                text += String.valueOf(pri3) + "円)\n";
//                    text += "還元：" + String.valueOf(pri3) + "円 (還元 ";
//                    text += String.valueOf(pri3) + "ﾎﾟｲﾝﾄ)\n";
            } else {
                text += "▶還元：-----\n";
            }
        }
        vtext.setText(text);

        /* ------- 最終損益 ------- */
        if (pri2 > 0 || pri3 > 0) {
            pri4 = pri2 - pri3;
            text2 = "【実質支払額】\n";
            if (pri4 > 0) {
                text2 += "  支払：" + String.valueOf(pri4) + "円\n";
//                    rtext.setTextColor(Color.RED);
                rtext.setTextColor(Color.rgb(255,14,93));
                rtext.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                text2 += "  還元：" + String.valueOf((pri4 * -1)) + "円\n";
                rtext.setTextColor(Color.BLUE);
            }
            rtext.setText(text2);
        } else {
            text2 = "【実質支払額】\n";
            text2 += "-----";
            rtext.setText(text2);
            rtext.setTextColor(Color.BLACK);
        }

        switch (index)
        {
            case 1: final_pri_1 = pri4; break;
            case 2: final_pri_2 = pri4; break;
            case 3: final_pri_3 = pri4; break;
        }
    }

    //店舗情報
    public void show_shopmd(int index)
    {
        String text = "";
        int data = 0;
        switch(index)
        {
            case 1:
                CheckBox v1 = (CheckBox) findViewById(R.id.check_mdshop_1);
                data =  Integer.parseInt( _shop1_md );
                if (data < 0 || data > 99) text = "割引なし";
                else text = _shop1_md + "% OFF";
                v1.setText(text);
//                v.setTextColor(Color.WHITE);
//                v.setBackgroundColor(Color.BLACK);
                break;
            case 2:
                CheckBox v2 = (CheckBox) findViewById(R.id.check_mdshop_2);
                data =  Integer.parseInt( _shop2_md );
                if (data < 0 || data > 99) text = "割引なし";
                else text = _shop2_md + "% OFF";
                v2.setText(text);
                break;
            case 3:
                CheckBox v3 = (CheckBox) findViewById(R.id.check_mdshop_3);
                data =  Integer.parseInt( _shop3_md );
                if (data < 0 || data > 99) text = "割引なし";
                else text = _shop3_md + "% OFF";
                v3.setText(text);
                break;
        }
    }
    //店舗情報
    public void show_shopmult(int index)
    {
        String text = "";
        int data = 0;
        switch(index)
        {
            case 1:
                CheckBox v1 = (CheckBox) findViewById(R.id.check_pointshop_1);
                data =  Integer.parseInt( _shop1_mult );
                if (data < 0 || data > 99) text = "通常ﾎﾟｲﾝﾄ";
                else text = "ﾎﾟｲﾝﾄ " + _shop1_mult + "倍";
                v1.setText(text);
//                v.setTextColor(Color.WHITE);
//                v.setBackgroundColor(Color.BLACK);
                break;
            case 2:
                CheckBox v2 = (CheckBox) findViewById(R.id.check_pointshop_2);
                data =  Integer.parseInt( _shop2_mult );
                if (data < 0 || data > 99) text = "通常ﾎﾟｲﾝﾄ";
                else text = "ﾎﾟｲﾝﾄ " + _shop2_mult + "倍";
                v2.setText(text);
                break;
            case 3:
                CheckBox v3 = (CheckBox) findViewById(R.id.check_pointshop_3);
                data =  Integer.parseInt( _shop3_mult );
                if (data < 0 || data > 99) text = "通常ﾎﾟｲﾝﾄ";
                else text = "ﾎﾟｲﾝﾄ " + _shop3_mult + "倍";
                v3.setText(text);
                break;
        }
    }
    //店舗情報
    public void show_status()
    {
        TextView vtext;
        vtext = (TextView) findViewById(R.id.text_status);
        vtext.setText("調べる金額を入力した後「計算」を押してね！");
        vtext.setTypeface(Typeface.DEFAULT_BOLD);
        vtext.setTextSize(16);
    }

    // 画面描画
    public void showText() {
        RelativeLayout temp_v1 = (RelativeLayout) findViewById(R.id.lay_shop1);
        RelativeLayout temp_v2 = (RelativeLayout) findViewById(R.id.lay_shop2);
        RelativeLayout temp_v3 = (RelativeLayout) findViewById(R.id.lay_shop3);

        show_status();

        show_shopinfo(1);
        show_shopmd(1);
        show_shopmult(1);

        show_shopinfo(2);
        show_shopmd(2);
        show_shopmult(2);

        show_shopinfo(3);
        show_shopmd(3);
        show_shopmult(3);


        if (final_pri_1 < final_pri_2 && final_pri_2 < final_pri_3) {
            /* a<b<c */
            temp_v1.setBackgroundResource(R.drawable.bak_select);
            temp_v2.setBackgroundResource(R.drawable.bak_2);
            temp_v3.setBackgroundResource(R.drawable.bak_3);
        } else if (final_pri_1 < final_pri_3 && final_pri_3 < final_pri_2) {
            /* a<c<b */
            temp_v1.setBackgroundResource(R.drawable.bak_select);
            temp_v2.setBackgroundResource(R.drawable.bak_2);
            temp_v3.setBackgroundResource(R.drawable.bak_3);
        } else if (final_pri_2 < final_pri_1 && final_pri_1 < final_pri_3) {
            /* b<a<c */
            temp_v1.setBackgroundResource(R.drawable.bak_1);
            temp_v2.setBackgroundResource(R.drawable.bak_select2);
            temp_v3.setBackgroundResource(R.drawable.bak_3);
        } else if (final_pri_2 < final_pri_3 && final_pri_3 < final_pri_1) {
            /* b<c<a */
            temp_v1.setBackgroundResource(R.drawable.bak_1);
            temp_v2.setBackgroundResource(R.drawable.bak_select2);
            temp_v3.setBackgroundResource(R.drawable.bak_3);
        } else if (final_pri_3 < final_pri_1 && final_pri_1 < final_pri_2) {
            /* c<a<b */
            temp_v1.setBackgroundResource(R.drawable.bak_1);
            temp_v2.setBackgroundResource(R.drawable.bak_2);
            temp_v3.setBackgroundResource(R.drawable.bak_select);
        } else if (final_pri_3 < final_pri_2 && final_pri_2 < final_pri_1) {
            /* c<b<a */
            temp_v1.setBackgroundResource(R.drawable.bak_1);
            temp_v2.setBackgroundResource(R.drawable.bak_2);
            temp_v3.setBackgroundResource(R.drawable.bak_select);
        } else {
            if (final_pri_1 == final_pri_2 && final_pri_1 < final_pri_3) {
                /* a=b<c */
                temp_v1.setBackgroundResource(R.drawable.bak_select);
                temp_v2.setBackgroundResource(R.drawable.bak_select2);
                temp_v3.setBackgroundResource(R.drawable.bak_3);
            } else if (final_pri_1 == final_pri_2 && final_pri_1 > final_pri_3) {
                /* a=b>c */
                temp_v1.setBackgroundResource(R.drawable.bak_1);
                temp_v2.setBackgroundResource(R.drawable.bak_2);
                temp_v3.setBackgroundResource(R.drawable.bak_select);
            } else if (final_pri_2 == final_pri_3 && final_pri_2 < final_pri_1) {
                /* b=c<a */
                temp_v1.setBackgroundResource(R.drawable.bak_1);
                temp_v2.setBackgroundResource(R.drawable.bak_select2);
                temp_v3.setBackgroundResource(R.drawable.bak_select);
            } else if (final_pri_2 == final_pri_3 && final_pri_2 > final_pri_1) {
                /* b=c>a */
                temp_v1.setBackgroundResource(R.drawable.bak_select);
                temp_v2.setBackgroundResource(R.drawable.bak_2);
                temp_v3.setBackgroundResource(R.drawable.bak_3);
            } else if (final_pri_3 == final_pri_1 && final_pri_3 > final_pri_2) {
                /* c=a>b */
                temp_v1.setBackgroundResource(R.drawable.bak_1);
                temp_v2.setBackgroundResource(R.drawable.bak_select2);
                temp_v3.setBackgroundResource(R.drawable.bak_3);
            } else if (final_pri_3 == final_pri_1 && final_pri_3 < final_pri_2) {
                /* c=a<b */
                temp_v1.setBackgroundResource(R.drawable.bak_select);
                temp_v2.setBackgroundResource(R.drawable.bak_2);
                temp_v3.setBackgroundResource(R.drawable.bak_select);
            }
            else {
                /* a=b=c */
                temp_v1.setBackgroundResource(R.drawable.bak_1);
                temp_v2.setBackgroundResource(R.drawable.bak_2);
                temp_v3.setBackgroundResource(R.drawable.bak_3);
            }
        }
    }

    //  計算
    public void onCalExec(View view){
        final_pri_1 = 0;
        final_pri_2 = 0;
        final_pri_3 = 0;
        _calflag = true;
        showText();
    }

    //  メニュー
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //  メニュー選択時の処理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent1 = new Intent(this, GsSetActivity.class);
            startActivity(intent1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private TextWatcher watchHandler = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            String inputStr= s.toString();
            // 文字長をカウントして５文字を超えると「オーバー」とする
            if(inputStr.length() > 5){
                editPriceText.setText("");
                etext1.setText("");
                etext2.setText("");
                etext3.setText("");
                showText();

                ad.setTitle("金額入力オーバー");
                ad.setMessage("\n\n\n有効範囲：1～99999\n\n\n\n\n\n");
                ad.setPositiveButton("ＯＫ", null);
                ad.show();
            }
            else{
                etext1.setText(inputStr);
                etext2.setText(inputStr);
                etext3.setText(inputStr);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    };
}