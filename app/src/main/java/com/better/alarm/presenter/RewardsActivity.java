/*
 * Copyright (C) 2009 The Android Open Source Project
 * Copyright (C) 2012 Yuriy Kulikov yuriy.kulikov.87@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.better.alarm.presenter;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.better.alarm.R;
import com.better.alarm.configuration.InjectKt;
import com.better.alarm.model.Rewards;

import java.util.ArrayList;
import java.util.List;



/**
 * Settings for the Alarm Clock.
 */
public class RewardsActivity extends AppCompatActivity {
    private final Rewards rewardorinos = InjectKt.globalInject(Rewards.class).getValue();
    private SolveAdapter solveAdapter;
    private List<Reward> rewards = new ArrayList<>();
    private final DynamicThemeHandler dynamicThemeHandler = InjectKt.globalInject(DynamicThemeHandler.class).getValue();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(dynamicThemeHandler.getIdForName(RewardsActivity.class.getName()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewards_layout);
        if (!getResources().getBoolean(R.bool.isTablet)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        rewards.add(new Reward(100, "Sorting Algorithms"));
        rewards.add(new Reward(200, "Soothing Violin"));
        rewards.add(new Reward(50, "Chainsaw"));
        rewards.add(new Reward(130, "Fewer numbers"));

        ((TextView) findViewById(R.id.tvRewardCash)).setText(rewardorinos.getRewardPoints().toString());


        solveAdapter = new SolveAdapter(rewards, (TextView) findViewById(R.id.tvRewardCash));



        RecyclerView rv = findViewById(R.id.rvRewards);
        rv.setAdapter(solveAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            goBack();
            return true;
        } else return false;
    }

    private void goBack() {
        // This is called when the Home (Up) button is pressed
        // in the Action Bar.
        Intent parentActivityIntent = new Intent(this, AlarmsListActivity.class);
        // parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(parentActivityIntent);
        finish();
    }


}
