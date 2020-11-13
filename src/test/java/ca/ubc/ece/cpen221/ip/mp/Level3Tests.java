package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.Test;

import static org.junit.Assert.*;

public class Level3Tests {

    @Test
    public void test_Rotate180() throws Exception {
        Image originalImg = new Image("resources/tests/0001.png");
        Image expectedImg = new Image("resources/tests/0001-rotate-180.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.rotate(180);
        assertTrue(TestUtils.rotationMatch(expectedImg, outputImage, 0.96));
    }

    @Test
    public void test_Rotate90() throws Exception {
        Image originalImg = new Image("resources/tests/0001.png");
        Image expectedImg = new Image("resources/tests/0001-rotate-90.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.rotate(270);
        assertTrue(TestUtils.rotationMatch(expectedImg, outputImage, 0.96));
    }

    @Test
    public void test_Rotate25() throws Exception {
        Image originalImg = new Image("resources/tests/0001.png");
        Image expectedImg = new Image("resources/tests/0001-rotate-25.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.rotate(25);
        assertTrue(TestUtils.rotationMatch(expectedImg, outputImage, 0.96));
    }

    @Test
    public void test_Rotate360() throws Exception {
        Image originalImg = new Image("resources/tests/0001.png");
        Image expectedImg = new Image("resources/tests/0001.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.rotate(360);
        assertEquals(expectedImg, outputImage);
    }


    @Test
    public void test_CosineSimilarityIdentity() throws Exception {
        Image im = new Image("resources/tests/0001.png");
        double output = ImageProcessing.cosineSimilarity(im, im);
        assertEquals(1.0, output, 0.0001);
    }

    @Test
    public void test_CosineSimilarity() throws Exception {
        Image im1 = new Image("resources/tests/0001.png");
        Image im2 = new Image("resources/tests/0002.png");
        double expected = 0.8369;
        double output = ImageProcessing.cosineSimilarity(im1, im2);
        assertEquals(expected, output, 0.0001);
    }

}
