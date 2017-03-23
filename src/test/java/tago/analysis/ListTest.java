package tago.analysis;

import domain.AnalysisResult;
import domain.FindDataCountResult;
import model.analysis.Analysis;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListTest {
    
    public ListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void test() {
        System.out.println("Analysis list");
        Analysis an = new Analysis();
        AnalysisResult ar = an.list();

        assertEquals(ar.status, true);
    }
}
