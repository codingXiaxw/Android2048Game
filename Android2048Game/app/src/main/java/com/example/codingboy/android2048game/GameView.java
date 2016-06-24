package com.example.codingboy.android2048game;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codingBoy on 16/6/23.
 */
public class GameView extends GridLayout
{

    public GameView(Context context)
    {
        super(context);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initGameView();
    }

    private void initGameView()
    {
        setColumnCount(4);
        setBackgroundColor(0xffbbada0);

        setOnTouchListener(new OnTouchListener() {

            private float startX, startY, offsetX, offSetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;
                        offSetY = event.getX() - startY;

                        if (Math.abs(offsetX) > Math.abs(offSetY)) //水平方向移动
                        {
                            if (offsetX > 5)    //向右边移动
                            {
                                System.out.println("right");
                            } else if (offsetX < -5) //向左边移动
                            {
                                System.out.println("left");
                            }
                        } else              //竖直方向移动
                        {
                            if (offSetY > 5)           //向下移动
                            {
                                System.out.println("down");
                            } else if (offSetY < -5) {
                                System.out.println("up");
                            }

                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h,int oldw,int oldh)
    {
        super.onSizeChanged(w,h,oldw,oldh);

        int cardWidth=(Math.min(w,h)-10)/4;

        addCards(cardWidth, cardWidth);

        startGame();
    }

    public void addCards(int cardWidth,int cardHeight)
    {
        Card c;
        for (int x=0;x<4;x++)
        {
            for (int y=0;y<4;y++)
            {
                c=new Card(getContext());
                c.setNum(0);
                addView(c, cardWidth, cardHeight);

                cardsMap[x][y]=c;
            }
        }
    }

    public void addRandomNum()
    {
        emptyPoint.clear();

        for (int y=0;y<4;y++)
        {
            for (int x=0;x<4;x++)
            {
                if (cardsMap[x][y].getNum()<=0)
                {
                    emptyPoint.add(new Point(x,y));
                }
            }
        }

        Point p=emptyPoint.remove((int)(Math.random()*emptyPoint.size()));
        cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
    }
    private void startGame()
    {
        for (int y=0;y<4;y++)
        {
            for (int x=0;x<4;x++)
            {
                cardsMap[x][y].setNum(0);
            }
        }
        addRandomNum();
        addRandomNum();
    }

    public void swipeRight()
    {

    }
    public void swipeLeft()
    {

    }
    public void swipeDown()
    {

    }
    public void swipeUp()
    {

    }
    private Card[][] cardsMap=new Card[4][4];
    private List<Point> emptyPoint=new ArrayList<>();

}
