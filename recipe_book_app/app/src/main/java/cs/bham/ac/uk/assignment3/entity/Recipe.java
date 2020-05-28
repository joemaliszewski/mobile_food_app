package cs.bham.ac.uk.assignment3.entity;


public class Recipe{

    private String[] ingredients;
    private String[] steps;
    private Integer id;
    private String description;


    //IDS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //Steps
    public String[] getSteps() {
        return steps;
    }

    public void setSteps(String[] all_steps) {
        this.steps = all_steps;
    }



    //Ingreds
    public void setIngredients(String[] all_ingredients) {
        this.ingredients = all_ingredients;
    }

    public String[] getIngredients() {
        return ingredients;
    }


    //Description
    public String getDescription() {
        return description;
    }
    public void setDescription(String item_description) {
        this.description = item_description;
    }


}
