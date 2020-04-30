package com.project.placementcell;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.project.placementcell.helpers.MyHelper;

import java.util.List;

public class BarcodeActivity extends BaseActivity {
	private ImageView mImageView;
	private TextView mTextView,Mail;
	String pass,mail;
 	Button login;
 	FirebaseAuth auth;
    BottomNavigationView bottomNavigationView;
	DatabaseReference reference;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device);
		bottomNavigationView=findViewById(R.id.bottom);
		bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				switch (item.getItemId()) {
					case R.id.action_gallery:
						checkStoragePermission(RC_STORAGE_PERMS1);
						break;
					case R.id.action_camera:
						checkStoragePermission(RC_STORAGE_PERMS2);
						break;
				}return true;
			}
		});
		mImageView = findViewById(R.id.image_view);
		mTextView = findViewById(R.id.text_view);
		Mail=findViewById(R.id.userEmail);
		login=findViewById(R.id.login);
		auth = FirebaseAuth.getInstance();
		login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pass = mTextView.getText().toString().trim();
				mail = Mail.getText().toString().trim();

				auth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							Toast.makeText(BarcodeActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
							startActivity(new Intent(BarcodeActivity.this, MainActivity.class));
						} else {
							Toast.makeText(BarcodeActivity.this, "Login failed", Toast.LENGTH_LONG).show();

						}
					}
				});
			}
		});

	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Bitmap bitmap;
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
				case RC_STORAGE_PERMS1:
				case RC_STORAGE_PERMS2:
					checkStoragePermission(requestCode);
					break;
				case RC_SELECT_PICTURE:
					Uri dataUri = data.getData();
					String path = MyHelper.getPath(this, dataUri);
					if (path == null) {
						bitmap = MyHelper.resizeImage(imageFile, this, dataUri, mImageView);
					} else {
						bitmap = MyHelper.resizeImage(imageFile, path, mImageView);
					}
					if (bitmap != null) {
						mTextView.setText(null);
						mImageView.setImageBitmap(bitmap);
						barcodeDetector(bitmap);
					}
					break;
				case RC_TAKE_PICTURE:
					bitmap = MyHelper.resizeImage(imageFile, imageFile.getPath(), mImageView);
					if (bitmap != null) {
						mTextView.setText(null);
						mImageView.setImageBitmap(bitmap);
						barcodeDetector(bitmap);
					}
					break;
			}
		}
	}

	private void barcodeDetector(Bitmap bitmap) {
		FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
		/*
		FirebaseVisionBarcodeDetectorOptions options = new FirebaseVisionBarcodeDetectorOptions.Builder()
				.setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE, FirebaseVisionBarcode.FORMAT_AZTEC)
				.build();
		*/
		FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance().getVisionBarcodeDetector();
		detector.detectInImage(image).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
			@Override
			public void onSuccess(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {
				mTextView.setText(getInfoFromBarcode(firebaseVisionBarcodes));
			}
		}).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure(@NonNull Exception e) {
				mTextView.setText(R.string.error_detect);
			}
		});
	}

	private String getInfoFromBarcode(List<FirebaseVisionBarcode> barcodes) {
		StringBuilder result = new StringBuilder();
		for (FirebaseVisionBarcode barcode : barcodes) {
			//int valueType = barcode.getValueType();
			result.append(barcode.getRawValue() + "\n");

			/*
			int valueType = barcode.getValueType();
			switch (valueType) {
				case FirebaseVisionBarcode.TYPE_WIFI:
					String ssid = barcode.getWifi().getSsid();
					String password = barcode.getWifi().getPassword();
					int type = barcode.getWifi().getEncryptionType();
					break;
				case FirebaseVisionBarcode.TYPE_URL:
					String title = barcode.getUrl().getTitle();
					String url = barcode.getUrl().getUrl();
					break;
			}
			*/
		}
		if ("".equals(result.toString())) {
			return getString(R.string.error_detect);
		} else {
			return result.toString();
		}
	}
}
