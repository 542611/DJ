package com.example.code1.View.acticvity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.code1.R;
import com.example.code1.View.acticvity.qrcode.CaptureActivity;
import com.umeng.socialize.utils.Log;

public class QRcodeActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity====";
    private final int REQUEST_CODE = 0;
    private TextView result_tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erweima_layout);

        initPermission();

        ImageView scan = findViewById(R.id.scan);
        result_tv = findViewById(R.id.result_text);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击开始扫描

                Intent intent = new Intent(QRcodeActivity.this, CaptureActivity.class);

                startActivityForResult(intent,REQUEST_CODE);

            }
        });

    }

    private void initPermission() {
        if (ContextCompat.checkSelfPermission(QRcodeActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(QRcodeActivity.this, new String[]
                    {Manifest.permission.CAMERA}, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "接收到数据-----: ");
        Bundle extras = data.getBundleExtra("data");
        String result = extras.getString("result");
        result_tv.setText(result+"----------");

    }
}
