package gsu;

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;

public class Road {

    public static TransformGroup createRoad(float width, float length, float thickness, float x, float y, float z) {
        TransformGroup roadTG = new TransformGroup();

        // Create the road geometry
        Box road = new Box(length, thickness, width, Box.GENERATE_NORMALS, null);

        // Set the appearance of the road (dark grey color)
        Appearance appearance = new Appearance();
        Material material = new Material();
        material.setDiffuseColor(new Color3f(0.2f, 0.2f, 0.2f));
        appearance.setMaterial(material);
        road.setAppearance(appearance);

        // Create a translation transform to position the road at (x, y, z)
        Transform3D transform = new Transform3D();
        transform.setTranslation(new Vector3f(x, y - 0.002f, z)); // Adjust the y coordinate



        // Add the road to the transform group
        roadTG.setTransform(transform);
        roadTG.addChild(road);

        return roadTG;
    }
}
