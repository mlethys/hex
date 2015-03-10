package pl.mlethys.poc.hexmap;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    public static final int DEFAULT_RADIUS = 10;

    private HexCanvas hexCanvas;
    private SwingOMeterCanvas swingOMeterCanvas;

    private Button quickViewButton;
    private Button yesNoButton;
    private Button mapButton;
    private Button newsButton;

    private Drawable quickViewRaw;
    private Drawable yesNoRaw;
    private Drawable mapRaw;
    private Drawable newsRaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hexCanvas = (HexCanvas) this.findViewById(R.id.HexCanvas);
        hexCanvas.setRadius(DEFAULT_RADIUS );

        swingOMeterCanvas = (SwingOMeterCanvas) this.findViewById(R.id.SwingOMeterCanvas);
        swingOMeterCanvas.setHexCanvas(hexCanvas);

        quickViewRaw = getResources().getDrawable(R.drawable.quickview);
        yesNoRaw = getResources().getDrawable(R.drawable.yesno);
        mapRaw = getResources().getDrawable(R.drawable.map);
        newsRaw = getResources().getDrawable(R.drawable.newsfeed);

        quickViewButton = (Button) findViewById(R.id.quickViewButton);
        yesNoButton = (Button) findViewById(R.id.yesNoButton);
        mapButton = (Button) findViewById(R.id.electionMapButton);
        newsButton = (Button) findViewById(R.id.newsButton);

        setOnClickListenerForButtons();
    }


    private void setOnClickListenerForButtons() {
        quickViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable top = getResources().getDrawable(R.drawable.quickview_on);
                quickViewButton.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);
                yesNoButton.setCompoundDrawablesWithIntrinsicBounds(null, yesNoRaw , null, null);
                mapButton.setCompoundDrawablesWithIntrinsicBounds(null, mapRaw , null, null);
                newsButton.setCompoundDrawablesWithIntrinsicBounds(null, newsRaw , null, null);

                quickViewButton.setTextColor(getResources().getColor(R.color.white));
                yesNoButton.setTextColor(getResources().getColor(R.color.SandyBrown));
                mapButton.setTextColor(getResources().getColor(R.color.SandyBrown));
                newsButton.setTextColor(getResources().getColor(R.color.SandyBrown));

            }
        });
        yesNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable top = getResources().getDrawable(R.drawable.yesno_on);
                yesNoButton.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);
                quickViewButton.setCompoundDrawablesWithIntrinsicBounds(null, quickViewRaw , null, null);
                mapButton.setCompoundDrawablesWithIntrinsicBounds(null, mapRaw , null, null);
                newsButton.setCompoundDrawablesWithIntrinsicBounds(null, newsRaw , null, null);

                yesNoButton.setTextColor(getResources().getColor(R.color.white));
                quickViewButton.setTextColor(getResources().getColor(R.color.SandyBrown));
                mapButton.setTextColor(getResources().getColor(R.color.SandyBrown));
                newsButton.setTextColor(getResources().getColor(R.color.SandyBrown));
            }
        });
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable top = getResources().getDrawable(R.drawable.map_on);
                mapButton.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);
                newsButton.setCompoundDrawablesWithIntrinsicBounds(null, newsRaw , null, null);
                quickViewButton.setCompoundDrawablesWithIntrinsicBounds(null, quickViewRaw , null, null);
                yesNoButton.setCompoundDrawablesWithIntrinsicBounds(null, yesNoRaw , null, null);

                mapButton.setTextColor(getResources().getColor(R.color.white));
                quickViewButton.setTextColor(getResources().getColor(R.color.SandyBrown));
                yesNoButton.setTextColor(getResources().getColor(R.color.SandyBrown));
                newsButton.setTextColor(getResources().getColor(R.color.SandyBrown));
            }
        });
        newsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable top = getResources().getDrawable(R.drawable.newsfeed_on);
                newsButton.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);
                mapButton.setCompoundDrawablesWithIntrinsicBounds(null, mapRaw , null, null);
                quickViewButton.setCompoundDrawablesWithIntrinsicBounds(null, quickViewRaw , null, null);
                yesNoButton.setCompoundDrawablesWithIntrinsicBounds(null, yesNoRaw , null, null);

                newsButton.setTextColor(getResources().getColor(R.color.white));
                quickViewButton.setTextColor(getResources().getColor(R.color.SandyBrown));
                yesNoButton.setTextColor(getResources().getColor(R.color.SandyBrown));
                mapButton.setTextColor(getResources().getColor(R.color.SandyBrown));
            }});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
