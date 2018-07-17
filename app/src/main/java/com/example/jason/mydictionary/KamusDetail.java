package com.example.jason.mydictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KamusDetail extends AppCompatActivity {

    public static final String EXTRA_KATA = "extra_kata";
    public static final String EXTRA_ARTI = "extra_arti";

    @BindView(R.id.tvKata)
    TextView tvKata;
    @BindView(R.id.tvArti)
    TextView tvArti;

    String kata, arti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_detail);
        ButterKnife.bind(this);
        kata = getIntent().getStringExtra(EXTRA_KATA);
        arti = getIntent().getStringExtra(EXTRA_ARTI);

        tvKata.setText(kata);
        tvArti.setText(arti);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
