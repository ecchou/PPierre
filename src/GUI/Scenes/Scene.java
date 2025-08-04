package GUI.Scenes;

import GUI.Components.Component;

import java.util.List;

public abstract class Scene {

    private List<Component> components;

    public Scene(List<Component> components) {
        this.components = components;
    }

    public List<Component> getComponents(){return this.components;}

    public void addComponent(Component c){
        // Ajouter un Component (à faire à la création, pas une fois sur une fenêtre)
        components.add(c);
    }

}
