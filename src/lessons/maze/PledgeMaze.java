package lessons.maze;

import java.awt.Color;

import jlm.core.model.lesson.ExecutionProgress;
import jlm.core.model.lesson.ExerciseTemplated;
import jlm.core.model.lesson.Lesson;
import jlm.universe.Direction;
import jlm.universe.Entity;
import jlm.universe.World;
import jlm.universe.bugglequest.AbstractBuggle;
import jlm.universe.bugglequest.Buggle;
import jlm.universe.bugglequest.BuggleWorld;
import jlm.universe.bugglequest.exception.AlreadyHaveBaggleException;
import jlm.universe.bugglequest.exception.NoBaggleUnderBuggleException;

public class PledgeMaze extends ExerciseTemplated {

	public PledgeMaze(Lesson lesson) {
		super(lesson);
		tabName = "Escaper";
				
		/* Create initial situation */
		BuggleWorld myWorlds[] = new BuggleWorld[1];
		myWorlds[0] = new BuggleWorld("Labyrinth", 4, 4); 
		loadMap(myWorlds[0],"lessons/maze/PledgeMaze");

		new Buggle(myWorlds[0], "Thésée", 12, 14, Direction.NORTH, Color.black, Color.lightGray);
		newSourceAliased("lessons.maze.Main","lessons.maze.WallFollowerMaze","Escaper");
		setup(myWorlds);
	}

	// to shorten loading time	
	@Override
	protected void computeAnswer(){
		AbstractBuggle b = (AbstractBuggle)answerWorld[0].entities().next();
		b.setPosFromLesson(19, 19);
		b.setDirection(Direction.EAST);
		try {
			b.pickUpBaggle();
		} catch (NoBaggleUnderBuggleException e) {
			e.printStackTrace();
		} catch (AlreadyHaveBaggleException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void check() {
		lastResult = new ExecutionProgress();
		for (World w: this.getCurrentWorld()) 
			for (Entity e:w.getEntities()) {
				lastResult.totalTests++;
				if (!((AbstractBuggle) e).isCarryingBaggle())
					lastResult.details += "Buggle "+e.getName()+" didn't find the baggle";
				else
					lastResult.passedTests++;
			}
	}

}
