package com.programmerdatch.webviewdatchapp;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class AnimateImage {

    private Animation rotateAnimation;
    private Animation zoomAnimation;
    private Animation fadeInAnimation;
    private Animation fadeOutAnimation;
    private Animation slideUpAnimation;
    private Animation slideDownAnimation;
    private Animation translateAnimation;

    public AnimateImage(Context context) {
        // Create the rotation animation
        rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setInterpolator(new LinearInterpolator());

        // Create the zoom animation
        zoomAnimation = new ScaleAnimation(1, 1.5f, 1, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        zoomAnimation.setDuration(1000);
        zoomAnimation.setRepeatCount(Animation.INFINITE);
        zoomAnimation.setRepeatMode(Animation.REVERSE);

        // Create the fade-in animation
        fadeInAnimation = new AlphaAnimation(0, 1);
        fadeInAnimation.setDuration(1000);

        // Create the fade-out animation
        fadeOutAnimation = new AlphaAnimation(1, 0);
        fadeOutAnimation.setDuration(1000);

        // Create the slide-up animation
        slideUpAnimation = new TranslateAnimation(0, 0, 0, -100);
        slideUpAnimation.setDuration(1000);

        // Create the slide-down animation
        slideDownAnimation = new TranslateAnimation(0, 0, 0, 100);
        slideDownAnimation.setDuration(1000);

        // Create the translate animation
        translateAnimation = new TranslateAnimation(0, 100, 0, 0);
        translateAnimation.setDuration(1000);
    }

    public void applyRotationAnimation(ImageView imageView) {
        imageView.startAnimation(rotateAnimation);
    }

    public void applyZoomAnimation(ImageView imageView) {
        imageView.startAnimation(zoomAnimation);
    }

    public void applyFadeInAnimation(ImageView imageView) {
        imageView.startAnimation(fadeInAnimation);
    }

    public void applyFadeOutAnimation(ImageView imageView) {
        imageView.startAnimation(fadeOutAnimation);
    }

    public void applySlideUpAnimation(ImageView imageView) {
        imageView.startAnimation(slideUpAnimation);
    }

    public void applySlideDownAnimation(ImageView imageView) {
        imageView.startAnimation(slideDownAnimation);
    }

    public void applyTranslateAnimation(ImageView imageView) {
        imageView.startAnimation(translateAnimation);
    }
}
