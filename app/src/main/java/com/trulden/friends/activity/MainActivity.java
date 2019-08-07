package com.trulden.friends.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.trulden.friends.adapter.PagerAdapter;
import com.trulden.friends.R;
import com.trulden.friends.adapter.TabCounterView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int NEW_INTERACTION_REQUEST = 1;

    //private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private BottomNavigationView mBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new ReminderFragment());
        mBottomNavigation = findViewById(R.id.bottom_navigation);
        mBottomNavigation.setOnNavigationItemSelectedListener(this);

        findViewById(R.id.bottom_reminder).performClick();
    }



    private void initToolbar() {
        //mToolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(mToolbar);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);  // TODO меню сейчас пустое и бесполезное. Оно мне нужно?
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addMeeting(View view) { // TODO повесить на кнопку несколько действий
        Intent intent = new Intent(this, AddInteractionActivity.class);
        startActivityForResult(intent, NEW_INTERACTION_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_INTERACTION_REQUEST){
            if(resultCode == RESULT_OK){
                String reply = data.getStringExtra(AddInteractionActivity.EXTRA_NEW_INTERACTION);
                // TODO save as log entry
            }
        }
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()){
            case R.id.bottom_log:
                Toast.makeText(this, "TODO", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bottom_reminder:
                fragment = new ReminderFragment();
                break;
            case R.id.bottom_friends:
                Toast.makeText(this, "TODO", Toast.LENGTH_SHORT).show();
        }

        return loadFragment(fragment);
    }
}