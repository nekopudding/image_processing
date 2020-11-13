package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;

public class Level6Tests {
    @Test(timeout = 2000)
    /*
        This test does not really test anything.
        It is only an indicator for further manual testing.
    */
    public void test_Align() throws Exception {

        Method aligner = null;
        try {
            aligner = ImageTransformer.class.getMethod("alignTextImage");
        }
        catch (Exception e) {
            fail("Method not defined");
        }

        try {
            Image im = new Image("resources/tests/stripes.png");
            ImageTransformer t = new ImageTransformer(im);
            assertTrue(aligner.invoke(t) != null);
        }
        catch (Exception e) {
            fail("Method invocation error");
        }
    }

}
