package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class Level5Tests {
    @Test(timeout = 1000)
    public void test_GreenScreen1() throws Exception {
        Image screen = new Image("resources/tests/green_screen.png");
        Image background = new Image("resources/tests/red.png");
        Image expected = new Image("resources/tests/green_screen-green-red.png");

        ImageTransformer t = new ImageTransformer(screen);
        Image output = t.greenScreen(Color.green, background);
        assertEquals(expected, output);
    }

    @Test(timeout = 1000)
    public void test_GreenScreen2() throws Exception {
        Image screen = new Image("resources/tests/green_screen.png");
        Image background = new Image("resources/tests/red.png");
        Image expected = new Image("resources/tests/green_screen-black-red.png");

        ImageTransformer t = new ImageTransformer(screen);
        Image output = t.greenScreen(Color.black, background);
        assertEquals(expected, output);
    }

    @Test(timeout = 1000)
    public void test_GreenScreen3() throws Exception {
        Image screen = new Image("resources/tests/green_screen.png");
        Image background = new Image("resources/tests/0001.png");
        Image expected = new Image("resources/tests/green_screen-green-0001.png");

        ImageTransformer t = new ImageTransformer(screen);
        Image output = t.greenScreen(Color.green, background);
        assertEquals(expected, output);
    }

    @Test(timeout = 5000)
    public void test_GreenScreen4() throws Exception {
        Image screen = new Image("resources/tests/voronoi.png");
        Image background = new Image("resources/tests/0001.png");
        Image expected = new Image("resources/tests/voronoi-0001.png");

        ImageTransformer t = new ImageTransformer(screen);
        Image output = t.greenScreen(Color.white, background);
        output.save("resources/tests/voronoi-0001.png");
        assertEquals(expected, output);
    }
}
