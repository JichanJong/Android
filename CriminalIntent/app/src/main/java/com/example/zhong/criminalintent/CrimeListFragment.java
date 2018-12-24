package com.example.zhong.criminalintent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhong.criminalintent.model.JsonResult;
import com.example.zhong.criminalintent.model.PageModel;
import com.example.zhong.criminalintent.model.Person;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private Handler mHandler;
    private List<Person> mPersonList =new ArrayList<>();
    private List<Crime> crimes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);
        mCrimeRecyclerView = (RecyclerView)view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHandler = new Handler();
        updateUI();
        return  view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        crimes = crimeLab.getCrimes();
        if(mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }
        else{
            mAdapter.setCrimes(crimes);
            mAdapter.notifyDataSetChanged();
        }
        /*mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                PageModel model =new PageModel();
                model.pageIndex = 1;
                model.pageSize = 10;
                String jsonPara = new Gson().toJson(model);
                String json = HttpHelper.getRespnose(jsonPara);
                JsonResult result = new Gson().fromJson(json,JsonResult.class);
                mPersonList.addAll(result.listData);
                if(mAdapter == null){
                    mAdapter = new CrimeAdapter(mPersonList);
                    mCrimeRecyclerView.setAdapter(mAdapter);
                }
                else {
                    mAdapter.notifyDataSetChanged();
                }
            }
        }, 100);
        */

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity.newIntent(getActivity(),crime.getId());
                startActivity(intent);
                return  true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private   class CrimeHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;
        private Crime mCrime;
        private Person mPerson;
        public CrimeHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime,parent,false));
            mTitleTextView = (TextView)itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView)itemView.findViewById(R.id.crime_date);
            mSolvedImageView = (ImageView)itemView.findViewById(R.id.crime_solved);
            itemView.setOnClickListener(this);
        }
        //Crime crime
        public void bind(Crime crime){
            mCrime = crime;
           /* mPerson = person;*/
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedImageView.setVisibility(mCrime.isSolved() ? View.VISIBLE : View.GONE);
            if(mCrime.isSolved()){
                itemView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
            else{
                itemView.setBackground(null);
            }
        }

        @Override
        public void onClick(View v) {

            /*mCrime.setSolved(!mCrime.isSolved());
            int position = crimes.indexOf(mCrime);
            mAdapter.notifyItemChanged(position);*/

            /*Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);*/

            Intent intent = CrimePagerActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);
            /*if(mCrime.isSolved()){
                v.setBackgroundColor();
            }
            else{

            }*/

            //mSolvedImageView.setVisibility(mCrime.isSolved() ? View.VISIBLE : View.GONE);
            //Toast.makeText(getActivity(), mCrime.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(getActivity(),CrimeActivity.class);
            /*Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);*/
        }
    }

    private  class CrimeAdapter  extends RecyclerView.Adapter<CrimeHolder>{
        //private List<Person> mPersons;
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
           mCrimes = crimes;
           // mPersons = persons;
        }
        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater,viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder crimeHolder, int i) {
            Crime crime = mCrimes.get(i);
            crimeHolder.bind(crime);
            /*Person person = mPersons.get(i);
            crimeHolder.bind(person);*/
        }

        @Override
        public int getItemCount() {

            return mCrimes.size();
            //return mPersons.size();
        }

        public void setCrimes(List<Crime> crimes) {
            mCrimes = crimes;
        }
    }
}
