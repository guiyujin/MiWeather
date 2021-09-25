package com.guiyujin.android_lib_base.http;



import com.guiyujin.android_lib_base.Lib;

import dagger.Component;

@Component(modules = NetworkModule.class)
public interface NetworkComponent {

    void inject(Lib mainActivity);
}
