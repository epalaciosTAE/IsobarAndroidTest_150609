package com.roundarch.codetest.part3;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.roundarch.codetest.MainActivity;
import com.roundarch.codetest.R;
import com.roundarch.codetest.part2.Part2Fragment;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import model.Listing;
import model.Person;
import receiver.Part3TestReceiver;

public class Part3Fragment extends Fragment {

    private Part3Adapter adapter;
    private Person person;
    private Part3TestReceiver receiver;

    public static Part3Fragment newInstance() {
        return new Part3Fragment();
    }

    public static Part3Fragment newInstance(Person person) {
        Bundle b = new Bundle();
        b.putParcelable(Constants.EXTRA_PART3, person);
        Part3Fragment fragment = new Part3Fragment();
        fragment.setArguments(b);
        return fragment;
    }


        private void fakeDataForRecyclerView() {
            person = new Person();
            List<Listing> listings = new ArrayList<>(10);
            int counter = 0;
            for (int i = 0; i < 10; i++) {
                listings.add(new Listing("name " + String.valueOf(counter), String.valueOf(counter)));
                counter++;
            }
            person.setListing(listings);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiver = new Part3TestReceiver(person, new Handler(), this);
        fakeDataForRecyclerView();
        getActivity().startService(new Intent(getActivity(), Part3Service.class));
    }

    public Part3Adapter getAdapter() {
        return adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part3, container, false);

//        View emptyView = (View) view.findViewById(R.id.empty_textview);
        RecyclerView listView = (RecyclerView) view.findViewById(R.id.part3_listview);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new Part3Adapter(person, getActivity());
        listView.setAdapter(adapter);

        // TODO - the listview will need to be provided with a source for data

        // TODO - (optional) you can set up handling to list item selection if you wish

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // TODO - when the fragment resumes, it would be a good time to register to receieve broadcasts
        // TODO - from the service.  The broadcast will serve as a way to inform us that data is available
        // TODO - for consumption
        getActivity().registerReceiver(receiver, new IntentFilter(Constants.ACTION_DOWNLOAD_SUCCESS));
        // TODO - this is also a good place to leverage the Service's IBinder interface to tell it you want
        // TODO - to refresh data
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
        Part3TestReceiver.receiverBugHandler = 0;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    // TODO - our listView needs a source of data, and here might be a good place to create that

    // TODO - we also need a means of responding to the Broadcasts sent by our Service

}
