package tago.analysis;

import domain.AnalysisCreateResult;
import domain.AnalysisResult;
import domain.FindDataCountResult;
import domain.StringResult;
import java.util.ArrayList;
import model.analysis.Analysis;
import model.analysis.KeyVal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeleteTest {
    
    public DeleteTest() {
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
        System.out.println("Analysis delete");
        Analysis an = new Analysis();
        Analysis analysis = new Analysis();
        analysis.name = "JAVA SDK test Analysis";
        analysis.description = "Created by JAVA SDK test Analysis";
        analysis.interval = "1 day";
        analysis.active = true;
        
        analysis.tags = new ArrayList<>();
        KeyVal kv = new KeyVal();
        kv.key = "var1";
        kv.value = "var1Value";
        analysis.tags.add(kv);
        kv = new KeyVal();
        kv.key = "var2";
        kv.value = "var2Value";
        analysis.tags.add(kv);
        analysis.variables = new ArrayList<>();
        kv = new KeyVal();
        kv.key = "env1";
        kv.value = "env1Value";
        analysis.variables.add(kv);
        kv = new KeyVal();
        kv.key = "env2";
        kv.value = "env2Value";
        analysis.variables.add(kv);
        
        AnalysisCreateResult acr = an.create(analysis);
        StringResult dr = an.delete(acr.result.id);

        assertEquals(dr.status, true);
    }
}
