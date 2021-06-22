package com.example.passwordsafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class ViewPass extends AppCompatActivity {

    Button ok, clear, show;
    TextView desc, user, pass;
    int length;
    DbOperations dbOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        dbOperations = new DbOperations(ViewPass.this);
        ok = findViewById(R.id.view_ok);
        clear = findViewById(R.id.view_clear);
        show = findViewById(R.id.view_show);
        desc = findViewById(R.id.view_desc);
        user = findViewById(R.id.view_user);
        pass = findViewById(R.id.view_pwd);

        clear.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                desc.setText("Description : ");
                user.setText("Username/Email : ");
                pass.setText("Password : ");
                Toast.makeText(ViewPass.this, "All Fields Cleared!", Toast.LENGTH_SHORT).show();
                show.setText("View Password");
            }
        });

        ok.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Toast.makeText(ViewPass.this, "Close", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        show.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(final android.view.View v) {
                PopupMenu popupMenu = new PopupMenu(ViewPass.this, show);
                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());

                {

                    Vector vector = dbOperations.getDescription();
                    String string[] = new String[vector.size()];
                    vector.copyInto(string);
                    length = vector.size();
                    if (length == 0)
                        showMsg("No Record Found");
                    else {
                        for (int i = 0; i < length; i++)
                            popupMenu.getMenu().add(0, i, 0, string[i]);
                    }

                }

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(ViewPass.this, "You Selected " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        show.setText(item.getTitle());

                        Vector vector = dbOperations.recordAt(item.getTitle().toString().trim());

                        desc.setText("Description : " + vector.elementAt(0).toString());
                        user.setText("Username/Email : " + vector.elementAt(1).toString());
                        pass.setText("Password : " + vector.elementAt(2).toString());
                        return true;

                    }
                });
                popupMenu.show();
            }

        });
    }

    public void showMsg(String message) {
        final androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(ViewPass.this);
        builder.setTitle("Alert");
        builder.setIcon(R.drawable.ic_error);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
