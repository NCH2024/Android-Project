package com.example.sms_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsMessage;
import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        procceedSMS(context, intent);
    }

    private void procceedSMS(Context context, Intent intent) {
        Bundle extra = intent.getExtras();
        String message ="";
        String body = "";
        String adress = "";
        if (extra != null) {
            Object[] pdus = (Object[]) extra.get("pdus");
            for(int i=0; i< pdus.length; i++) {
                String format = extra.getString("format");
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                body += smsMessage.getMessageBody();
                adress = smsMessage.getOriginatingAddress();
            }
            message = "Từ: " + adress + "\n"+ "Nội dung:\n" + body + "\n" + "Vừa gửi đến.";
        };
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}