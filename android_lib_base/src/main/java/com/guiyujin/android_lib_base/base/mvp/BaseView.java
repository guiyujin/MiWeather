package com.guiyujin.android_lib_base.base.mvp;

import androidx.lifecycle.LifecycleOwner;

/**
 * @ProjectName: AndroidLibBase
 * @Package: com.guiyujin.android_lib_base.base.mvp
 * @ClassName: BaseView
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/10/5 10:53
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/10/5 10:53
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface BaseView extends LifecycleOwner {
    void onLoading();
    void disLoading();
}
