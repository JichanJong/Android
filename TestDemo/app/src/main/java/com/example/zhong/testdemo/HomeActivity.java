package com.example.zhong.testdemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private LinearLayout mLinearLayout;
    private List<MenuItem> mMenuItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mLinearLayout = findViewById(R.id.ly_container);
        mMenuItems =new ArrayList<>();
        initData();
    }

    private void initData() {
        MenuItem item =new MenuItem();
        item.setNo("main_program");
        item.setName("主程序");
        item.setUrl("com.example.zhong.testdemo.MainActivity");
        item.setImageUrl("ic_home_page");   //注意：这里不用.png 后缀, 下同

        mMenuItems.add(item);

        item =new MenuItem();
        item.setNo("about_info");
        item.setName("关于");
        item.setUrl("com.example.zhong.testdemo.AboutActivity");
        item.setImageUrl("ic_info_page");

        mMenuItems.add(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLinearLayout.removeAllViews();
        for (final MenuItem item :
                mMenuItems) {
            LinearLayout linearLayout =new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            ImageView imageView =new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            int resImgId = getResources().getIdentifier(item.getImageUrl(),"drawable",getPackageName());
            if(resImgId != 0){
                imageView.setImageResource(resImgId);
            }
            else{
                imageView.setImageResource(R.drawable.ic_default_page);
            }
            linearLayout.addView(imageView);

            Button button =new Button(this);
            button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            int resStringId = getResources().getIdentifier(item.getNo(),"strings",getPackageName());
            if(resStringId != 0){
                button.setText(resStringId);
            }
            else{
                button.setText(item.getName());
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = null;
                    try {
                        intent = new Intent(getApplicationContext(), Class.forName(item.getUrl()));
                        startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });

            linearLayout.addView(button);
            mLinearLayout.addView(linearLayout);
        }


    }
}
