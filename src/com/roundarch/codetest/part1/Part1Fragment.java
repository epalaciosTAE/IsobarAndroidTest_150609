package com.roundarch.codetest.part1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import com.roundarch.codetest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * Created by mdigiovanni on 8/15/13.
 */
public class Part1Fragment extends Fragment implements SeekBar.OnSeekBarChangeListener{
    private static final String TAG = Part1Fragment.class.getSimpleName();
    // TODO - any member variables you need to store?
    boolean isSync;
    int valueSeekBarA, valueSeekBarB, difference;
    @Bind(R.id.switch_sync)
    protected Switch switchSync;
    @Bind(R.id.seekBarA)
    protected SeekBar seekBarA;
    @Bind(R.id.seekBarB)
    protected SeekBar seekBarB;
    @Bind(R.id.tvDifference)
    protected TextView tvDifference;

    //FIXME: Improve something! Anything
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part1_fixed, container, false);
        ButterKnife.bind(this, view);
        seekBarA.setOnSeekBarChangeListener(this);
        seekBarB.setOnSeekBarChangeListener(this);

        // TODO - obtain references to your views from the layout

        // TODO - hook up any event listeners that make sense for the task

        return view;
    }

    @OnCheckedChanged(R.id.switch_sync)
    protected void onCheckedChangedListener(CompoundButton buttonView, boolean isChecked) {
        isSync = isChecked;
    }

    public int calculateDifference(int a, int b) {
        return a - b;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (isSync) {
            setValuesSync(seekBar, progress);
        } else {
            setValuesNoSync(seekBar, progress);
        }
        tvDifference.setText(String.valueOf(calculateDifference(valueSeekBarA, valueSeekBarB)));
    }

    private void setValuesNoSync(SeekBar seekBar, int progress) {
        if (isSeekBarA(seekBar)) {
            valueSeekBarA = progress;
            Log.i(TAG, "onProgressChanged: value seekbar A: " + valueSeekBarA);
        } else {
            valueSeekBarB = progress;
            Log.i(TAG, "onProgressChanged: value seekbar B: " + valueSeekBarB);
        }
    }

    private void setValuesSync(SeekBar seekBar, int progress) {
        valueSeekBarA = valueSeekBarB = progress;
        if (isSeekBarA(seekBar)) {
            seekBarB.setProgress(valueSeekBarA);
        } else {
            seekBarA.setProgress(valueSeekBarA);
        }
    }

    private boolean isSeekBarA(SeekBar seekBar) {
        return seekBar.getId() == R.id.seekBarA;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

//    public int SeekBarValue(SeekBar seekBar) {
//        if (seekBar.getId() == R.id.seekBarA) {
//            valueSeekBarA =
//        }
//    }
}
