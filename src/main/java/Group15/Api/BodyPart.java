package Group15.Api;

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
    UpperBack,
    Back,
    Traps,
    Quadriceps,
    Shoulders,
    Triceps,
    Glutes,
    Hamstrings,
    RearDeltoids,
    Quads,
    Core,
    Legs,
    Arms,
    Lats,
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


