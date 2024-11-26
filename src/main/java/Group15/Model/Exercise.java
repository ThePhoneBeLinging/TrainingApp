package Group15.Model;

import java.util.List;
import java.util.Objects;

public class Exercise
{
    public String title;
    public String description;
    public List<BodyPart> bodyParts;
    public List<Equipment> equipment;
    public String difficulty;
    public String imagePath;
    public int timePerRep;
    public String type;

    public Exercise(String title, String description, List<BodyPart> bodyParts, List<Equipment> equipment, String difficulty, String imagePath, int timePerRep) {
        this.title = title;
        this.description = description;
        this.bodyParts = bodyParts;
        this.equipment = equipment;
        this.difficulty = difficulty;
        this.imagePath = imagePath;
        this.timePerRep = timePerRep;
    }

    public Exercise()
    {

    }

    public String makeBodypartsString() {
        StringBuilder bodypartsString = new StringBuilder();
        for (BodyPart bodyPart : bodyParts) {
            bodypartsString.append(bodyPart.toString());
            if (bodyParts.indexOf(bodyPart) != bodyParts.size() - 1) {
                bodypartsString.append(", ");
            }
        }
        return bodypartsString.toString();
    }

    public String makeEquipmentString() {
        StringBuilder equipmentString = new StringBuilder();
        for (Equipment equipment : equipment) {
            equipmentString.append(equipment.toString());
            if (this.equipment.indexOf(equipment) != this.equipment.size() - 1) {
                equipmentString.append(", ");
            }
        }
        return equipmentString.toString();
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
        result &= Objects.equals(this.bodyParts,((Exercise) obj).bodyParts);
        result &= Objects.equals(this.equipment,((Exercise) obj).equipment);
        result &= Objects.equals(this.difficulty,((Exercise) obj).difficulty);
        result &= Objects.equals(this.timePerRep,((Exercise) obj).timePerRep);



        return result;
    }
}