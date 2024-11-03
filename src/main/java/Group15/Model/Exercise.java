package Group15.Model;

import java.util.List;

public class Exercise
{
    public String title;
    public String description;
    public String type;
    public List<BodyPart> bodyPart;
    public List<Equipment> equipment;
    public String difficulty;
    public String imagePath;
    public int timePerRep;

    public Exercise(String title, String description, String type, List<BodyPart> bodyPart, List<Equipment> equipment, String difficulty, String imagePath, int timePerRep) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.bodyPart = bodyPart;
        this.equipment = equipment;
        this.difficulty = difficulty;
        this.imagePath = imagePath;
        this.timePerRep = timePerRep;
    }

    public Exercise()
    {

    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Exercise))
        {
            return false;
        }
        boolean result = true;
        result &= this.title.equals(((Exercise) obj).title);
        result &= this.description.equals(((Exercise) obj).description);
        result &= this.type.equals(((Exercise) obj).type);
        result &= this.bodyPart.equals(((Exercise) obj).bodyPart);
        result &= this.equipment.equals(((Exercise) obj).equipment);
        result &= this.difficulty.equals(((Exercise) obj).difficulty);


        return result;
    }
}