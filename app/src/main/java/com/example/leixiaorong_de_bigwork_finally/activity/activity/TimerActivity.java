package com.example.leixiaorong_de_bigwork_finally.activity.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.example.leixiaorong_de_bigwork_finally.R;

public class TimerActivity extends AppCompatActivity {


    private TimePicker timePick1;
    private Button buttone1;
    private TextView h1,h2,m1,m2,s1,s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        timePick1=(TimePicker)findViewById(R.id.timePic1);

        h1 = (TextView)findViewById(R.id.tv_hour_decade);
        h2 = (TextView)findViewById(R.id.tv_hour_unit);
        m1 = (TextView)findViewById(R.id.tv_min_decade);
        m2 = (TextView)findViewById(R.id.tv_min_unit);
        s1 = (TextView)findViewById(R.id.tv_sec_decade);
        s2 = (TextView)findViewById(R.id.tv_sec_unit);

        buttone1=(Button)findViewById(R.id.buttone1);
        OnChangeListener  buc=new OnChangeListener();
        buttone1.setOnClickListener(buc);
        //是否使用24小时制
        timePick1.setIs24HourView(true);
        TimeListener times=new TimeListener();
        timePick1.setOnTimeChangedListener(times);
    }

    class OnChangeListener implements OnClickListener{
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int h=timePick1.getCurrentHour();
            int m=timePick1.getCurrentMinute();
            int s = h * 3600 + m * 60;

            /** 倒计时60秒，一次1秒 */
            CountDownTimer timer = new CountDownTimer(s*1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    // TODO Auto-generated method stub
                    h1.setText(String.valueOf(millisUntilFinished/1000/36000));
                    h2.setText(String.valueOf((millisUntilFinished/1000/3600)%10));
                    m1.setText(String.valueOf((millisUntilFinished/1000/60)%60/10));
                    m2.setText(String.valueOf((millisUntilFinished/1000/60)%60%10));
                    s1.setText(String.valueOf((millisUntilFinished/1000%60)/10));
                    s2.setText(String.valueOf((millisUntilFinished/1000%60)%10));
                }

                @Override
                public void onFinish() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TimerActivity.this);
                    builder.setTitle("提醒")
                            .setMessage("时间到了！")
                            .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            })
                            .setNegativeButton("不用你提醒", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            })
                            .show();
                }
            }.start();

            System.out.println("h:"+h+"   m:"+m);
            m2.setText(String.valueOf(m));//不可直接传入数字 以免当作id来处理
        }
    }
    class TimeListener implements OnTimeChangedListener {

        /**
         * view 当前选中TimePicker控件
         * hourOfDay 当前控件选中TimePicker 的小时
         * minute 当前选中控件TimePicker  的分钟
         */
        @Override
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            // TODO Auto-generated method stub
            System.out.println("h:" + hourOfDay + " m:" + minute);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timer_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_back:
                Intent intent = new Intent(TimerActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
