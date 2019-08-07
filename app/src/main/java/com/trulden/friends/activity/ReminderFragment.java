package com.trulden.friends.activity;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.trulden.friends.R;
import com.trulden.friends.adapter.PagerAdapter;
import com.trulden.friends.adapter.TabCounterView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReminderFragment extends Fragment {

    TabLayout mTabLayout;

    public ReminderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_reminder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initTabsAndPageViewer(view);
    }

    private void initTabsAndPageViewer(View view) {
        mTabLayout = view.findViewById(R.id.reminder_tab_layout);

        TabCounterView tcv0 = new TabCounterView(getContext(), "Meetings", getResources().getStringArray(R.array.meetings_a_while_ago).length);
        TabCounterView tcv1 = new TabCounterView(getContext(), "Texting", getResources().getStringArray(R.array.texting_a_while_ago).length);
        TabCounterView tcv2 = new TabCounterView(getContext(), "Calls", getResources().getStringArray(R.array.call_a_while_ago).length);

        mTabLayout.addTab(mTabLayout.newTab().setCustomView(tcv0)); // TODO имена и прочие параметры вкладок должны браться не из констант
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(tcv1));         // Но это когда я настрою бд
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(tcv2));

        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = view.findViewById(R.id.reminder_pager);
        final PagerAdapter adapter = new PagerAdapter(getFragmentManager(), mTabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}