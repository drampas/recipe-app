package drampas.springframework.recipeapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer cookTime;
    private Integer prepTime;
    private Integer serving;
    private String source;
    private String url;
    @Lob
    private String directions;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe")
    private Set<Ingredient> ingredients=new HashSet<>();
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private Byte[] image;
    @ManyToMany
    @JoinTable(name = "recipe_category",joinColumns = @JoinColumn(name = "recipe_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories=new HashSet<>();

    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

}
