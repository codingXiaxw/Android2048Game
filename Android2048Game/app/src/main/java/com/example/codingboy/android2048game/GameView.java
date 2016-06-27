package com.example.codingboy.android2048game;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
                                swipeRight();
                            } else if (offsetX < -5) //向左边移动
                            {
                                swipeLeft();
                            }
                        } else              //竖直方向移动
                        {
                            if (offSetY > 5)           //向下移动
                            {
                                swipeDown();
                            } else if (offSetY < -5) {
                                swipeUp();
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
        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth=(Math.min(w,h)-10)/4;

        addCards(cardWidth, cardWidth);

        startGame();
    }

    public void addCards(int cardWidth,int cardHeight)
    {
        Card c;
        for (int y=0;y<4;y++)
        {
            for (int x=0;x<4;x++)
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
        cardsMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
    }
    private void startGame()
    {
        MainActivity.getMainActivity().clearScore();
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
    public void swipeLeft()
    {
        boolean merge=false;
        for (int y=0; y<4;y++)
        {
            for (int x=0;x<4;x++)
            {
                for (int x1=x+1;x1<4;x1++)
                {
                    if (cardsMap[x1][y].getNum()>0) {
                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x--;

                            merge=true;
                        } else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge=true;
                        }
                        break;
                    }

                }
            }
        }
        if (merge)
        {
            addRandomNum();
            checkComplete();
        }
    }

    public void swipeRight()
    {
        boolean merge=false;
        for (int y=0; y<4;y++)
        {
            for (int x=3;x>=0;x--)
            {
                for (int x1=x-1;x1>=0;x1--)
                {
                    if (cardsMap[x1][y].getNum()>0) {
                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x++;

                            merge=true;
                        } else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge=true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge)
        {
            addRandomNum();
            checkComplete();
        }
    }

    public void swipeDown()
    {
        boolean merge=false;
        for (int x=0; x<4;x++)
        {
            for (int y=3;y>=0;y--)
            {
                for (int y1=y-1;y1>=0;y1--)
                {
                    if (cardsMap[x][y1].getNum()>0) {
                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y++;

                            merge=true;
                        } else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge=true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge)
        {
            addRandomNum();
            checkComplete();
        }
    }
    public void swipeUp()
    {
        boolean merge=false;
        for (int x=0; x<4;x++)
        {
            for (int y=0;y<4;y++)
            {
                for (int y1=y+1;y1<4;y1++)
                {
                    if (cardsMap[x][y1].getNum()>0) {
                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y--;

                            merge=true;
                        } else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge=true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    private void checkComplete()
    {
        boolean complete=true;
        ALL:
        for (int y=0;y<4;y++)
        {
            for (int x=0;x<4;x++)
            {
                if (cardsMap[x][y].getNum()==0||
                        (x>0&&cardsMap[x][y].equals(cardsMap[x-1][y]))||
                        (x<3&&cardsMap[x][y].equals(cardsMap[x+1][y]))||
                        (y>0&&cardsMap[x][y].equals(cardsMap[x][y-1]))||
                        (y<3&&cardsMap[x][y].equals(cardsMap[x][y+1])))
                {
                    complete=false;
                    break ALL;
                }
            }
        }
        if (complete)
        {
            new AlertDialog.Builder(getContext()).setTitle("你好").setMessage("游戏结束").setPositiveButton("重来",new DialogInterface.OnClickListener(){
                @Override
            public void onClick(DialogInterface dialog,int which)
                {
                    startGame();
                }
            }).show();
        }
    }
    private Card[][] cardsMap=new Card[4][4];
    private List<Point> emptyPoint=new ArrayList<>();

}
