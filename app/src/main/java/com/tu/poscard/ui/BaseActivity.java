package com.tu.poscard.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tu.poscard.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther Tu
 * @date 2018/12/14
 * @email enum@foxmail.com
 */
public abstract class BaseActivity extends AppCompatActivity {
    Toast mToast;
    @BindView(R.id.toolbar)
    @Nullable
    Toolbar toolbar;
    private long lastToolbarClick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() - lastToolbarClick < 500) {
                    onTitleDoubleClick();
                }
                lastToolbarClick = System.currentTimeMillis();
            }
        });
        initView();
    }

    abstract int layoutId();

    abstract void initView();

    /**
     * title 双击
     */
    void onTitleDoubleClick() {
    }


    void setTitle(int titleStrId, boolean displayHomeAsUpdateEnable) {
        getSupportActionBar().setHomeButtonEnabled(displayHomeAsUpdateEnable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(displayHomeAsUpdateEnable);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText(titleStrId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void toast(CharSequence message) {
        synchronized (this) {
            if (mToast == null) {
                mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
            }
            mToast.setText(message);
            mToast.show();
        }
    }

    void toast(int strId) {
        toast(getString(strId));
    }

    void showError(EditText editText, int strId) {
        showError(editText, getString(strId));
    }

    void showError(EditText editText, String msg) {
        TextInputLayout textInputLayout = (TextInputLayout) editText.getParent().getParent();
        textInputLayout.setError(msg);
        editText.requestFocus();
        editText.setSelection(textInputLayout.getEditText().length());
    }
}
