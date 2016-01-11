package com.roundarch.codetest.part2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.roundarch.codetest.ProgressDialogFragment;
import com.roundarch.codetest.R;
import com.roundarch.codetest.part1.Part1Fragment;

public class EditFragment extends Fragment {
    public static final int RESULT_SAVE = 1;
    public static final String EXTRA_MODEL = "extra_model";
    private static final String TAG = EditFragment.class.getSimpleName();

    private DataModel mModel; // TODO - needs to be provided from original Activity/Fragment
    private EditText edit1;
    private EditText edit2;
    private EditText edit3;

    // TODO - This fragment should allow you to edit the fields of DataModel
    // TODO - Then when you click the save button, it should get the DataModel back to the prior activity
    // TODO - so it's up to date

    public static EditFragment newInstance(DataModel dataModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Part2Fragment.EXTRA_MODEL, dataModel);
        EditFragment fragment = new EditFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = (DataModel) getArguments().get(Part2Fragment.EXTRA_MODEL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        view.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_save();
            }
        });

        edit1 = (EditText)view.findViewById(R.id.editText1);
        edit2 = (EditText)view.findViewById(R.id.editText2);
        edit3 = (EditText)view.findViewById(R.id.editText3);

        setRetainInstance(true);

        return view;
    }

    private void modifyModelOperation(final DataModel model) {
        showLoadingDialog();
        refreshModelFromViews();

        // TODO - you need to implement swapText
        swapText(model);

        // TODO - the BlackBox simulates a slow operation, so you will need to update
        // TODO - this code to prevent it from blocking the main thread
//        double newValue = BlackBox.doMagic(model.getText3());
//        double newValue = 0;
        BlackBoxTask blackBoxTask = new BlackBoxTask(model, (EditActivity) getActivity());
        blackBoxTask.execute(model.getText3());
//        model.setText3(newValue);
//        Log.i(TAG, "modifyModelOperation: model text 3: "+ model.getText3());
        // TODO - once the model has been updated, you need to find a good way to
        // TODO - to provide it back to Part2Fragment in the MainActivity
//        getActivity().setResult(Activity.RESULT_OK, new Intent().putExtra(Part2Fragment.EXTRA_MODEL, model));
//        getActivity().finish();
    }

    private void showLoadingDialog() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        ProgressDialogFragment editNameDialog = new ProgressDialogFragment();
        editNameDialog.show(fm, "progress_dialog_fragment");
    }

    private void refreshModelFromViews() {
        // TODO - update our model from the views in our layout
        mModel.setText1(edit1.getText().toString());
        Log.i(TAG, "refreshModelFromViews: model value 1: " + mModel.getText1());
        mModel.setText2(edit2.getText().toString());
        Log.i(TAG, "refreshModelFromViews: model value 2: " + mModel.getText2());
        if (edit3.getText().toString().isEmpty()) {
            mModel.setText3(0);
        } else {
            mModel.setText3(Double.parseDouble(edit3.getText().toString()));
        }
        Log.i(TAG, "refreshModelFromViews: model value 3: " + mModel.getText3());

    }

    // Modifies the data model to swap the values in text1 and text2
    private void swapText(DataModel model) {
        // TODO - swap the text1 and text2 fields on the data model
        model.setText1(edit2.getText().toString());
        Log.i(TAG, "swapText: model model text 1 " + model.getText1());
        model.setText2(edit1.getText().toString());
        Log.i(TAG, "swapText: model model text 2: " + model.getText2());

    }

    public void onClick_save() {
        modifyModelOperation(mModel);
    }

    // TODO - use this method from the Activity to set the model and update
    // TODO - the views in the layout.  You will need to implement the refreshViewsFromModel()
    // TODO - method as well
    public void setModel(DataModel model) { //**** NOT in use, newINstance pattern instead
        mModel = model;
        refreshViewsFromModel();
    }

    private void refreshViewsFromModel() {
        // TODO - update our views based on the model's state
        edit1.setText(mModel.getText1());
        edit2.setText(mModel.getText2());
        edit3.setText(String.valueOf(mModel.getText3()));
    }

//    public int zeroIfValueIsEmpty(String value) {
//        if (value.isEmpty()) {
//            return 0;
//        } else {
//            return value;
//        }

//    }
}
