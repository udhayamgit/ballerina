/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ballerinalang.plugins.idea.psi.injector;

import com.intellij.json.JsonLanguage;
import com.intellij.psi.ElementManipulators;
import com.intellij.psi.InjectedLanguagePlaces;
import com.intellij.psi.LanguageInjector;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * Injects JSON language to valid JSON strings.
 */
public class JsonLanguageInjector implements LanguageInjector {

    private static final Set<String> JSON_PACKAGE_NAMES = ContainerUtil.newHashSet("jsons");
    private static final Set<String> JSON_FUNCTION_NAMES = ContainerUtil.newHashSet("parse");

    @Override
    public void getLanguagesToInject(@NotNull PsiLanguageInjectionHost host,
                                     @NotNull InjectedLanguagePlaces injectionPlacesRegistrar) {
        if (LanguageInjectorUtils.isValid(host, JSON_PACKAGE_NAMES, JSON_FUNCTION_NAMES)) {
            injectionPlacesRegistrar.addPlace(JsonLanguage.INSTANCE, ElementManipulators.getValueTextRange(host),
                    null, null);
        }
    }
}
