package com.danazone.sharesms;

/**
 * Created by ismail on 7/12/17.
 */

import android.content.*;
import android.os.Bundle;
import android.telephony.*;
import android.util.Log;
import android.widget.Toast;

// Todo : SMS Reciever Class

public class SimpleSmsReciever extends BroadcastReceiver {

    private static final String TAG = "Message recieved";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle pudsBundle = intent.getExtras();
        Object[] pdus = (Object[]) pudsBundle.get("pdus");
        SmsMessage messages = SmsMessage.createFromPdu((byte[]) pdus[0]);
        // Log.i(TAG,  messages.getMessageBody());

        // Todo : Start Application's  MainActivty activity

        // Todo : Send Message And Number In Activity
       if (!messages.getOriginatingAddress().equals("Facebook")) {

            Intent smsIntent = new Intent(context, SMS_Receive.class);
            smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           // if(messages.getOriginatingAddress().equals("Facebook")) {
                smsIntent.putExtra("MessageNumber", messages.getOriginatingAddress());
                smsIntent.putExtra("Message", messages.getMessageBody());
                context.startActivity(smsIntent);
           // }

            // Todo : Show Message In Toast

            Toast.makeText(context, "SMS Received From :" + messages.getOriginatingAddress() + "\n" + messages.getMessageBody(), Toast.LENGTH_LONG).show();
        }
    }

}
