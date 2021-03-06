package com.example.jason.mydictionary.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.jason.mydictionary.adapter.KamusAdapter;
import com.example.jason.mydictionary.Database.KamusHelper;
import com.example.jason.mydictionary.Model.KamusModel;
import com.example.jason.mydictionary.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class Indonesia2English extends Fragment {

    @BindView(R.id.rvKamus)
    RecyclerView rvKamus;
    @BindView(R.id.searchView)
    SearchView searchView;

    KamusAdapter adapter;
    KamusHelper kamusHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indonesia2_english, container, false);
        ButterKnife.bind(this, view);
        rvKamus = view.findViewById(R.id.rvKamus);
        searchView = view.findViewById(R.id.searchView);
        adapter = new KamusAdapter(getContext());
        kamusHelper = new KamusHelper(getContext());

        rvKamus.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        rvKamus.setAdapter(adapter);

        showAllDataIND_ENG();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                kamusHelper.open();
                ArrayList<KamusModel> kamus_IND_ENG = kamusHelper.getDataByKataIND(s);
                kamusHelper.close();
                adapter.addItem(kamus_IND_ENG);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return view;
    }

    public void showAllDataIND_ENG(){
        kamusHelper.open();
        ArrayList<KamusModel> kamus_IND_ENG = kamusHelper.getAllDataIND_ENG();
        kamusHelper.close();

        adapter.addItem(kamus_IND_ENG);
    }
}
