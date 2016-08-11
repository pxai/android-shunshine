package org.cuatrovientos.android.shunshine;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {
    ArrayAdapter<String> mForecastAdapter;
    public ForecastFragment() {
    }

    @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                // Add this line in order for this fragment to handle menu events.
                        setHasOptionsMenu(true);
            }

                @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
                inflater.inflate(R.menu.forecastfragment, menu);
            }

                @Override
        public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                        // automatically handle clicks on the Home/Up button, so long
                                // as you specify a parent activity in AndroidManifest.xml.
                                        int id = item.getItemId();

                if (id == R.id.action_refresh) {
                    Log.d("Shunshine", "Refresh clicked");
                    FetchWeatherTask weatherTask = new FetchWeatherTask(mForecastAdapter);
                    weatherTask.execute("94043");
                        return true;
                    }
                return super.onOptionsItemSelected(item);
          }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] data = {
                "Mon 6/23 - Sunny - 31/17",
                "Tue 6/24 - Foggy - 21/8",
                "Wed 6/25 - Cloudy - 22/17",
                "Thurs 6/26 - Rainy - 18/11",
                "Fri 6/27 - Foggy - 21/10",
                "Sat 6/28 - Muggy - 23/18",
                "Sun 6/29 - Sunny - 20/7"
        };
        List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));
        mForecastAdapter =
                new ArrayAdapter<String>(
                        (MainActivity)getActivity(), // The current context (this activity)
                        R.layout.list_item_forecast, // The name of the layout ID.
                        R.id.list_item_forecast_textview, // The ID of the textview to populate.
                        weekForecast);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);

        // Alternate way:
        // (ListView) getView().findViewById(R.id.listview_forecast);
        // getView() only works in fragments in onCreateViewMethod.

        Log.d("Shunshine", weekForecast.toString());
        listView.setAdapter(mForecastAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                               String forecast = mForecastAdapter.getItem(position);
                                //Toast.makeText(getActivity(), forecast, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), DetailActivity.class)
                                .putExtra(Intent.EXTRA_TEXT, forecast);
                                startActivity(intent);
                            }
                    });

        Log.d("Shunshine", "Refresh clicked");
        FetchWeatherTask weatherTask = new FetchWeatherTask(mForecastAdapter);
        weatherTask.execute("94043");

        return rootView;
    }
}
