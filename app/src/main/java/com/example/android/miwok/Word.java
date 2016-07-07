package com.example.android.miwok;

/**
 * Created by PotnuruSiva on 01-07-2016.
 */
//it contains Telugu translation and English translation of one word
public class Word {

    /*Default translation of the word*/
    private String mDefaultTranslation;
    /*Telugu translation of the word*/
    private String mTeluguTranslation;
    /*Image resource Id*/
    private int mImageResourceId = NO_PROVIDED_IMAGE;
    /*Audio resource Id*/
    private int mAudioResourceId;


    /*Below constant indicates that there is no image in the list item*/
    private static final int NO_PROVIDED_IMAGE = -1;

    public Word(String defaultTranslation, String teluguTranslation, int imageResourceId, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mTeluguTranslation = teluguTranslation;
        mImageResourceId   = imageResourceId;
        mAudioResourceId   = audioResourceId;
    }

    public Word(String defaultTranslation, String teluguTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mTeluguTranslation = teluguTranslation;
        mAudioResourceId = audioResourceId;
    }

    /*Get the default translation of the word*/
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /*Get the telugu translation of the word*/
    public String getTeluguTranslation() {
        return mTeluguTranslation;
    }
    /*Get the image resource id*/
    public int getImageResourceId() {
        return mImageResourceId;
    }
    /*Get the audio resource id*/
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    /*Check for the image associated with the current list item*/
    public boolean hasImage(){
        return  mImageResourceId != NO_PROVIDED_IMAGE;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mTeluguTranslation='" + mTeluguTranslation + '\'' +
                ", mImageResourceId='" + mImageResourceId + '\'' +
                ", mAudioResourceId='" + mAudioResourceId + '\'' +
                '}';
    }
}