package admin.example.com.pipes_v2;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 16.05.2016.
 */
/**
public class Product implements Parcelable {
    public String type_pipes;
    public String L;
    public String D;
    public String S;
    public String M;
    public boolean box;


    public Product(String _describe, String _L, String _D, String _S, String _M, boolean _box) {
        type_pipes = _describe;
        L = _L;
        D = _D;
        S = _S;
        M = _M;
        box = _box;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel parcel, int flag) {
        parcel.writeString(type_pipes);
        parcel.writeString(L);
        parcel.writeString(D);
        parcel.writeString(S);
        parcel.writeString(S);

        //dest.writeStringArray(new String[] { mName, mWhiskers, mPaws, mTail });
    }
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {

        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
    */
public class Product implements Parcelable {
    public String type_pipes;
    public String L;
    public String D;
    public String S;
    public String M;
    public boolean box;

    public Product() {

    }

    public int describeContents() {
        return 0;
    }

    public Product(String _describe, String _L, String _D, String _S, String _M, boolean _box) {
        type_pipes = _describe;
        L = _L;
        D = _D;
        S = _S;
        M = _M;
        box = _box;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(type_pipes);
        out.writeString(L);
        out.writeString(D);
        out.writeString(S);
        out.writeString(M);
    }

    public static final Parcelable.Creator<Product> CREATOR
            = new Parcelable.Creator<Product>() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    private Product(Parcel in) {
        type_pipes = in.readString();
    }
}
