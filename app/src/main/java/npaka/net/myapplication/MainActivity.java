package npaka.net.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static int REQUEST_CODE_CHOOSER = 101;

    public static final List<String> types = Collections
            .unmodifiableList(new LinkedList<String>() {
                {
                    add("image/jpeg");
                    add("image/jpg");
                    add("image/png");
                }
            });

    private ImageView selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!OpenCVLoader.initDebug()){
            Log.i("OpenCV", "Failed");
        }else{
            Log.i("OpenCV", "successfully built !");
            TextView textView =(TextView)findViewById(R.id.text1432);
            textView.setText("ok");
        }

            Toast.makeText(this, "^^", Toast.LENGTH_LONG).show();

        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
        Mat src_img = new Mat();
                Utils.bitmapToMat(src, src_img);

        if(src_img.empty()) Toast.makeText(this, "^^;", Toast.LENGTH_LONG).show();
        int rowStart = 100;
        int rowEnd =  200;
        int colStart = 100;
        int colEnd = 200;
        Mat sub = new Mat();
        src_img.submat(rowStart , rowEnd , colStart , colEnd ).copyTo(sub);
        Bitmap bmp1 = Bitmap.createBitmap(sub.width(), sub.height(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(sub, bmp1);
        ImageView imgv = (ImageView)findViewById(R.id.imageView1);
        imgv.setImageBitmap(bmp1);

        

    }
}
