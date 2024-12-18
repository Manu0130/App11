package lk.manujaya.app11;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(Intent.ACTION_SENDTO);
//                i.setType("text/plain");
//                i.setData(Uri.parse("smsto:071xxxxxx"));
//                i.putExtra("sms_body", "Hello");
//                startActivity(i);
//                Log.i("App11", "Testinggggg");

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(
                        "071xxxxxxx",
                        null,
                        "Hellow",
                        null,
                        null
                );
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("App11", "Requesting Permission");

                ArrayList<String> permissionList = new ArrayList<>();

                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    Log.i("App11", "Write External Permission Granted");
                }else{
                    permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }

                if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                    Log.i("App11", "Send SMS Permission Granted");
                }else{
                    permissionList.add(Manifest.permission.SEND_SMS);
                }

                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    Log.i("App11", "Camera Permission Granted");
                }else{
                    permissionList.add(Manifest.permission.CAMERA);
                }

                String permissionArray[] = permissionList.toArray(new String[permissionList.size()]);
                requestPermissions(permissionArray,100);

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==100){
            for (int i = 0; i < permissions.length; i++){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.i("App11", permissions[i] + "PERMISSION GRANTED");
                }else{
                    Log.i("App11", permissions[i] + "PERMISSION DENIED");
                }
            }
        }

    }

    }