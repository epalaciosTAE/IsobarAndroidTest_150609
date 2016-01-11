package api;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import constants.Constants;
import model.Person;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Eduardo on 10/01/2016.
 */
public class TestApiRestAdapter {

    private ITestApi iTestApi;
    private Context context;

    public TestApiRestAdapter(Context context) {
        this.context = context;
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        iTestApi = restAdapter.create(ITestApi.class);
    }

    public void getDataApi() {
        iTestApi.getWomanClothes(new Callback<Person>() {
            @Override
            public void success(Person person, Response response) {
                Log.i("REST API", "success: person " + person.getDescription());
                context.sendBroadcast(new Intent(Constants.ACTION_DOWNLOAD_SUCCESS)
                        .putExtra(Constants.EXTRA_PART3,person));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
