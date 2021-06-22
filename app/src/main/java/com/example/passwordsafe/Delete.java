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

public class Delete extends AppCompatActivity {

    String dbpass, edittextpass, title;
    Button del, clear, show;
    EditText editText;
    DbOperations dbOperations;
    Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        dbOperations = new DbOperations(Delete.this);

        del = findViewById(R.id.delete_del);
        clear = findViewById(R.id.delete_clear);
        show = findViewById(R.id.delete_show);
        editText = findViewById(R.id.delete_pwd);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                Toast.makeText(Delete.this, "Enter password again", Toast.LENGTH_SHORT).show();
                show.setText("Select and Delete Password");
                flag = false;
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == true) {
                    if (editText.getText().toString().trim().length() == 0) {
                        showerror("Please enter password.");
                    } else {
                        edittextpass = editText.getText().toString().trim();
                        if (dbpass.equals(edittextpass)) {
                            Toast.makeText(Delete.this, "Password Deleted Successfully", Toast.LENGTH_SHORT).show();
                            dbOperations.deleteRecord(title);
                            finish();
                        } else {
                            showerror("Incorrect Password");
                        }
                    }

                } else {
                    showerror("Please Select a Record.");
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Delete.this, show);
                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());

                {
                    Vector vector = dbOperations.getDescription();
                    if (vector.size() == 0) {
                        showerror("No Records Found");
                    } else {
                        String string[] = new String[vector.size()];
                        vector.copyInto(string);
                        for (int i = 0; i < vector.size(); i++)
                            popupMenu.getMenu().add(0, i, 0, string[i]);
                    }
                }

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(Delete.this, item.getTitle() + " is selected.", Toast.LENGTH_SHORT).show();
                        show.setText(item.getTitle());
                        flag = true;
                        title = item.getTitle().toString().trim();
                        Vector vector = dbOperations.recordAt(item.getTitle());
                        dbpass = vector.elementAt(2).toString().trim();
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(Delete.this);
        builder.setTitle("Alert");
        builder.setIcon(R.drawable.ic_error);
        builder.setMessage(s);
        builder.setCancelable(true);
        builder.show();
    }

}

