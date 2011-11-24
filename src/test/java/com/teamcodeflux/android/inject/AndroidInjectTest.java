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

import com.teamcodeflux.android.inject.model.Dependency;
import com.teamcodeflux.android.inject.model.InterfaceDependency;
import com.teamcodeflux.android.inject.model.InterfaceDependencyTestClass;
import com.teamcodeflux.android.inject.model.MultipleDependenciesTestClass;
import com.teamcodeflux.android.inject.model.NoDependencyTestClass;
import com.teamcodeflux.android.inject.model.OneDependencyTestClass;
import com.teamcodeflux.android.inject.model.OtherDependency;
import com.teamcodeflux.android.inject.model.SubClassTestClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.teamcodeflux.android.inject.AndroidInject.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class AndroidInjectTest {
    private static final Dependency DEPENDENCY = new Dependency();
    private static final OtherDependency OTHER_DEPENDENCY = new OtherDependency();
    private static final InterfaceDependency MOCKED_DEPENDENCY = Mockito.mock(InterfaceDependency.class);

    @Test(expected = IllegalAccessException.class)
    public void shouldNotAllowInstantiation() throws Exception {
        AndroidInject.class.newInstance();

        fail("Should not be allowed");
    }

    @Test(expected = InvocationTargetException.class)
    public void shouldNotAllowInstantiationUsingReflection() throws Exception {
        Constructor defaultConstructor = AndroidInject.class.getDeclaredConstructors()[0];
        defaultConstructor.setAccessible(true);
        defaultConstructor.newInstance((Object[]) null);

        fail("Should not be allowed");
    }

    @Test
    public void shouldInjectDependenciesToFieldsWithInjectAnnotation() {
        registerDependency(DEPENDENCY);

        OneDependencyTestClass testClass = new OneDependencyTestClass();

        assertThat(testClass.getDependency(), is(sameInstance(DEPENDENCY)));
    }

    @Test
    public void shouldNotInjectDependenciesIfNoFieldsWithInjectAnnotation() {
        registerDependency(DEPENDENCY);

        NoDependencyTestClass testClass = new NoDependencyTestClass();

        assertThat(testClass.getDependency(), is(nullValue()));
    }

    @Test
    public void shouldInjectDependenciesToAllFieldsWithInjectAnnotation() {
        registerDependency(DEPENDENCY);
        registerDependency(OTHER_DEPENDENCY);

        MultipleDependenciesTestClass testClass = new MultipleDependenciesTestClass();

        assertThat(testClass.getDependency(), is(sameInstance(DEPENDENCY)));
        assertThat(testClass.getOtherDependency(), is(sameInstance(OTHER_DEPENDENCY)));
    }

    @Test
    public void shouldInjectDependenciesToFieldsWithInjectAnnotationInSuperClass() {
        registerDependency(DEPENDENCY);

        SubClassTestClass testClass = new SubClassTestClass();

        assertThat(testClass.getDependency(), is(sameInstance(DEPENDENCY)));
    }

    @Test
    public void shouldInjectMockDependenciesToFieldsWithInjectAnnotation() {
        registerDependency(MOCKED_DEPENDENCY);

        InterfaceDependencyTestClass testClass = new InterfaceDependencyTestClass();

        assertThat(testClass.getDependency(), is(sameInstance(MOCKED_DEPENDENCY)));
    }
}




