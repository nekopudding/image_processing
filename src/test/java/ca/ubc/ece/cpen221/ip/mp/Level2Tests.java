package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.Test;

import static org.junit.Assert.*;

public class Level2Tests {

    @Test
    public void test_Weathering() {
        Image originalImg = new Image("resources/95006.jpg");
        Image expectedImg = new Image("resources/tests/95006-weathered.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.weather();
        assertEquals(expectedImg, outputImage);
    }

    @Test
    public void test_BlockPaint_4x4() {
        Image originalImg = new Image("resources/95006.jpg");
        Image expectedImg = new Image("resources/tests/95006-seurat-4x4.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.blockPaint(4);
        assertEquals(expectedImg, outputImage);
    }

    @Test
    public void test_BlockPaint_3x3() {
        Image originalImg = new Image("resources/216053.jpg");
        Image expectedImg = new Image("resources/tests/216053-seurat-3x3.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.blockPaint(3);
        assertEquals(expectedImg, outputImage);
    }
}
