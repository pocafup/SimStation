package prisonerdilemma;

public class Cheat extends Strategy {

    public Cheat(Prisoner agent) {
        super(agent);
    }

    @Override
    public boolean cooperate() {
        return false;
    }
}
