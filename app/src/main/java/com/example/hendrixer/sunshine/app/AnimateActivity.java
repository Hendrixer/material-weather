package com.example.hendrixer.sunshine.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.transition.MoveImage;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;

public class AnimateActivity extends Activity {

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
        setContentView(R.layout.activity_animate);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new AnimateFragment())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        final View view = findViewById(R.id.textView);

        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getBottom() + view.getTop()) / 2;

        int finalRadius = view.getWidth();

        ValueAnimator reveal =
                ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);

        reveal.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });
        reveal.start();

        this.finishAfterTransition();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.animate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class AnimateFragment extends Fragment {

        public AnimateFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            Log.d("ONCREATEVIEW", "HERE");

            View rootView = inflater.inflate(R.layout.fragment_animate, container, false);

            final View view = rootView.findViewById(R.id.textView);

            view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                    int cx = (view.getLeft() + view.getRight()) / 2;
                    int cy = (view.getBottom() + view.getTop()) / 2;

                    int finalRadius = view.getWidth();

                    ValueAnimator reveal =
                            ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);

                    reveal.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            view.setVisibility(View.VISIBLE);
                        }

                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
//                            view.setVisibility(View.VISIBLE);
                        }
                    });
                    reveal.start();
                }
            });

            return rootView;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("RESUME", "SHITT");
    }
}
