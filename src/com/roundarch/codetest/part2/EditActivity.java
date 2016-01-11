package com.roundarch.codetest.part2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.roundarch.codetest.R;

public class EditActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit);

        // TODO - you will need to obtain the model object provided to this activity and provide it to the EditFragment
        DataModel dataModel = getIntent().getParcelableExtra(Part2Fragment.EXTRA_MODEL);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container, EditFragment.newInstance(dataModel)).commit();
    }
}
