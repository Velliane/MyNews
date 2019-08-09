package com.menard.mynews.modelTest;

import com.menard.mynews.model.search.Doc;
import com.menard.mynews.model.search.Response;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ResponseTest {

    @Test
    public void getDocs(){
        Response response = new Response();
        List<Doc> docList = new ArrayList<>();

        response.setDocs(docList);

        assertEquals(docList, response.getDocs());
    }
}
