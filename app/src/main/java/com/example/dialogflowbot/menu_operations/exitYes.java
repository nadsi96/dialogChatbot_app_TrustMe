package com.example.dialogflowbot.menu_operations;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.dialogflowbot.interfaces.YesClicked;

public class exitYes extends Activity implements YesClicked {

    Context context;

    public exitYes(Context context){
        this.context = context;
    }
    @Override
    public void yesClick() {
        finishAffinity();
        System.runFinalization();
        System.exit(0);
        // finish() == 가장 상위(현재 화면에 보이는 Activity 종료)
        // finishAffinity() == root Activity 종료(모든 Activity 종료)
    }
}
