package gui.components;

import world.World;
import world.objects.Object3D;

import javax.swing.*;

public class ObjectSelector extends JList<Object3D> {
    private World world;
    private ObjectController objectController;

    public ObjectSelector() {
        super();
        addListSelectionListener(e -> {
            if (world == null) return;
            objectController.setObject(getSelectedValue());
        });
    }

    public void setWorld(World world) {
        this.world = world;
        // Will not update even if more objects are added while running
        setListData(world.getWorldObjects().toArray(new Object3D[0]));
    }

    public void setObjectController(ObjectController objectController) {
        this.objectController = objectController;
    }
}
