package api;

import constants.Constants;
import model.Person;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Eduardo on 10/01/2016.
 */
public interface ITestApi {

    @GET(Constants.END_POINT_CLOTHS_WOMAN)
    public void getWomanClothes(Callback<Person> response);
}
