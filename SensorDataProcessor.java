import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class SensorDataProcessor {

    public double[][][] data;
    public double[][] limit;

    // Constructor
    public SensorDataProcessor(double[][][] data, double[][] limit) { 
        // Fixed by Razan Ibrahim, ID: 2111213 - Refactor name class mismatch to match class name
        this.data = data;
        this.limit = limit;
    }

    // Calculates the average of an array
    private double average(double[] array) {
        double sum = 0;
        for (double value : array) {
            sum += value;
        }
        return sum / array.length; 
        // Fixed by Fai Alshareaf, ID: 2110919 - Refactor inefficient average calculation to enhance readability and efficiency
    }

    // Calculate data
    public void calculate(double d) {
        int i, j;
        int k = 0; 
        // Fixed by Razan Ibrahim, ID: 2111213 - Initialize variable `k` separately to avoid confusion and ensure clarity

        double[][][] data2 = new double[data.length][data[0].length][data[0][0].length];

        try {
            // Refactor: Using try-with-resources to handle BufferedWriter
            // Fixed by Aleen Alsulaiman, ID: 2111910 - Refactor improper use of BufferedWriter by applying try-with-resources for efficient resource handling
            try (BufferedWriter out = new BufferedWriter(new FileWriter("RacingStatsData.txt"))) {
                for (i = 0; i < data.length; i++) {
                    for (j = 0; j < data[0].length; j++) {
                        for (k = 0; k < data[0][0].length; k++) {
                            // Fixed by Fai Alshareaf, ID: 2110919 - Add bounds check to handle potential ArrayIndexOutOfBoundsException
                            if (i >= data.length || j >= data[0].length || k >= data[0][0].length) {
                                throw new ArrayIndexOutOfBoundsException("Index out of bounds: [" + i + "][" + j + "][" + k + "]");
                            }

                            data2[i][j][k] = data[i][j][k] / d - Math.pow(limit[i][j], 2.0);

                            if (average(data2[i][j]) > 10 && average(data2[i][j]) < 50)
                                break;
                            else if (Math.max(data[i][j][k], data2[i][j][k]) > data[i][j][k])
                                break;
                            else if (Math.pow(Math.abs(data[i][j][k]), 3) < Math.pow(Math.abs(data2[i][j][k]), 3)
                                    && average(data[i][j]) < data2[i][j][k] && (i + 1) * (j + 1) > 0)
                                data2[i][j][k] *= 2;
                            else
                                continue;
                        }
                    }
                }

                // Write processed data to file
                for (double[][] sensorData : data2) {
                    for (double[] row : sensorData) {
                        out.write(Arrays.toString(row) + "\t");
                    }
                    out.newLine(); 
                    // Fixed by Yara Sulaiman, ID: 2110553 - Refactor writing arrays to file for better formatting and readability
                }
            } // BufferedWriter auto-closed here
            

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("File writing failed: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Error: Processed data is null. Please check your input.");
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
        }
        // Fixed by Yara Sulaiman, ID: 2110553 - Refactor error handling to handle exceptions more effectively
    }
}   
 