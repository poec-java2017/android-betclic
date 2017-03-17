package net.xylphid.betclic;

import android.view.View;

public class MyClickListener implements View.OnClickListener {
    protected String id = "";

    public MyClickListener(String id){
        this.id = id;
    }

    @Override
    public void onClick(View v) {

    }
}
