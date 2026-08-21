import java.io.*; 
import java.util.ArrayList;
 
public class GradeAnalyzer {
 
    public static void main(String[] args) {
        // Step 1: read scores from file
        ArrayList<Integer> scores = readScores("scores.txt");

        if (scores.isEmpty()) {
            System.out.println("No valid scores found in the file.");
            return;
        }

        // Step 2: calculate statistics
        double avg = calculateAverage(scores);
        int highScore = Integer.MIN_VALUE;
        int lowScore = Integer.MAX_VALUE;
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;

        for (int score : scores) {
            if (score > highScore) {
                highScore = score;
            }

            if (score < lowScore) {
                lowScore = score;
            }

            if (score >= 90) {
                countA++;
            } else if (score >= 80) {
                countB++;
            } else if (score >= 70) {
                countC++;
            } else if (score >= 60) {
                countD++;
            } else {
                countF++;
            }
        }

        // Step 3: write and print report
        writeReport(scores, avg, highScore, lowScore, countA, countB, countC, countD, countF, "report.txt");
    } 
 
    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
        ArrayList<Integer> scores = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String trimmedLine = line.trim();

                if (trimmedLine.isEmpty()) {
                    continue;
                }
                
                try {
                    int number = Integer.parseInt(trimmedLine);
                    scores.add(number);
                } catch(NumberFormatException e) {
                    System.err.println("Not a number: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Could not read file: " + e.getMessage());
        }

        return scores;
    }
 
    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (int score : scores) {
            sum += score;
        }

        return sum / scores.size();
    } 
 
    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   int countA, int countB, int countC, int countD, int countF,
                                   String outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("=== Grade Analysis Report ===");
            writer.newLine();
            writer.write(String.format("Average score:\t%.2f%n", avg));
            writer.write(String.format("Highest score:\t%d%n", high));   
            writer.write(String.format("Lowest score:\t%d%n", low));   
            writer.newLine();
            
            writer.write("Grade distribution:");
            writer.newLine();  
            writer.write(String.format("A (90-100):\t%d%n", countA));
            writer.write(String.format("B (80-89):\t%d%n", countB));
            writer.write(String.format("C (70-79):\t%d%n", countC));
            writer.write(String.format("D (60-69):\t%d%n", countD));
            writer.write(String.format("F (0-59):\t%d%n", countF));

        } catch (IOException e) {
            System.out.println("Could not write file: " + e.getMessage());
        }
    }
} 