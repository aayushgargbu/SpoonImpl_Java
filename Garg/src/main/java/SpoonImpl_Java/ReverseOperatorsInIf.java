package SpoonImpl_Java;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import spoon.Launcher;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.support.compiler.VirtualFile;
import spoon.support.reflect.code.CtBinaryOperatorImpl;
import spoon.support.reflect.code.CtIfImpl;

public class ReverseOperatorsInIf {
	public String RevOpsInIf(String code) throws Exception {
		try {
			String modCode = null;
			Launcher launcher = new Launcher();
			launcher.addInputResource(new VirtualFile(code));
			launcher.getEnvironment().setNoClasspath(true);
			launcher.getEnvironment().setAutoImports(true);
			Collection<CtType<?>> allTypes = launcher.buildModel().getAllTypes();
			if (allTypes.size() == 1) {
				CtClass<?> singleClass = (CtClass<?>) allTypes.stream().findFirst().get();
				Set<CtMethod<?>> methods = singleClass.getAllMethods();
				ReverseBinOps(methods);
				modCode = singleClass.toString();
			} else {
				System.out.println("Limitation hit (as of now): Size: " + allTypes.size());
//				for (CtType<?> anotherClass : allTypes) {
//					System.out.println(anotherClass.getClass().getTypeName() + anotherClass);
//					CtClass<?> singleClass = (CtClass<?>) allTypes.stream().findFirst().get();
//					Set<CtMethod<?>> methods = anotherClass.getAllMethods();
//					ReverseBinOps(methods);
//				}
			}
			return modCode;
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void ReverseBinOps(Set<CtMethod<?>> setOfMethods) throws Exception {
		try {
			for (CtMethod<?> method : setOfMethods) {
				List<CtStatement> statements = method.getBody().getStatements();
				if (statements.size() > 0) {
					for (CtStatement statement : statements) {
						if (statement.getClass().getTypeName() == CtIfImpl.class.getTypeName()) {
							CtIfImpl ifImpl = (CtIfImpl) statement;
							CtBinaryOperatorImpl<?> binaryOperatorImpl = (CtBinaryOperatorImpl<?>) ifImpl
									.getCondition();
							OperationOnCondition(binaryOperatorImpl);

							// Then statement
							if (ifImpl.getThenStatement() != null) {
								for (CtElement element : ifImpl.getThenStatement().asIterable()) {
									if (element.getClass().getTypeName() == CtBinaryOperatorImpl.class.getTypeName()) {
										CtBinaryOperatorImpl<?> binaryOpImpl = (CtBinaryOperatorImpl<?>) element;
										OperationOnCondition(binaryOpImpl);
									}
								}
							}

							// Else Statement
							if (ifImpl.getElseStatement() != null) {
								for (CtElement element : ifImpl.getElseStatement().asIterable()) {
									if (element.getClass().getTypeName() == CtBinaryOperatorImpl.class.getTypeName()) {
										CtBinaryOperatorImpl<?> binaryOpImpl = (CtBinaryOperatorImpl<?>) element;
										OperationOnCondition(binaryOpImpl);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void OperationOnCondition(CtBinaryOperatorImpl<?> binaryOperatorImpl) throws Exception {
		try {
			BinaryOperatorKind kind = binaryOperatorImpl.getKind();
			BinaryOperatorKind reverseKind = ReverseBinaryOperatorKind(kind);
			if (kind != reverseKind) {
				binaryOperatorImpl.setKind(reverseKind);
			}
			if (binaryOperatorImpl.getLeftHandOperand().getClass().getTypeName() == CtBinaryOperatorImpl.class
					.getTypeName()) {
				OperationOnCondition((CtBinaryOperatorImpl<?>) binaryOperatorImpl.getLeftHandOperand());
			}
			if (binaryOperatorImpl.getRightHandOperand().getClass().getTypeName() == CtBinaryOperatorImpl.class
					.getTypeName()) {
				OperationOnCondition((CtBinaryOperatorImpl<?>) binaryOperatorImpl.getRightHandOperand());
			}
		} catch (Exception ex) {
			throw ex;
		}
	}

	private BinaryOperatorKind ReverseBinaryOperatorKind(BinaryOperatorKind originalKind) throws Exception {
		try {
			BinaryOperatorKind reverseKind;
			switch (originalKind) {
			case EQ:
				reverseKind = BinaryOperatorKind.NE;
				break;
			case NE:
				reverseKind = BinaryOperatorKind.EQ;
				break;
			case LT:
				reverseKind = BinaryOperatorKind.GT;
				break;
			case GT:
				reverseKind = BinaryOperatorKind.LT;
				break;
			case LE:
				reverseKind = BinaryOperatorKind.GE;
				break;
			case GE:
				reverseKind = BinaryOperatorKind.LE;
				break;
			default:
				reverseKind = originalKind;
				break;
			}
			return reverseKind;
		} catch (Exception ex) {
			throw ex;
		}
	}
}
