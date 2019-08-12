package com.menard.mynews;


import com.menard.mynews.model.Category;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class CategoryTest {

    @Test
    public void checkMOVIESNotNull(){
        assertThat(Category.valueOf("MOVIES"), is(notNullValue()));
    }

    @Test
    public void getSCIENCECategoryName(){
        assertEquals("SCIENCE", Category.SCIENCE.getName());
    }
}
