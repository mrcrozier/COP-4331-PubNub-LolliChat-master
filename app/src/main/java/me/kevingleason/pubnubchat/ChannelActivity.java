package me.kevingleason.pubnubchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

public class ChannelActivity extends Activity {

    ArrayList<Chatroom> chatrooms;
    ArrayList<String> chatroomStrings;
    Location myLocation;

    private MyClassAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        Chatroom studentUnion = new Chatroom("Student Union", 28.601925, -81.200535);
        Chatroom library = new Chatroom("Library", 28.600368, -81.201542);
        Chatroom starbucks = new Chatroom("Starbucks", 28.603421, -81.198883);

        ListView channelList = (ListView)findViewById(R.id.listView);

        myLocation = new Location("myLocation");

        myLocation.setLatitude(28.602390);
        myLocation.setLongitude(-81.200929);


        Location locationA = new Location("locationA");
        locationA.setLatitude(studentUnion.latitude);
        locationA.setLongitude(studentUnion.longitude);

        Location locationB = new Location("locationB");
        locationB.setLatitude(library.latitude);
        locationB.setLongitude(library.longitude);

        Location locationC = new Location("locationB");
        locationC.setLatitude(starbucks.latitude);
        locationC.setLongitude(starbucks.longitude);

        studentUnion.distanceTo = getMiles(myLocation.distanceTo(locationA));
        library.distanceTo = getMiles(myLocation.distanceTo(locationB));
        starbucks.distanceTo = getMiles(myLocation.distanceTo(locationC));

        chatrooms = new ArrayList<Chatroom>();
        chatrooms.add(studentUnion);
        chatrooms.add(library);
        chatrooms.add(starbucks);

        Collections.sort(chatrooms, new Comparator<Chatroom>() {
            public int compare(Chatroom x, Chatroom y) {
                if (x.distanceTo < y.distanceTo) return -1;
                if (x.distanceTo > y.distanceTo) return 1;
                return 0;
            }
        });

        chatroomStrings = new ArrayList<String>();

        for(int i = 0; i < chatrooms.size(); i++) {
            chatroomStrings.add(chatrooms.get(i).name);
        }

        myAdapter = new MyClassAdapter(ChannelActivity.this, R.layout.list_item, chatrooms);
        channelList.setAdapter(myAdapter);

        /*
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, chatroomStrings);
        channelList.setAdapter(arrayAdapter);
        */



        channelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.channel = chatroomStrings.get(i).toString();

                Intent intent = new Intent(ChannelActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public double getMiles(float meters)
    {
        return meters*0.000621371192;
    }

    // Adds the same menu as in chat room but doesn't include number of subscribers.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_modified, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        MainActivity main = new MainActivity();

        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.action_sign_out:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
