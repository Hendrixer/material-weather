package com.example.hendrixer.sunshine.app;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.transition.MoveImage;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();

        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        window.setAllowEnterTransitionOverlap(true);
        window.setAllowExitTransitionOverlap(true);

        Transition transition = new MoveImage();
        window.setSharedElementEnterTransition(transition);
        window.setSharedElementExitTransition(transition);

        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class DetailFragment extends Fragment {
        private static final String LOG_TAG = DetailFragment.class.getSimpleName();

        private static final String FORCAST_SHARE_HASHTAG = "#SunshineApp";
        private String mForecastString;

        public DetailFragment() {
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            Intent intent = getActivity().getIntent();
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
               mForecastString = intent.getStringExtra(Intent.EXTRA_TEXT);
                ((TextView) rootView.findViewById((R.id.detail_text)))
                        .setText(mForecastString);
            }

            Button button = (Button) rootView.findViewById(R.id.button);
            final ImageView android = (ImageView) rootView.findViewById(R.id.imageView);

            button.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent demoIntent = new Intent(getActivity(), AnimateActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), android, "roboman");
                    startActivity(demoIntent, options.toBundle());
                }
            });
            return rootView;
        }

        private Intent createShareForecastIntent() {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT,
                    mForecastString + FORCAST_SHARE_HASHTAG);
            return shareIntent;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.detailfragment, menu);

            MenuItem menuItem = menu.findItem(R.id.action_share);

            ShareActionProvider mShareActionProvider =
                    (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

            if (mShareActionProvider != null) {
                mShareActionProvider.setShareIntent(createShareForecastIntent());
            } else {
                Log.d(LOG_TAG, "Share action provider is null?");
            }
        }
    }
}
