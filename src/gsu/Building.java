package gsu;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;

public class Building {

    public static TransformGroup createHorizontalBuilding(float width, float height, float depth, float x, float y, float z) {
        TransformGroup tg = new TransformGroup();

        // Set the appearance of the building (e.g., color, texture, etc.)
        Appearance appearance = new Appearance();
        Material material = new Material();
        material.setAmbientColor(new Color3f(0.8f, 0.6f, 0.3f));
        material.setDiffuseColor(new Color3f(0.8f, 0.6f, 0.3f));
        appearance.setMaterial(material);

        // Load the texture from the file
        String textureFilename = "resources/horizontal_texture.jpg";
        TextureLoader textureLoader = new TextureLoader(textureFilename, null);
        Texture texture = textureLoader.getTexture();
        appearance.setTexture(texture);

        // Create a box with the specified dimensions and the appearance
        Box building = new Box(width, height, depth, Box.GENERATE_TEXTURE_COORDS | Box.GENERATE_NORMALS, appearance);

        // Create a translation transform to position the building at (x, y, z)
        Transform3D transform = new Transform3D();
        transform.setTranslation(new Vector3f(x, y, z));

        // Add the building to the transform group
        tg.setTransform(transform);
        tg.addChild(building);

        return tg;
    }



    public static TransformGroup createBuilding(float size, float x, float y, float z) {
        TransformGroup tg = new TransformGroup();

        // Create a cube with the specified size
        Box building = new Box(size, size, size, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS, null);

        // Set the appearance of the building (e.g., color, texture, etc.)
        Appearance appearance = new Appearance();

        // Load the texture
        TextureLoader textureLoader = new TextureLoader("resources/building_texture.jpg", null);
        Texture texture = textureLoader.getTexture();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        appearance.setTexture(texture);

        // Set the texture attributes
        TextureAttributes textureAttributes = new TextureAttributes();
        textureAttributes.setTextureMode(TextureAttributes.MODULATE);
        appearance.setTextureAttributes(textureAttributes);

        // Set the base color to a lighter brown using the Material object
        Material material = new Material();
        material.setAmbientColor(new Color3f(0.8f, 0.6f, 0.3f));
        material.setDiffuseColor(new Color3f(0.8f, 0.6f, 0.3f));
        appearance.setMaterial(material);


        building.setAppearance(appearance);

        // Create a translation transform to position the building at (x, y, z)
        Transform3D transform = new Transform3D();
        transform.setTranslation(new Vector3f(x, y, z));

        // Add the building to the transform group
        tg.setTransform(transform);
        tg.addChild(building);

        return tg;
    }
}
