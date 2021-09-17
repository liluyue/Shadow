package com.tencent.shadow.dynamic.host;

import android.content.ComponentName;
import android.os.IBinder;

public interface EnterServiceCallBack extends EnterCallback {
    void onServiceConnected(ComponentName name, IBinder service);

    void onServiceDisconnected(ComponentName name);

}
