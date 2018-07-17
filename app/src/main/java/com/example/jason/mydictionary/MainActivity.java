package com.example.jason.mydictionary;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.jason.mydictionary.Database.KamusHelper;
import com.example.jason.mydictionary.Model.KamusModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {

        final String TAG = LoadData.class.getSimpleName();
        KamusHelper kamusHelper;
        AppPreference appPreference;
        double progress;
        double maxprogress = 100;

        @Override
        protected void onPreExecute() {
            kamusHelper = new KamusHelper(MainActivity.this);
            appPreference = new AppPreference(MainActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Boolean firstRun = appPreference.getFirstRun();
            if (firstRun) {
                ArrayList<KamusModel> kamusModels_ENG_IND = preLoadRawENG_IND();
                ArrayList<KamusModel> kamusModels_IND_ENG = preLoadRawIND_ENG();

                kamusHelper.open();
                progress = 30;
                publishProgress((int) progress);
                Double progressMaxInsert = 80.0;
                Double progressDiff = (progressMaxInsert - progress) / (kamusModels_ENG_IND.size()+kamusModels_IND_ENG.size());

                kamusHelper.beginTransaction();
                try {
                    for (KamusModel ENG_IND : kamusModels_ENG_IND) {
                        kamusHelper.insert_ENG_IND(ENG_IND);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                    for (KamusModel IND_ENG : kamusModels_IND_ENG) {
                        kamusHelper.insert_IND_ENG(IND_ENG);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                    kamusHelper.setTransactionSuccess();
                } catch (Exception e) {
                    Log.e(TAG, "doInbackground : exception");
                }

                kamusHelper.endTransaction();

                kamusHelper.close();

                appPreference.setFirstRun(false);

                publishProgress((int) maxprogress);
            } else {
                try {
                    synchronized (this) {
                        this.wait(2000);
                        publishProgress(50);
                        this.wait(2000);
                        publishProgress((int) maxprogress);
                    }
                } catch (Exception e) {
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent = new Intent(MainActivity.this, Navigation.class);
            startActivity(intent);
            finish();
        }
    }

    public ArrayList<KamusModel> preLoadRawENG_IND() {
        ArrayList<KamusModel> kamus_ENG_ING = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.english_indonesia);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;

            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                KamusModel kamusModel;
                kamusModel = new KamusModel(splitstr[0], splitstr[1]);
                kamus_ENG_ING.add(kamusModel);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kamus_ENG_ING;
    }

    public ArrayList<KamusModel> preLoadRawIND_ENG() {
        ArrayList<KamusModel> kamus_ING_ENG = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.indonesia_english);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;

            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                KamusModel kamusModel;
                kamusModel = new KamusModel(splitstr[0], splitstr[1]);
                kamus_ING_ENG.add(kamusModel);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kamus_ING_ENG;
    }
}
