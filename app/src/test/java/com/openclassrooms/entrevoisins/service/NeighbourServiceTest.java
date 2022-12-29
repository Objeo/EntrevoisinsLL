package com.openclassrooms.entrevoisins.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours(false);
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void getFavoritesWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours(true);
        int Count = neighbours.size();
        assertEquals(Count, 1);
    }

    @Test
    public void setNeighbourAsFavorite() {
        Neighbour n = service.getNeighbours(false).get(0);
        n.toggleFavoriteStatus();
        Neighbour q = service.getNeighbours(true).get(0);
        assertTrue(q.isFavorite());
    }

    @Test
    public void createOneNeighbour() {
        Neighbour n = new Neighbour(99, "name", "avatar", "adress", "phone", "about");
        assertNotNull(n);
        assertEquals(99, n.getId());
        assertEquals("name", n.getName());
        assertEquals("avatar", n.getAvatarUrl());
        assertEquals("adress", n.getAddress());
        assertEquals("phone", n.getPhoneNumber());
        assertEquals("about", n.getAboutMe());

    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours(false).get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours(false).contains(neighbourToDelete));
    }

    @Test
    public void getNeighbourByidWithSuccess() {
        Neighbour neighbourToGetById = service.getNeighbourById(1);
        assertEquals(1, neighbourToGetById.getId());
    }
}
