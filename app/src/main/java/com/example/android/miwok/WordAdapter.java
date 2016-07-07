package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PotnuruSiva on 01-07-2016.
 */
public class WordAdapter extends ArrayAdapter<Word> {

    /*Resource ID for the background color of all the list items*/
    private int mColorResourceId;


    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param words          A List of Word objects to display in a list
     */
        public WordAdapter(Activity context, ArrayList<Word> words, int backgroundColor) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
            super(context, 0, words);
            this.mColorResourceId = backgroundColor;

        }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID list_textView_one
        TextView EnglishTextView = (TextView) listItemView.findViewById(R.id.list_textView_two);
        // Get the english word from the current Word object and
        // set this text on the English Word TextView
        EnglishTextView.setText(currentWord.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID List_textView_two
        TextView TeluguTextView = (TextView) listItemView.findViewById(R.id.list_textView_one);
        // Get the telugu word from the current Word object and
        // set this text on the Telugu word TextView
        TeluguTextView.setText(currentWord.getTeluguTranslation());



        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        //Ensure that if list item has image, need to display on the screen and making the setVisibility to VISIBLE
        //Constant values VISIBLE = 0; INVISIBLE=4; GONE=8;
        if(currentWord.hasImage()) {
            // Get the image resource ID from the current word object and
            // set the image to iconView

            iconView.setImageResource(currentWord.getImageResourceId());
            iconView.setVisibility(View.VISIBLE);
        }
        else {
            iconView.setVisibility(View.GONE);
        }

        //set the theme color  from the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        //Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        //Set the background color of the textContainer view
        textContainer.setBackgroundColor(color);
        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
