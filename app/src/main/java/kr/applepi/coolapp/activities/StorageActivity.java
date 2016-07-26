package kr.applepi.coolapp.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import kr.applepi.coolapp.R;

public class StorageActivity extends AppCompatActivity {


    private VideoView videoView;

    private Button btnPlay, btnSignOut;

    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        auth = FirebaseAuth.getInstance();

        btnPlay = (Button) findViewById(R.id.btn_play);
        btnSignOut = (Button) findViewById(R.id.btn_sign_out);

        videoView = (VideoView) findViewById(R.id.video_view);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://coolapp-b2bfd.appspot.com");

        StorageReference overwatch = storageRef.child("overwatch.mp4");
        overwatch.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                videoView.setVideoURI(uri);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(videoView.isPlaying()) {
                    videoView.pause();
                } else {
                    videoView.start();
                }

            }
        });


        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(auth.getCurrentUser() != null) {
                    auth.signOut();
                    Toast.makeText(StorageActivity.this, "Sign Out", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });


    }
}
