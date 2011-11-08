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

import static com.teamcodeflux.android.inject.Classes.*;

final class Injects {
    static List<Field> annotatedFields(final Object target) {
        List<Field> annotatedFields = new ArrayList<Field>();

        for (Class<?> clazz : classHierarchy(target)) {
            for (Field field : clazz.getDeclaredFields()) {
                if (hasInjectAnnotation(field)) {
                    annotatedFields.add(field);
                }
            }
        }

        return annotatedFields;
    }

    private static boolean hasInjectAnnotation(final Field field) {
        return field.getAnnotation(Inject.class) != null;
    }

    private Injects() throws Exception {
        throw new IllegalAccessException();
    }
}



