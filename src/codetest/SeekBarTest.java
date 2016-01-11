package codetest;

import android.app.Instrumentation;
import android.test.InstrumentationTestCase;

import org.junit.Test;

/**
 * Created by Eduardo on 09/01/2016.
 */
public class SeekBarTest extends InstrumentationTestCase{


    public void updateSeekBarValue () {
        final int expected = 1;
        final int reality = 5;
        assertEquals(expected, reality);
    }
}
