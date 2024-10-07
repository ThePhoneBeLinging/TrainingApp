package Group15.Api;

public class Exercise
{
    private final String title;
    private final String description;
    private final String type;
    private final String bodyPart;
    private final String equipment;
    private final String difficulty;

    public Exercise(String title, String description, String type, String bodyPart, String equipment, String difficulty)
    {
        this.title = title;
        this.description = description;
        this.type = type;
        this.bodyPart = bodyPart;
        this.equipment = equipment;
        this.difficulty = difficulty;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public String getType()
    {
        return type;
    }

    public String getBodyPart()
    {
        return bodyPart;
    }

    public String getEquipment()
    {
        return equipment;
    }

    public String getDifficulty()
    {
        return difficulty;
    }
}