package prisonerdilemma;

import simstation.Agent;

import java.io.Serializable;

public abstract class Strategy implements Serializable {

    protected Prisoner strategist;
    public Strategy(Prisoner agent) {
        strategist = agent;
    }
    public abstract boolean cooperate();
}
