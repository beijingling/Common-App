package com.littleox.common.app;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.littleox.common.app.bean.GithubUser;
import com.littleox.common.app.network.WebApi;
import com.littleox.common.commonlib.baserx.RxManager;
import com.littleox.common.commonlib.baserx.RxSubscriber;
import com.littleox.common.commonlib.baserx.RxUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        RxManager rxManager = new RxManager();
        rxManager.addSubscribe(WebApi.getInstance().getService().getUserInfo("octokit").compose(RxUtil.rxSchedulerHelper())
                .subscribeWith(new RxSubscriber<GithubUser>(this) {
                    @Override
                    protected void _onNext(GithubUser githubUser) {
                        
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
