package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */

    @Override
    public List<Neighbour> getNeighbours(Boolean OnlyFavorite) {
        if (!OnlyFavorite) {
            return neighbours;
        } else {
            List<Neighbour> favorite_neighbours = new ArrayList<Neighbour>();
            for (Neighbour neighbour : neighbours) {
                if (neighbour.isFavorite()) {
                    favorite_neighbours.add(neighbour);
                }
            }
            return favorite_neighbours;
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public Neighbour getNeighbourById(long id) {
        for (Neighbour n : neighbours) {
            if (n.getId() == id) {
                return n;
            }
        }
        return null;
    }

}
