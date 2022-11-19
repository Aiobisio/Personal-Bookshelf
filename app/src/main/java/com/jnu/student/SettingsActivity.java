package com.jnu.student;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("设置");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            Preference EditText = findPreference("account");
            ListPreference ChannelList = findPreference("news_channel");
            if (EditText != null) {
                EditText.setOnPreferenceChangeListener(this);
            }
            if (ChannelList != null) {
                ChannelList.setOnPreferenceChangeListener(this);
                if(ChannelList.getEntry()!=null) {
                    ChannelList.setSummary(ChannelList.getEntry());
                }
            }
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String key = preference.getKey();
            if (key.equals("account")) {
                return true;
            }else if (key.equals("new_channel")){
                ListPreference ChannelList = (ListPreference) preference;
                CharSequence[] entries = ChannelList.getEntries();
                int index = ChannelList.findIndexOfValue((String) newValue);
                ChannelList.setSummary(entries[index]);
                Toast.makeText(getActivity(),entries[index].toString(),Toast.LENGTH_LONG).show();
                return true;
            }else return false;
        }
    }
}
