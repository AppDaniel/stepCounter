package com.example.macbookair.stepcounter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Created by macbookair on 2018-03-08.
 */

public class Profile extends AppCompatActivity {

    ChangeActivities changeActivities;
    TextView mostSteps, nameText, goalText;
    DatabaseHandler sqlFinder;
    ImageView imageView;
//    final static int MY_REQUEST_CODE = 200;
//    PermissonHandler permissionHandler;
//    private int GALLERY = 1, CAMERA = 2;;
//    private static final int PROFILE_PAGE_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        sqlFinder = new DatabaseHandler(this);
        sqlFinder.insert();

//        permissionHandler = new PermissonHandler();
//        permissionHandler.checkPermissions(this, MY_REQUEST_CODE);

        imageView = findViewById(R.id.imageView);
        Drawable myDraw = getResources().getDrawable(R.drawable.steps);
        imageView.setImageDrawable(myDraw);

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPictureDialog();
//
//
//            }
//        });

        String mosteSteps = sqlFinder.sortMosteSteps();
        mostSteps = findViewById(R.id.mostStepstextView);
        mostSteps.setText(mosteSteps);

        String name = sqlFinder.findName();
        nameText = findViewById(R.id.nameTextViewP);
        nameText.setText(name);

        String goal = sqlFinder.getGoal();
        goalText = findViewById(R.id.goalTextViewP);
        goalText.setText(goal);







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.historik) {

            changeActivities.StartNewActivity(Profile.this, HistoryList.class);

        }
        if (item.getItemId() == R.id.weather) {

            changeActivities.StartNewActivity(Profile.this, MainActivity.class);

        }
        if (item.getItemId() == R.id.action_settings) {

            changeActivities.StartNewActivity(Profile.this, UserSettings.class);

        }

        return super.onOptionsItemSelected(item);
    }

//    private void showPictureDialog(){
//        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
//        pictureDialog.setTitle("Select Action");
//        String[] pictureDialogItems = {
//                "Select photo from gallery",
//                "Capture photo from camera" };
//        pictureDialog.setItems(pictureDialogItems,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which) {
//                            case 0:
//                                choosePhotoFromGallary();
//                                break;
//                            case 1:
//                                takePhotoFromCamera();
//                                break;
//                        }
//                    }
//                });
//        pictureDialog.show();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_REQUEST_CODE: {
//                boolean readAccepted = false;
//                boolean writeAccepted = false;
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                    readAccepted = true;
//                if (grantResults[1] == PackageManager.PERMISSION_GRANTED)
//                    writeAccepted = true;
//                if (readAccepted && writeAccepted)
//                    Toast.makeText(this, "Permisson Okay", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(this, "Permisson Not okay", Toast.LENGTH_SHORT).show();
//
//
//            }
//
//        }
//
//
//
//    }
//
//    public void choosePhotoFromGallary() {
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//        startActivityForResult(galleryIntent, GALLERY);
//    }
//
//    private void takePhotoFromCamera() {
//        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, CAMERA);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == GALLERY) {
//            if (data != null) {
//                Uri contentURI = data.getData();
//                try {
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//
//                    Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
//                    imageView.setImageBitmap(bitmap);
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        } else if (requestCode == CAMERA) {
//            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//            imageView.setImageBitmap(thumbnail);
//
//            Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
//        }
//
//
//    }
}
