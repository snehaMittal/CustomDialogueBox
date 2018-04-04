package com.sneha.customdialoguebox;

import android.content.Context;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.animation.Animation;

public class MainActivity extends AppCompatActivity {

    Button button , cancel ;
    Animation animShake ;
    Vibrator vibrator ;
    EditText name  ,amnt ;
    TextInputLayout tl1 , tl2 ;
    AlertDialog dialog = null ;
    LinearLayout linearLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        animShake = AnimationUtils.loadAnimation(getApplicationContext() , R.anim.shake);
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        linearLayout = (LinearLayout)findViewById(R.id.mem_list);
        button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder mBuilder  = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.customlayout , null);
                 name = (EditText)mView.findViewById(R.id.name);
                 amnt = (EditText)mView.findViewById(R.id.amount);
                tl1 = (TextInputLayout)mView.findViewById(R.id.text_name);
                tl2 = (TextInputLayout)mView.findViewById(R.id.text_amnt);
                Button save = (Button)mView.findViewById(R.id.save);
                Button cancel = (Button)mView.findViewById(R.id.cancel);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        submitForm();
                    }
                });
                mBuilder.setView(mView);
                dialog = mBuilder.create();
                dialog.show();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }

    private void submitForm(){
        if (!checkName()){
            name.setAnimation(animShake);
            name.startAnimation(animShake);
            vibrator.vibrate(120);
            return;
        }
        if (!checkAmount()){
            amnt.setAnimation(animShake);
            amnt.startAnimation(animShake);
            vibrator.vibrate(120);
            return;
        }
        tl1.setErrorEnabled(false);
        tl2.setErrorEnabled(false);
        layout();
        dialog.dismiss();

    }

    private void layout() {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10 , 5 , 10 , 5);

        LinearLayout parent = new LinearLayout(this);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        llp.setMargins(10,10,10,10);
        parent.setLayoutParams(llp);

        parent.setOrientation(LinearLayout.VERTICAL);

        LinearLayout child_1a = new LinearLayout(this);;
        child_1a.setLayoutParams(llp);
        child_1a.setOrientation(LinearLayout.HORIZONTAL);

        ImageView iv = new ImageView(this);
        iv.setLayoutParams(params);

        TextView tv1 = new TextView(this);
        tv1.setText(name.getText().toString());
        tv1.setLayoutParams(params);
        child_1a.addView(iv);
        child_1a.addView(tv1);
        parent.addView(child_1a);

        LinearLayout child_1b = new LinearLayout(this);;
        child_1b.setLayoutParams(llp);
        child_1b.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout child_2a = new LinearLayout(this);;
        child_2a.setLayoutParams(new LinearLayout.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT));
        child_2a.setOrientation(LinearLayout.VERTICAL);

        TextView tv2 = new TextView(this);
        tv2.setText("Amount paid");
        tv2.setLayoutParams(params);

        TextView tv3 = new TextView(this);
        tv3.setText(amnt.getText().toString());
        tv3.setLayoutParams(params);

        child_2a.addView(tv2);
        child_2a.addView(tv3);

        LinearLayout child_2b = new LinearLayout(this);;
        child_2b.setLayoutParams(new LinearLayout.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT));
        child_2b.setOrientation(LinearLayout.VERTICAL);

        TextView tv4 = new TextView(this);
        tv4.setText("Amount to be paid");
        tv4.setLayoutParams(params);

        TextView tv5 = new TextView(this);
        tv5.setText("calc");
        tv5.setLayoutParams(params);
        child_2b.addView(tv4);
        child_2b.addView(tv5);

        LinearLayout child_2c = new LinearLayout(this);;
        child_2c.setLayoutParams(new LinearLayout.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT));
        child_2c.setOrientation(LinearLayout.VERTICAL);

        TextView tv6 = new TextView(this);
        tv6.setText("Balance");
        tv6.setLayoutParams(params);
        Log.d("Tag" , tv6.getText().toString());

        TextView tv7 = new TextView(this);
        tv7.setText("Calc");
        tv7.setLayoutParams(params);

        child_2c.addView(tv6);
        child_2c.addView(tv7);

        child_1b.addView(child_2a);
        child_1b.addView(child_2b);
        child_1b.addView(child_2c);

        parent.addView(child_1b);
        linearLayout.addView(parent);
    }

    private boolean checkAmount() {
        if (amnt.getText().toString().trim().isEmpty()){
            tl2.setErrorEnabled(true);
            tl2.setError("Please enter a amount");
            amnt.setError("Valid input required");
            return false;
        }
        tl2.setErrorEnabled(false);
        return true ;
    }

    private boolean checkName() {
        if (name.getText().toString().trim().isEmpty()){
            tl1.setErrorEnabled(true);
            tl1.setError("Please enter a name");
            name.setError("Valid input required");
            return false;
        }
        tl1.setErrorEnabled(false);
        return true ;
    }


}
