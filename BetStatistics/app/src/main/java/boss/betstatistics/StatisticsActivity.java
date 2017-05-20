package boss.betstatistics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class StatisticsActivity extends BettingTips {

    String[] Teams = {"a", "b", "c"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Teams[0] = "Select team";

        getTeamData();

        Spinner team1 = (Spinner)findViewById(R.id.team1);
        team1.setAdapter(new MyCustomAdapter(StatisticsActivity.this, R.layout.team_select, Teams));

        Spinner team2 = (Spinner)findViewById(R.id.team2);
        team2.setAdapter(new MyCustomAdapter(StatisticsActivity.this, R.layout.team_select, Teams));
    }

    void getTeamData() {

    }

    public class MyCustomAdapter extends ArrayAdapter<String>{

        String[] elem;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               String[] objects) {
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
            label.setText(elem[position]);

            ImageView icon=(ImageView)row.findViewById(R.id.icon);

            String name = elem[position];
            name = "ic_statistics"; // TODO delete
            int id = getResources().getIdentifier(name, "drawable", getPackageName());
            icon.setImageResource(id);

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
