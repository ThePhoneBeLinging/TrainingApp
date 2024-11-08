package Group15.Model;

import java.util.List;
import java.util.Objects;

public class Exercise
{
    public String title;
    public String description;
    public List<BodyPart> bodyPart;
    public List<Equipment> equipment;
    public String difficulty;
    public String imagePath;
    public int timePerRep;
    public String type;

    public Exercise(String title, String description, List<BodyPart> bodyPart, List<Equipment> equipment, String difficulty, String imagePath, int timePerRep) {
        this.title = title;
        this.description = description;
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

        result &= Objects.equals(this.title,((Exercise) obj).title);
        result &= Objects.equals(this.description,((Exercise) obj).description);
        result &= Objects.equals(this.bodyPart,((Exercise) obj).bodyPart);
        result &= Objects.equals(this.equipment,((Exercise) obj).equipment);
        result &= Objects.equals(this.difficulty,((Exercise) obj).difficulty);
        result &= Objects.equals(this.timePerRep,((Exercise) obj).timePerRep);



        return result;
    }
}