package Group15.Model;

public enum Equipment
{
    Barbell,
    Bodyweight,
    Cable,
    Dumbbell,
    EZBar,
    Machine,
    SmithMachine
    ;

    @Override
    public String toString() {
        if(this == EZBar) {
            return "EZ Bar";
        }
        if(this == SmithMachine) {
            return "Smith Machine";
        }
        return super.toString();
    }
}
