package com.roundarch.codetest.part2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.EditText;

import com.roundarch.codetest.ProgressDialogFragment;

import java.lang.ref.WeakReference;

/**
 * Created by Eduardo on 10/01/2016.
 */
public class BlackBoxTask extends AsyncTask<Double, Void, Double> {

    private DataModel model;
    private WeakReference<FragmentActivity> weakEditActivity;

    public BlackBoxTask(DataModel model, EditActivity activity) {
        this.model = model;
        weakEditActivity = new WeakReference<FragmentActivity>(activity);
    }

    @Override
    protected Double doInBackground(Double... params) {
        return BlackBox.doMagic(params[0]);
    }

    @Override
    protected void onPostExecute(Double aDouble) {
        super.onPostExecute(aDouble);
        model.setText3(aDouble);
        FragmentActivity editActivity = weakEditActivity.get();
        editActivity.setResult(Activity.RESULT_OK, new Intent().putExtra(Part2Fragment.EXTRA_MODEL, model));
        Log.i("BlackBoxTask", "modifyModelOperation: model text 3: " + model.getText3());
        editActivity.finish();

    }
}
