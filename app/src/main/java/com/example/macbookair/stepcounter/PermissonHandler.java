//package com.example.macbookair.stepcounter;
//
//import android.Manifest;
//import android.annotation.TargetApi;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.pm.PackageManager;
//import android.os.Build;
//import android.support.v4.app.ActivityCompat;
//import android.util.Log;
//
///**
// * Created by macbookair on 2018-03-08.
// */
//
//public class PermissonHandler {
//
//    void checkPermissions(final Profile activity, final int requestCode) {
//
//
//        String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA,};
//        if (!alreadyGranted(activity, PERMISSIONS))
//
//        {
//
//            //om användaren klickar nej gång på gång kan det tyda på att den ej förstår varför det
//            //ska användas-->förklaringsdialog-->ska bara visas om användaren har sagt nej
//
//            boolean userHadDeniedCamera = ActivityCompat.shouldShowRequestPermissionRationale(activity,
//                    Manifest.permission.CAMERA);
//
//            boolean userHadDeniedReadStorage = ActivityCompat.shouldShowRequestPermissionRationale(activity,
//                    Manifest.permission.READ_EXTERNAL_STORAGE);
//            if (userHadDeniedReadStorage || userHadDeniedCamera) {
//
////          specialfall om användaren sagt nej
//
////                Informations Dialog, varför appen behöver premisson
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//                builder.setMessage("Permission to access the microphone is required for this app " +
//                        "to visualize music.");
//                builder.setTitle("Permission required");
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //när användaren har fått info, så får den möjlighet att godkänna igen
//                        makeRequest(activity,requestCode);
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//
//            } else {
//
//                makeRequest(activity,requestCode);
//            }
//
//        }
//        else {
//
//            Log.i("Permission Ok", "Japp");
//        }
//
//    }
//
//    //    Target Api(23) är bara för android version 6 och över. Koden kommer inte att köras i Android 5 eller 4.
//
//    @TargetApi(23)
//    private void makeRequest(Activity activity, int requestCode) {
//        //ändra dessa för andra permissions i Övningsuppgifter!
//        String[] perms = {"android.permission.READ_EXTERNAL_STORAGE","android.permission.CAMERA"};
//        activity.requestPermissions(perms, requestCode);
//        //anropet leder till att onRequestPermissionsResult anropas
//
//    }
//
//    boolean alreadyGranted(Activity context, String... permissions) {
//        boolean status = true;
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
//
//            for (String permission : permissions) {
//                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
//                    status = false;
//                }
//            }
//        }
//        return status;
//    }
//
//
//}
