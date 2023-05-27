package gsu;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;

public class Main {
    public static void main(String[] args) {
        SimpleUniverse universe = new SimpleUniverse();
        BranchGroup root = createScene(universe);

        // Adjust the viewing platform
        ViewingPlatform viewingPlatform = universe.getViewingPlatform();
        TransformGroup viewTransformGroup = viewingPlatform.getViewPlatformTransform();
        Transform3D viewTransform = new Transform3D();
        viewTransformGroup.getTransform(viewTransform);
        viewTransform.lookAt(new Point3d(-2.0, 4.0, 8.0), new Point3d(0, -1, -5), new Vector3d(0, 1, 0));
        viewTransform.invert();
        viewTransformGroup.setTransform(viewTransform);

        universe.addBranchGraph(root);
    }


    private static BranchGroup createScene(SimpleUniverse universe) {
        BranchGroup root = new BranchGroup();

        // Add a background to the scene
        // Load the sunset texture
        String sunsetFilename = "resources/sunset2.jpg";
        TextureLoader textureLoader = new TextureLoader(sunsetFilename, null);
        ImageComponent2D image = textureLoader.getImage();

        // Create a textured background
        Background background = new Background();
        background.setImage(image);
        background.setImageScaleMode(Background.SCALE_FIT_MAX);
        background.setApplicationBounds(new BoundingSphere(new Point3d(0, 0, 0), 100));
        root.addChild(background);

        // Add building 1 to the scene (building to the far right)
        float building1Size = 0.8f;
        float building1X = 4.0f;
        float building1Y = building1Size + 0.7f;
        float building1Z = -5.0f;
        TransformGroup building1TG = Building.createBuilding(building1Size, building1X, building1Y, building1Z);
        root.addChild(building1TG);

        // Add building 2 to the scene
        float building2Size = 1.2f;
        float building2X = 1.5f;
        float building2Y = building2Size + 0.8f;
        float building2Z = building1Z;
        TransformGroup building2TG = Building.createBuilding(building2Size, building2X, building2Y, building2Z);
        root.addChild(building2TG);

        // Add building 3 to the scene (horizontal building)
        float building3Width = 1.5f; // Increase the width for more horizontality
        float building3Height = 0.6f;
        float building3Depth = 1.2f;
        float building3X = -3.8f; // Decrease the X value to move the building further to the left
        float building3Y = building3Height + 0.8f;
        float building3Z = building1Z;
        TransformGroup building3TG = Building.createHorizontalBuilding(building3Width, building3Height, building3Depth, building3X, building3Y, building3Z);
        root.addChild(building3TG);

        // Add building 4 to the scene (building behind building 2)
        float building4Size = 1.0f;
        float building4X = building2X;
        float building4Y = building4Size + 0.7f;
        float building4Z = building2Z - 6.0f; // Adjust this value to move the building further back or closer
        TransformGroup building4TG = Building.createBuilding(building4Size, building4X, building4Y, building4Z);
        root.addChild(building4TG);

        // Add building 5 to the scene (building behind building 3)
        float building5Size = 1.0f;
        float building5X = building3X;
        float building5Y = building5Size + 0.7f;
        float building5Z = building3Z - 3.0f; // Increase the subtraction value to move the building further back
        TransformGroup building5TG = Building.createBuilding(building5Size, building5X, building5Y, building5Z);
        root.addChild(building5TG);

        // Add a lake to the scene
        TransformGroup lakeTG = Lake.createLake(2.0f, 0.01f, 3.0f, -1.0f, -4.0f);
        root.addChild(lakeTG);
        // Add grass around the lake
        // Position the grass to the left side of the lake
        float grassWidth = 20.0f; // Increase the width
        float grassLength = 20.0f; // Increase the length
        float grassX = -1.0f;
        float grassY = -1.01f; // Slightly below the lake's y-value to avoid Z-fighting
        float grassZ = -0.5f;

        // Add a road to the scene
        float roadWidth = 0.5f;
        float roadLength = 20.0f; // Increase the length to match the grass
        float roadThickness = 0.01f;
        float roadX = -1.0f;
        float roadY = grassY + 0.01f; // Slightly above the grass's y-value to avoid Z-fighting
        float roadZ = -grassLength / 2; // Align with the grass starting point above the lake

//        // Add a brick road to the scene
//        float brickRoadWidth = 0.6f; // Decrease this value to make the road narrower
//        float brickRoadLength = Math.abs(building2X - building3X) - building2Size - building3Width - 0.5f; // Decrease this value to make the road shorter
//        float brickRoadThickness = 0.01f;
//        float brickRoadX = (building2X + building3X) / 2 - 0.3f; // Decrease this value to move the brick road to the left
//        float brickRoadY = 2.31f; // Increase this value to move the brick road up
//        float brickRoadZ = grassZ;

        // Add a large brick area under the buildings
        float minZ = Math.min(building1Z, Math.min(building2Z, building3Z));
        float brickAreaWidth = 20.0f;
        float brickAreaLength = 8.75f;
        float brickAreaX = (building1X + building3X) / 2;
        float brickAreaY = 1.0f;
        // Align the brick area above the starting point of the road
        // and under the building's z value
        float brickAreaZ = roadZ + (minZ - roadZ) / 2;

        TransformGroup brickAreaTG = BrickRoad.createLargeBrickArea(brickAreaWidth, brickAreaLength, brickAreaX, brickAreaY, brickAreaZ);
        root.addChild(brickAreaTG);

//        TransformGroup brickRoadTG = BrickRoad.createBrickRoad(brickRoadWidth, brickRoadLength, brickRoadThickness, brickRoadX, brickRoadY, brickRoadZ, "resources/brick_texture.jpg");
//        root.addChild(brickRoadTG);

        TransformGroup roadTG = Road.createRoad(roadWidth, roadLength, roadThickness, roadX, roadY, roadZ);
        root.addChild(roadTG);

        TransformGroup grassTG = Grass.createGrass(grassWidth, grassLength, grassX, grassY, grassZ, "grass_texture.jpg");
        root.addChild(grassTG);

        // Add a tree to the scene
        Tree treeBuilder = new Tree();
        TransformGroup tree1TG = treeBuilder.createTree(-3.0f, -1.0f, -4.0f, 1.0f);
        TransformGroup tree2TG = treeBuilder.createTree(-4.0f, -1.0f, -5.0f, 1.5f);
        TransformGroup tree3TG = treeBuilder.createTree(-5.0f, -1.0f, -3.0f, 2.0f);

        root.addChild(tree1TG);
        root.addChild(tree2TG);
        root.addChild(tree3TG);

        // Add a group of trees to the right side of the lake
        //TransformGroup tree4TG = treeBuilder.createTree(2.0f, -0.5f, -3.0f, 1.0f); // Move this tree up
        //TransformGroup tree5TG = treeBuilder.createTree(3.0f, -1.5f, -2.0f, 1.2f); // Move this tree down
        TransformGroup tree6TG = treeBuilder.createTree(3.5f, 1.6f, -2.5f, 1.8f);
        TransformGroup tree7TG = treeBuilder.createTree(4.5f, -0.5f, -2.5f, 1.8f);

        //root.addChild(tree4TG);
        //root.addChild(tree5TG);
        root.addChild(tree6TG);
        root.addChild(tree7TG);


        // Create the canvas
        Canvas3D canvas = universe.getCanvas();
        canvas.setSize(800, 600);

        // Add lighting to the scene
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        DirectionalLight light = new DirectionalLight(new Color3f(1.0f, 1.0f, 1.0f), new Vector3f(-0.5f, -0.5f, -0.5f));
        light.setInfluencingBounds(bounds);
        root.addChild(light);

        return root;
    }

}
