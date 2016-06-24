package com.example.codingboy.android2048game;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by codingBoy on 16/6/23.
 */
public class Card extends FrameLayout
{
    private TextView label;
    private int num=0;

    public Card(Context context) {
        super(context);

        label=new TextView(context);
        label.setTextSize(32);
        label.setGravity(Gravity.CENTER);
        label.setBackgroundColor(0x33ffffff);

        LayoutParams lp=new LayoutParams(-1,-1);
        lp.setMargins(10,10,0,0);
        addView(label, lp);

        setNum(0);

    }
    public void setNum(int num)
    {
        this.num=num;
        if (num<=0)
        {
            label.setText("");
        }else {
            label.setText(num + "");
        }
    }
    public int getNum()
    {
        return num;
    }

    public boolean equals(Card o)
    {
        return getNum()==o.getNum();
    }
}

