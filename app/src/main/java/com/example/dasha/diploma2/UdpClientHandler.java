package com.example.dasha.diploma2;
import android.os.Handler;
import android.os.Message;
/**
 * Created by dasha on 15.04.2018.
 */

public class UdpClientHandler extends Handler {
    public static final int UPDATE_END = 2;
    private MainActivity parent;

    public UdpClientHandler(MainActivity parent) {
        super();
        this.parent = parent;
    }

    @Override
    public void handleMessage(Message msg) {

        switch (msg.what){
            case UPDATE_END:
                parent.clientEnd();
                break;
            default:
                super.handleMessage(msg);
        }

    }
}