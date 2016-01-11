package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 20/12/2015.
 */
public class Person implements Parcelable{

    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("Listing")
    @Expose
    private List<Listing> Listing = new ArrayList<Listing>();
    @SerializedName("SortType")
    @Expose
    private String SortType;

    private boolean gender;

    public Person() {

    }

    protected Person(Parcel in) {
        Description = in.readString();
        Listing = in.createTypedArrayList(model.Listing.CREATOR);
        SortType = in.readString();
        gender = in.readByte() != 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     *     The Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     *
     * @param Description
     *     The Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     *
     * @return
     *     The Listing
     */
    public List<Listing> getListing() {
        return Listing;
    }

    /**
     *
     * @param Listing
     *     The Listing
     */
    public void setListing(List<Listing> Listing) {
        this.Listing = Listing;
    }

    /**
     *
     * @return
     *     The SortType
     */
    public String getSortType() {
        return SortType;
    }

    /**
     *
     * @param SortType
     *     The SortType
     */
    public void setSortType(String SortType) {
        this.SortType = SortType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(Description);
        dest.writeTypedList(Listing);
        dest.writeString(SortType);
        dest.writeByte((byte) (gender ? 1 : 0));
    }

}
