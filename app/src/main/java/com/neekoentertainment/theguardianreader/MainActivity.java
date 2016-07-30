package com.neekoentertainment.theguardianreader;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_FRAGMENT = "list_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Prevents the re-creation of the fragment
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Fragment fragment = new ArticlesListFragment();
            fragmentTransaction.add(R.id.fragment_container, fragment, TAG_FRAGMENT);
            fragmentTransaction.commit();
        }
    }
}
