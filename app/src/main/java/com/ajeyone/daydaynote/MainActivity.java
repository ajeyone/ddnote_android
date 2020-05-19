package com.ajeyone.daydaynote;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.ajeyone.ui.utils.StatusBarUtil;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;

    private Date mCurrentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.setTranslucentStatus(this);

        initViews();
        initData();
    }

    private void initData() {
        mCurrentDate = new Date();
        onCurrentDateChanged();
    }

    private void initViews() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.toolbar);
        mNavigationView = findViewById(R.id.navigation_view);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawer(GravityCompat.START);
                switch (item.getItemId()) {
                    case R.id.menu_login_or_sign_up:
                        LoginManager.setLogin(true);
                        break;
                    case R.id.menu_logout:
                        LoginManager.setLogin(false);
                        break;
                }
                return true;
            }
        });
        updateNavigationViewMenu();

        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerStateChanged(int newState) {
                updateNavigationViewMenu();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void onCurrentDateChanged() {
        String format = getResources().getString(R.string.current_date_format);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        getSupportActionBar().setTitle(formatter.format(mCurrentDate));
    }

    private void updateNavigationViewMenu() {
        Menu menu = mNavigationView.getMenu();
        if (LoginManager.isLogin()) {
            menu.findItem(R.id.menu_login_or_sign_up).setVisible(false);
            menu.findItem(R.id.menu_password).setVisible(true);
            menu.findItem(R.id.menu_logout).setVisible(true);
        } else {
            menu.findItem(R.id.menu_login_or_sign_up).setVisible(true);
            menu.findItem(R.id.menu_password).setVisible(false);
            menu.findItem(R.id.menu_logout).setVisible(false);
        }
        TextView textViewTitle = findViewById(R.id.title);
        if (textViewTitle != null) {
            textViewTitle.setText(LoginManager.getUserName());
        }
    }
}
