package prisonerdilemma;

import simstation.Agent;

public abstract class Strategy {

    protected Prisoner strategist;
    public Strategy(Prisoner agent) {
        strategist = agent;
    }
    public abstract boolean cooperate();
}
