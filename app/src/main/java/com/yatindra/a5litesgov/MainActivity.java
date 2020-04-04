package com.yatindra.a5litesgov;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.DynamoDBEntry;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.DynamoDBList;
import com.amazonaws.regions.Region;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.yatindra.a5litesgov.model.PingNode;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    Button button;
//    private SharedPreferences prefs;
    private String PHONE_NO = "Phone";
    private String MAC = "Mac_add";
    static final String LOG_CHECK_KEY = "logged";
    static final String CREDENTIALS = "Credentials";
    //Changes:
//    LinearLayout outputLayout;
    //    Button bt1,bt2;
    Button mButton;
    Boolean flag;
    LinearLayout ll, outerll;
    private final String COGNITO_POOL_ID =  "ap-south-1:402f5cc9-0567-4261-bc92-768d44d79b08";
    private final Region COGNITO_REGION =  Region.getRegion("ap-south-1");
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = findViewById(R.id.phonelist);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("CLICKED");
                Log.d("button","Clicked");
                GetAllItemsAsyncTask getAllItemsAsyncTask = new GetAllItemsAsyncTask();
                try{
                    Document i = getAllItemsAsyncTask.execute("a4:45:23:51:i6").get();

//                    System.out.println("O/P::" + i.toString());
                    Log.d("button",i.toString());
                    i = (Document)i.get("Pings");
                    List<PingNode> pingNodes = new ArrayList<>();//List<>();
                    for(Map.Entry<String, DynamoDBEntry> set : i.entrySet()) {
                        Log.d("button", "KEY : " + set.getKey() + "\nValue : " + set.getValue());
                        String phone = new String();
                        GetAllItemsAsyncTask getAllItemsAsyncTask1 = new GetAllItemsAsyncTask();
                        try{
                            Document j = getAllItemsAsyncTask1.execute(set.getKey()).get();
                            if(!j.get("phn_no").asString().equals(null)) {
                                phone = j.get("phn_no").asString();
                            }
                        }catch(Exception e) {

                        }
                        pingNodes.add(new PingNode(set.getKey(), (int)Integer.parseInt(set.getValue().asDocument().get("no_pings").asInt().toString()), set.getValue().asDocument().get("timestamp").asString(), phone));
                    }
                    Log.d("button","DONE!" + pingNodes.get(0).getNo_pings());
                    for(int ij=0;ij<pingNodes.size();ij++) {
                        CardView card = (CardView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.phonecard, null);
                        TextView mac = card.findViewById(R.id.mac_text) , pings = card.findViewById(R.id.ping_text) , timestamp = card.findViewById(R.id.timestamp), phone = card.findViewById(R.id.phone_text);
                        mac.setText(pingNodes.get(ij).getMacid());
                        pings.setText(Integer.toString(pingNodes.get(ij).getNo_pings()));
                        timestamp.setText(pingNodes.get(ij).getTimestamp());
                        phone.setText(pingNodes.get(ij).getPhn_no());
                        ll.addView(card);
                    }
//                    Log.d("button",entry.toString());
////                    entry.asDynamoDBList();
//                        entry = i.get("Pings");
//                    Log.d("button",entry.asDynamoDBList().toString());
//                    if (entry instanceof DynamoDBEntry) {
//                        DynamoDBList list = entry.asDynamoDBList();
//                        Log.d("button","InIF");
//                        for (Iterator I = list.iterator(); I.hasNext(); ) {
//                            DynamoDBEntry listEntry = (DynamoDBEntry)I.next();
//                            Log.d("button", I + listEntry.toString() );
//                        }
//                    }
//                    i = (Document)i.get("Pings");
//                    Log.d("button",i.toString());

//            Document document = new Document();
//            document.put("MACid", "d4:63:c6:77:92:52");
//            document.put("status", "negative");
//            document.put("phone", "12345678");
//            Document ping = new Document(), allPings = new Document();
//            ping.put("counter", 1);
//            ping.put("timestamp", "04-04-2020-20:05");
//            allPings.put("newmac", ping);
//            document.put("Pings", allPings);
//            System.out.println("Size: " + i.size());
//            Integer temp = new PutDataAsyncTask().execute(document).get();
//            System.out.println("O/P: " + temp);
                }catch (Exception e){
                    e.printStackTrace();
                }

                // Do something in response to button click
            }
        });
        //Changes:

//        getDatabase();
    }
    private class GetAllItemsAsyncTask extends AsyncTask<String, Void, com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document> {
        @Override
        protected com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document doInBackground(String... params) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(MainActivity.this);
            return databaseAccess.getItem(params[0]);
        }

        @Override
        protected void onPostExecute(com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document documents) {
        }

    }

    private class PutDataAsyncTask extends AsyncTask<Document, Void, Integer> {
        @Override
        protected Integer doInBackground(Document... params) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(MainActivity.this);
            databaseAccess.putItem(params[0]);
            System.out.println("In background!!!");
            return 1;
        }

        @Override
        protected void onPostExecute(Integer integer) {
        }

    }


    private void getDatabase(){
    }

//    public void startBackground(View view){
//        SharedPreferences.Editor edit = pref.edit();
//        edit.commit();
//        BluetoothIntentService.setEditor(edit);
//        BluetoothIntentService.startDiscovery(getBaseContext());
//        Toast.makeText(this, "Background process started!", Toast.LENGTH_SHORT).show();
//    }
//
//    public void stopBackground(View view){
//        Toast.makeText(this, "Background process stopped!", Toast.LENGTH_SHORT).show();
//        bt1.setVisibility(View.GONE);
//        bt2.setVisibility(View.GONE);
//        int totalDevices = pref.getInt("totalDevices", 0);
//        if(totalDevices == 0){
//            System.out.println("Total devices are 0!! :(");
//        }
//        for(int i=0;i<totalDevices;i++){
//            View newView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_card, null);
//            TextView name = newView.findViewById(R.id.deviceName);
//            TextView add = newView.findViewById(R.id.devicceAddress);
//            name.setText(pref.getString(("name"+i), "name"));
//            add.setText(pref.getString(("address"+i), "address"));
//            outputLayout.addView(newView);
//        }
//        outputLayout.setVisibility(View.VISIBLE);
//        BluetoothIntentService.stopDiscovery(getBaseContext());
//
//    }


}
