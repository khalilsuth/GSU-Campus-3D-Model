package gsu;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;


public class Tree {

        public TransformGroup createTree(float x, float y, float z, float scale) {
            TransformGroup treeTG = new TransformGroup();

            // Create the trunk
            Cylinder trunk = createTrunk();
            Transform3D trunkTransform = new Transform3D();
            trunkTransform.setTranslation(new Vector3f(x, y, z));
            trunkTransform.setScale(scale);
            TransformGroup trunkTG = new TransformGroup(trunkTransform);
            trunkTG.addChild(trunk);
            treeTG.addChild(trunkTG);

            // Create the foliage
            Sphere foliage = createFoliage();
            Transform3D foliageTransform = new Transform3D();
            foliageTransform.setTranslation(new Vector3f(x, y + 0.7f * scale, z));
            foliageTransform.setScale(scale);
            TransformGroup foliageTG = new TransformGroup(foliageTransform);
            foliageTG.addChild(foliage);
            treeTG.addChild(foliageTG);

            return treeTG;
        }


        private Cylinder createTrunk() {
        float height = 1.0f;
        float radius = 0.1f;
        int xDivision = 20;
        int yDivision = 20;

        Appearance trunkAppearance = new Appearance();

        TextureLoader textureLoader = new TextureLoader("resources/tree_trunk.jpg", null); // Loaded the texture here, not in the main
        Texture texture = textureLoader.getTexture();
        trunkAppearance.setTexture(texture);


        // Change material properties to a brown color
        Material material = new Material();
        material.setAmbientColor(new Color3f(0.5f, 0.25f, 0.1f));
        material.setDiffuseColor(new Color3f(0.5f, 0.25f, 0.1f));
        material.setSpecularColor(new Color3f(0.1f, 0.1f, 0.1f));
        trunkAppearance.setMaterial(material);

        Cylinder trunk = new Cylinder(radius, height, Cylinder.GENERATE_NORMALS | Cylinder.GENERATE_TEXTURE_COORDS, xDivision, yDivision, trunkAppearance);
        return trunk;
    }


    private Sphere createFoliage() {
        float radius = 0.3f;
        int xDivision = 20;
        int yDivision = 20;

        Appearance foliageAppearance = new Appearance();

        // Load the texture
        TextureLoader textureLoader = new TextureLoader("resources/leaves_texture.jpg", null);
        Texture texture = textureLoader.getTexture();
        foliageAppearance.setTexture(texture);

        // Set the texture attributes
        TextureAttributes textureAttributes = new TextureAttributes();
        textureAttributes.setTextureMode(TextureAttributes.MODULATE);
        foliageAppearance.setTextureAttributes(textureAttributes);



        ColoringAttributes coloringAttributes = new ColoringAttributes(new Color3f(0.0f, 1.0f, 0.0f), ColoringAttributes.NICEST);
        foliageAppearance.setColoringAttributes(coloringAttributes);

        Sphere foliage = new Sphere(radius, Sphere.GENERATE_NORMALS, foliageAppearance);
        return foliage;
    }

}
