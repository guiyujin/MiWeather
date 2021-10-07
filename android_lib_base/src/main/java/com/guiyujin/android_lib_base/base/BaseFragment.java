package com.guiyujin.android_lib_base.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @ProjectName: AndroidLibBase
 * @Package: com.guiyujin.android_lib_base.base
 * @ClassName: BaseFragment
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/10/5 11:20
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/10/5 11:20
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private ViewGroup rootView;
    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            rootView = (ViewGroup) inflater.inflate(getLayoutRes(), container, false);
        }
        initView(rootView);
        initListener();
        return rootView;
    }

    /**
     * 设置布局资源id
     *
     * @return
     */
    protected abstract int getLayoutRes();

    /**
     * 初始化视图
     *
     * @param view
     */
    protected void initView(View view) {
    }

    /**
     * 初始化事件监听
     */
    protected void initListener() {

    }

    /** View点击 **/
    public abstract void widgetClick(int id);

    @Override
    public void onClick(View v) {
        if (fastClick())
            widgetClick(v.getId());
    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }
}
