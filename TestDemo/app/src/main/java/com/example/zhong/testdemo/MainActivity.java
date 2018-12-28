package com.example.zhong.testdemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<PersonEntity> mPersonEntityList;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPersonEntityList =new ArrayList<>();
        String[] nations = { "China", "America", "French","Australia"};
        for (int i = 0; i < 50; i++) {
            PersonEntity entity =new PersonEntity();
            entity.setName("ABCD" + i);
            entity.setNation(nations[i % 4]);
            entity.setNo("L1934021"+ i);
            mPersonEntityList.add(entity);
        }
        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview_data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyAdapter myAdapter = new MyAdapter(mPersonEntityList);
        mRecyclerView.setAdapter(myAdapter);
    }

    private class MyHolder extends RecyclerView.ViewHolder{
        private TextView mName;
        private TextView mNo;
        private TextView mNation;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            mName = (TextView)itemView.findViewById(R.id.txt_name);
            mNo = (TextView)itemView.findViewById(R.id.txt_no);
            mNation = (TextView)itemView.findViewById(R.id.txt_nation);
        }

        public void bind(PersonEntity info){
            mName.setText(info.getName());
            mNo.setText(info.getNo());
            mNation.setText(info.getNation());
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyHolder>{
        private List<PersonEntity> mDatas;
        public MyAdapter(List<PersonEntity> mListData) {
            mDatas = mListData;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View view = inflater.inflate(R.layout.person_item,viewGroup,false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
            PersonEntity entity = mDatas.get(i);
            myHolder.bind(entity);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }
}
