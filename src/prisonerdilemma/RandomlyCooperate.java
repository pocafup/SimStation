package prisonerdilemma;

import static mvc.Utilities.rng;

public class RandomlyCooperate extends Strategy {
    public RandomlyCooperate(Prisoner agent) {
        super(agent);
    }

    @Override
    public boolean cooperate() {
        int random = rng.nextInt(2);
        return random == 1;
    }
}
