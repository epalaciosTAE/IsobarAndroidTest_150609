package com.roundarch.codetest.part2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.roundarch.codetest.R;

public class Part2Fragment extends Fragment {
    public static final int REQUEST_EDIT_ACTIVITY = 100;
    private static final String TAG = Part2Fragment.class.getSimpleName();
    public static String EXTRA_MODEL = "extra_model";
    private DataModel mModel = new DataModel();

    private TextView textView1;
    private TextView textView3;
    private TextView textView2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part2, container, false);

        // TODO -
        view.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_edit();
            }
        });

        textView1 = (TextView)view.findViewById(R.id.textView1);
        textView2 = (TextView)view.findViewById(R.id.textView2);
        textView3 = (TextView)view.findViewById(R.id.textView3);
//        textView3.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(EXTRA_MODEL)) {
                mModel = (DataModel) savedInstanceState.get(EXTRA_MODEL);
            }
        }

        setTextViews();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Part2Fragment.REQUEST_EDIT_ACTIVITY) {
            DataModel model = data.getParcelableExtra(Part2Fragment.EXTRA_MODEL);
            Log.i(TAG, "onActivityResult: model " + model.getText1());

            textView1.setText(model.getText1());
            textView2.setText(model.getText2());
            textView3.setText(String.valueOf((float) model.getText3()));

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(EXTRA_MODEL, mModel); // **** changed to parcelable

        super.onSaveInstanceState(outState);
    }

    public void onClick_edit() {
        // TODO - package up the data model and provide it to the new EditActivity as it is being created
        Intent intent = new Intent(this.getActivity(), EditActivity.class);
        intent.putExtra(EXTRA_MODEL, mModel);// **** adding data model
        // TODO - this probably isn't the best way to start the EditActivty, try to fix it
        startActivityForResult(intent, REQUEST_EDIT_ACTIVITY);
    }

    // TODO - provide a method to obtain the data model when it is returned from the EditActivity

    private void setTextViews() {
        textView1.setText(mModel.getText1());
        textView2.setText(mModel.getText2());
        textView3.setText(String.format("%1$,.2f", mModel.getText3()));
    }
}
