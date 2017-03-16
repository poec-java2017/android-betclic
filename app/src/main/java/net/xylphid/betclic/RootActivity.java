package net.xylphid.betclic;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mikepenz.materialdrawer.DrawerBuilder;

public class RootActivity extends AppCompatActivity {

    protected LinearLayout globalLayout;
    protected FrameLayout activityLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        // Retreive layouts
        globalLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.activity_root, null);
        activityLayout = (FrameLayout)globalLayout.findViewById(R.id.flActivity);

        // Set the content
        getLayoutInflater().inflate(layoutResID, activityLayout, true);
        super.setContentView(globalLayout);


        // Build the menu
        new DrawerBuilder()
                .withActivity(this)
                //.withToolbar()
                //.inflateMenu(R.menu.header)
                .build();
    }
}
