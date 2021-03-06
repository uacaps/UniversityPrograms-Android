package ua.edu.universityprograms.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ua.edu.universityprograms.app.R;

/**
 * Created by vcaciuc on 6/4/2014.
 */
public class ContactUs extends Base {

    @InjectView(R.id.bCall)
    ImageButton call;

    String txtPhn = "2053488404";
    SharedPreferences preferences;

    // Sets the Title for this page
    public void ActionBarRefresher() {
        getActionBar().setTitle("Contact Us");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        setTheme(preferences.getInt("theme", android.R.style.Theme_Holo));
        super.onCreate(savedInstanceState);
        ActionBarRefresher();
        setContentView(R.layout.contact_us);
        ButterKnife.inject(this);
        overridePendingTransition(R.anim.slide_in_left, R.anim.abc_fade_out);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + txtPhn));
                startActivity(callIntent);
            }
        });
    }
}
