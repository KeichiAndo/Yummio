package com.example.yummio;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public static int CURRENT_MENU;
    public static int CURRENT_PAGE = 0;
    private int TOTAL_PAGE = 0;

    private TextView mSteps;
    private Button mPrevButton;
    private Button mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        CURRENT_MENU = Integer.parseInt(intent.getStringExtra(intent.EXTRA_TEXT));
        CURRENT_PAGE = 0;
        TOTAL_PAGE = MainActivity.cakeArrayList.get(CURRENT_MENU).getSteps().size() - 1;

        mSteps = findViewById(R.id.tv_steps_label);
        mPrevButton = findViewById(R.id.btn_previous);
        mNextButton = findViewById(R.id.btn_next);

        changeStepsLabel();
        buttonsEnabler();
        showFragment();

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_PAGE--;
                changeStepsLabel();
                buttonsEnabler();
                showFragment();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_PAGE++;
                changeStepsLabel();
                buttonsEnabler();
                showFragment();
            }
        });
    }

    private void changeStepsLabel() {
        mSteps.setText("Steps " + (CURRENT_PAGE + 1) + " of " + (TOTAL_PAGE + 1));
    }

    private void buttonsEnabler() {
        if (CURRENT_PAGE <= 0) {
            mPrevButton.setEnabled(false);
        } else {
            mPrevButton.setEnabled(true);
        }
        if (CURRENT_PAGE >= TOTAL_PAGE) {
            mNextButton.setEnabled(false);
        } else {
            mNextButton.setEnabled(true);
        }
    }

    private void showFragment() {
        Log.v("CURRENT PAGE", CURRENT_PAGE + " / " + TOTAL_PAGE);
        Bundle bundle = new Bundle();
        bundle.putString("SHORT", MainActivity.cakeArrayList.get(CURRENT_MENU).getSteps().get(CURRENT_PAGE).getShortDescription());
        bundle.putString("LONG", MainActivity.cakeArrayList.get(CURRENT_MENU).getSteps().get(CURRENT_PAGE).getDescription());

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .commit();
    }

}
