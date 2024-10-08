package Group15.Api;

public enum BodyPart {
    Abdominals,
    Adductor,
    Abductor,
    Biceps,
    Calves,
    Chest,
    Forearms,
    LowerBack,
    Quadriceps,
    Shoulders,
    Triceps,
    ;

    @Override
    public String toString() {
        if(this.equals(LowerBack)) {
            return "Lower Back";
        }
        return super.toString();
    }
}

