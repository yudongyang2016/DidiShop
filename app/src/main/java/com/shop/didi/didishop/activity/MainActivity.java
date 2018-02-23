package com.shop.didi.didishop.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.shop.didi.didishop.R;
import com.shop.didi.didishop.fragment.FragmentOne;
import com.shop.didi.didishop.fragment.FragmentThree;
import com.shop.didi.didishop.fragment.FragmentTwo;
import com.shop.didi.didishop.utils.AppManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
    private FragmentOne mFragmentOne;
    private FragmentTwo mFragmentTwo;
    private FragmentThree mFragmentThree;
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager mFragmentManager;
    private Fragment mCurrentShowFragment;
    /**
     * 双击退出程序
     */
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        initFragments();
        initView();
    }

    /**
     * 初始化Fragment
     */
    private void initFragments() {
        mFragmentOne = FragmentOne.newInstance();
        mFragmentTwo = FragmentTwo.newInstance();
        mFragmentThree = FragmentThree.newInstance();
        fragments.add(mFragmentOne);
        fragments.add(mFragmentTwo);
        fragments.add(mFragmentThree);
        ctrlFragment(mFragmentOne);
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.main_bottom_rg);
        mRadioGroup.check(R.id.main_bottom_rb_one);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_bottom_rb_one:
                        ctrlFragment(mFragmentOne);
                        break;
                    case R.id.main_bottom_rb_two:
                        ctrlFragment(mFragmentTwo);
                        break;
                    case R.id.main_bottom_rb_three:
                        ctrlFragment(mFragmentThree);
                        break;
                }
            }
        });
    }

    /**
     * fragment控制器：不联动
     * @param fragment 当前要显示的Fragment
     */
    private void ctrlFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (mCurrentShowFragment != null && mCurrentShowFragment.isAdded()) {
            fragmentTransaction.hide(mCurrentShowFragment);
        }
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.fragment_contains, fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
//         fragmentTransaction.commitAllowingStateLoss();//Exception:Can not perform this action after onSaveInstanceState时使用
        mCurrentShowFragment = fragment;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            // 判断是否在两秒之内连续点击返回键，是则退出，否则不退出
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                // 将系统当前的时间赋值给exitTime
                exitTime = System.currentTimeMillis();
            } else {
                AppManager.getAppManager().AppExit(this, false);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
