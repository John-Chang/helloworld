package ChocoLearn;

import choco.Choco;
import choco.cp.model.CPModel;
import choco.kernel.model.variables.integer.IntegerVariable;
import choco.kernel.model.variables.real.RealExpressionVariable;
import choco.kernel.model.variables.real.RealVariable;
import choco.kernel.model.variables.set.SetVariable;
import choco.kernel.model.constraints.Constraint;
import choco.kernel.solver.Configuration;
import choco.cp.solver.CPSolver;
import choco.cp.solver.search.real.CyclicRealVarSelector;
import choco.cp.solver.search.real.RealIncreasingDomain;
import choco.cp.solver.search.set.MinDomSet;
import choco.cp.solver.search.set.MinEnv;

public class CPDemo {
	public static final int intupperlimit = Integer.MAX_VALUE;
	public static final int intlowerlimit = Integer.MIN_VALUE;

	public static final float floatupperlimit = Float.MAX_VALUE;
	public static final float floatlowerlimit = Float.MIN_VALUE;

	public static final double doubleupperlimit = Double.MAX_VALUE;
	public static final double doublelowerlimit = Double.MIN_VALUE;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 simpleDemo();
		problem1();
	}

	private static void print(Object o) {
		System.out.println(o);
	}

	// a simple demo of solve exhibition
	private static void simpleDemo() {
		IntegerVariable v = Choco.makeIntVar("v", 1, 10);
		Constraint c = Choco.eq(v, 11);
		CPModel model = new CPModel();
		model.addConstraint(c);
		CPSolver solver = new CPSolver();
		solver.read(model);
		boolean solverable = solver.solve();
		print(solverable);
	}

	// a demo to exhibit the process of Choco solving
	private static void chocoStreamline() {
		// Constant declaration
		int n = 3; // Order of the magic square
		int magicSum = n * (n * n + 1) / 2; // Magic sum
		// Build the model
		CPModel m = new CPModel();
		// Creation of an array of variables
		IntegerVariable[][] var = new IntegerVariable[n][n];
		// For each variable, we define its name and the boundaries of its
		// domain.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				var[i][j] = Choco.makeIntVar("var_" + i + "_" + j, 1, n * n);
				// Associate the variable to the model.
				m.addVariable(var[i][j]);
			}
		}
		// All cells of the matrix must be different
		for (int i = 0; i < n * n; i++) {
			for (int j = i + 1; j < n * n; j++) {
				Constraint c = (Choco.neq(var[i / n][i % n], var[j / n][j % n]));
				m.addConstraint(c);
			}
		}
	}

	/**
	 * the n-queens problem
	 */
	private static void problem1() {
		int nbQueen = 3;
		// 1- Create the model
		CPModel m = new CPModel();
		// 2- Create the variables
		IntegerVariable[] queens = Choco.makeIntVarArray("Q", nbQueen, 1, nbQueen);// 四个皇后分列四行，下面讨论其所在列的情况
		// 3- Post constraints
		for (int i = 0; i < nbQueen; i++) {
			for (int j = i + 1; j < nbQueen; j++) {
				int k = j - i;
				m.addConstraint(Choco.neq(queens[i], queens[j]));// queens[i]的值就是第i个皇后所在的列编号
				m.addConstraint(Choco.neq(queens[i], Choco.plus(queens[j], k))); // 反对角线约束，为什么不采取Choco.neq(k, Choco.minus(queens[j], queens[i]))
//				m.addConstraint(Choco.neq(k, Choco.minus(queens[j], queens[i]))); // 测试Choco.neq(k, Choco.minus(queens[j], queens[i]))，结果失败，解数量大增
				m.addConstraint(Choco.neq(queens[i], Choco.minus(queens[j], k))); // 正对角线约束
			}
		}
		// 4- Create the solver
		CPSolver s = new CPSolver();
		s.read(m);
		s.solveAll();
		// 5- Print the number of solutions found
		System.out.println("Number of solutions found:" + s.getSolutionCount());
	}
	
	/**
	 *  Steiner problem
	 */
	private static void problem2() {
		// 1- Create the problem
		CPModel mod = new CPModel();
		int m = 7;
		int n = m * (m - 1) / 6;
		// 2- Create Variables
		SetVariable[] vars = new SetVariable[n]; // A variable for each set
		SetVariable[] intersect = new SetVariable[n * n]; // A variable for each
															// pair of sets
		for (int i = 0; i < n; i++)
			vars[i] = Choco.makeSetVar("set " + i, 1, n);
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				intersect[i * n + j] = Choco.makeSetVar("interSet " + i + " " + j, 1, n);
		// 3- Post constraints
		for (int i = 0; i < n; i++)
			mod.addConstraint(Choco.eqCard(vars[i], 3));
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++) {
				// the cardinality of the intersection of each pair is equal to
				// one
				mod.addConstraint(Choco.setInter(vars[i], vars[j], intersect[i * n + j]));
				mod.addConstraint(Choco.leqCard(intersect[i * n + j], 1));
			}
		// 4- Search for a solution
		CPSolver s = new CPSolver();
		s.read(mod);
		s.setVarSetSelector(new MinDomSet(s, s.getVar(vars)));
		s.setValSetSelector(new MinEnv());
		s.solve();
		// 5- Print the solution found
		for (SetVariable var : vars) {
			System.out.println(s.getVar(var).pretty());
		}
	}

	/**
	 * Descripton: Example 3: the CycloHexane problem. The problem consists in
	 * nding the 3D con guration of a cyclohexane molecule. It is described with
	 * a system of three non linear equations: Variables: x; y; z. Domain:[8, 8]
	 * Constraints: 
	 * y2 * (1 + z2) + z * (z - 24 * y) = -13 
	 * x2 * (1 + y2) + y * (y - 24 * x) = -13 
	 * z2 * (1 + x2) + x * (x - 24 * z) = -13
	 */
	private static void problem3() {
		// 1- Create the problem
		CPModel pb = new CPModel();
		// 2- Create the variable
		RealVariable x = Choco.makeRealVar("x", -1.0e8, 1.0e8);
		RealVariable y = Choco.makeRealVar("y", -1.0e8, 1.0e8);
		RealVariable z = Choco.makeRealVar("z", -1.0e8, 1.0e8);
		// 3- Create and post the constraints
		RealExpressionVariable exp1 = Choco.plus(// 对约束表达式的构建，是通过作用于XXXExpressionVariable的函数实现的
				Choco.mult(Choco.power(y, 2), Choco.plus(1, Choco.power(z, 2))),// y2 *(1 + z2)
				Choco.mult(z, Choco.minus(z, Choco.mult(24, y)))// z * (z - 24 * y)
				);
		RealExpressionVariable exp2 = Choco.plus(Choco.mult(Choco.power(z, 2), Choco.plus(1, Choco.power(x, 2))),
				Choco.mult(x, Choco.minus(x, Choco.mult(24, z))));
		RealExpressionVariable exp3 = Choco.plus(Choco.mult(Choco.power(x, 2), Choco.plus(1, Choco.power(y, 2))),
				Choco.mult(y, Choco.minus(y, Choco.mult(24, x))));
		Constraint eq1 = Choco.eq(exp1, -13);
		Constraint eq2 = Choco.eq(exp2, -13);
		Constraint eq3 = Choco.eq(exp3, -13);
		pb.addConstraint(eq1);
		pb.addConstraint(eq2);
		pb.addConstraint(eq3);
		// 4- Search for all solution
		CPSolver s = new CPSolver();
		s.getConfiguration().putDouble(Configuration.REAL_PRECISION, 1e-8);
		s.read(pb);
		s.setVarRealSelector(new CyclicRealVarSelector(s));
		s.setValRealIterator(new RealIncreasingDomain());
		s.solve();
		// 5- print the solution found
		System.out.println("x " + s.getVar(x).getValue());
		System.out.println("y " + s.getVar(y).getValue());
		System.out.println("z " + s.getVar(z).getValue());
	}
}
