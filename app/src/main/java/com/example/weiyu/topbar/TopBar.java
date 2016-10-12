package com.example.weiyu.topbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/12.
 */
public class TopBar extends RelativeLayout {

    // 包含topbar上的元素：左按钮、右按钮、标题
    private Button mLeftButton, mRightButton;
    private TextView mTitleView;

    // 布局属性，用来控制组件元素在ViewGroup中的位置
    private LayoutParams mLeftParams, mTitlepParams, mRightParams;

    // 左按钮的属性值，即我们在atts.xml文件中定义的属性
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;
    // 右按钮的属性值，即我们在atts.xml文件中定义的属性
    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;
    // 标题的属性值，即我们在atts.xml文件中定义的属性
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;

    // 映射传入的接口对象
    private topbarClickListener mListener;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置topbar的背景
        setBackgroundColor(0xfff0ffff);
        //通过这个方法慢慢讲在attrs.xml中定义的declare-styleable的所有属性值存储到TypeArray中
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.TopBar);
        //从TypeArray中取出对应的值来为要设置的属性赋值
        mTitle = ta.getString(R.styleable.TopBar_title);
        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize,10);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor,0);

        mLeftText = ta.getString(R.styleable.TopBar_leftText);
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor,0);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);

        mRightText = ta.getString(R.styleable.TopBar_rightText);
        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor,0);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        //获取完TypeArray的值后，一般要调用recycle方法来避免重新创建的时候的错误
        ta.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        //为创建的组件元素赋值，值就来源于在引用的xml文件中给对应属性的赋值
        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        mLeftButton.setText(mLeftText);
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);

        mRightButton.setText(mRightText);
        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);

        //为组件元素设置相应的布局文件
        mTitlepParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mTitlepParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        //添加到ViewGroup
        addView(mTitleView,mTitlepParams);

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(mLeftButton,mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(mRightButton,mRightParams);

        //按钮的点击事件，不需要具体的实现，只需要调用接口的方法，回调的时候会具体实现
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.leftCilck();
            }
        });
        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.rightClick();
            }
        });

    }

    //暴露一个方法给调用者来注册接口回调，通过接口来获得回调者对接口方法的实现
    public void setOnTopbarCllickListener(topbarClickListener mListener){
        this.mListener = mListener;
    }

    //接口对象，实现毁掉机制，在回调方法中通过映射的接口对象调用接口中的方法
    //不用去考虑如何去实现，具体的实现由调用者去创建
    public interface topbarClickListener{
        //左按钮点击事件
        void leftCilck();
        //右按钮点击事件
        void rightClick();
    }

    public void setButtonVisable(int id,boolean flag){
        if(flag){
            if(id==0) {
                mLeftButton.setVisibility(View.VISIBLE);
            }else {
                mRightButton.setVisibility(View.VISIBLE);
            }
        }else{
            if(id==0){
                mLeftButton.setVisibility(View.GONE);
            }else{
                mRightButton.setVisibility(View.GONE);
            }
        }
    }
}
