package com.venns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout); //设置布局
        Button button1 = findViewById(R.id.button_1); //添加按钮监听
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this,"hello world",Toast.LENGTH_SHORT).show(); //设置点击触发事件
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class); //构建一个显式intent 将first传入second
                startActivity(intent); //执行intent
//                finish(); //销毁活动
            }
        });
    }

    //设置菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu); //为当前活动创建菜单
        return true; // 返回true表示显示菜单
    }

    //设置菜单点击触发事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"You Clicked Add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"You Clicked Remove",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}