package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.*;

public class greenscreenTests {
    @Test
    public void test_greenScreenCustom() {
        Image originalImg = new Image("resources/lc.png");
        Image expectedImg = new Image("resources/lcexpected.png");
        Image backImg = new Image("resources/stamp.png");
        Color backColor = new Color(69, 0, 100);
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImg = t.greenScreen(backColor, backImg);
        assertEquals(outputImg, expectedImg);
    }

    @Test
    public void test_greenScreenClapper() {
        Image originalImg = new Image("resources/clapper-green.jpg");
        Image expectedImg = new Image("resources/tests/clapper302003.png");
        Image backImg = new Image("resources/302003.jpg");
        Color backColor = new Color(13, 190, 14);
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImg = t.greenScreen(backColor, backImg);
        assertEquals(outputImg, expectedImg);
    }

    @Test
    public void test_greenScreenAllGreen() {
        Image originalImg = new Image("resources/green5x5.png");
        Image expectedImg = new Image("resources/expectedBlack.png");
        Image backImg = new Image("resources/stamp.png");
        Color backColor = new Color(13, 190, 14);
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImg = t.greenScreen(backColor, backImg);
        assertEquals(outputImg, expectedImg);
    }

    @Test
    public void test_greenScreenNoGreen() {
        Image originalImg = new Image("resources/green5x5.png");
        Image expectedImg = new Image("resources/green5x5.png");
        Image backImg = new Image("resources/stamp.png");
        Color backColor = new Color(15, 190, 14);
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImg = t.greenScreen(backColor, backImg);
        assertEquals(outputImg, expectedImg);
    }

}
