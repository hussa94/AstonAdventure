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
                "Sprites/Objects/Text/Text 1/Level1Text1.atlas",
                "Sprites/Objects/Text/Text 2/Level1Text2.atlas",
                "Sprites/Objects/Text/Text 3/Level1Text3.atlas",
                "Sprites/Objects/Text/Text 4/Level1Text4.atlas",
                "Sprites/Objects/Text/Text 5/Level1Text5.atlas"));

        LevelTwoTextFilePaths = new HashMap<Integer, List<String>>();
        //Level Two Character One Test
        LevelTwoTextFilePaths.put(1, Arrays.asList(
                "Sprites/Objects/Text/Text 1/Level1Text1.atlas",
                "Sprites/Objects/Text/Text 2/Level1Text2.atlas",
                "Sprites/Objects/Text/Text 3/Level1Text3.atlas",
                "Sprites/Objects/Text/Text 4/Level1Text4.atlas",
                "Sprites/Objects/Text/Text 5/Level1Text5.atlas"));

        // TODO: Add Level Three File Paths for Text
        LevelThreeTextFilePaths = new HashMap<Integer, List<String>>();
        //Level Three Character One Test
        LevelThreeTextFilePaths.put(1, Arrays.asList(
                "Sprites/Objects/Text/Text 1/Level1Text1.atlas",
                "Sprites/Objects/Text/Text 2/Level1Text2.atlas",
                "Sprites/Objects/Text/Text 3/Level1Text3.atlas",
                "Sprites/Objects/Text/Text 4/Level1Text4.atlas",
                "Sprites/Objects/Text/Text 5/Level1Text5.atlas"));

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
