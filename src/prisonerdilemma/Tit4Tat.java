package prisonerdilemma;

import simstation.Agent;

public class Tit4Tat extends Strategy {

    public Tit4Tat(Prisoner agent) {
        super(agent);
    }

    public boolean cooperate() {
        return ! strategist.cheated;
    }

}
