package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Locale;

/**
 * Created by PotnuruSiva on 03-07-2016.
 */
public class CategoryAdapter extends FragmentPagerAdapter {

    /** Context of the app */
    private Context mContext;
    /**
            * Create a new {@link CategoryAdapter} object.
    *
            * @param context is the context of the app
    * @param fm is the fragment manager that will keep each fragment's state in the adapter
            *           across swipes.
            */
    public CategoryAdapter(Context context,FragmentManager fm){
            super(fm);
            mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //return PlaceholderFragment.newInstance(position + 1);

        switch (position){
            case 0:
                return new NumbersFragment();
            case 1:
                return new FamilyFragment();
            case 2:
                return new ColorsFragment();
            case 3:
                return new PhrasesFragment();

        }
        return null;
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();

        // Generate title based on item position
        switch (position){
            case 0:

                return mContext.getString(R.string.category_numbers).toUpperCase(l);
            case 1:

                return mContext.getString(R.string.category_family).toUpperCase(l);
            case 2:

                return mContext.getString(R.string.category_colors).toUpperCase(l);
            case 3:

                return mContext.getString(R.string.category_phrases).toUpperCase(l);
        }
        return null;
    }


}
