package com.guiyujin.android_lib_base.base.mvp;

import android.os.Bundle;

import com.guiyujin.android_lib_base.base.BaseFragment;

/**
 * @ProjectName: AndroidLibBase
 * @Package: com.guiyujin.android_lib_base.base.mvp
 * @ClassName: BaseMVPFragment
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/10/5 11:22
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/10/5 11:22
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public abstract class BaseMVPFragment<T extends BasePresenter, E extends BaseModel>  extends BaseFragment {
    protected T presenter;
    protected E model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = initPresenter();
        model = initModel();
    }

    protected abstract T initPresenter();
    protected abstract E initModel();

}
