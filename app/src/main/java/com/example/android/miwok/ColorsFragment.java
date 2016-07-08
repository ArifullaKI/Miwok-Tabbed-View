package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mediaPlayer;
    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager mAudioManager;
    //This listener gets triggered whenever the audio focus changes (i.e., we gain or lose audio focus because of another app or device).
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                //The AUDIOFOCUS_LOSS case means we've lost audio focus and Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };
    //This listener gets triggered when the (@link media palyer} has completed playing the audio file
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            //Now that the sound file has finished playing, release the media player resources
            releaseMediaPlayer();
        }
    };

    public ColorsFragment() {
        // Required empty public constructor
    }

      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("red", "Yerupu", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("yellow", "pasupu", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Word("violet", "godhuma", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("black", "nalupu", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "thelupu", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("gray", "budida", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("green", "pacha", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "chamanachaya", R.drawable.color_brown, R.raw.color_brown));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Release the MediaPlayer if it is currently exists because we are about to play a different song
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Word word = words.get(position);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                // Start the audio file
                //mediaPlayer.start();

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //we have audio focus now
                    // Create and setup the {@link MediaPlayer} for the audio resource associated with the current word
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());

                    //Start the audio file
                    mediaPlayer.start();
                    //setup a listener on the media player, so that we can stop and release the media player
                    //once the sound has finished playing
                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
        return rootView;
    }

    //Clean up the media player by releasing its resources
    private void releaseMediaPlayer() {
        //If the media player is not null, then it may be currently playing a sound
        if (mediaPlayer != null) {
            //Regardless of the current state of the media player, release its resources because we no longer need it
            mediaPlayer.release();

            //set the media player back to null. For our code, we've decided that setting the mediaplayer
            //to null is an easy way to tell that the media player is not configured to play an audio file at the moment
            mediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        //when the activity is stopped, release the mediaplayer resources because we won't be playing any more sounds
        releaseMediaPlayer();
    }

}
