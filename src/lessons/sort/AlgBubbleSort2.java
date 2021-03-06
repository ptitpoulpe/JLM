package lessons.sort;

import jlm.universe.sort.SortingEntity;

/* BEGIN TEMPLATE */
public class AlgBubbleSort2 extends SortingEntity {
	public void run() {
		/* BEGIN SOLUTION */
		for (int i = getValueCount()-1; i>=0; i--) {
			for (int j = 0; j<i; j++) 
				if (!compare(j,j+1)) 
					swap(j,j+1);
			sorted(i);
		}
		checkme(); /* color everything in blue */
		/* END SOLUTION */
	}
}
/* END TEMPLATE */
