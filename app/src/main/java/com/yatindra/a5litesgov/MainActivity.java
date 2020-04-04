package com.yatindra.a5litesgov;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Button button;
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
//        GetAllItemsAsyncTask getAllItemsAsyncTask = new GetAllItemsAsyncTask();
//        try{
////            Document i = getAllItemsAsyncTask.execute("d4:63:c6:77:92:52").get();
//            Document document = new Document();
//            document.put("MACid", "d4:63:c6:77:92:52");
//            document.put("status", "negative");
//            document.put("phone", "12345678");
//            Document ping = new Document(), allPings = new Document();
//            ping.put("counter", 1);
//            ping.put("timestamp", "04-04-2020-20:05");
//            allPings.put("newmac", ping);
//            document.put("Pings", all);
////            System.out.println("Size: " + i.size());
//            Integer temp = new PutDataAsyncTask().execute(document).get();
//            System.out.println("O/P: " + temp);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }



}
