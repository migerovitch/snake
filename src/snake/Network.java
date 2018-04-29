package snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
** Name : snake.Network
** Author : Kyle
** Date : 4/15/18
** Description : A simple SGD-less network used for a genetic algorithim.  Can mutate and crossover with other networks.
*/
public class Network {

    //actual network
    private List<Matrix> biases = new ArrayList<Matrix>(); //there are no biases for the input layer
    private List<Matrix> weights = new ArrayList<Matrix>(); //there are no weights for the input layer
    private List<Matrix> weightedSums = new ArrayList<Matrix>(); //there are no weighted sums for the input layer
    private List<Matrix> activations = new ArrayList<Matrix>();

    //bunch of parameters
    private double mean;
    private double stDev;
    private int[] layers;
    private int numOfLayers;
    private double fitness;


    public Network(int[] layers, double mean, double stDev){
        //instance data
        this.mean = mean;
        this.stDev = stDev;
        this.layers = layers;
        this.fitness = 0;
        numOfLayers = layers.length;

        //activation values
        for (int i : layers){
            activations.add(new Matrix(i,1));
        }

        //biases, weighted sums and weights
        for (int i = 1; i < layers.length; i++){

            biases.add(new Matrix(layers[i],1,mean,stDev));

            weightedSums.add(new Matrix(layers[i],1));

            weights.add(new Matrix(layers[i],layers[i-1],mean,stDev));

        }
    }

    //feeds the matrix of inputs forward and returns the output layer
    public Matrix feedforward(Matrix inputs){
        //safety stuff
        if (!inputs.isSameSize(activations.get(0)))
            throw new RuntimeException("Inputs not the same size as input layer");
        //sets inputs
        activations.set(0,inputs);
        //feeds forward
        for (int i = 1, n = numOfLayers; i < n; i++){
            weightedSums.set(i,Matrix.add(Matrix.multiply(weights.get(i-1),activations.get(i-1)),biases.get(i-1)));
            activations.set(i,Matrix.sigmoid(weightedSums.get(i)));
        }
        //returns output layer
        return activations.get(numOfLayers - 1);
    }

    //Crosses over with another network
    public static Network crossover(Network a, Network b){
        //Checks dimensions
        if (a.getLayers() != b.getLayers())
            throw new RuntimeException("Matrices not the same size");

        //Creates child
        Network child = new Network(a.getLayers(), 0, 0);
        double temp;
        Random random = new Random();
        boolean bool;

        //weights
        for (int i = 0; i < child.getWeights().size(); i++){
            for (int j = 0; j < child.getWeights().get(i).getRows(); j++){
                for (int k = 0; k < child.getWeights().get(i).getCols(); k++){
                    //gets gene
                    bool = random.nextBoolean();
                    if (bool)
                        temp = a.getWeights().get(i).get(j,k);
                    else
                        temp = b.getWeights().get(i).get(j,k);
                    //sets
                    child.getWeights().get(i).set(temp,j,k);
                }
            }
        }

        //biases
        for (int i = 0; i < child.getBiases().size(); i++){
            for (int j = 0; j < child.getBiases().get(i).getRows(); j++){
                for (int k = 0; k < child.getBiases().get(i).getCols(); k++){
                    //gets gene
                    bool = random.nextBoolean();
                    if (bool)
                        temp = a.getBiases().get(i).get(j,k);
                    else
                        temp = b.getBiases().get(i).get(j,k);
                    //sets
                    child.getBiases().get(i).set(temp,j,k);
                }
            }
        }

        return child;
    }

    public static Network mutate(Network n, double mutationChance, double mean, double stDev){
        Random random = new Random();
        double temp;
        for (int i = 0; i < n.getWeights().size(); i++){
            for (int j = 0; j < n.getWeights().get(i).getRows(); j++){
                for (int k = 0; k < n.getWeights().get(i).getCols(); k++){
                    temp = random.nextDouble();
                    if (temp <= mutationChance)
                        n.getWeights().get(i).randomize(j,k,mean,stDev);
                }
            }
        }

        //biases
        for (int i = 0; i < n.getBiases().size(); i++){
            for (int j = 0; j < n.getBiases().get(i).getRows(); j++){
                for (int k = 0; k < n.getBiases().get(i).getCols(); k++){
                    temp = random.nextDouble();
                    if (temp <= mutationChance)
                        n.getBiases().get(i).randomize(j,k,mean,stDev);
                }
            }
        }
        return n;
    }

    public List<Matrix> getBiases() {
        return biases;
    }

    public List<Matrix> getWeights() {
        return weights;
    }

    public List<Matrix> getWeightedSums() {
        return weightedSums;
    }

    public int getNumOfLayers() {
        return numOfLayers;
    }

    public int[] getLayers(){
        return layers;
    }

    public void setFitness(double d){
        fitness = d;
    }

    public double getFitness(){
        return fitness;
    }

}
