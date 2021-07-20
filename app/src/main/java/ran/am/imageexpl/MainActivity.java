package ran.am.imageexpl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    FileOutputStream outStream;
    ImageView imageView;
    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
    }

    public void saExt(View view) throws IOException {
        Bitmap bm = BitmapFactory.decodeResource( getResources(), R.drawable.i);
        File file = new File(extStorageDirectory, "newironExt.PNG");
        try {
            outStream = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void saint(View view) {
        Drawable drawable = getDrawable(R.drawable.i);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
        File file = wrapper.getDir("Images",MODE_PRIVATE);
        file = new File(file, "newironInt"+".jpg");
        try{
            OutputStream stream = null;
            stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();
        }catch (IOException e) // Catch the exception
        {
            e.printStackTrace();
        }
        Uri savedImageURI = Uri.parse(file.getAbsolutePath());
        imageView.setImageURI(savedImageURI);
        Toast.makeText(MainActivity.this, "Image saved in internal storage.\n"+savedImageURI, Toast.LENGTH_SHORT).show();
    }

    public void lodInt(View view) {
        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
        File file = wrapper.getDir("Images",MODE_PRIVATE);
        file = new File(file, "newironInt"+".jpg");
        Uri savedImageURI = Uri.parse(file.getAbsolutePath());
        imageView.setImageURI(savedImageURI);
    }

    public void lodExt(View view) {
        ///storage/emulated/0/ironExt.PNG
        File f = new File("storage/emulated/0/newironExt.PNG");
        ImageView mImgView1 = (ImageView)findViewById(R.id.imageView);
        Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());
        mImgView1.setImageBitmap(bmp);

    }
}