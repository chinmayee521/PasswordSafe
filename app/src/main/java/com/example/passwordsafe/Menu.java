package com.example.passwordsafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    Button btnAdd, btnUpdate, btnView, btnDelete,btnViewAll,btnClose;
    AlertDialog.Builder builder, builder1, builder2;
    private AnimationDrawable animationDrawable;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnViewAll = findViewById(R.id.btnViewAll);
        btnClose = findViewById(R.id.btnClose);

        //gradient animation code
        // init constraintLayout
        linearLayout = (LinearLayout) findViewById(R.id.menulinlay);
        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(5000);
        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2000);





        //start new activity when btnAdd button is clicked
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Add.class));
            }
        });

        //start new activity when btnView button is clicked
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this,ViewPass.class));
            }
        });

        //start new activity when btnUpdate button is clicked
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Update.class));
            }
        });

        //start new activity when btnDelete button is clicked
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Delete.class));
            }
        });

        //start new activity when btnViewAll button is clicked
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, ViewAll.class));
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu.this,"Thank you for using PasswordSafe!",Toast.LENGTH_SHORT).show();
                Menu.this.finish();
                System.exit(0);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);

        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){

            case R.id.aboutapp:
                builder = new AlertDialog.Builder(this);
                builder.setMessage("Offline password manager app to securely store passwords. " +
                        "\nEasy to use and flexible as update, delete, view options available! " +
                        "\n\nNever forget a password anymore 😄 ") .setTitle("About App")
                        .setCancelable(false)
                        .setPositiveButton("Okay", null);
                AlertDialog alert = builder.create();
                alert.show();
                return true;

            case R.id.developer:
                builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("Developed by 💻 \nChinmayee Taralkar " +
                        "\n\nGithub 👇 \nhttps://github.com/chinmayee521") .setTitle("About Developer")
                        .setCancelable(false)
                        .setPositiveButton("Okay", null);
                AlertDialog alert1 = builder1.create();
                alert1.show();
                return true;

            case R.id.exittt:
                builder2=new AlertDialog.Builder(this);
                builder2.setMessage("Do you want to close PasswordSafe ?").setTitle("PasswordSafe")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Menu.this.finish();
                                System.exit(0);
                                Toast.makeText(getApplicationContext(),"Thank you for using PasswordSafe!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"Continue using PasswordSafe",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alert2 = builder2.create();
                alert2.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
    }
}
