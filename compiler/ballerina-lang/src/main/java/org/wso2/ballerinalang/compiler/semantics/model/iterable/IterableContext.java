/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
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
package org.wso2.ballerinalang.compiler.semantics.model.iterable;

import org.wso2.ballerinalang.compiler.semantics.model.symbols.BInvokableSymbol;
import org.wso2.ballerinalang.compiler.semantics.model.types.BType;
import org.wso2.ballerinalang.compiler.tree.BLangVariable;
import org.wso2.ballerinalang.compiler.tree.expressions.BLangExpression;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents Chain of Iterable Operation.
 * This will converted into a function at code generation.
 *
 * @since 0.961.0
 */
public class IterableContext {

    public LinkedList<Operation> operations;
    public BType resultType;

    /* Filed used for code generation */
    public BLangVariable countVar, skipVar, resultVar;
    public List<BLangVariable> streamRetVars;
    public BLangExpression collectionExpr;
    public BLangExpression iteratorCaller;

    public BInvokableSymbol streamFuncSymbol, iteratorFuncSymbol;

    public IterableContext(BLangExpression collectionExpr) {
        this.operations = new LinkedList<>();
        this.collectionExpr = collectionExpr;
    }

    public void addOperation(Operation operation) {
        operation.previous = operations.size() > 0 ? operations.getLast() : null;
        operations.add(operation);
    }

    public Operation getFirstOperation() {
        return this.operations.getFirst();
    }

    public Operation getLastOperation() {
        return this.operations.getLast();
    }
}
