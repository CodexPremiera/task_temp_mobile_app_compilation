package task.mobile_app_portfolio.app07_menu_items;

import static android.graphics.Color.argb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

import task.mobile_app_portfolio.R;

public class MenuActivity extends AppCompatActivity {

    Button btnChanger;
    int sizeIndex = 0, iconIndex = 0, clicks = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        btnChanger = findViewById(R.id.btnTransformingButton);
        btnChanger.setBackgroundColor(Color.argb(255, 66, 245, 167));

        // Was bored, this function is purely for making the button unclickable more interesting
        btnChanger.setOnClickListener(v -> {
                    clicks++;
                    Toast.makeText(this, clicks + " clicks made!", Toast.LENGTH_SHORT).show();
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_modal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mItemChange) Toast.makeText(this, "Change item clicked", Toast.LENGTH_SHORT).show();
        else if(item.getItemId() == R.id.mItemChangeSize) {
            Toast.makeText(this, "Change Size item clicked", Toast.LENGTH_SHORT).show();
            int[] sizeArray = {900, 950, 1000};
            sizeIndex++;
            sizeIndex = (sizeIndex > 2) ? 0 : sizeIndex;
            btnChanger.setLayoutParams(new LinearLayout.LayoutParams(sizeArray[sizeIndex], sizeArray[sizeIndex]));
        }
        else if(item.getItemId() == R.id.mItemChangeColor) {
            btnChanger.setBackgroundColor(Color.argb(255, new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
        }
        else if(item.getItemId() == R.id.mItemChangeIcon) {
            Toast.makeText(this, "Change Icon item clicked", Toast.LENGTH_SHORT).show();
            Drawable[] iconArray = {
                    getResources().getDrawable(R.drawable.ig_icon_home),
                    getResources().getDrawable(R.drawable.ig_icon_heart),
                    getResources().getDrawable(R.drawable.ig_icon_messenger)};
            iconIndex = (iconIndex > 2) ? 0 : iconIndex;
            Drawable icon;
            icon = resizeDrawable(iconArray[iconIndex], 300, 300);
            btnChanger.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
            btnChanger.setPadding(310, 0, 0, 0);
            iconIndex++;
        }
        else if(item.getItemId() == R.id.mItemChangeVisibility) {
            Toast.makeText(this, "Change Visibility item clicked", Toast.LENGTH_SHORT).show();
            int visible;
            if(btnChanger.getVisibility() == Button.VISIBLE) visible = Button.INVISIBLE;
            else visible = Button.VISIBLE;
            btnChanger.setVisibility(visible);
        }
        else if(item.getItemId() == R.id.mItemChangeButtonState) {
            Toast.makeText(this, "Change Button State item clicked", Toast.LENGTH_SHORT).show();
            btnChanger.setEnabled(!btnChanger.isEnabled());
        }
        else if (item.getItemId() == R.id.mItemReset) {
            Toast.makeText(this, "Reset item clicked", Toast.LENGTH_SHORT).show();
            btnChanger.setLayoutParams(new LinearLayout.LayoutParams(900, 900));
            btnChanger.setBackgroundColor(Color.argb(255, 66, 245, 167));
            btnChanger.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            btnChanger.setEnabled(true);
        }
        else if (item.getItemId() == R.id.mItemExit) finish();
        return true;
    }

    private Drawable resizeDrawable(Drawable drawable, int width, int height) {
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        return new BitmapDrawable(getResources(), resizedBitmap);
    }
}