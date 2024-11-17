package io.github.katarem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Tests {
    
    private List<String> names;

    @Before
    public void setUp(){
        names = new ArrayList<>();
        names.add("foo");
        names.add("bar");
        names.add("foobar");
    }
    
    @After
    public void tearUp(){
        names.clear();
    }

    @Test
    public void list_should_iterate_3_times(){
        AtomicInteger in = new AtomicInteger(0);
        ListUtils.forEachIndexed(names, (index, name) -> in.incrementAndGet());
        assertEquals(3, in.get());
    }

    @Test
    public void list_should_have_any_items(){
        var contains = ListUtils.any(names, name -> name.contains("oo"));
        assertTrue(contains);
    }

    @Test
    public void list_shouldnt_have_any_items(){
        var contains = ListUtils.any(names, name -> name.contains("var"));
        assertFalse(contains);
    }

    @Test
    public void list_should_have_all_items(){
        var containsAll = ListUtils.all(names, name -> name.length() % 2 == 0);
        assertTrue(containsAll);
    }

    @Test
    public void list_shouldnt_have_all_items(){
        var containsAll = ListUtils.all(names, name -> name.length() == 3);
        assertFalse(containsAll);
    }

}
