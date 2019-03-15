package Entities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TextFilePaths {

    private static HashMap<Integer, List<String>> LevelOneTextFilePaths;
    private static HashMap<Integer, List<String>> LevelTwoTextFilePaths;
    private static HashMap<Integer, List<String>> LevelThreeTextFilePaths;
    static {
        LevelOneTextFilePaths = new HashMap<Integer, List<String>>();
        //Sylvia Text Box file paths
        LevelOneTextFilePaths.put(1, Arrays.asList(
                "Sprites/Objects/Text/Level One Text/Text 1/Level1Text1.atlas",
                "Sprites/Objects/Text/Level One Text/Text 2/Level1Text2.atlas",
                "Sprites/Objects/Text/Level One Text/Text 3/Level1Text3.atlas",
                "Sprites/Objects/Text/Level One Text/Text 4/Level1Text4.atlas",
                "Sprites/Objects/Text/Level One Text/Text 5/Level1Text5.atlas"));
        LevelTwoTextFilePaths = new HashMap<Integer, List<String>>();
        //Level Two Character One Test
        LevelTwoTextFilePaths.put(1, Arrays.asList(
                "Sprites/Objects/Text/Level Two Text/Text 1/Level2Text1.atlas"));
        LevelTwoTextFilePaths.put(2, Arrays.asList(
                "Sprites/Objects/Text/Level Two Text/Text 2/Level2Text2.atlas"));
        LevelTwoTextFilePaths.put(3, Arrays.asList(
                "Sprites/Objects/Text/Level Two Text/Text 3/Level2Text3.atlas",
                "Sprites/Objects/Text/Level Two Text/Text 4/Level2Text4.atlas"));
        LevelTwoTextFilePaths.put(4, Arrays.asList(
                "Sprites/Objects/Text/Level Two Text/Text 5/Level2Text5.atlas"));
        LevelTwoTextFilePaths.put(5, Arrays.asList(
                "Sprites/Objects/Text/Level Two Text/Text 6/Level2Text6.atlas"));
        LevelTwoTextFilePaths.put(6, Arrays.asList(
                "Sprites/Objects/Text/Level Two Text/Text 7/Level2Text7.atlas"));
        LevelTwoTextFilePaths.put(7, Arrays.asList(
                "Sprites/Objects/Text/Level Two Text/Text 8/Level2Text8.atlas",
                "Sprites/Objects/Text/Level Two Text/Text 9/Level2Text9.atlas"));
        LevelTwoTextFilePaths.put(8, Arrays.asList(
                "Sprites/Objects/Text/Level Two Text/Text 10/Level2Text10.atlas",
                "Sprites/Objects/Text/Level Two Text/Text 11/Level2Text11.atlas"));
        LevelTwoTextFilePaths.put(9, Arrays.asList(
                "Sprites/Objects/Text/Level Two Text/Text 12/Level2Text12.atlas"));
        LevelTwoTextFilePaths.put(10, Arrays.asList(
                "Sprites/Objects/Text/Level Two Text/Text 13/Level2Text13.atlas"));

        // TODO: Test Level Three File Paths for Text
        LevelThreeTextFilePaths = new HashMap<Integer, List<String>>();
        //Level Three Character One Test
        LevelThreeTextFilePaths.put(1, Arrays.asList(
                "Sprites/Objects/Text/Level Three Text/Text 1/Level3Text1.atlas"));
        LevelThreeTextFilePaths.put(2, Arrays.asList(
                "Sprites/Objects/Text/Level Three Text/Text 2/Level3Text2.atlas"));
        LevelThreeTextFilePaths.put(3, Arrays.asList(
                "Sprites/Objects/Text/Level Three Text/Text 3/Level3Text3.atlas"));
        LevelThreeTextFilePaths.put(4, Arrays.asList(
                "Sprites/Objects/Text/Level Three Text/Text 4/Level3Text4.atlas"));
        LevelThreeTextFilePaths.put(5, Arrays.asList(
                "Sprites/Objects/Text/Level Three Text/Text 5/Level3Text5.atlas"));
        LevelThreeTextFilePaths.put(6, Arrays.asList(
                "Sprites/Objects/Text/Level Three Text/Text 6/Level3Text6.atlas"));

    }


    public static List<String> getAtlasPaths(int id, int level){
        switch(level){
            case 1: return LevelOneTextFilePaths.get(id);
            case 2: return LevelTwoTextFilePaths.get(id);
            case 3: return LevelThreeTextFilePaths.get(id);
            default: return null;
        }
    }


}
