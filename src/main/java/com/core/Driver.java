package com.core;

public class Driver {

    public static void main(String[] args) {

        int[][][] data = Perceptron.andData;
        double[] weights = Perceptron.INITIAL_WEIGHTS;

        int epochNumber = 0;
        boolean errorFlag = true;

        double error = 0;
        double[] adjustedWeights = null;

        Driver driver = new Driver();
        Perceptron perceptron = new Perceptron();


        while (errorFlag){

            driver.printHeading(epochNumber++);
            errorFlag = false;
            error = 0;

            for (int x = 0; x < data.length; x++){

                double weightedSum = perceptron.calculateWeightedSum(data[x][0], weights);
                int result = perceptron.applyActivationFunction(weightedSum);

                error = data[x][1][0] - result;

                if (error != 0)
                    errorFlag = true;

                adjustedWeights = perceptron.adjustWeights(data[x][0], weights, error);
                driver.printVector(data[x], weights, result, error, weightedSum, adjustedWeights);

                weights = adjustedWeights;
            }
        }


    }

    public void printHeading(int epochNumber){
        System.out.println("\n===========================Epoch # " +epochNumber+"===============================================================================");
        System.out.println("    W1  |   W2  |  X1  |  X2  | Target Result |  Result | error | Weighted Sum | adjusted w1 | adjusted w2");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }

    public void printVector(int[][] data, double[] weights, int result, double error, double weightedSum, double[] adjustedWeights) {
        System.out.println("  "+String.format("%.2f", weights[0])+"  |  "+String.format("%.2f", weights[1])+"  |  "+data[0][0]+"  |  "+data[0][1] +
                            "  |      "+data[1][0]+"        |   "+result+"      | "+error+"   |      "+String.format("%.2f", weightedSum) +
                            "    |      "+ String.format("%.2f",adjustedWeights[0])+"      | "+String.format("%.2f",adjustedWeights[1]));
    }
}
