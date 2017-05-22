package boss.betstatistics;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class StatisticsActivity extends BettingTips {

    ArrayList<String> teamNames = new ArrayList<>();
    Team[] teams;
    ArrayList<Bitmap> images = new ArrayList<>();
    Spinner team1, team2;
    int pos1 = -1, pos2 = -1;

    LinearLayout linearLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        teamNames.add("Select team");
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.ic_statistics));

        getTeamData();

        linearLayout1 = (LinearLayout) findViewById(R.id.fixturesView);

        team1 = (Spinner)findViewById(R.id.team1);
        team2 = (Spinner)findViewById(R.id.team2);

        team1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                --pos;

                pos1 = pos;
                if(pos2 > 0) {

                    String name1 = teamNames.get(pos1 + 1);
                    String name2 = teamNames.get(pos2 + 1);

                    clearList();
                    for(Fixture i : teams[pos].getFixtures()) {

                        if((name1 == i.getHomeTeam() && name2 == i.getAwayTeam()) ||
                                (name2 == i.getHomeTeam() && name1 == i.getAwayTeam())) {
                            addFixtureToList(i, pos1, pos2);
                        }
                    }
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        team2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                --pos;

                pos2 = pos;
                if(pos1 > 0) {

                    String name1 = teamNames.get(pos1 + 1);
                    String name2 = teamNames.get(pos2 + 1);

                    Log.v(name1, name2);

                    clearList();
                    for(Fixture i : teams[pos].getFixtures()) {
    Log.v(i.getHomeTeam(), i.getAwayTeam());
                        if((name1.equals(i.getHomeTeam()) && name2.equals(i.getAwayTeam())) ||
                                (name2.equals(i.getHomeTeam()) && name1.equals(i.getAwayTeam()))) {
                            addFixtureToList(i, pos1, pos2);
                        }
                    }
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    void addFixtureToList(Fixture x, int pos1, int pos2) {

        LayoutInflater inflater=getLayoutInflater();
        View row=inflater.inflate(R.layout.fixture, linearLayout1, false);
        TextView label=(TextView)row.findViewById(R.id.score);

        String sc = Integer.toString(x.getHomeScored());
        sc = sc.concat(" - ");
        sc = sc.concat(Integer.toString(x.getAwayScored()));

        label.setText(sc);

        Log.v("AddedFixture", sc);

        ImageView icon1=(ImageView)row.findViewById(R.id.iconTeam1);
        ImageView icon2=(ImageView)row.findViewById(R.id.iconTeam2);

        if(teamNames.get(pos1 + 1).equals(x.getAwayTeam())) {
            int t = pos2;
            pos2 = pos1;
            pos1 = t;
        }

        icon1.setImageBitmap(images.get(pos1));
        icon2.setImageBitmap(images.get(pos2));

        linearLayout1.addView(row);
    }

    void clearList() {
        if(linearLayout1.getChildCount() > 0)
            linearLayout1.removeAllViews();
    }

    void getTeamData() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("data.json")));

            String data = reader.readLine();
            Gson gson = new Gson();
            teams = gson.fromJson(data, Team[].class);

            for(int i = 0; i < teams.length; ++i) {
                teamNames.add(teams[i].getName());
                images.add(BitmapFactory.decodeResource(getResources(), R.drawable.ic_statistics));

                new DownloadImageTask().execute(i);
            }

        } catch(IOException ie) {
            ie.printStackTrace();
        }
    }

    private int nrComplete = 0;

    private class DownloadImageTask extends AsyncTask<Integer, Integer, Bitmap> {

        @Override
        protected Bitmap doInBackground(Integer... poz) {
            return loadImageFromNetwork(poz[0]);
        }

        protected Bitmap loadImageFromNetwork(Integer poz) {
            String url = teams[poz].getImageUrl();
            String name = teams[poz].getName();

            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("profile", Context.MODE_PRIVATE);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File mypath = new File(directory, name);

            if(mypath.exists()) {
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                Bitmap img = BitmapFactory.decodeFile(mypath.getAbsolutePath(), bmOptions);
                images.set(poz, img);
                return img;
            }

            Bitmap loadedImage = getBitmapFromURL(url);

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(mypath);
                loadedImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.close();
            } catch (Exception e) {
                Log.e("SAVE_IMAGE", e.getMessage(), e);
            }

            images.set(poz, loadedImage);
            return loadedImage;
        }

        public Bitmap getBitmapFromURL(String src) {
            try {
                java.net.URL url = new java.net.URL(src);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            ++nrComplete;

            if(nrComplete == teamNames.size() - 1) {
                team1.setAdapter(new MyCustomAdapter(StatisticsActivity.this, R.layout.team_select, teamNames));
                team2.setAdapter(new MyCustomAdapter(StatisticsActivity.this, R.layout.team_select, teamNames));
            }
        }
    }

    public class MyCustomAdapter extends ArrayAdapter<String>{

        ArrayList<String> elem;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<String> objects) {
            super(context, textViewResourceId, objects);
            this.elem = objects;
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.team_select, parent, false);
            TextView label=(TextView)row.findViewById(R.id.team);
            label.setText(elem.get(position));

            ImageView icon=(ImageView)row.findViewById(R.id.icon);

            String name = elem.get(position);
            if(position == 0) {
                name = "ic_statistics";
                int id = getResources().getIdentifier(name, "drawable", getPackageName());
                icon.setImageResource(id);
            }
            else {
                icon.setImageBitmap(images.get(position - 1));
            }

            return row;
        }
    }

    @Override
    int getContentViewId() {
        return R.layout.statistics_main;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.statistics_dashboard;
    }
}
