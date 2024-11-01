package Group15.Model;

public enum BodyPart
{
    Abdominals,
    Adductors,
    Abductors,
    Biceps,
    Calves,
    Chest,
    Forearms,
    LowerBack,
    MiddleBack,
    Traps,
    Quadriceps,
    Shoulders,
    Triceps,
    ;

    @Override
    public String toString()
    {
        if (this == LowerBack)
        {
            return "Lower Back";
        }
        if (this == MiddleBack)
        {
            return "Middle Back";
        }
        return super.toString();
    }
}


