package com.example.game2048;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Random;

/*
author:smc
date 2020/11/11 14:26
 */
public class GameActivity extends Activity {


    int[][] a=new int[4][4];    //棋盘里的数
    int cnt=0,ma=0;
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        init();
        Button up = findViewById(R.id.up);
        Button down = findViewById(R.id.down);
        Button left = findViewById(R.id.left);
        Button right = findViewById(R.id.right);
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goUp();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLeft();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRight();
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goDown();
            }
        });
        Button restart = findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt=0;
                init();
            }
        });
    }
    public void init(){
        cnt=0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                a[i][j]=0;
            }
        }
        createNewRand();
        setPan();
    }
    public void setPan(){
        TextView[] textView = new TextView[16];
        textView[0]=findViewById(R.id.a0);
        textView[1]=findViewById(R.id.a1);
        textView[2]=findViewById(R.id.a2);
        textView[3]=findViewById(R.id.a3);
        textView[4]=findViewById(R.id.a4);
        textView[5]=findViewById(R.id.a5);
        textView[6]=findViewById(R.id.a6);
        textView[7]=findViewById(R.id.a7);
        textView[8]=findViewById(R.id.a8);
        textView[9]=findViewById(R.id.a9);
        textView[10]=findViewById(R.id.a10);
        textView[11]=findViewById(R.id.a11);
        textView[12]=findViewById(R.id.a12);
        textView[13]=findViewById(R.id.a13);
        textView[14]=findViewById(R.id.a14);
        textView[15]=findViewById(R.id.a15);
        for(int i=0;i<16;i++){
            textView[i].setTextSize(30);
            textView[i].setWidth(50);
            textView[i].setHeight(30);

            int p=a[i/4][i%4];
            if(p==0)textView[i].setBackgroundColor(0x1416CCFA);
            if(p==2)textView[i].setBackgroundColor(0x8888FFFF);
            if(p==4)textView[i].setBackgroundColor(Color.LTGRAY);
            if(p==8)textView[i].setBackgroundColor(Color.GRAY);
            if(p==16)textView[i].setBackgroundColor(0x129471FD);
            if(p==32)textView[i].setBackgroundColor(Color.GREEN);
            if(p==64)textView[i].setBackgroundColor(Color.RED);
            if(p==128)textView[i].setBackgroundColor(0x900000FF);
            if(p==256)textView[i].setBackgroundColor(Color.YELLOW);
            if(p==512)textView[i].setBackgroundColor(Color.BLUE);
            if(p==1024)textView[i].setBackgroundColor(Color.CYAN);
            if(p==2048)textView[i].setBackgroundColor(0x6EF11000);

            if(p!=0)textView[i].setText(String.valueOf(p));
            else textView[i].setText("");
        }

        TextView score = findViewById(R.id.score);
        TextView maxscore = findViewById(R.id.maxscore);
        ma=Math.max(ma,cnt);
        Log.d("before","cnt="+cnt);
        score.setText(""+cnt);
        maxscore.setText(""+ma);
        Log.d("after","cnt="+cnt);
    }
    public void goLeft(){
        int jud=0;
        int i,j,k;
        for(i=0;i<4;i++){
            for(j=1;j<4;j++){
                if(a[i][j]!=0) {
                    for (k = j - 1; k >= 0; k--) {
                        if (a[i][k] == 0) continue;
                        else if (a[i][k] == a[i][j]) {
                            jud = 1;
                            a[i][k] *= 2;
                            cnt += a[i][k] ;
                            a[i][j] = -1;
                            break;
                        }//先标记成-1，避免继续合并。
                        else break;
                    }
                }
            }
        }
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                if(a[i][j]==-1)a[i][j]=0;
            }
        }
        for(i=0;i<4;i++){
            for(j=1;j<4;j++){
                if(a[i][j]!=0) {
                    for (k = j - 1; k >= 0; k--) {
                        if (a[i][k] == 0) {
                            jud = 1;
                            a[i][k] = a[i][k + 1];
                            a[i][k + 1] = 0;
                        } else break;
                    }
                }
            }
        }
        if(jud==1)createNewRand();
        setPan();
        checkBox();
    }
    public void goRight(){
        int jud=0;
        int i,j,k;
        for(i=0;i<4;i++){
            for(j=2;j>=0;j--){
                if(a[i][j]!=0) {
                    for (k = j + 1; k < 4; k++) {
                        if (a[i][k] == 0) continue;
                        else if (a[i][k] == a[i][j]) {
                            jud = 1;
                            a[i][k] *= 2;
                            cnt += a[i][k] ;
                            a[i][j] = -1;
                            break;
                        }//先标记成-1，避免继续合并。
                        else break;
                    }
                }
            }
        }
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                if(a[i][j]==-1)a[i][j]=0;
            }
        }
        for(i=0;i<4;i++){
            for(j=2;j>=0;j--){
                if(a[i][j]!=0) {
                    for (k = j +1; k <4; k++) {
                        if (a[i][k] == 0) {
                            jud = 1;
                            a[i][k] = a[i][k - 1];
                            a[i][k - 1] = 0;
                        } else break;
                    }
                }
            }
        }
        if(jud==1)createNewRand();
        setPan();
        checkBox();
    }
    public void goUp(){
        int jud=0;
        int i,j,k;
        for(i=0;i<4;i++){
            for(j=1;j<4;j++){
                if(a[j][i]!=0) {
                    for (k = j - 1; k >= 0; k--) {
                        if (a[k][i] == 0) continue;
                        else if (a[k][i] == a[j][i]) {
                            jud = 1;
                            a[k][i] *= 2;
                            cnt += a[k][i] ;
                            a[j][i] = -1;
                            break;
                        }//先标记成-1，避免继续合并。
                        else break;
                    }
                }
            }
        }
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                if(a[i][j]==-1)a[i][j]=0;
            }
        }
        for(i=0;i<4;i++){
            for(j=1;j<4;j++){
                if(a[j][i]!=0) {
                    for (k = j - 1; k >= 0; k--) {
                        if (a[k][i] == 0) {
                            jud = 1;
                            a[k][i] = a[k+1][i];
                            a[k+1][i] = 0;
                        } else break;
                    }
                }
            }
        }
        if(jud==1)createNewRand();
        setPan();
        checkBox();
    }
    public void goDown(){
        int jud=0;
        int i,j,k;
        for(i=0;i<4;i++){
            for(j=2;j>=0;j--){
                if(a[j][i]!=0) {
                    for (k = j + 1; k < 4; k++) {
                        if (a[k][i] == 0) continue;
                        else if (a[k][i] == a[j][i]) {
                            jud = 1;
                            a[k][i] *= 2;
                            cnt += a[k][i] ;
                            a[j][i] = -1;
                            break;
                        }//先标记成-1，避免继续合并。
                        else break;
                    }
                }
            }
        }
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                if(a[i][j]==-1)a[i][j]=0;
            }
        }
        for(i=0;i<4;i++){
            for(j=2;j>=0;j--){
                if(a[j][i]!=0) {
                    for (k = j +1; k <4; k++) {
                        if (a[k][i] == 0) {
                            jud = 1;
                            a[k][i] = a[k-1][i];
                            a[k-1][i] = 0;
                        } else break;
                    }
                }
            }
        }
        if(jud==1)createNewRand();
        setPan();
        checkBox();
    }

    //检测是否可以继续游戏
    public void checkBox(){
        if(!isValid()){
            AlertDialog dialog;
            dialog = new AlertDialog.Builder(GameActivity.this)
                    .setTitle("游戏结束")  //设置标题
                    .setIcon(R.mipmap.ic_launcher) //设置图标
                    .setMessage("你挂了！"+'\n'+"最后的总分是："+'\n'+cnt) //提示信息
                    .setPositiveButton("重新来过",null)   //添加“确定”按钮
                    .create();  //创建对话框
            dialog.show();  //显示对话框
            init();
        }
    }

    public boolean isValid(){
        int i,j;
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                if(a[i][j]==0)return true;
            }
        }
        for(i=0;i<3;i++){
            for(j=0;j<3;j++){
                if(a[i][j]==a[i+1][j]||a[i][j]==a[i][j+1])return true;
            }
        }
        return false;
    }

    public void createNewRand(){
        while(true){
            Random rand =new Random();
            int p=rand.nextInt(3);
            int st;
            if(p==0)st=4;
            else st=2;
            int x=rand.nextInt(4);
            int y=rand.nextInt(4);
            if(a[x][y]!=0)continue;
            a[x][y]=st;
            break;
        }
    }


}