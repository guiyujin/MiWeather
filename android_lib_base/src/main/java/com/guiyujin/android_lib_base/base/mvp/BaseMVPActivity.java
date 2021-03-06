package com.guiyujin.android_lib_base.base.mvp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.guiyujin.android_lib_base.base.BaseActivity;

/**
 * @ProjectName: AndroidLibBase
 * @Package: com.guiyujin.android_lib_base.base.mvp
 * @ClassName: BaseMVPActivity
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/10/5 10:57
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/10/5 10:57
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public abstract class BaseMVPActivity <T extends BasePresenter, E extends BaseModel> extends BaseActivity{

    protected T presenter;
    protected E model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = initPresenter();
        model = initModel();

    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("ONRESULTSSS", "ONSTOP");
    }

    protected abstract T initPresenter();
    protected abstract E initModel();
}
