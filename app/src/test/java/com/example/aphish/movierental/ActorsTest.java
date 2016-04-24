package com.example.aphish.movierental;

import com.example.aphish.movierental.domain.Actors;
import com.example.aphish.movierental.factories.ActorsFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Aphish on 2016/04/22.
 */
public class ActorsTest {
    private ActorsFactory factory;
    @Before
    public void setUp() throws Exception {
        factory = ActorsFactory.getInstance();
    }

    @Test
    public void TestActorCreation() throws Exception {

        Actors actor = factory.createActors("Aphiwe","Blom","23","4.23");
        Assert.assertEquals("Aphiwe",actor.getName());
    }

    @Test
    public void TestActorUpdate() throws Exception {

        Actors actor = factory.createActors("Aphiwe","Blom","23","4.23");
        Actors newActor = new Actors.Builder()
                .name("Aphiwe")
                .surname("Blom")
                .age("24")
                .height("4.26")
                .copy(actor)
                .build();

        Assert.assertEquals("23",actor.getAge());
    }
}
