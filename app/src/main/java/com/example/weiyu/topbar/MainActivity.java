package com.example.weiyu.topbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TopBar mTopbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获得自定义topbar
        mTopbar = (TopBar) findViewById(R.id.topBar);
        //为topbar注册监听事件，传入定义的接口，并以匿名类的方法实现接口内的方法
        mTopbar.setOnTopbarCllickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftCilck() {
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            }
        });

        //控制topbar上组件的状态
        mTopbar.setButtonVisable(0,true);
        //mTopbar.setButtonVisable(1,true);
        //隐藏右按钮
        mTopbar.setButtonVisable(1,false);
    }
}
