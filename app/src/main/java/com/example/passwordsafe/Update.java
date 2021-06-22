package com.example.passwordsafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.Vector;

public class Update extends AppCompatActivity {
    Button save, clear, show;
    EditText editText;
    DbOperations dbOperations;
    String title;
    Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbOperations = new DbOperations(Update.this);
        save = findViewById(R.id.update_save);
        clear = findViewById(R.id.update_clear);
        show = findViewById(R.id.update_show);
        editText = findViewById(R.id.update_pwd);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editText.getText().clear();
                Toast.makeText(Update.this, "Enter password again", Toast.LENGTH_SHORT).show();
                show.setText("Select and Update Password");
                flag = false;
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == true) {
                    if (editText.getText().toString().trim().length() == 0) {
                        showerror("Password cannot be empty");
                    } else {
                        Toast.makeText(Update.this, "Password Updated", Toast.LENGTH_SHORT).show();
                        String password = editText.getText().toString().trim();
                        dbOperations.updatePassword(password, title);
                        finish();
                    }
                } else
                    showerror("Please Select a Record");
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Update.this, show);
                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());

                {
                    Vector vector = dbOperations.getDescription();
                    if (vector.size() == 0)
                        showerror("No Records Found.");
                    else {
                        String desc_array[] = new String[vector.size()];
                        vector.copyInto(desc_array);
                        for (int i = 0; i < desc_array.length; i++) {
                            popupMenu.getMenu().add(0, i, 0, desc_array[i]);
                        }
                    }
                }
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(Update.this, "You Selected " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        show.setText(item.getTitle());
                        title = item.getTitle().toString().trim();
                        flag = true;
                        return true;

                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void showerror(String s) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Update.this);
        builder.setTitle("Alert");
        builder.setIcon(R.drawable.ic_error);
        builder.setMessage(s);
        builder.setCancelable(true);
        builder.show();
    }
}

