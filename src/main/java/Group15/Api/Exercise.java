package Group15.Api;

public class Exercise {
    public String title;
    public String description;
    public String type;
    public String bodyPart;
    public String equipment;
    public String difficulty;
    public String imagePath;

    public Exercise(String title, String description, String type, String bodyPart, String equipment, String difficulty, String imagePath) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.bodyPart = bodyPart;
        this.equipment = equipment;
        this.difficulty = difficulty;
        this.imagePath = imagePath;
    }

    public Exercise(){

    }

}