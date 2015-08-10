package com.sourcey.materiallogindemo;

/**
 * Created by F_ALI on 10/08/2015.
 */
import java.io.Serializable;

// Container class to hold Contact information.
public class Contact implements Serializable {
    public int mId;
    private String mName;
    private int mThumbnailDrawable;
    private String mNumber;

    public Contact(int id, String name, int thumbnailDrawable, String number) {
        mId = id;
        mName = name;
        mThumbnailDrawable = thumbnailDrawable;
        mNumber = number;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getThumbnailDrawable() {
        return mThumbnailDrawable;
    }

    public String getNumber() {
        return mNumber;
    }
}

