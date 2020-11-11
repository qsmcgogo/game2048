package com.example.game2048;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
/*
auther:smc
date:2020/11/11 13:50
 */

public class MainActivity extends Activity{



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                startActivity(intent);
                finish();
            }

        });

        Button help = findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog;
                dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("帮助")  //设置标题
                        .setIcon(R.mipmap.ic_launcher) //设置图标
                        .setMessage("点击方向键移动数字，合并相同的数字，获得尽量高的分数吧！"+'\n'+"By Smarty 2020/11/11") //提示信息
                        .setPositiveButton("确定",null)   //添加“确定”按钮
                        .create();  //创建对话框
                dialog.show();  //显示对话框
            }
        });

        final Button exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog;
                dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("帮助")  //设置标题
                        .setIcon(R.mipmap.ic_launcher) //设置图标
                        .setMessage("确定要退出吗？") //提示信息
                        .setPositiveButton("确定",new  DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                System.exit(0);
                            }
                        })   //添加“确定”按钮
                        .setNegativeButton("取消",null)   //添加“确定”按钮
                        .create();  //创建对话框
                dialog.show();  //显示对话框
            }
        });
    }



}
