package se.kth.popup.equationsolver;

public final class EquationSolver {
    private EquationSolver() {}

    /**
     * Solves a linear equation system in the form Ax = b.
     * @param a a n*n square matrix
     * @param b a n-vector
     * @return a solution object
     */
    public static EquationSolution solve(double[][] a, double[] b) {



        return null; // TODO
    }

    public static final class EquationSolution {
        private final boolean consistent;
        private final double[][] solution;
        public EquationSolution(boolean consistent, double[][] solution) {
            if(!consistent && solution != null)
                throw new IllegalArgumentException("Solution is inconsistent, vector should be null");
            this.consistent = consistent;
            this.solution = solution;
        }

        public double[][] getSolution() {
            if(!consistent || solution == null)
                throw new IllegalStateException("Solution is not properly defined");
            return solution;
        }

        public boolean isConsistent() {
            return consistent;
        }

        public boolean isUnique() {
            return solution != null;
        }
    }

}
