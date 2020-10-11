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
        //compare dft results with Matlab's results
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
    public void test_cosSim1() {
        //test different widths
        Image img1 = new Image("resources/cosSim1.jpg");
        Image img2 = new Image("resources/cosSim_test1.jpg");
        double cosSim = cosineSimilarity(img2, img1);
    }
    @Test
    public void test_cosSim2() {
        //test different heights
        Image img1 = new Image("resources/cosSim1.jpg");
        Image img2 = new Image("resources/cosSim_test2.jpg");

        double cosSim2 = cosineSimilarity(img2, img1);
    }
    @Test
    public void test_cosSim_zeros() {
        //test black image
        Image img1 = new Image("resources/cosSim1.jpg");
        Image img2 = new Image("resources/cosSim1.jpg");
        double cosSim = cosineSimilarity(img2, img1);
        if (cosSim > (1 - 1e-7)) {
            System.out.println("Passed");
        } else
            System.out.println("Failed");
    }

    @Test
    public void test_cosSim3() {
        //test black and white image
        Image img1 = new Image("resources/cosSim_black_dot.png");
        Image img2 = new Image("resources/cosSim_white_dot.png");
        double cosSim = cosineSimilarity(img2, img1);
        if (cosSim > 1e-7) {
            System.out.println("Failed");
        } else
            System.out.println("Passed");
    }

    @Test
    public void test_DFT_dif_zeros() {
        //test dft for black image
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
    public void test_dftOutput1() {
        //test equals and hashcode function
        double[][] amp = {{1,1},{0,0}};
        double[][] amp2 = {{1,2},{3,3}};
        double[][] phase = {{0.5,0.5},{0.3,0.3}};
        double[][] phase2 = {{0.6,0.3},{0.5,0.4}};

        DFTOutput dft1 = new DFTOutput(amp, phase);
        DFTOutput dft2 = new DFTOutput(amp, phase);
        DFTOutput dft3 = new DFTOutput(amp, phase2);
        DFTOutput dft4 = new DFTOutput(amp2, phase);
        DFTOutput dft5 = new DFTOutput(amp2, phase2);


        //test object equality
        if (dft1.equals(dft2)) {
            System.out.println("Passed equals");
        } else {
            System.out.println("Failed equals");
        }
        if (dft1.equals(dft3)) {
            System.out.println("Failed inequality");
        } else {
            System.out.println("Passed inequality");
        }
        if (dft1.equals(dft4)) {
            System.out.println("Failed inequality");
        } else {
            System.out.println("Passed inequality");
        }
        if (dft1.equals(dft5)) {
            System.out.println("Failed inequality");
        } else {
            System.out.println("Passed inequality");
        }

        //test not dft instance
        if (!(dft1.equals(5))) {
            System.out.println("Passed not dft");
        } else {
            System.out.println("Failed not dft");
        }

        //test hashcode
        System.out.printf("Hashcode: %d", dft1.hashCode());
    }
    @Test
    public void test_dftOutput2() {
        double[][] amp = {{1,1},{0,0}};
        double[][] phase = {{0.5,0.5},{0.3,0.3}};
        double[][] phaseDif = {{0.5,0.5, 0.4},{0.3,0.3, 0.5}};
        double[][] phaseDif2 = {{0.5,0.5},{0.3,0.3}, {0.5,0.6}};
        //test different rows
        DFTOutput dft1 = new DFTOutput(amp, phaseDif2);
    }
    @Test
    public void test_dftOutput3() {
        double[][] amp = {{1,1},{0,0}};
        double[][] phase = {{0.5,0.5},{0.3,0.3}};
        double[][] phaseDif = {{0.5,0.5, 0.4},{0.3,0.3, 0.5}};
        double[][] phaseDif2 = {{0.5,0.5},{0.3,0.3}, {0.5,0.6}};
        //test different columns
        DFTOutput dft2 = new DFTOutput(amp, phaseDif);
    }
}