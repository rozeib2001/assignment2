public class SensorDataProcessor { 
 
    // Senson data and limits. 
    public double[][][] data; 
    public double[][] limit; 
 
    // constructor 
    public DataProcessor(double[][][] data, double[][] limit) { 
        this.data = data; 
        this.limit = limit; 
    } 
 
    // calculates average of sensor data 
    private double average(double[] array) { 
        int i = 0; 
        double val = 0; 
        for (i = 0; i < array.length; i++) { 
            val += array[i]; 
        } 
 
        return val / array.length; 
    } 
 
    // calculate data 
    public void calculate(double d) { 
 
        int i, j, k = 0; 
        double[][][] data2 = new 
double[data.length][data[0].length][data[0][0].length]; 
 
        BufferedWriter out; 
University of Jeddah 
College of Computer Science and Engineering 
Department of Software Engineering 
 
 
        // Write racing stats data into a file 
        private void writeToFile(double[][][] processedData, String fileName) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (double[][] sensorData : processedData) {
                    for (double[] row : sensorData) {
                        writer.write(Arrays.toString(row) + "\t");
                    }
                    writer.newLine(); // Add line breaks for better formatting
                }
            } catch (IOException e) {
                System.err.println("File writing failed: " + e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("Processed data is null. Please check your input data.");
            }
        }
        