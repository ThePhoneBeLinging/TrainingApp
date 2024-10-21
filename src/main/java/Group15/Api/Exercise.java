package Group15.Api;

public class Exercise
{
    public String title;
    public String description;
    public String type;
    public String bodyPart;
    public String equipment;
    public String difficulty;

    public Exercise()
    {
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" + "Description: " + description + "\n" + "Type: " + type + "\n" + "Body Part: "
                + bodyPart + "\n" + "Equipment: " + equipment + "\n" + "Difficulty: " + difficulty;
    }
}