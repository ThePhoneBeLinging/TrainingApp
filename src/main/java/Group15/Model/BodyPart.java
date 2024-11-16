package Group15.Model;

public enum BodyPart
{
    Abdominal,
    Biceps,
    Calves,
    Chest,
    Forearms,
    Glutes,
    Hamstrings,
    LowerBack,
    Quadriceps,
    Shoulders,
    Triceps,
    UpperBack

    ;

    @Override
    public String toString()
    {
        if (this == LowerBack)
        {
            return "Lower Back";
        }
        if (this == UpperBack)
        {
            return "Upper Back";
        }
        return super.toString();
    }
}