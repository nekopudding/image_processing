package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import ca.ubc.ece.cpen221.ip.core.ImageProcessingException;
import ca.ubc.ece.cpen221.ip.core.Rectangle;

import java.awt.Color;

import java.util.*;

/**
 * This datatype (or class) provides operations for transforming an image.
 *
 * <p>The operations supported are:
 * <ul>
 *     <li>The {@code ImageTransformer} constructor generates an instance of an image that
 *     we would like to transform;</li>
 *     <li></li>
 * </ul>
 * </p>
 */

public class ImageTransformer {

    private Image image;
    private int width;
    private int height;

    /**
     * Creates an ImageTransformer with an image. The provided image is
     * <strong>never</strong> changed by any of the operations.
     *
     * @param img is not null
     */
    public ImageTransformer(Image img){
        width = img.width();
        height = img.height();
        image = img;
    }

    /**
     * Obtain the grayscale version of the image.
     *
     * @return the grayscale version of the instance.
     */
    public Image grayscale() {
        Image gsImage = new Image(width, height);
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                Color color = image.get(col, row);
                Color gray = Image.toGray(color);
                gsImage.set(col, row, gray);
            }
        }
        return gsImage;
    }

    /**
     * Obtain a version of the image with only the red colours.
     *
     * @return a reds-only version of the instance.
     */
    public Image red() {
        Image redImage = new Image(width, height);
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                int originalPixel = image.getRGB(col, row);
                int alpha = (originalPixel >> 24) & 0xFF;
                int red = (originalPixel >> 16) & 0xFF;
                int desiredColor = (alpha << 24) | (red << 16) | (0 << 8) | (0);
                redImage.setRGB(col, row, desiredColor);
            }
        }
        return redImage;
    }

    /**
     * Returns the mirror image of an instance.
     *
     * @return the mirror image of the instance.
     */
    public Image mirror() {
        // TODO: Implement this method
        return null;
    }

    /**
     * <p>Returns the negative version of an instance.<br />
     * If the colour of a pixel is (r, g, b) then the colours of the same pixel
     * in the negative of the image are (255-r, 255-g, 255-b).</p>
     *
     * @return the negative of the instance.
     */
    public Image negative() {
        // TODO: Implement this method
        return null;
    }

    /**
     * <p>Returns the posterized version of an instance.<br />
     * For each pixel, each colour is analyzed independently to produce a new image as follows:
     * <ul>
     * <li>if the value of the colour is between 0 and 64 (limits inclusive), set it to 32;</li>
     * <li>if the value of the colour is between 65 and 128, set it to 96;</li>
     * <li>if the value of the colour is between 129 and 255, set it to 222.</li>
     * </ul>
     * </p>
     *
     * @return the posterized version of the instance.
     */
    public Image posterize() {
        // TODO: Implement this method
        return null;
    }

    /**
     * Clip the image given a rectangle that represents the region to be retained.
     *
     * @param clippingBox is not null.
     * @return a clipped version of the instance.
     * @throws ImageProcessingException if the clippingBox does not fit completely
     *                                  within the image.
     */
    public Image clip(Rectangle clippingBox) {
        // TODO: Implement this method
        return null;
    }

    /**
     * Denoise an image by replacing each pixel by the median value of that pixel and
     * all its neighbouring pixels. During this process, each colour channel is handled
     * separately.
     *
     * @return a denoised version of the instance.
     */
    public Image denoise() {
        Image denoisedImg = new Image(width, height);
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                float medRed = getMedian(scanNeighbors('r', col, row));
                float medGreen = getMedian(scanNeighbors('g', col, row));
                float medBlue = getMedian(scanNeighbors('b', col, row));

                Color medColor = new Color(medRed, medGreen, medBlue);

                denoisedImg.set(col, row, medColor);
            }
        }

        return denoisedImg;
    }

    /**
     * Helper function that scans neighbouring pixels for color.
     * Creates three int arrays, which store the red, green,
     * and blue values for each neighboring pixel.
     * scanNeighbors will return the color array based off char color.
     *
     * Relies on countNeighbors function to operate properly.
     *
     * @param col is the column (x-axis) position of the pixel to be analyzed.
     *            Must be greater than 0 and less than width.
     *
     * @param row is the row (y-axis) position of the pixel to be analyzed.
     *            Must be greater than 0 and less than height.
     *
     * @param color is either 'r', 'g', or 'b'. scanNeighbors returns
     *              array of red values if 'r', array of green values if
     *              'g', and array of blue values if 'b'.
     *
     * @throws IllegalArgumentException if color char is not 'r','g', or 'b'.
     *
     * @return int array of either red, green, or blue color values for each
     * neighboring pixel (including pixel located by row and col).
     *
     */

    private int[] scanNeighbors(char color, int col, int row) {
        int neighbors = countNeighbors(col, row);
        int[] redVals = new int[neighbors + 1];
        int[] greenVals = new int[neighbors + 1];
        int[] blueVals = new int[neighbors + 1];
        int counter = 0;
        for (int i = -1; i < 2; i++) {
            if (col + i >= 0 && col + i < width) {
                for (int k = -1; k < 2; k++) {
                    if (row + k >= 0 && row + k < height) {
                        Color currColor = image.get(col + i, row + k);
                        redVals[counter] = currColor.getRed();
                        greenVals[counter] = currColor.getGreen();
                        blueVals[counter] = currColor.getBlue();
                        counter++;
                    }
                }
            }
        }
        if (color == 'r') {
            return redVals;
        }
        if (color == 'g') {
            return greenVals;
        }
        if (color == 'b') {
            return blueVals;
        } else {
            throw new IllegalArgumentException("illegal color parameter.");
        }
    }

    /**
     * Helper method getMedian. Takes in an array, sorts it, and then finds the median.
     * If the array has even number of elements, getMedian will take the average of the
     * two middle elements of the array.
     * If the array has odd number of elements, getMedian will return the middle element.
     *
     * @param set is an array of integers. set assumed to not be empty.
     * @return float number representing the median of set.
     */
    private float getMedian(int[] set) {
        Arrays.sort(set);
        if (set.length % 2 == 0) {
            double med = (set[set.length / 2] + set[set.length / 2 - 1]) / 2.0;
            float median = (float) med;

        } else {
            double med = (set[set.length / 2]);
            float median = (float) med;
        }
        return 0;
    }

    /**
     * Helper method countNeighbors. This method takes the column and row number of a pixel,
     * and then checks how many neighbors it will have.
     * <p>
     * If the pixel is on the first or last column or row, the number of neighbors goes to 5.
     * If the pixel is on a corner, the number of neighbors goes to 3.
     * <p>
     * There are some other edge cases accounted for if the height or width of the image
     * is 1.
     *
     * @param col the column number of the pixel. 0 >= col > width.
     * @param row the row number of the pixel. 0 >= row > height.
     * @return the number of neighbors the pixel has.
     */
    private int countNeighbors(int col, int row) {
        int neighbors = 8;
        if (col == 0 || col == width - 1) {
            neighbors -= 3;
            if (width == 1) {
                neighbors -= 3;
            }
        }
        if (row == 0 || row == height - 1) {
            if (neighbors == 5) {
                neighbors -= 2;
            } else if (neighbors == 2) {
                neighbors--;
                if (height == 1) {
                    neighbors--;
                }
            } else {
                neighbors -= 3;
                if (height == 1) {
                    neighbors -= 3;
                }
            }
        }
        return neighbors;
    }

    /**
     * Returns a weathered version of the image by replacing each pixel by the minimum value
     * of that pixel and all its neighbouring pixels. During this process, each colour channel
     * is handled separately.
     *
     * @return a weathered version of the image.
     */
    public Image weather() {
        Image weatheredImg = new Image(width, height);
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                int[] redVals = scanNeighbors('r', col, row);
                Arrays.sort(redVals);
                int minRed = redVals[0];

                int[] greenVals = scanNeighbors('g', col, row);
                Arrays.sort(greenVals);
                int minGreen = greenVals[0];

                int[] blueVals = scanNeighbors('b', col, row);
                Arrays.sort(blueVals);
                int minBlue = blueVals[0];

                Color minColor = new Color(minRed, minGreen, minBlue);

                weatheredImg.set(col, row, minColor);
            }

        }
        return weatheredImg;
    }

    /**
     * Return a block paint version of the instance by treating the image as a
     * sequence of squares of a given size and replacing all pixels in a square
     * by the average value of all pixels in that square.
     * During this process, each colour channel is handled separately.
     *
     * @param blockSize the dimension of the square block, > 1.
     * @return the block paint version of the instance.
     * When the original image is not a perfect multiple of blockSize * blockSize,
     * the bottom rows and right columns are obtained by averaging the pixels that
     * fit the smaller rectangular regions. For example, if we have a 642 x 642 size
     * original image and the block size is 4 x 4 then the bottom two rows will use
     * 2 x 4 blocks, the rightmost two columns will use 4 x 2 blocks, and the
     * bottom-right corner will use a 2 x 2 block.
     */
    public Image blockPaint(int blockSize) {
        Image blockImg = new Image(width, height);
        for (int col = 0; col <= width - blockSize; col += blockSize){
            for (int row = 0; row <= height - blockSize; row += blockSize){
                Color averageColor = getColorBlock(col, row, blockSize, blockSize);
                for (int blockCol = col; blockCol < col + blockSize; blockCol++){
                    for (int blockRow = row; blockRow < row + blockSize; blockRow++){
                        blockImg.set(blockCol, blockRow, averageColor);
                    }
                }
            }
        }
        int remainderCols = width % blockSize;
        int remainderRows = height % blockSize;
        int remainderCol = width - remainderCols;
        int remainderRow = height - remainderRows;
        for (int row = 0; row <= height - blockSize; row += blockSize){
            Color averageColor = getColorBlock(remainderCol, row, remainderCols, blockSize);
            for (int blockCol = remainderCol; blockCol < width; blockCol++){
                for (int blockRow = row; blockRow < height; blockRow++){
                    blockImg.set(blockCol, blockRow, averageColor);
                }
            }
        }
        for (int col = 0; col <= width - blockSize; col += blockSize){
            Color averageColor = getColorBlock(col, remainderRow, blockSize, remainderRows);
            for (int blockRow = remainderRow; blockRow < height; blockRow++){
                for (int blockCol = col; blockCol < col + blockSize; blockCol++){
                    blockImg.set(blockCol, blockRow, averageColor);
                }
            }
        }
        Color averageColor = getColorBlock(remainderCol, remainderRow, remainderCols, remainderRows);
        for (int lastCol = remainderCol; lastCol < width; lastCol++){
            for (int lastRow = remainderRow; lastRow < height; lastRow++){
                blockImg.set(lastCol, lastRow, averageColor);
            }
        }

        return blockImg;
    }
    public static void main(String[] args){
        Image originalImg = new Image("resources/95006.jpg");
        Image expectedImg = new Image("resources/tests/95006-seurat-4x4.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.blockPaint(4);
        outputImage.show();
        expectedImg.show();
    }

    /**
     * Helper method for blockPaint. Method will take the starting row and column
     * of a block, as well as the block size, and will check all pixels in that box.
     * Method will take in the RGB values of all pixels, and then average them (each
     * color separately). This method will then return a Color object with the average
     * RGB values.
     * 
     * @param col this is the column number where the specified block is starting.
     *            Requirement: 0 <= col <= width - colSize.
     * @param row this is the row number where the specified block is starting.
     *            Requirement: 0 <= row <= height - rowSize.
     * @param colSize this is the width of the block. Usually the box will be a square, but
     *                in the edges, the block will have odd dimensions.
     *                Requirement: 0 < colSize < width
     * @param rowSize this is the height of the block.
     *                Requirement: 0 < rowSize < height
     * @return a Color object that has the average values of Red in the block, average
     * values of Blue in the block, and average values of Green in the block.
     *
     */
    private Color getColorBlock(int col, int row, int colSize, int rowSize){
        int[] redVals = new int[colSize*rowSize];
        int[] greenVals = new int[colSize*rowSize];
        int[] blueVals = new int[colSize*rowSize];
        int counter = 0;
        for (int blockCol = col; blockCol < col + colSize - 1; blockCol++){
            for (int blockRow = row; blockRow < row + rowSize - 1; blockRow++){
                Color currColor = image.get(blockCol, blockRow);
                redVals[counter] = currColor.getRed();
                greenVals[counter] = currColor.getGreen();
                blueVals[counter] = currColor.getBlue();
                counter++;
            }
        }
        int averageRed = findAverageArray(redVals);
        int averageGreen = findAverageArray(greenVals);
        int averageBlue = findAverageArray(blueVals);
        Color averageColor = new Color(averageRed, averageGreen, averageBlue);
        return averageColor;
    }

    /**
     * Helper method to find average value of an array.
     *
     * @param array is the array to be analyzed. Array must be of ints, and must
     *              not be empty.
     *
     * @return average is a number of float datatype that is equal to the sum
     * of the elements of the array, divided by the number of elements in the array.
     *
     */
    private int findAverageArray(int[] array){
        int sum = 0;
        int numElements = array.length;
        for (int i = 0; i < numElements; i++){
            sum += array[i];
        }
        return sum/numElements;
    }

    /**
     * Rotate an image by the given angle (degrees) about the centre of the image.
     * The centre of an image is the pixel at (width/2, height/2). The new regions
     * that may be created are given the colour white (<code>#ffffff</code>) with
     * maximum transparency (alpha = 255).
     *
     * @param degrees the angle to rotate the image by, 0 <= degrees <= 360.
     * @return a rotate version of the instance.
     */

    /*
    the implementation requires x and y to be centered, which is col - (width/2) and row - (height/2)
    x*cos(angle) + y*sin(angle) + oriWidth/2
    new width = hypotenuse * cos(rotation_angle - original angle)
    - the width and height of new image is currently offset
    */
    public Image rotate(double degrees) {
        // TODO: Implement this method
        int original_width = width;
        int original_height = height;
        Image original_image = image;


        int new_width = (int) (width * Math.cos(degrees * Math.PI / 180) +
                height * Math.sin(degrees * Math.PI / 180));
        int new_height = (int) (width * Math.sin(degrees * Math.PI / 180) +
                height * Math.cos(degrees * Math.PI / 180));
        Image outImage = new Image(new_width, new_height);


        for (int col = 0; col < new_width; col++) {
            for (int row = 0; row < new_height; row++) {
                int original_x = (int) ((col - width / 2) * Math.cos(degrees * Math.PI / 180) +
                        (row - new_height / 2) * Math.sin(degrees * Math.PI / 180) + original_width / 2);
                int original_y = (int) (-(col - width / 2) * Math.sin(degrees * Math.PI / 180) +
                        (row - height / 2) * Math.cos(degrees * Math.PI / 180) + original_height / 2 );
                if (original_x >= 0 && original_y >= 0 &&
                        original_x < original_width &&
                        original_y < original_height) {
                    outImage.set(col, row, original_image.get(original_x, original_y));
                } else {
                    outImage.set(col, row, new Color(255,255,255));
                }
            }
        }
        /*outImage.show();
        while(width > 0) {
        }*/
        return outImage;
    }

    /**
     * Compute the discrete Fourier transform of the image and return the
     * amplitude and phase matrices as a DFTOutput instance.
     *
     * @return the amplitude and phase of the DFT of the instance.
     */
    public DFTOutput dft() {
        // TODO: Implement this method
        return null;
    }

    /**
     * Replaces a background screen with a provided image.
     * <p>
     * This operation identifies the largest connected region of the image that matches
     * <code>screenColour</code> exactly. This operation determines a rectangle that bounds
     * the "green screen" region and overlays the <code>backgroundImage</code> over that
     * rectangle by aligning the top-left corner of the image with the top-left corner of the
     * rectangle. After determining the screen region, all pixels in that region matching
     * <code>screenColour</code> are replaced with corresponding pixels from
     * <code>backgroundImage</code>.
     * <p>
     * If <code>backgroundImage</code> is smaller
     * than the screen then the image is tiled over the screen.
     *
     * @param screenColour    the colour of the background screen, is not null
     * @param backgroundImage the image to replace the screen with, is not null
     * @return an image with provided image replacing the background screen
     * of the specified colour, tiling the screen with the background image if the
     * background image is smaller than the screen size.
     */
    public Image greenScreen(Color screenColour, Image backgroundImage) {
        // TODO: Implement this method
        return null;
    }

    /**
     * Align (appropriately rotate) an image of text that was improperly aligned.
     * This transformation can work properly only with text images.
     *
     * @return the aligned image.
     */
    public Image alignTextImage() {
        // TODO: Implement this method
        return null;
    }
}
