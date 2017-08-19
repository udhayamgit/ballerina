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
package org.ballerinalang.model;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.ballerinalang.model.elements.PackageID;
import org.ballerinalang.model.tree.PackageNode;
import org.ballerinalang.repository.PackageSource;
import org.ballerinalang.repository.PackageSourceEntry;
import org.ballerinalang.repository.PackageSourceRepository;
import org.wso2.ballerinalang.compiler.parser.BLangParserListener;
import org.wso2.ballerinalang.compiler.parser.antlr4.BallerinaLexer;
import org.wso2.ballerinalang.compiler.parser.antlr4.BallerinaParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * This contains model tree related utility functions. 
 */
public class TreeUtils {

    public static PackageNode loadPackageModel(PackageSourceRepository repo, PackageID pkgID) {
        PackageNode pkgNode = TreeBuilder.createPackageNode(pkgID.getNameComps(), pkgID.getVersion());
        PackageSource pkgSource = repo.getPackageSource(pkgID);
        if (pkgSource == null) {
            return null;
        }
        pkgSource.getPackageSourceEntries().forEach(e -> populatePackageModel(pkgNode, e));
        return pkgNode;
    }
    
    private static void populatePackageModel(PackageNode pkgNode, PackageSourceEntry sourceEntry) {
        try {
            ANTLRInputStream ais = new ANTLRInputStream(new ByteArrayInputStream(sourceEntry.getCode()));
            ais.name = sourceEntry.getEntryName();
            BallerinaLexer lexer = new BallerinaLexer(ais);
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            BallerinaParser parser = new BallerinaParser(tokenStream);
            BLangParserListener listener = new BLangParserListener(pkgNode);
            parser.addParseListener(listener);
            parser.compilationUnit();
        } catch (IOException e) {
            throw new RuntimeException("Error in populating package model: " + e.getMessage(), e);
        }
    }
    
}
