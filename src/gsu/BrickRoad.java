package gsu;

import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.image.TextureLoader;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BrickRoad {


    private static Geometry createGeometryFromRectangle(Rectangle2D.Float rectangle) {
        QuadArray geometry = new QuadArray(4, QuadArray.COORDINATES | QuadArray.TEXTURE_COORDINATE_2);
        Point3f[] coordinates = new Point3f[]{
                new Point3f(rectangle.x, 0.0f, rectangle.y),
                new Point3f(rectangle.x + rectangle.width, 0.0f, rectangle.y),
                new Point3f(rectangle.x + rectangle.width, 0.0f, rectangle.y + rectangle.height),
                new Point3f(rectangle.x, 0.0f, rectangle.y + rectangle.height)
        };
        geometry.setCoordinates(0, coordinates);

        TexCoord2f[] texCoords = new TexCoord2f[]{
                new TexCoord2f(0.0f, 0.0f),
                new TexCoord2f(1.0f, 0.0f),
                new TexCoord2f(1.0f, 1.0f),
                new TexCoord2f(0.0f, 1.0f)
        };
        geometry.setTextureCoordinates(0, 0, texCoords);

        return geometry;
    }

    public static TransformGroup createLargeBrickArea(float width, float length, float x, float y, float z) {
        TransformGroup tg = new TransformGroup();

        // Create a box with the specified dimensions
        Box brickArea = new Box(width / 2, 0.01f, length / 2, Box.GENERATE_TEXTURE_COORDS | Box.GENERATE_NORMALS, null);

        // Set the appearance of the brick area (e.g., color, texture, etc.)
        Appearance appearance = new Appearance();
        Material material = new Material();

        Color3f lightBrown = new Color3f(0.8f, 0.6f, 0.4f); // Lighter brown color
        material.setDiffuseColor(lightBrown);
        material.setAmbientColor(lightBrown);

        appearance.setMaterial(material);

        // Set the appearance of the brick area
        brickArea.setAppearance(appearance);

        // Create a translation transform to position the brick area at (x, y, z)
        Transform3D transform = new Transform3D();
        transform.setTranslation(new Vector3f(x, y, z));

        // Add the brick area to the transform group
        tg.setTransform(transform);
        tg.addChild(brickArea);

        return tg;
    }







}
