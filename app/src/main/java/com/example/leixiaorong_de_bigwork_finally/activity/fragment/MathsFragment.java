package com.example.leixiaorong_de_bigwork_finally.activity.fragment;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leixiaorong_de_bigwork_finally.R;
import com.example.leixiaorong_de_bigwork_finally.activity.Model.AlarmModel;
import com.example.leixiaorong_de_bigwork_finally.activity.activity.PlayAlarmActivity;
import com.example.leixiaorong_de_bigwork_finally.activity.data.MyAlarmDataBase;

import java.util.Calendar;
import java.util.Random;

/**
 * 文件描述
 *
 * @author Even.P
 * @since 2018/4/19 11:04
 */
public class MathsFragment extends Fragment {

    private static final long FIVE_MINUTE_TIME = 1000 * 60 * 5;
    private static final int SNOOZE_ALARM_ID = 100;
    private Button confirmBtn;
    private ImageButton sleepButton;
    private TextView mathsTV,hourText,minuteText;
    private PlayAlarmActivity activity;
    private EditText answerET; //回答的答案
    private String problem; //随机生成的问题
    private int answer;  //随机问题的答案

    private MediaPlayer sound;  //失败音效

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_maths,container,false);

        confirmBtn = (Button) view.findViewById(R.id.btn_maths_confirm);
        mathsTV = (TextView) view.findViewById(R.id.tv_maths);
        sleepButton = (ImageButton) view.findViewById(R.id.imageButton);
        hourText = (TextView) view.findViewById(R.id.tv_maths_hour);
        minuteText = (TextView) view.findViewById(R.id.tv_maths_minute);
        answerET = view.findViewById(R.id.et_maths_answer);
        activity = (PlayAlarmActivity) getActivity();

        MyAlarmDataBase db = new MyAlarmDataBase(getActivity());
        AlarmModel alarm = db.getAlarm(activity.getmId());

        Log.d("id " , String.valueOf(activity.getmId()));
        //设置mathsTV 随机生成算数题
        randomProblem();

        Calendar calendar = Calendar.getInstance();
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String  minute = null;

        int showHour = calendar.get(Calendar.HOUR_OF_DAY);
        int showMinute = calendar.get(Calendar.MINUTE);

        if (showMinute < 10){
            minute = "0"+String.valueOf(showMinute);
        }else if (showHour < 10){
            hour= "0"+String.valueOf(showHour);
        }else {
            minute = String.valueOf(showMinute);
            hour = String.valueOf(showHour);
        }

        hourText.setText(hour);
        minuteText.setText(minute);

        //回答正确 停止响铃
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTrue()){
                    playSoundTrue();
                    getActivity().finish();
                }
                else{
                    playSoundFail();
                    Toast.makeText(getContext(), "回答错误（￣︶￣）↗　", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    void randomProblem(){
        mathsTV.setText(" ");
        Random random = new Random();
        int randomInt1 = random.nextInt(500);
        int randomInt2 = random.nextInt(500);
        int symbol = random.nextInt(3);
        mathsTV.append(String.valueOf(randomInt1));
        switch (symbol){
            case 0:
                mathsTV.append(" + ");
                answer = randomInt1 + randomInt2;
                break;
            case 1:
                mathsTV.append(" - ");
                answer = randomInt1 - randomInt2;
                break;
            case 2:
                mathsTV.append(" × ");
                answer = randomInt1 * randomInt2;
                break;
            default:
                mathsTV.append(" + ");
                break;
        }
        mathsTV.append(String.valueOf(randomInt2));
        mathsTV.append(" = ?");
    }

    boolean isTrue(){
        if(Integer.parseInt(answerET.getText().toString()) == answer )
            return true;
        else
            return false;
    }

    private void playSoundFail(){
        sound = MediaPlayer.create(getContext(), R.raw.fail);
        sound.start();
    }
    private void playSoundTrue(){
        sound = MediaPlayer.create(getContext(), R.raw.bingo);
        sound.start();
    }
}
