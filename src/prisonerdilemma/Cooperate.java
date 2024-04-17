package prisonerdilemma;

public class Cooperate extends Strategy {

    public Cooperate(Prisoner agent) {
        super(agent);
    }

    @Override
    public boolean cooperate() {
        return true;
    }
}
