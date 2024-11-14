
import java.util.*;


public class AnimationManager {

    private final Map<Integer, String> animationMap = new HashMap<>();

    public AnimationManager() {
        // Populate the map with animation paths
        animationMap.put(5, "animation/animation1.gif");
        animationMap.put(6, "animation/animation2.gif");
        animationMap.put(7, "animation/animation3.gif");
        animationMap.put(8, "animation/animation4.gif");
        animationMap.put(9, "animation/animation5.gif");
        animationMap.put(10, "animation/animation6.gif");

        // Add more mappings as needed
    }

    public String getAnimationPath(int index) {
        return animationMap.getOrDefault(index, null);
    }
}
