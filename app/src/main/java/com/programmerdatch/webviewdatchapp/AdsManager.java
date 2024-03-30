package com.programmerdatch.webviewdatchapp;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class AdsManager {
    public AdsManager() {
    }

//    private void showRewardedAds() {
//        if (rewardedAd != null) {
//            Activity activityContext = HomeActivity.this;
//            rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
//                @Override
//                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
//                    // Handle the reward.
//                    int rewardAmount = rewardItem.getAmount();
//                    String rewardType = rewardItem.getType();
//                    Toast.makeText(activityContext, "Thank you for Supporting us!", Toast.LENGTH_SHORT).show();
//                }
//            });
//            loadRewardedAds();
//        }
//        else {
//            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//            if(networkInfo == null || !networkInfo.isConnected())
//            {
//                Toast.makeText(HomeActivity.this, "Turn on DATA Connection or WiFi please!", Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
//                //Toast.makeText(HomeActivity.this, "You may have poor connection!", Toast.LENGTH_SHORT).show();
//                if(!isAdLoading)
//                {
//                    //Toast.makeText(HomeActivity.this, "Never loaded", Toast.LENGTH_SHORT).show();
//                    loadRewardedAds();
//                }
//            }
//        }
//    }


//    private void loadRewardedAds() {
//        if(rewardedAd == null)
//        {
//            isAdLoading = true;
//            AdRequest adRequest = new AdRequest.Builder().build();
//            RewardedAd.load(this, "ca-app-pub-4379611405694352/2689869524",
//                    adRequest, new RewardedAdLoadCallback() {
//                        @Override
//                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                            rewardedAd = null;
//                            isAdLoading = false;
//                        }
//
//                        @Override
//                        public void onAdLoaded(@NonNull RewardedAd ad) {
//                            rewardedAd = ad;
//                            if(!isFirstAdsLoaded)
//                            {
//                                showRewardedAds();
//                                isFirstAdsLoaded = true;
//                            }
////                            if(isOnResume)
////                            {
////                                showRewardedAds();
////                                isOnResume = false;
////                            }
//                            isAdLoading = false;
//
//                            rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
//                                @Override
//                                public void onAdClicked() {
//                                    // Called when a click is recorded for an ad.
//                                }
//
//                                @Override
//                                public void onAdDismissedFullScreenContent() {
//                                    // Called when ad is dismissed.
//                                    // Set the ad reference to null so you don't show the ad a second time.
//                                    rewardedAd = null;
//                                }
//
//                                @Override
//                                public void onAdFailedToShowFullScreenContent(AdError adError) {
//                                    // Called when ad fails to show.
//                                    rewardedAd = null;
//                                }
//
//                                @Override
//                                public void onAdImpression() {
//                                    // Called when an impression is recorded for an ad.
//                                }
//
//                                @Override
//                                public void onAdShowedFullScreenContent() {
//                                    // Called when ad is shown.
//                                }
//                            });
//                        }
//                    });
//        }
//    }
}
