package com.sark.android;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileSave extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_save);

        String filename = "myfile1";
        String fileContents = "Helloworld!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());

            Toast.makeText(FileSave.this, "File created", Toast.LENGTH_LONG).show();

            try{

               FileInputStream fis=FileSave.this.openFileInput(filename);
                InputStreamReader isr=new InputStreamReader(fis);
                BufferedReader bufferedReader=new BufferedReader(isr);
                StringBuilder sb=new StringBuilder();
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    Toast.makeText(FileSave.this, ""+line, Toast.LENGTH_LONG).show();

                }


//                while((line=bufferedReader.readLine())!=null)
//                {
//                    sb.append(line);
//                }



            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(FileSave.this, "Couldn't create file!", Toast.LENGTH_LONG).show();

            }
        }

        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(FileSave.this, "Couldn't create file!", Toast.LENGTH_LONG).show();

        }


    }
}
