package com.zzj.learn;

import java.util.List;

import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.dom.*;

public class CreateHelloWorld {
	/**
	 * generate the java source code as below
		package example;
	 	import java.util.*;
	 	public class HelloWorld {
	  		public static void main(String[] args) {
	   			System.out.println("Hello" + " world");
	  		}
	 	}
	 */
	public static void main(String[] args) {
		AST ast = AST.newAST(AST.JLS3);
		CompilationUnit unit = ast.newCompilationUnit();
		PackageDeclaration packageDeclaration = ast.newPackageDeclaration();
		packageDeclaration.setName(ast.newSimpleName("example"));
		unit.setPackage(packageDeclaration);
		ImportDeclaration importDeclaration = ast.newImportDeclaration();
		QualifiedName name = ast.newQualifiedName(ast.newSimpleName("java"), ast.newSimpleName("util"));
		importDeclaration.setName(name);
		importDeclaration.setOnDemand(true);
		unit.imports().add(importDeclaration);
		TypeDeclaration type = ast.newTypeDeclaration();
		type.setInterface(false);
		type.modifiers().add(ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD));
		type.setName(ast.newSimpleName("HelloWorld"));
		MethodDeclaration methodDeclaration = ast.newMethodDeclaration();
		methodDeclaration.setConstructor(false);
		List modifiers = methodDeclaration.modifiers();
		modifiers.add(ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD));
		modifiers.add(ast.newModifier(Modifier.ModifierKeyword.STATIC_KEYWORD));
		methodDeclaration.setName(ast.newSimpleName("main"));
		methodDeclaration.setReturnType2(ast.newPrimitiveType(PrimitiveType.VOID));
		SingleVariableDeclaration variableDeclaration = ast.newSingleVariableDeclaration();
		variableDeclaration.setType(ast.newArrayType(ast.newSimpleType(ast.newSimpleName("String"))));
		variableDeclaration.setName(ast.newSimpleName("args"));
		methodDeclaration.parameters().add(variableDeclaration);
		org.eclipse.jdt.core.dom.Block block = ast.newBlock();
		MethodInvocation methodInvocation = ast.newMethodInvocation();
		name = ast.newQualifiedName(ast.newSimpleName("System"), ast.newSimpleName("out"));
		methodInvocation.setExpression(name);
		methodInvocation.setName(ast.newSimpleName("println"));
		InfixExpression infixExpression = ast.newInfixExpression();
		infixExpression.setOperator(InfixExpression.Operator.PLUS);
		StringLiteral literal = ast.newStringLiteral();
		literal.setLiteralValue("Hello");
		infixExpression.setLeftOperand(literal);
		literal = ast.newStringLiteral();
		literal.setLiteralValue(" world");
		infixExpression.setRightOperand(literal);
		methodInvocation.arguments().add(infixExpression);
		ExpressionStatement expressionStatement = ast.newExpressionStatement(methodInvocation);
		block.statements().add(expressionStatement);
		methodDeclaration.setBody(block);
		type.bodyDeclarations().add(methodDeclaration);
		unit.types().add(type);
	}
	public void testOnly(){
		ToolFactory.createScanner(false, false, false, false);
	}
}
