package org.terna.teh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    GoogleTranslate translator;
    EditText translateedittext;
    TextView translatabletext;
    String Lang = "ENGLISH";
    String code = "en";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        translateedittext = (EditText) findViewById(R.id.translateedittext);
        Button translatebutton = (Button) findViewById(R.id.translatebutton);
        translatebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new EnglishToTagalog().execute();

            }
        });



        Spinner spinner = (Spinner) findViewById(R.id.language_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ((Spinner) findViewById(R.id.language_spinner)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                Lang= (String) parent.getItemAtPosition(pos);


                if(Lang.equals("Hindi")){
                    code = "hi";
                }else if(Lang.equals("Bengali")){
                    code = "bn";
                }else if(Lang.equals("Gujarati")){
                    code = "gu";
                }else if(Lang.equals("Malayalam")){
                    code = "ml";
                }else if(Lang.equals("Marathi")){
                    code = "mr";
                }else if(Lang.equals("Nepali")){
                    code = "ne";
                }else if(Lang.equals("Punjabi")){
                    code = "pa";
                }else if(Lang.equals("Sindhi")){
                    code = "sd";
                }else if(Lang.equals("Tamil")){
                    code = "ta";
                }else if(Lang.equals("Telugu")){
                    code = "te";
                }else {
                    code = "ur";
                }







            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });







    }


    private class EnglishToTagalog extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        protected void onError(Exception ex) {

        }
        @Override
        protected Void doInBackground(Void... params) {

            try {
                translator = new GoogleTranslate("AIzaSyCfUyW-Ya9tPV13KHZQBm8mWioEOadZS9k");

                Thread.sleep(1000);


            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;

        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPreExecute() {
            //start the progress dialog
            progress = ProgressDialog.show(MainActivity.this, null, "Translating...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(Void result) {
            progress.dismiss();

            super.onPostExecute(result);
            translated();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }







    public void translated(){

        String translatetotagalog = translateedittext.getText().toString();//get the value of text
        String text = translator.translte(translatetotagalog, "en", code);
        translatabletext = (TextView) findViewById(R.id.translatabletext);
        translatabletext.setText(text);

    }
}
