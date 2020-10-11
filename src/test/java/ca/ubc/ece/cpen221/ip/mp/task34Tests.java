package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.DoubleMatrix;
import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.Test;

import java.awt.*;

import static ca.ubc.ece.cpen221.ip.mp.ImageProcessing.cosineSimilarity;
import static org.junit.Assert.assertEquals;

public class task34Tests {
    @Test
    public void test_Rotation() {
        Image originalImg = new Image("resources/12003.jpg");
        Image expectedImg = new Image("resources/tests/12003-r30.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.rotate(30);
        double cosSim = cosineSimilarity(expectedImg, outputImage);
        if (Math.abs(cosSim) > (1 - 1e-7)) {
            System.out.println("Passed");
        } else
            System.out.println("Failed");
    }

    @Test
    public void test_DFT() {
        Image originalImg = new Image("resources/dft3.jpg");
        ImageTransformer t = new ImageTransformer(originalImg);
        DFTOutput out = t.dft();
        DoubleMatrix amp = out.getAmp();
        DoubleMatrix phase = out.getPhase();

        int width = originalImg.width();
        int height = originalImg.height();

        double actualAmp;
        double actualPhase;
        boolean failed = false;

        double[][] expectedAmp = {{510, 10}, {10, 478}};
        double[][] expectedPhase = {{0, 0}, {0, 0}};

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                actualAmp = amp.get(row, col);
                actualPhase = phase.get(row, col);

                if (Math.abs(actualAmp - expectedAmp[row][col]) > 1e-7) {
                    failed = true;
                    System.out.printf("%n Test failed with magnitude at row:%i col: %i", row, col);
                }
                if (Math.abs(actualPhase - expectedPhase[row][col]) > 1e-7) {
                    failed = true;
                    System.out.printf("%n Test failed with phase at row:%i col: %i", row, col);
                }
            }
        }
        if (failed == false) {
            System.out.printf("Passed");
        } else {
            System.out.printf("Failed");
        }
    }
    @Test
    public void test_cosSim_black_dif_dimensions() {
        Image img1 = new Image("resources/cosSim1.jpg");
        Image img2 = new Image("resources/cosSim_test1.jpg");
        double cosSim = cosineSimilarity(img2, img1);
        if (Math.abs(cosSim) > (1 - 1e-7)) {
            System.out.println("Passed");
        } else
            System.out.println("Failed");
    }
    @Test
    public void test_cosSim_zeros() {
        Image img1 = new Image("resources/cosSim1.jpg");
        Image img2 = new Image("resources/cosSim1.jpg");
        double cosSim = cosineSimilarity(img2, img1);
        if (Math.abs(cosSim) > (1 - 1e-7)) {
            System.out.println("Passed");
        } else
            System.out.println("Failed");
    }

    @Test
    public void test_DFT_dif_zeros() {
        Image originalImg = new Image("resources/dft_black.jpg");
        ImageTransformer t = new ImageTransformer(originalImg);
        DFTOutput out = t.dft();
        DoubleMatrix amp = out.getAmp();
        DoubleMatrix phase = out.getPhase();

        int width = originalImg.width();
        int height = originalImg.height();

        double actualAmp;
        double actualPhase;
        boolean failed = false;

        double[][] expectedAmp = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        double[][] expectedPhase = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                actualAmp = amp.get(row, col);
                actualPhase = phase.get(row, col);

                if (Math.abs(actualAmp - expectedAmp[row][col]) > 1e-7) {
                    failed = true;
                    System.out.printf("%n Test failed with magnitude at row:%i col: %i", row, col);
                }
                if (Math.abs(actualPhase - expectedPhase[row][col]) > 1e-7) {
                    failed = true;
                    System.out.printf("%n Test failed with phase at row:%i col: %i", row, col);
                }
            }
        }
        if (failed == false) {
            System.out.printf("Passed");
        } else {
            System.out.printf("Failed");
        }
    }

    @Test
    public void test_dftOutput() {
        double[][] amp = {{1,1},{0,0}};
        double[][] phase = {{0.5,0.5},{0.3,0.3}};

        DFTOutput dft1 = new DFTOutput(amp, phase);
        DFTOutput dft2 = new DFTOutput(amp, phase);

        //test object equality
        if (dft1.equals(dft2)) {
            System.out.println("Passed equals");
        } else {
            System.out.println("Failed equals");
        }

        //test object inequality
        if (!(dft1.equals(5))) {
            System.out.println("Passed inequality");
        } else {
            System.out.println("Failed inequality");
        }

        //test hashcode
        System.out.printf("Hashcode: %d", dft1.hashCode());
    }
}