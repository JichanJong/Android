package com.example.zhong.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    /*public*/  private static  final String EXTRA_CRIME_ID = "com.example.zhong.criminalintent.crime_id";
    @Override
    protected Fragment createFragment() {
       // return new CrimeFragment();
        UUID crimeId =  (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return  CrimeFragment.newInstance(crimeId);
    }
    public  static Intent newIntent(Context packageContext,UUID crimeId){
        Intent intent =new Intent(packageContext,CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return intent;
    }
/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        *//*String json = "{\"username\":\"张三\"}";
        try {
            JSONObject obj = new JSONObject(json);
            Toast.makeText(CrimeActivity.this,obj.getString("username"),Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }*//*
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if(fragment == null){
            fragment = new CrimeFragment();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }
    }*/
}
