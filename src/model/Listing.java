
package model;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Listing implements Parcelable{

    @SerializedName("CategoryId")
    @Expose
    private String CategoryId;
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("ProductCount")
    @Expose
    private Integer ProductCount;

    public Listing(String name, String categoryId) {
        CategoryId = categoryId;
        Name = name;
    }

    protected Listing(Parcel in) {
        CategoryId = in.readString();
        Name = in.readString();
    }

    public static final Creator<Listing> CREATOR = new Creator<Listing>() {
        @Override
        public Listing createFromParcel(Parcel in) {
            return new Listing(in);
        }

        @Override
        public Listing[] newArray(int size) {
            return new Listing[size];
        }
    };

    /**
     * 
     * @return
     *     The CategoryId
     */
    public String getCategoryId() {
        return CategoryId;
    }

    /**
     * 
     * @param CategoryId
     *     The CategoryId
     */
    public void setCategoryId(String CategoryId) {
        this.CategoryId = CategoryId;
    }

    /**
     * 
     * @return
     *     The Name
     */
    public String getName() {
        return Name;
    }

    /**
     * 
     * @param Name
     *     The Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * 
     * @return
     *     The ProductCount
     */
    public Integer getProductCount() {
        return ProductCount;
    }

    /**
     * 
     * @param ProductCount
     *     The ProductCount
     */
    public void setProductCount(Integer ProductCount) {
        this.ProductCount = ProductCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(CategoryId);
        dest.writeString(Name);
    }
}
