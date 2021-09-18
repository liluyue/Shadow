package com.tencent.shadow.test.plugin.androidx_cases.lib;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.tencent.shadow.test.plugin.androidx_cases.lib.databinding.ButtonBinding;

/**
 * LiveData以Activity为LifecycleOwner
 */
public class LiveDataWithActivityTestActivity extends AppCompatActivity {

    final private MutableLiveData<String> mLiveData = new MutableLiveData<>();
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
//        way1();
            way2();
        } catch (Throwable e) {
            Log.e("way2", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    private void way2() {
        LayoutInflater layoutInflater = getLayoutInflater();
        ButtonBinding binding = ButtonBinding.inflate(layoutInflater);
        setContentView(binding.getRoot());
        Log.e("way2", layoutInflater.getFactory2() + "\t|\t" + binding.androidxBt);
        binding.androidxBt.setText(layoutInflater.getFactory2() + "\t" + binding.androidxBt);
        assert layoutInflater.getFactory2() != null;
//        assert binding.androidxBt instanceof AppCompatButton;
    }

    private void way1() {
        String tag = "data";
        ViewGroup viewGroup = UiUtil.setActivityContentView(this);
        ViewGroup item = UiUtil.makeItem(this, "Data", tag, "");
        viewGroup.addView(item);
        mTextView = item.findViewWithTag(tag);

        final Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String data) {
                mTextView.setText(data);
            }
        };

        mLiveData.observe(this, observer);

        Button button = new Button(this);
        button.setText("ChangeLiveData");
        button.setTag("button");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLiveData.setValue("onClick");
            }
        });
        viewGroup.addView(button);
    }
}
