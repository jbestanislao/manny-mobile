package manny.com.androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText custoNo = (EditText)findViewById(R.id.custNo);
        final EditText data = (EditText)findViewById(R.id.data);

        Button saveBtn = (Button)findViewById(R.id.saveBtn);
        Button getBtn = (Button)findViewById(R.id.getBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You've clicked me save your data!", Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this,
                                    "The data you entered are: " + custoNo.getText().toString() + ", " + data.getText().toString(),
                                    Toast.LENGTH_LONG).show();

                SaveTask saveTask = new SaveTask();
                saveTask.execute(custoNo.getText().toString(), data.getText().toString());
            }
        });

        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You've clicked me to get the data!", Toast.LENGTH_LONG).show();
                GetTask getTask = new GetTask();
                getTask.execute(custoNo.getText().toString());
                String output = null;
                try {
                    output = getTask.get();
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }
                Toast.makeText(MainActivity.this,
                        "The data retrieved were: " + output,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
