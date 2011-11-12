package com.teamcodeflux.android.inject.model;

import com.teamcodeflux.android.inject.AndroidInject;
import com.teamcodeflux.android.inject.Inject;

public class InterfaceDependencyTestClass {
    @Inject
    InterfaceDependency dependency;

    public InterfaceDependencyTestClass() {
        AndroidInject.injectDependencies(this);
    }

    public InterfaceDependency getDependency() {
        return dependency;
    }
}
