package com.example.passwordsafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {
    Button clear, save;
    EditText desc, user, pass;
    DbOperations dbOperations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dbOperations = new DbOperations(Add.this);
        clear = findViewById(R.id.add_clear);
        save = findViewById(R.id.add_save);
        desc = findViewById(R.id.add_desc);
        user = findViewById(R.id.add_user);
        pass = findViewById(R.id.add_pwd);

        //clear button click event
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desc.setText("");
                user.setText("");
                pass.setText("");
                Toast.makeText(Add.this, "All Fields Cleared", Toast.LENGTH_SHORT).show();
            }
        });

        //save button click event
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d, u, p;
                d = desc.getText().toString().trim();
                u = user.getText().toString().trim();
                p = pass.getText().toString().trim();
                if ((d.length() == 0) && (u.length() == 0) && (p.length() == 0))
                    showalert("Fields cannot be empty!");
                else {
                    dbOperations.insertRecord(d,u,p);
                    Toast.makeText(Add.this, "Password Saved!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void showalert(String s) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Add.this);
        builder.setTitle("Alert");
        builder.setIcon(R.drawable.ic_error);
        builder.setMessage(s);
        builder.setCancelable(true);
        builder.show();
    }
}
