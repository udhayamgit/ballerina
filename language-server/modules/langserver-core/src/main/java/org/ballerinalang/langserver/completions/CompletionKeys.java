/*
*  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing,
*  software distributed under the License is distributed on an
*  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*  KIND, either express or implied.  See the License for the
*  specific language governing permissions and limitations
*  under the License.
*/
package org.ballerinalang.langserver.completions;

import org.ballerinalang.langserver.LanguageServerContext;
import org.wso2.ballerinalang.compiler.tree.BLangNode;

import java.util.List;

/**
 * Text Document Service context keys for the completion operation context.
 * @since 0.95.5
 */
public class CompletionKeys {
    public static final LanguageServerContext.Key<BLangNode> SYMBOL_ENV_NODE_KEY
            = new LanguageServerContext.Key<>();
    public static final LanguageServerContext.Key<List<SymbolInfo>> VISIBLE_SYMBOLS_KEY
            = new LanguageServerContext.Key<>();
}
