/*
 * Created on 03.12.2013
 */
package ch.fhnw.algd1.sudoku.solver;

import ch.fhnw.algd1.sudoku.framework.SudokuChecker;
import ch.fhnw.algd1.sudoku.framework.SudokuModel;
import ch.fhnw.algd1.sudoku.framework.SudokuSolver;

/**
 * @author
 */
public final class SudokuSolverImpl implements SudokuSolver {
	private final SudokuChecker checker;

	/**
	 * Create a new solver based on a checker
	 * 
	 * @param checker
	 *          the SudokuChecker this solver will be using to determine the
	 *          validity of partial solutions
	 */
	public SudokuSolverImpl(SudokuChecker checker) {
		this.checker = checker;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.fhnw.algd1.sudoku.SudokuSolver#solved(ch.fhnw.algd1.sudoku.SudokuModel)
	 */
	@Override
	public boolean solved(SudokuModel model) {
		return solve(model,0);
	}

	private boolean solve(SudokuModel model,int fieldNr){
		if(fieldNr == model.size()*model.size()){
			return checker.allOK(model);
		}
		int i = fieldNr/model.size(); //zeile
		int j = fieldNr%model.size();
		if(model.get(i,j) != 0){ //feld hat vordefinierten Wert
			return solve(model,fieldNr+1);
		}
		boolean found = false;
		for(int k = 1; k <= model.size(); k++){	//
			model.set(i,j,k);
			if(checker.allOK(model)){ //Branch and Bounce
				if(solve(model,fieldNr+1)){ //try to solve next-field
					return true;
				}
			}
		}
		model.clear(i,j);
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.fhnw.algd1.sudoku.SudokuSolver#nofSolutions(ch.fhnw.algd1.sudoku.
	 * SudokuModel )
	 */
	@Override
	public int nofSolutions(SudokuModel model) {
		// TODO (A2) start recursive enumeration of solutions, beginning at square 0
		// or (0,0) resp.
		return 0;
	}
}
