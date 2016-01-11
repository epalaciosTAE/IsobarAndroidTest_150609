package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.roundarch.codetest.MainActivity;
import com.roundarch.codetest.R;
import com.roundarch.codetest.part2.EditActivity;
import com.roundarch.codetest.part3.Part3Fragment;

import java.lang.ref.WeakReference;

import constants.Constants;
import model.Person;

/**
 * Created by Eduardo on 10/01/2016.
 * IMPORTANT:
 * First i did the receiver to be managed by main activity, thats why i was using a weakreference.
 * Then i changed and did all the stuff from the fragment, maybe the activity approach would have been better.
 * I also realize that the receiver method onReceive was called two times, i read this is a bug that depends on the device.
 * The problem was the second time onReceive was called all my class properties were null, so i did
 * a workaround counting the times onReceive was called to make it work it.
 */
public class Part3TestReceiver extends BroadcastReceiver {

    private static boolean isFirstTime = true;
    public static int receiverBugHandler = 0;
    private Person person;
    private Handler handler;
    private Part3Fragment fragment;
    private WeakReference<MainActivity> weakActivity;

    public Part3TestReceiver() {
    }

    public Part3TestReceiver(Person person, Handler handler, Part3Fragment fragment) {
        this.person = person;
        this.handler = handler;
        this.fragment = fragment;

    }

    public Part3TestReceiver(Person person, Handler handler, MainActivity activity) {
        this.person = person;
        this.handler = handler;
        weakActivity = new WeakReference<MainActivity>(activity);

    }


    @Override
    public void onReceive(Context context, final Intent intent) {
//        if (handler == null) {
//            handler = new Handler();
//        }
//        if (person == null) {
//            person = new Person();
//        }

        if (receiverBugHandler == 0) {
            Log.i("RECEIVER", "onReceive: COUNTER " + receiverBugHandler);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    person = intent.getParcelableExtra(Constants.EXTRA_PART3);
                    Log.i("RECEIVER", "onReceive: person" + person.getListing().get(0).getName());
//                    MainActivity activity = weakActivity.get();
//                    Part3Fragment fragment = activity.getTestPagerAdapter().
                    fragment.getAdapter().updateList(person);
                }
            });
            receiverBugHandler++;

        }

    }

}
