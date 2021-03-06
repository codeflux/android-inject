/* License added by: GRADLE-LICENSE-PLUGIN
 *
 * Copyright (C)2011 - CodeFlux, Inc <info@teamcodeflux.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.teamcodeflux.android.inject.model;

import com.teamcodeflux.android.inject.Inject;

import static com.teamcodeflux.android.inject.AndroidInject.*;

public class MultipleDependenciesTestClass {
    public static final String DEPENDENCY_FIELD = "dependency";
    public static final String OTHER_DEPENDENCY_FIELD = "otherDependency";

    @Inject
    Dependency dependency;

    @Inject
    OtherDependency otherDependency;

    public MultipleDependenciesTestClass() {
        injectDependencies(this);
    }

    public Dependency getDependency() {
        return dependency;
    }

    public OtherDependency getOtherDependency() {
        return otherDependency;
    }
}



