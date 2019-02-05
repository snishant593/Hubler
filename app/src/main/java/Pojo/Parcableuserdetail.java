package Pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Parcableuserdetail implements Parcelable
{

    private UserDetail userDetail;

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public Parcableuserdetail(UserDetail userDetail) {
        super();
        this.userDetail = userDetail;
    }


    private Parcableuserdetail(Parcel in)
    {

        userDetail = new UserDetail();
        userDetail.setName(in.readString());
        userDetail.setAge(in.readString());
        userDetail.setAddress(in.readString());
        userDetail.setGender(in.readString());
    }

    public static final Creator<Parcableuserdetail> CREATOR = new Creator<Parcableuserdetail>() {
        @Override
        public Parcableuserdetail createFromParcel(Parcel in) {
            return new Parcableuserdetail(in);
        }

        @Override
        public Parcableuserdetail[] newArray(int size) {
            return new Parcableuserdetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(userDetail.getName());
        dest.writeString(userDetail.getAge());
        dest.writeString(userDetail.getAddress());


    }
}
