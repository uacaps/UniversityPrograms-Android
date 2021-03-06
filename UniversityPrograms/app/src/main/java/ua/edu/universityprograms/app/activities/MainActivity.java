package ua.edu.universityprograms.app.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Locale;

import ua.edu.universityprograms.app.Asyncs.GetCommentAsync;
import ua.edu.universityprograms.app.Asyncs.UpComingEventsAsync;
import ua.edu.universityprograms.app.R;
import ua.edu.universityprograms.app.Utils.UpConstants;
import ua.edu.universityprograms.app.fragments.AboutUPFragment;
import ua.edu.universityprograms.app.fragments.MyUPFragment;
import ua.edu.universityprograms.app.fragments.NoCWIDdialog;
import ua.edu.universityprograms.app.fragments.UpComingEventsFragment;
import ua.edu.universityprograms.app.models.DtoComment;
import ua.edu.universityprograms.app.models.DtoEventBase;
import ua.edu.universityprograms.app.models.User;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener ,NoCWIDdialog.noCWID {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    SharedPreferences preferences;

    UpComingEventsFragment upcomingEvents;
    AboutUPFragment aboutUp;
    MyUPFragment myUp;

    public User you;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        setTheme(preferences.getInt("theme", R.style.darkAppTheme));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitFragments();


        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this)
            );
        }
        ActionBarRefresher();
    }

    // Sets the Title for this page
    public void ActionBarRefresher() {
        getActionBar().setTitle("UA Programs");
    }

    // Gets user
    @Override
    protected void onResume() {
        super.onResume();
        getUpComingEventsAndUserComments();
    }

    // Initializes fragments
    private void InitFragments() {
        upcomingEvents = UpComingEventsFragment.fragmentInstance();
        aboutUp = AboutUPFragment.fragmentInstance();
        myUp = MyUPFragment.fragmentInstance();
    }

    private void getUpComingEventsAndUserComments() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String user = preferences.getString(UpConstants.USER_KEY, "");
        you = new Gson().fromJson(user, User.class);
        String cwid = "";
        if (you != null) {
            if (you.uCwid != null) {
                cwid = you.uCwid;
            }
        }
        UpComingEventsAsync ucea = new UpComingEventsAsync(this, cwid) {
            @Override
            protected void onPostExecute(ArrayList<DtoEventBase> dtoEventBases) {
                super.onPostExecute(dtoEventBases);
                if (dtoEventBases == null) {
                    dtoEventBases = new ArrayList<DtoEventBase>();
                }
                upcomingEvents.setUpcomingEventsList(dtoEventBases);
                myUp.setRSVPedEvents(dtoEventBases);
            }
        };
        ucea.execute("");
        GetCommentAsync getComments = new GetCommentAsync(this, cwid) {
            @Override
            protected void onPostExecute(ArrayList<DtoComment> dtoComments) {
                super.onPostExecute(dtoComments);
                if (dtoComments == null) {
                    dtoComments = new ArrayList<DtoComment>();
                }
                myUp.setComments(dtoComments);
            }
        };
        getComments.execute();
    }

    // When the given tab is selected, switch to the corresponding page in the ViewPager.
    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }
    // Used with the NoCwid Dialog
    @Override
    public void PositiveButton() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return upcomingEvents;
                case 1:
                    return aboutUp;
                case 2:
                    return myUp;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }


}
