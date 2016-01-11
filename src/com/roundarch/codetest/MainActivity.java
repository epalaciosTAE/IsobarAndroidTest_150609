package com.roundarch.codetest;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.roundarch.codetest.part1.Part1Fragment;
import com.roundarch.codetest.part2.DataModel;
import com.roundarch.codetest.part2.Part2Fragment;
import com.roundarch.codetest.part3.Part3Fragment;
import com.roundarch.codetest.part3.Part3Service;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import model.Listing;
import model.Person;
import receiver.Part3TestReceiver;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewPager mViewPager;
    private Part3Service.Part3ServiceBinder zipBinder = null;
    private Part3Service part3Service;
    private Intent serviceIntent;
//    private Part3TestReceiver receiver;
//    private Person person;
    private TestPagerAdapter testPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        testPagerAdapter = new TestPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(testPagerAdapter);
        serviceIntent = new Intent(this, Part3Service.class);


//        fakeDataForRecyclerView();
//        receiver = new Part3TestReceiver(person, new Handler(), MainActivity.this);
//        startService(serviceIntent);
    }

    public TestPagerAdapter getTestPagerAdapter() {
        return testPagerAdapter;
    }

//    private void fakeDataForRecyclerView() {
//        person = new Person();
//        List<Listing> listings = new ArrayList<>(10);
//        int counter = 0;
//        for (int i = 0; i < 10; i++) {
//            listings.add(new Listing("name " + String.valueOf(counter), String.valueOf(counter)));
//            counter++;
//        }
//        person.setListing(listings);
//    }

    @Override
    protected void onStart() {
        super.onStart();

        // TODO - for Part3, this might be a good place to bind to our Service
        Log.i(TAG, "onStart: Bind service");
        bindService(new Intent(this, Part3Service.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected: ");
            zipBinder = (Part3Service.Part3ServiceBinder) service;
            part3Service = ((Part3Service.Part3ServiceBinder) zipBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            part3Service = null;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
//        registerReceiver(receiver, new IntentFilter(Constants.ACTION_DOWNLOAD_SUCCESS));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(serviceConnection);
//        unregisterReceiver(receiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(serviceIntent);
    }

    public class TestPagerAdapter extends FragmentStatePagerAdapter {

        private Fragment currentFragment;
        private Part3Fragment part3Fragment;

        public TestPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                {
                    Fragment fragment = new Part1Fragment();
                    return fragment;
                }
                case 1:
                {
                    Fragment fragment = new Part2Fragment();
                    return fragment;
                }
                case 2: {
//                    Fragment fragment = new Part3Fragment();
                    part3Fragment = Part3Fragment.newInstance();
                    return part3Fragment;
                }

                default:
                    throw new RuntimeException("Invalid count for pager adapter");
            }

        }



        public Fragment getCurrentFragment() {
            return currentFragment;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            if (getCurrentFragment() != object) {
                currentFragment = (Fragment) object;
            }
            super.setPrimaryItem(container, position, object);
        }


        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return "Part 1";
                case 1:
                    return "Part 2";
                case 2:
                    return "Part 3";
            }
            return "OBJECT " + (position + 1);
        }
    }


}
