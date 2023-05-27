package gsu;

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;

public class Grass {
    public static TransformGroup createGrass(float width, float length, float x, float y, float z, String textureFilePath) {
        TransformGroup tg = new TransformGroup();

        // Create a TriangleStripArray to represent the grass plane
        int[] stripCounts = {4};
        TriangleStripArray grassStrip = new TriangleStripArray(4, GeometryArray.COORDINATES | GeometryArray.TEXTURE_COORDINATE_2 | GeometryArray.NORMALS, stripCounts);

        // Set the grass quad coordinates
        float halfWidth = width / 2;
        float halfLength = length / 2;
        grassStrip.setCoordinate(0, new Point3f(-halfWidth, y, -halfLength));
        grassStrip.setCoordinate(1, new Point3f(-halfWidth, y, halfLength));
        grassStrip.setCoordinate(2, new Point3f(halfWidth, y, -halfLength));
        grassStrip.setCoordinate(3, new Point3f(halfWidth, y, halfLength));

        // Set the grass texture coordinates
        grassStrip.setTextureCoordinate(0, 0, new TexCoord2f(0.0f, 0.0f));
        grassStrip.setTextureCoordinate(0, 1, new TexCoord2f(0.0f, 1.0f));
        grassStrip.setTextureCoordinate(0, 2, new TexCoord2f(1.0f, 0.0f));
        grassStrip.setTextureCoordinate(0, 3, new TexCoord2f(1.0f, 1.0f));

        // Set the grass normals
        Vector3f normal = new Vector3f(0.0f, 1.0f, 0.0f);
        grassStrip.setNormal(0, normal);
        grassStrip.setNormal(1, normal);
        grassStrip.setNormal(2, normal);
        grassStrip.setNormal(3, normal);

        // Load the grass texture
        TextureLoader textureLoader = new TextureLoader("resources/grass_texture.jpg", null);

        Texture texture = textureLoader.getTexture();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);

        // Create a texture attributes object to configure how the texture is rendered
        TextureAttributes textureAttributes = new TextureAttributes();
        textureAttributes.setTextureMode(TextureAttributes.MODULATE);

        // Create a new appearance for the grass, and set the texture and texture attributes
        Appearance grassAppearance = new Appearance();
        grassAppearance.setTexture(texture);
        grassAppearance.setTextureAttributes(textureAttributes);

        // Create a translation transform to position the grass at (x, y, z)
        Transform3D transform = new Transform3D();
        transform.setTranslation(new Vector3f(x, 0, z));

        // Add the grass to the transform group
        tg.setTransform(transform);
        tg.addChild(new Shape3D(grassStrip, grassAppearance));

        return tg;
    }

}
