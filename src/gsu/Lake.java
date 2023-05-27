package gsu;

import com.sun.j3d.utils.geometry.Cylinder;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.image.TextureLoader;


public class Lake {
    public static TransformGroup createLake(float radius, float height, float x, float y, float z) {
        // Create a cylinder and scale it to create an oval shape
        Cylinder cylinder = new Cylinder(radius*1.5f, height);
        Transform3D scaleTransform = new Transform3D();
        scaleTransform.setScale(new Vector3d(1.0, 0.5, 1.0)); // Scale the cylinder to create an oval shape

        // Set the appearance of the lake with a blue color
        Appearance appearance = new Appearance();
        Material material = new Material();
        material.setDiffuseColor(new Color3f(0.0f, 0.0f, 1.0f));
        appearance.setMaterial(material);
        cylinder.setAppearance(appearance);

        // Position the lake in the scene
        Transform3D translateTransform = new Transform3D();
        translateTransform.setTranslation(new Vector3f(x- 2.0f, y, z)); // Shift the lake to the left by 0.5 units

        // Combine the scale and translation transformations
        Transform3D combinedTransform = new Transform3D();
        combinedTransform.mul(translateTransform, scaleTransform);

        // Create a transform group for the lake
        TransformGroup lakeTG = new TransformGroup();
        lakeTG.setTransform(combinedTransform);
        lakeTG.addChild(cylinder);

        return lakeTG;
    }

}
