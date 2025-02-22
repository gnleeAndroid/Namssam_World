package org.techtown.locale_homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;

public class SettingActivity extends AppCompatActivity {

    RadioButton radio_ko;
    RadioButton radio_ja;
    RadioButton radio_en;

    Button btn_setting;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    String Mylocale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // 화면 세로 고정 (왜 이렇게 쓰는지 더 찾아볼 것!!!!!)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        setContentView(R.layout.activity_setting);

        btn_setting = findViewById(R.id.btn_setting);

        // SharedPreferences 사용법 잘 익혀두자.... 코드 많이 보고 분석해야 할듯..(우선 포기)
        preferences = getSharedPreferences("Mylocale", MODE_PRIVATE);
        editor = preferences.edit();

        Mylocale = preferences.getString("Mylocale", "ko_kr");

        // Ok 버튼 클릭 시 이벤트 설정
        Button btn_ok = findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio_ko = findViewById(R.id.radio_ko);
                radio_ja = findViewById(R.id.radio_ja);
                radio_en = findViewById(R.id.radio_en);
                
                // "한국" 라디오 버튼이 선택 되었을 시
                if (radio_ko.isChecked()) {
                    // 한국어로 변환
                    Locale locale_ko = new Locale(Mylocale);
                    getResources().getConfiguration().setLocale(locale_ko);

                    // TextView에 보여줄 str 값 설정
                    String str_ko = "ko_kr";

                    Intent intent_kor = new Intent(SettingActivity.this, MainActivity.class);
                    intent_kor.putExtra("locale", str_ko);

                    startActivity(intent_kor);
                    
                    // "일본" 라디오 버튼이 선택 되었을 시
                } else if (radio_ja.isChecked()) {
                    Locale locale_ja = new Locale("ja", "jp");
                    getResources().getConfiguration().setLocale(locale_ja);
                    
                    String str_ja = "ja_jp";
                    
                    Intent intent_jap = new Intent(SettingActivity.this, MainActivity.class);
                    intent_jap.putExtra("locale", str_ja);
                    startActivity(intent_jap);
                    
                    // "미국" 라디오 버튼이 선택 되었을 시
                } else {
                    Locale locale_en = new Locale(Locale.US.toString());
                    getResources().getConfiguration().setLocale(locale_en);

                    String str_en = "en_us";

                    Intent intent_en = new Intent(SettingActivity.this, MainActivity.class);
                    intent_en.putExtra("locale", str_en);
                    startActivity(intent_en);
                }
            }
        });
    }
}