package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import ca.ubc.ece.cpen221.ip.core.ImageProcessingException;
import ca.ubc.ece.cpen221.ip.core.Rectangle;

import javax.imageio.ImageIO;
import java.awt.Color;

import java.io.File;
import java.sql.SQLOutput;
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
    public ImageTransformer(Image img) {
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
        Image mirImage = new Image(width, height);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < Math.ceil(width / 2.0); col++) {
                mirImage.setRGB(col, row, image.getRGB(width - col - 1, row));
                mirImage.setRGB(width - col - 1, row, image.getRGB(col, row));
            }
        }
        return mirImage;
    }

    /**
     * <p>Returns the negative version of an instance.<br />
     * If the colour of a pixel is (r, g, b) then the colours of the same pixel
     * in the negative of the image are (255-r, 255-g, 255-b).</p>
     *
     * @return the negative of the instance.
     */
    public Image negative() {
        Image negImage = new Image(width, height);
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                int originalPixel = image.getRGB(col, row);
                int alpha = (originalPixel >> 24) & 0xFF;
                int red = (originalPixel >> 16) & 0xFF;
                int blue = (originalPixel >> 8) & 0xFF;
                int green = originalPixel & 0xFF;
                int desiredColor =
                    (alpha << 24) | ((255 - red) << 16) | ((255 - blue) << 8) | (255 - green);
                negImage.setRGB(col, row, desiredColor);
            }
        }
        return negImage;
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
        Image posterImage = new Image(width, height);
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                int originalPixel = image.getRGB(col, row);
                int alpha = (originalPixel >> 24) & 0xFF;
                int red = (originalPixel >> 16) & 0xFF;
                int blue = (originalPixel >> 8) & 0xFF;
                int green = originalPixel & 0xFF;

                if (red <= 64) {
                    red = 32;
                } else if (red <= 128) {
                    red = 96;
                } else {
                    red = 222;
                }

                if (blue <= 64) {
                    blue = 32;
                } else if (blue <= 128) {
                    blue = 96;
                } else {
                    blue = 222;
                }

                if (green <= 64) {
                    green = 32;
                } else if (green <= 128) {
                    green = 96;
                } else {
                    green = 222;
                }
                int desiredColor = (alpha << 24) | (red << 16) | (blue << 8) | (green);
                posterImage.setRGB(col, row, desiredColor);
            }
        }
        return posterImage;
    }

    /**
     * Clip the image given a rectangle that represents the region to be retained.
     *
     * @param clippingBox is not null.
     * @return a clipped version of the instance.
     * @throws ImageProcessingException if the clippingBox does not fit completely
     *                                  within the image.
     */
    public Image clip(Rectangle clippingBox) throws ImageProcessingException {
        if (clippingBox.xBottomRight > width || clippingBox.xTopLeft > width ||
            clippingBox.yTopLeft > height || clippingBox.yBottomRight > height) {
            throw new ImageProcessingException("clippingBox is out of bounds");
        }
        int clipWidth = clippingBox.xBottomRight - clippingBox.xTopLeft + 1;
        int clipHeight = clippingBox.yBottomRight - clippingBox.yTopLeft + 1;
        Image clipImage = new Image(clipWidth, clipHeight);
        for (int row = 0; row < clipHeight; row++) {
            for (int col = 0; col < clipWidth; col++) {
                clipImage.setRGB(col, row,
                    image.getRGB(col + clippingBox.xTopLeft, row + clippingBox.yTopLeft));
            }
        }
        return clipImage;
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
                HashMap<String, int[]> colorVals = scanNeighbors(col, row);
                float medRed = getMedian(colorVals.get("red"));
                float medGreen = getMedian(colorVals.get("green"));
                float medBlue = getMedian(colorVals.get("blue"));

                Color medColor = new Color(medRed / 255, medGreen / 255, medBlue / 255);

                denoisedImg.set(col, row, medColor);
            }
        }

        return denoisedImg;
    }

    /**
     * Helper function that scans neighbouring pixels for color.
     * Creates three int arrays, which store the red, green,
     * and blue values for each neighboring pixel.
     * scanNeighbors will return a HashMap containing all three arrays mapped to a String
     * of their color (e.g. "red").
     * <p>
     * Relies on countNeighbors function to operate properly.
     *
     * @param col   is the column (x-axis) position of the pixel to be analyzed.
     *              Must be greater than 0 and less than width.
     * @param row   is the row (y-axis) position of the pixel to be analyzed.
     *              Must be greater than 0 and less than height.
     *
     * @return HashMap of int arrays. Keys are strings that represent color, and the value is
     * the int array that contains the red, green, or blue values of the specified
     * pixel's neighbors.
     */

    private HashMap<String, int[]> scanNeighbors(int col, int row) {
        int neighbors = countNeighbors(col, row);
        HashMap<String, int[]> colorVals = new HashMap<String, int[]>();
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
        colorVals.put("red", redVals);
        colorVals.put("green", greenVals);
        colorVals.put("blue", blueVals);
        return colorVals;
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
            return median;

        } else {
            double med = (set[set.length / 2]);
            float median = (float) med;
            return median;
        }

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
                HashMap<String, int[]> colorVals = scanNeighbors(col, row);
                int[] redVals = colorVals.get("red");
                Arrays.sort(redVals);
                int minRed = redVals[0];

                int[] greenVals = colorVals.get("green");
                Arrays.sort(greenVals);
                int minGreen = greenVals[0];

                int[] blueVals = colorVals.get("blue");
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
        for (int col = 0; col <= width - blockSize; col += blockSize) {
            for (int row = 0; row <= height - blockSize; row += blockSize) {
                Color averageColor = getColorBlock(col, row, blockSize, blockSize);
                for (int blockCol = col; blockCol < col + blockSize; blockCol++) {
                    for (int blockRow = row; blockRow < row + blockSize; blockRow++) {
                        blockImg.set(blockCol, blockRow, averageColor);
                    }
                }
            }
        }
        int remainderCols = width % blockSize;
        int remainderRows = height % blockSize;
        int remainderCol = width - remainderCols;
        int remainderRow = height - remainderRows;
        if (remainderCols != 0) {
            for (int row = 0; row <= height - blockSize; row += blockSize) {
                Color averageColor = getColorBlock(remainderCol, row, remainderCols, blockSize);
                for (int blockCol = remainderCol; blockCol < width; blockCol++) {
                    for (int blockRow = row; blockRow < height; blockRow++) {
                        blockImg.set(blockCol, blockRow, averageColor);
                    }
                }
            }
        }
        if (remainderRows != 0) {
            for (int col = 0; col <= width - blockSize; col += blockSize) {
                Color averageColor = getColorBlock(col, remainderRow, blockSize, remainderRows);
                for (int blockRow = remainderRow; blockRow < height; blockRow++) {
                    for (int blockCol = col; blockCol < col + blockSize; blockCol++) {
                        blockImg.set(blockCol, blockRow, averageColor);
                    }
                }
            }
        }
        if (remainderCols == 0) {
            remainderCol--;
        }
        if (remainderRows == 0) {
            remainderRow--;
        }
        if (remainderRows != 0 && remainderCols != 0) {
            Color averageColor =
                getColorBlock(remainderCol, remainderRow, remainderCols, remainderRows);
            for (int lastCol = remainderCol; lastCol < width; lastCol++) {
                for (int lastRow = remainderRow; lastRow < height; lastRow++) {
                    blockImg.set(lastCol, lastRow, averageColor);
                }
            }
        }

        return blockImg;
    }

    /**
     * Helper method for blockPaint. Method will take the starting row and column
     * of a block, as well as the block size, and will check all pixels in that box.
     * Method will take in the RGB values of all pixels, and then average them (each
     * color separately). This method will then return a Color object with the average
     * RGB values.
     *
     * @param col     this is the column number where the specified block is starting.
     *                Requirement: 0 <= col <= width - colSize.
     * @param row     this is the row number where the specified block is starting.
     *                Requirement: 0 <= row <= height - rowSize.
     * @param colSize this is the width of the block. Usually the box will be a square, but
     *                in the edges, the block will have odd dimensions.
     *                Requirement: 0 < colSize < width
     * @param rowSize this is the height of the block.
     *                Requirement: 0 < rowSize < height
     * @return a Color object that has the average values of Red in the block, average
     * values of Blue in the block, and average values of Green in the block.
     */
    private Color getColorBlock(int col, int row, int colSize, int rowSize) {
        int[] redVals = new int[colSize * rowSize];
        int[] greenVals = new int[colSize * rowSize];
        int[] blueVals = new int[colSize * rowSize];
        int counter = 0;
        for (int blockCol = col; blockCol < col + colSize; blockCol++) {
            for (int blockRow = row; blockRow < row + rowSize; blockRow++) {
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
     * @return average is a number of float datatype that is equal to the sum
     * of the elements of the array, divided by the number of elements in the array.
     */
    private int findAverageArray(int[] array) {
        int sum = 0;
        int numElements = array.length;
        for (int i = 0; i < numElements; i++) {
            sum += array[i];
        }
        return sum / numElements;
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


        int new_width = (int) Math.round((width * Math.cos(degrees * Math.PI / 180) +
            height * Math.sin(degrees * Math.PI / 180)));
        int new_height = (int) Math.ceil((width * Math.sin(degrees * Math.PI / 180) +
            height * Math.cos(degrees * Math.PI / 180)));
        Image outImage = new Image(new_width, new_height);


        for (int col = 0; col < new_width; col++) {
            for (int row = 0; row < new_height; row++) {
                int original_x = (int) ((col - new_width / 2) * Math.cos(degrees * Math.PI / 180) +
                    (row - new_height / 2) * Math.sin(degrees * Math.PI / 180) +
                    original_width / 2);
                int original_y = (int) (-(col - new_width / 2) * Math.sin(degrees * Math.PI / 180) +
                    (row - new_height / 2) * Math.cos(degrees * Math.PI / 180) +
                    original_height / 2);
                if (original_x >= 0 && original_y >= 0 &&
                    original_x < original_width &&
                    original_y < original_height) {
                    outImage.set(col, row, original_image.get(original_x, original_y));
                } else {
                    outImage.set(col, row, new Color(255, 255, 255));
                }
            }
        }
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
        Image gsImg = new Image(this.grayscale());
        int width = gsImg.width();
        int height = gsImg.height();

        double realSum;
        double imagSum;
        double angle;

        double ampSum;

        Color color;

        double[][] amplitude = new double[height][width];
        double[][] phase = new double[height][width];

        //computing amp and phase for each v,u
        for (int v = 0; v < height; v++) {
            for (int u = 0; u < width; u++) {
                ampSum = 0;
                realSum = 0;
                imagSum = 0;

                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        angle = -2.0 * Math.PI * ((double) u * x / (double) width + (double) v * y / height);
                        realSum += Math.cos(angle);
                        imagSum += Math.sin(angle);

                        color = new Color(gsImg.getRGB(x, y));
                        ampSum += color.getRed();
                    }
                }
                amplitude[v][u] = ampSum;
                phase[v][u] = Math.atan(imagSum / realSum);
            }
        }
        return new DFTOutput(amplitude, phase);
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
        Image greenScreenImg = new Image(image);
        int[] rectangle = boundingRect(screenColour);
        int minCol = rectangle[1];
        int minRow = rectangle[2];
        int maxCol = rectangle[3];
        int maxRow = rectangle[4];

        int backgroundCol = -1;
        int backgroundRow = -1;
        for (int col = minCol; col <= maxCol; col++) {
            backgroundCol++;
            if (backgroundCol >= backgroundImage.width()) {
                backgroundCol = 0;
            }
            for (int row = minRow; row <= maxRow; row++) {
                backgroundRow++;
                if (backgroundRow >= backgroundImage.height()) {
                    backgroundRow = 0;
                }
                if (screenColour.equals(image.get(col, row))) {
                    Color currColor = backgroundImage.get(backgroundCol, backgroundRow);
                    greenScreenImg.set(col, row, currColor);
                }
            }
            backgroundRow = -1;
        }

        return greenScreenImg;

    }

    /**
     * Helper method that takes in a color and returns an int array describing
     * the bounding rectangle of the largest connected region of pixels of the specified color.
     * Required helper methods:
     * pixelScannerRedux, setFalse, rectCoords
     *
     * @param color is the color that boundingRect tries to the find the largest
     *              connected region of. color cannot be null.
     * @return int array that describes the number of "correctly colored"
     * (their color = color variable) pixels, and coordinates of the
     * top left and bottom right pixels of the bounding rectangle.
     * Values of the array are as follows:
     * array[0]: number of "correctly colored pixels" in bounding rectangle.
     * array[1]: column of the top left point of the bounding rectangle.
     * array[2]: row of the top left point of the bounding rectangle.
     * array[3]: column of the bottom right point of the bounding rectangle.
     * array[4]: row of the bottom right point of the bounding rectangle.
     */
    private int[] boundingRect(Color color) {
        Boolean[][] record = new Boolean[width][height];
        Boolean[][] region = new Boolean[width][height];
        setFalse(region);
        setFalse(record);
        int maxSize = 0;
        int[] maxCoords = new int[5];
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                if (image.get(col, row).equals(color) && !record[col][row]) {
                    region = new Boolean[width][height];
                    setFalse(region);
                    pixelScannerRedux(col, row, color, region, record);
                    int[] coords = rectCoords(region);
                    int size = coords[0];
                    if (size > maxSize) {
                        maxSize = size;
                        maxCoords = coords;
                    }
                }
            }
        }
        return maxCoords;
    }


    /**
     * Helper method that takes the column and row of a pixel of a specific color
     * and checks all of its neighbors that have not been examined yet.
     * If a valid neighboring pixel is of the same color as specified,
     * then it will add it to a queue.
     * The method will then add the pixel to the record of all pixels examined
     * as well as the record of the pixels in the current region being examined.
     * After that, the next pixel in the queue will be removed from the queue and examined.
     * This will continue until the queue is empty.
     * Effectively creating a matrix of the connecting region of specific color pixels
     * connected to the original pixel.
     *
     * @param startCol the column number of the first pixel being examined.
     *                 0 < startCol < width
     * @param startRow the row number of the first pixel being examined.
     *                 0 < startRow < height
     * @param color    the color to be matched.
     * @param pixels   2D boolean array representing all pixels as true or false. True if
     *                 pixel is same color as "color", false otherwise.
     *                 pixels' width and height should be equal to image's width and height.
     *                 pixels should be empty.
     * @param record   2D boolean array representing all pixels as true or false. True if
     *                 pixel has been examined before, false otherwise.
     *                 record's width and height should be equal to image's width and height.
     */
    private void pixelScannerRedux(int startCol, int startRow, Color color, Boolean[][] pixels,
                                   Boolean[][] record) {
        LinkedList<int[]> queue = new LinkedList<>();
        int[] startPoint = {startCol, startRow};
        queue.add(startPoint);
        while (!queue.isEmpty()) {
            int[] nextPoint = queue.remove();
            int col = nextPoint[0];
            int row = nextPoint[1];
            for (int i = -1; i < 2; i++) {
                if (col + i >= 0 && col + i < width) {
                    for (int k = -1; k < 2; k++) {
                        if (row + k >= 0 && row + k < height) {
                            if (!record[col + i][row + k]) {
                                Color currColor = image.get(col + i, row + k);
                                if (currColor.equals(color)) {
                                    int[] foundPoint = {col + i, row + k};
                                    queue.add(foundPoint);
                                    record[col + i][row + k] = true;
                                    pixels[col + i][row + k] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Helper method that takes a 2D array of booleans and will
     * create a bounding rectangle around the region of true values.
     * Will then return an array that contains the size of the region
     * and coordinates of top left and bottom right points of the
     * bounding rectangle.
     *
     * @param region 2D boolean array that has > 0 rows and > 0 columns.
     *               Will not be mutated.
     * @return array of ints of size 5 that contains the following values in order:
     * array[0]: number of true values in region.
     * array[1]: column of the top left point of the bounding rectangle.
     * array[2]: row of the top left point of the bounding rectangle.
     * array[3]: column of the bottom right point of the bounding rectangle.
     * array[4]: row of the bottom right point of the bounding rectangle.
     */
    private int[] rectCoords(Boolean[][] region) {
        int height = region[0].length;
        int width = region.length;
        int minCol = width;
        int minRow = height;
        int maxCol = 0;
        int maxRow = 0;
        int count = 0;
        int[] results = new int[5];
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                if (region[k][i] == true) {
                    count++;
                    if (i < minRow) {
                        minRow = i;
                    }
                    if (i > maxRow) {
                        maxRow = i;
                    }
                    if (k < minCol) {
                        minCol = k;
                    }
                    if (k > maxCol) {
                        maxCol = k;
                    }
                }
            }
        }
        results[0] = count;
        results[1] = minCol;
        results[2] = minRow;
        results[3] = maxCol;
        results[4] = maxRow;

        return results;
    }

    /**
     * Helper method to take in a 2D array of booleans
     * and set everything to false.
     *
     * @param matrix 2D boolean array that has > 0 rows and > 0 columns.
     *               Will be mutated.
     *               All values will be changed to false.
     */
    private void setFalse(Boolean[][] matrix) {
        int width = matrix[0].length;
        int height = matrix.length;
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                matrix[i][k] = false;
            }
        }
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
