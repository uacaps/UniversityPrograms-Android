package ua.edu.universityprograms.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ua.edu.universityprograms.app.Adapters.AboutAdapter;
import ua.edu.universityprograms.app.R;

/**
 * Created by vcaciuc on 6/4/2014.
 */
public class TeamMembers extends Activity {
    private String categories[] = {"Director", "Graduate Assistent", "Event Programmer",
            "Communication Team", "Office Assistant", "Intern"};
    @InjectView(R.id.lvAbout)
    ListView lvMembers;

    AboutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_members);
        ButterKnife.inject(this);
        initialize();
    }

    public void initialize(){
        adapter = new AboutAdapter(TeamMembers.this, categories);
        lvMembers.setAdapter(adapter);
        lvMembers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Intent intent = null;
                switch (pos) {
                    case 0:
                        intent = new Intent(TeamMembers.this, Director.class);
                        break;
                    case 1:
                        intent = new Intent(TeamMembers.this, GraduateAssistant.class);
                        break;
                    case 2:
                        intent = new Intent(TeamMembers.this, TeamMembers.class);
                        break;
                    case 3:
                        intent = new Intent(TeamMembers.this, ContactUs.class);
                        break;
                    case 4:
                        intent = new Intent(TeamMembers.this, TeamMembers.class);
                        break;
                    case 5:
                        intent = new Intent(TeamMembers.this, ContactUs.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }
}