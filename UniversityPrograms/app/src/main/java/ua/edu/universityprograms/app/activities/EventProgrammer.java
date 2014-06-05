package ua.edu.universityprograms.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ua.edu.universityprograms.app.Adapters.MembersAdapter;
import ua.edu.universityprograms.app.R;
import ua.edu.universityprograms.app.models.Members;

/**
 * Created by vcaciuc on 6/5/2014.
 */
public class EventProgrammer  extends Activity implements AdapterView.OnItemClickListener{
    @InjectView(R.id.gvAssist)
    GridView assistants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grad_assist);
        ButterKnife.inject(this);
        MembersAdapter adapter = new MembersAdapter(this, getAssistants());
        assistants.setAdapter(adapter);
        assistants.setOnItemClickListener(this);
    }
    ArrayList<Members> list;
    public ArrayList<Members> getAssistants(){
        list = new ArrayList<Members>();
        Resources res = getResources();
        list.add(new Members("http://www.up.ua.edu/images/UPWebsite-StaffNew_14.jpg", res.getString(R.string.event_prog1_name), res.getString(R.string.event_prog1_info)));
        list.add(new Members("http://www.up.ua.edu/images/UPWebsite-StaffNew_6.jpg", res.getString(R.string.event_prog2_name), res.getString(R.string.event_prog2_info)));
        list.add(new Members("http://www.up.ua.edu/images/UPWebsite-StaffNew.jpg", res.getString(R.string.event_prog3_name), res.getString(R.string.event_prog3_info)));
        list.add(new Members("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQCH54gYMjvoXek0ju1q9xlFB635cIdQTNHXcxw_vh6HHEuBVR4UPUbszyl", res.getString(R.string.event_prog4_name), res.getString(R.string.event_prog4_info)));

        return list;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(EventProgrammer.this, Member.class);
        intent.putExtra("memb",list.get(i));
        startActivity(intent);
    }
}