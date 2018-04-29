package snake;

import java.util.ArrayList;
import java.util.List;

/*
** Name : snake.GeneticAlgorithim
** Author : Kyle
** Date : 4/27/18
** Description : 
*/
public class GeneticAlgorithim {
    public static void main(String[] args){

        //hyper parameters
        double mean = 0;
        double standardDeviation = 1;
        int[] arraySize = {400,50,50,4};
        double mutationChance = 0.01;
        int populationSize = 1000;
        double survivalRate = 0.50;
        double maximumFitness = 100;

        //other data
        List<Network> population = new ArrayList<Network>();
        List<Network> lastPopulation = new ArrayList<Network>();
        Network bestNetwork = new Network(arraySize,0,1);

        //Creates initial networks
        Network temp;
        for (int i = 0; i < populationSize; i++){
            temp = new Network(arraySize,mean,standardDeviation);
            population.add(temp);
        }

        do {
            //get fitnesses for every network
            for (int i = 0, n = population.size(); i < n; i++){
                // TODO
            }

            //sort networks based on fitness
            for (int i = 0, n = population.size(); i < n; i++){
                // TODO
            }

            //stores highest fitness and best network
            //assumes the population list has its best network at 0
            if (population.get(0).getFitness() > bestNetwork.getFitness())
                bestNetwork = population.get(0);

            //stores the last population

            //take the top survival rate * population size
        } while (bestNetwork.getFitness() < maximumFitness);
    }
}

