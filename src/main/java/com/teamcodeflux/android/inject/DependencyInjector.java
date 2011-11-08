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

package com.teamcodeflux.android.inject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.teamcodeflux.android.inject.Injects.*;

class DependencyInjector {
    private Object target;

    private List<Object> dependencies;

    DependencyInjector(final Object target, final List<Object> dependencies) {
        this.target = target;
        this.dependencies = new ArrayList<Object>(dependencies);
    }

    void injectDependencies() {
        for (Field field : annotatedFields(target)) {
            injectDependency(field);
        }
    }

    private void injectDependency(final Field field) {
        for (Object dependency : dependencies) {
            if (tryAndInjectDependency(field, dependency)) {
                break;
            }
        }
    }

    private boolean tryAndInjectDependency(final Field field, final Object dependency) {
        Class<?> type = field.getType();
        boolean inject = type.equals(dependency.getClass());
        boolean success = false;

        if (!inject) {
            for (Class<?> i : dependency.getClass().getInterfaces()) {
                if (type.equals(i)) {
                    inject = true;
                    break;
                }
            }
        }

        if (inject) {
            try {
                field.setAccessible(true);
                field.set(target, dependency);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            success = true;
        }

        return success;
    }
}



