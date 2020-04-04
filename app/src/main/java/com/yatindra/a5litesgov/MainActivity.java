package com.yatindra.a5litesgov;

import androidx.appcompat.app.AppCompatActivity;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.DynamoDBEntry;
import com.amazonaws.regions.Region;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

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
    private final String COGNITO_POOL_ID =  "ap-south-1:402f5cc9-0567-4261-bc92-768d44d79b08";
    private final Region COGNITO_REGION =  Region.getRegion("ap-south-1");
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Do something in response to button click
            }
        });
        //Changes:

//        getDatabase();
        GetAllItemsAsyncTask getAllItemsAsyncTask = new GetAllItemsAsyncTask();
        try{
            Document i = getAllItemsAsyncTask.execute("d4:63:c6:77:92:52").get();
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
