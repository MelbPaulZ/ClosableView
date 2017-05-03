package com.developer.paul.closableview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Paul on 3/5/17.
 */

public class ItemViewModel extends BaseObservable {
    private TestData testData;
    private LinearLayout linearLayout;
    private TextView textView;
    public ItemViewModel() {
        testData = new TestData("name", "icon");
    }

    public void setLinearLayout(LinearLayout linearLayout){
        this.linearLayout = linearLayout;
    }

    public void setTextView(TextView tv){
        this.textView = tv;
    }


    @Bindable
    public TestData getTestData() {
        return testData;
    }

    public void setTestData(TestData testData) {
        this.testData = testData;
        notifyPropertyChanged(BR.testData);
    }

    public View.OnClickListener delete(){
        return new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                v.animate()
                        .alpha(0f)
                        .setDuration(500)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                v.setVisibility(View.GONE);
                            }
                        });

            }
        };
    }


}
